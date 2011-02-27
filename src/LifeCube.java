import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.*;
import javax.vecmath.*;

import java.text.DecimalFormat;

public class LifeCube
{
	private BranchGroup lifeCube;
	private RenderingAttributes[][][] ra;
	private LineAttributes[][][] la;

	private int rows, columns, steps;

	// Constructor
	public LifeCube(int rows, int columns, int steps, int lives)
	{
		this.rows = rows;
		this.columns = columns;
		this.steps = steps;

		createCube(lives);
	}

	// Create Cube
	private void createCube(int lives)
	{
		// cube dimension
		float halfLength = 0.6f / findMax(rows, columns, steps);
		float dimension = 2 * halfLength;
		
		// position adjust factor
		float adjustX = (rows + 1) / 2.0f * dimension;
		float adjustY = (columns + 1) / 2.0f * dimension;
		float adjustZ = (steps + 1) / 2.0f * dimension;
		
		DecimalFormat formatter = new DecimalFormat("000");

		lifeCube = new BranchGroup();
		la = new LineAttributes[rows][columns][steps];
		ra = new RenderingAttributes[rows][columns][steps];

		// create cube
		for (int x = 0; x < rows; x++)
		{
			float xPos = (x + 1) * dimension - adjustX;	// position in x-axis adjust
			for (int y = 0; y < columns; y++)
			{
				float yPos = (y + 1) * dimension - adjustY;	// position in y-axis adjust
				for (int z = 0; z < steps; z++)
				{
					// wire frame of cell
					la[x][y][z] = new LineAttributes();
					la[x][y][z].setCapability(LineAttributes.ALLOW_WIDTH_READ);
					la[x][y][z].setCapability(LineAttributes.ALLOW_WIDTH_WRITE);
					
					SingleCell cell = new SingleCell(halfLength, la[x][y][z]);
					
					// cell
					ColoringAttributes ca = new ColoringAttributes();
					ca.setColor(0.0f, 1.0f, 0.0f);	// set the color of cell to green

					ra[x][y][z] = new RenderingAttributes();
					ra[x][y][z].setVisible(false);
					ra[x][y][z].setCapability(RenderingAttributes.ALLOW_VISIBLE_READ);
					ra[x][y][z].setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);

					Appearance app = new Appearance();
					app.setColoringAttributes(ca);
					app.setRenderingAttributes(ra[x][y][z]);

					Box box = new Box(halfLength, halfLength, halfLength, app);
					box.setName(formatter.format(x) + formatter.format(y) + formatter.format(z));
					box.setCapability(Box.ENABLE_PICK_REPORTING);
					box.setCapability(Box.ENABLE_GEOMETRY_PICKING);
          
					// translate the cell to it's right position
					Transform3D translate = new Transform3D();
					translate.setTranslation(
							new Vector3f(xPos, yPos, (z + 1) * dimension - adjustZ));
					TransformGroup tg = new TransformGroup(translate);

					// add cell to cube
					tg.addChild(box);
					tg.addChild(cell);
					lifeCube.addChild(tg);
				}
			}
		}

		fillLives(lives);	// adding lives
	}

	// Fill in Lives to the Cube
	private void fillLives(int lives)
	{
		// if the value of lives is greater than 3 / 4 of the total cell,
		// the program will fill all the cell then unfill the cell with corresponding number.
		// else it will fill the cell with corresponding number.
		if (lives > (rows * columns * steps * 3 / 4))
		{
			clearCube(true);	// fill the entire cube
			randomLifes(rows * columns * steps - lives, false);
		}
		else
		{
			clearCube(false);	// unfill the enitre cube
			randomLifes(lives, true);
		}
	}

	public BranchGroup getBG()
	{   return lifeCube;   }
	
	// Fill/Unfill the cell
	public void cellLife(int x, int y, int z)
	{
		if (ra[x][y][z].getVisible() == false)
			ra[x][y][z].setVisible(true);
		else
			ra[x][y][z].setVisible(false);
	}
	
	// Set the Width of the WireFrame
	public void cellFrame(int x, int y, int z, float width)
	{	la[x][y][z].setLineWidth(width);	}

	// Find the Largest Number among Rows, Columns and Steps
	private float findMax(int rows, int columns, int steps)
	{
		int temp = Math.max(rows, columns);
		return (float)(Math.max(temp, steps));
	}

	// Fill/Unfill the whole cube
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

	// Randomly Fill/Unfill the Cell
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