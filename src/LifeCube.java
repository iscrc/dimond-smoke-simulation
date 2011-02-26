import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.*;
import javax.vecmath.*;

import java.text.DecimalFormat;

public class LifeCube
{
	private BranchGroup lifeCube;
	private Box[][][] box;
	private RenderingAttributes[][][] ra;

	private int rows, columns, steps;

	public LifeCube(int rows, int columns, int steps, int lives)
	{
		this.rows = rows;
		this.columns = columns;
		this.steps = steps;

		createCube(lives);
	}

	private void createCube(int lives)
	{
		float halfLength = 0.6f / findMax(rows, columns, steps);
		//float halfLength = 0.6f / Math.max(rows, columns);
		float dimension = 2 * halfLength;
		float adjustX = (rows + 1) / 2.0f * dimension;
		float adjustY = (columns + 1) / 2.0f * dimension;
		float adjustZ = (steps + 1) / 2.0f * dimension;
		DecimalFormat formatter = new DecimalFormat("000");

		lifeCube = new BranchGroup();
		lifeCube.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		box = new Box[rows][columns][steps];
		ra = new RenderingAttributes[rows][columns][steps];

		for (int x = 0; x < rows; x++)
		{
			float xPos = (x + 1) * dimension - adjustX;
			for (int y = 0; y < columns; y++)
			{
				float yPos = (y + 1) * dimension - adjustY;
				for (int z = 0; z < steps; z++)
				{
					ColoringAttributes ca = new ColoringAttributes();
					ca.setColor(0.0f, 1.0f, 0.0f);

					ra[x][y][z] = new RenderingAttributes();
					ra[x][y][z].setVisible(false);
					ra[x][y][z].setCapability(RenderingAttributes.ALLOW_VISIBLE_READ);
					ra[x][y][z].setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);

					Appearance app = new Appearance();
					app.setColoringAttributes(ca);
					app.setRenderingAttributes(ra[x][y][z]);

					SingleCell cell = new SingleCell(halfLength);
					box[x][y][z] = new Box(
							halfLength, halfLength, halfLength, app);
					box[x][y][z].setName(formatter.format(x) 
							+ formatter.format(y) + formatter.format(z));
					box[x][y][z].setCapability(Node.ENABLE_PICK_REPORTING);
					box[x][y][z].setCapability(Primitive.ENABLE_GEOMETRY_PICKING);
          
					Transform3D translate = new Transform3D();
					translate.setTranslation(
							new Vector3f(xPos, yPos, (z + 1) * dimension - adjustZ));
					TransformGroup tg = new TransformGroup(translate);
					//tg.setName(formatter.format(x) 
					//		+ formatter.format(y) + formatter.format(z));
					//tg.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
					//tg.setCapability(Primitive.ENABLE_GEOMETRY_PICKING);

					tg.addChild(box[x][y][z]);
					tg.addChild(cell);
					lifeCube.addChild(tg);
				}
			}
		}

		fillLifes(lives);
	}

	private void fillLifes(int lives)
	{
		if (lives > (rows * columns * steps * 3 / 4))
		{
			clearCube(true);
			randomLifes(rows * columns * steps - lives, false);
		}
		else
		{
			clearCube(false);
			randomLifes(lives, true);
		}
	}

	public BranchGroup getBG()
	{   return lifeCube;   }

	private float findMax(int rows, int columns, int steps)
	{
		int temp = Math.max(rows, columns);
		return (float)(Math.max(temp, steps));
	}

	private void clearCube(boolean doWhat)
	{
		for (int x = 1; x <= rows; x++)
		{
			for (int y = 1; y <= columns; y++)
			{
				for (int z = 1; z <= steps; z++)
				{
					ra[x - 1][y - 1][z - 1].setVisible(doWhat);
				}
			}
		}
	}

	private void randomLifes(int lives, boolean doWhat)
	{
		int count = 1;
		while (count <= lives)
		{
			int x = (int)(rows * Math.random());
			int y = (int)(columns * Math.random());
			int z = (int)(steps * Math.random());

			if (ra[x][y][z].getVisible() != doWhat)
			{
				ra[x][y][z].setVisible(doWhat);
				count++;
			}      
		}
	}
}