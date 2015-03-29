import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.swing.JPanel;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class GamePanel extends JPanel
{
  private BranchGroup scene, cube;
  //Thread animation;
  LifeCube lc ;

  public GamePanel()
  {
    setLayout(new BorderLayout());

    GraphicsConfiguration config = 
	SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3D = new Canvas3D(config);
    add("Center", canvas3D);

    createSceneGraph();

    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
    simpleU.getViewingPlatform().setNominalViewingTransform();
    simpleU.addBranchGraph(scene);
    
    //animation = null;
  }

  public void createLifeCube(int rows, int columns, int steps, int lives)
  {
    cube.detach();
    cube = mouseControl(rows, columns, steps, lives);
    scene.addChild(cube);
  }

  public void createSceneGraph()
  {
    scene = new BranchGroup();
    scene.setCapability(Group.ALLOW_CHILDREN_WRITE);
    scene.setCapability(Group.ALLOW_CHILDREN_EXTEND);

    cube = new BranchGroup();
    cube.setCapability(BranchGroup.ALLOW_DETACH);
    scene.addChild(cube);

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

    if (lc!=null) lc.KeepRunning=false;
    lc = new LifeCube(rows, columns, steps, lives);
    //if (animation!=null) animation.stop();
    //animation = new Thread(lc);
    //animation.start();
    new Thread(lc).start();
    
    objTransform.addChild(lc.getBG());
    cube.addChild(objTransform);

    MouseRotate myMouseRotate = new MouseRotate();
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