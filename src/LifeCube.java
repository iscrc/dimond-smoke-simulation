import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;

public class LifeCube
{
  private BranchGroup lifeCube;
  private SingleCell[][][] cell;
  private PolygonAttributes[][][] pa;

  private static final int CLEAR_CUBE = 0, FILL_CUBE = 1;

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
    float halfLength = 0.6f / Math.max(rows, columns);
    float dimension = 2 * halfLength;

    // cube position adjust
    float adjustX = (rows + 1) / 2.0f * dimension;
    float adjustY = (columns + 1) / 2.0f * dimension;
    float adjustZ = (steps + 1) / 2.0f * dimension;

    lifeCube = new BranchGroup();
    cell = new SingleCell[rows][columns][steps];
    pa = new PolygonAttributes[rows][columns][steps];

    for (int x = 1; x <= rows; x++)
    {
      float xPos = x * dimension - adjustX;
      for (int y = 1; y <= columns; y++)
      {
        float yPos = y * dimension - adjustY;
        for (int z = 1; z <= steps; z++)
        {
          // give the cube color green
          ColoringAttributes ca = new ColoringAttributes();
          ca.setColor(0.0f, 1.0f, 0.0f);

          // turn the cube into wireframe mode
          pa[x - 1][y - 1][z - 1] = new PolygonAttributes();
          pa[x - 1][y - 1][z - 1].setPolygonMode(PolygonAttributes.POLYGON_LINE);
          pa[x - 1][y - 1][z - 1].setCullFace(PolygonAttributes.CULL_NONE);

          // appearance of each cube
          Appearance app = new Appearance();
          app.setCapability(Appearance.ALLOW_POLYGON_ATTRIBUTES_READ);
          app.setCapability(Appearance.ALLOW_POLYGON_ATTRIBUTES_WRITE);
          app.setColoringAttributes(ca);
          app.setPolygonAttributes(pa[x - 1][y - 1][z - 1]);

          // creation of each cell
          cell[x - 1][y - 1][z - 1] = new SingleCell(halfLength, app);

          // move the cube to the right position
          Transform3D translate = new Transform3D();
          translate.setTranslation(
		new Vector3f(xPos, yPos, z * dimension - adjustZ));
          TransformGroup tg = new TransformGroup(translate);

          tg.addChild(cell[x - 1][y - 1][z - 1]);
          lifeCube.addChild(tg);
        }
      }
    }

    fillLifes(lives);
  }

  // add life to the cube by fill the cube (not done yet)
  private void fillLifes(int lives)
  {
    if (lives > (rows * columns * steps * 3 / 4))
    {
      clearCube(FILL_CUBE);

      
    }
    else
    {
      
    }
  }

  // access to the cube
  public BranchGroup getBG()
  { return lifeCube; }

  private float findMax(int rows, int columns, int steps)
  {
    int temp = Math.max(rows, columns);
    return (float)(Math.max(temp, steps));
  }

  // empty or full fill the cube (not done yet)
  private void clearCube(int doWhat)
  {
    for (int x = 1; x <= rows; x++)
    {
      for (int y = 1; y <= columns; y++)
      {
        for (int z = 1; z <= steps; z++)
        {
          pa[x - 1][y - 1][z - 1].setPolygonMode(PolygonAttributes.POLYGON_FILL);
        }
      }
    }
  }
}