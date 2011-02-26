import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.behaviors.picking.PickObject;

import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
	private BranchGroup scene, cube;
	private MyMouseListener mouse;
	
	public GamePanel()
	{
		setLayout(new BorderLayout());

		GraphicsConfiguration config = 
			SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);

		createSceneGraph(canvas3D);

		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		simpleU.getViewingPlatform().setNominalViewingTransform();
		simpleU.addBranchGraph(scene);
	}

	public void createLifeCube(int rows, int columns, int steps, int lives)
	{
		cube.detach();
    	cube = mouseControl(rows, columns, steps, lives);
    	scene.addChild(cube);
	}

	public void createSceneGraph(Canvas3D canvas)
	{
		scene = new BranchGroup();
		scene.setCapability(Group.ALLOW_CHILDREN_WRITE);
		scene.setCapability(Group.ALLOW_CHILDREN_EXTEND);

		cube = new BranchGroup();
		cube.setCapability(BranchGroup.ALLOW_DETACH);
		scene.addChild(cube);
		
		PickObject picking = new PickObject(canvas, scene);
		mouse = new MyMouseListener(picking);
		canvas.addMouseListener(mouse);

		Background backg = new Background(1.0f, 1.0f, 1.0f);
		backg.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0));
		scene.addChild(backg);

		scene.compile();
	}

	public BranchGroup mouseControl(int rows, int columns, int steps, int lives)
	{
		cube = new BranchGroup();
		cube.setCapability(BranchGroup.ALLOW_DETACH);

		TransformGroup objTransform = new TransformGroup();
		objTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		LifeCube lifeCube = new LifeCube(rows, columns, steps, lives);
		mouse.newLifeCube(lifeCube);
		objTransform.addChild(lifeCube.getBG());
		cube.addChild(objTransform);

		RightMouseRotate myMouseRotate = new RightMouseRotate();
		myMouseRotate.setTransformGroup(objTransform);
		myMouseRotate.setSchedulingBounds(new BoundingSphere());
		cube.addChild(myMouseRotate);

		MouseZoom myMouseZoom = new MouseZoom();
		myMouseZoom.setTransformGroup(objTransform);
		myMouseZoom.setSchedulingBounds(new BoundingSphere());
		cube.addChild(myMouseZoom);

		cube.compile();
    	return cube;
	}
}