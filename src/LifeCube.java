import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.RenderingAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;

public class LifeCube implements Runnable
{
  private BranchGroup lifeCube;
  private Box[][][] box;
  private RenderingAttributes[][][] ra;
  public Boolean KeepRunning;

  private static final int CLEAR_CUBE = 0, FILL_CUBE = 1;

  private int rows, columns, steps, lives;

  public LifeCube(int rows, int columns, int steps, int lives)
  {
    this.rows = rows;
    this.columns = columns;
    this.steps = steps;
    this.lives = lives;

    createCube(lives);
  }

  public void run()
  {
	  KeepRunning=true;
	  while (KeepRunning)
	  {
		  fillLifes(lives);
		  try {	Thread.sleep(100);} 
		  catch (InterruptedException e) {  }
	  }
  }
  
  private void createCube(int lives)
  {
    //float halfLength = 0.6f / findMax(rows, columns, steps);
    float halfLength = 0.6f / Math.max(rows, columns);
    float dimension = 2 * halfLength;
    float adjustX = (rows + 1) / 2.0f * dimension;
    float adjustY = (columns + 1) / 2.0f * dimension;
    float adjustZ = (steps + 1) / 2.0f * dimension;

    lifeCube = new BranchGroup();
    box = new Box[rows][columns][steps];
    ra = new RenderingAttributes[rows][columns][steps];

    for (int x = 1; x <= rows; x++)
    {
      float xPos = x * dimension - adjustX;
      for (int y = 1; y <= columns; y++)
      {
        float yPos = y * dimension - adjustY;
        for (int z = 1; z <= steps; z++)
        {
          ColoringAttributes ca = new ColoringAttributes();
          ca.setColor(0.0f, 1.0f, (float)Math.random());

          ra[x - 1][y - 1][z - 1] = new RenderingAttributes();
          ra[x - 1][y - 1][z - 1].setVisible(false);
          ra[x - 1][y - 1][z - 1].setCapability(RenderingAttributes.ALLOW_VISIBLE_READ);
          ra[x - 1][y - 1][z - 1].setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);

          Appearance app = new Appearance();
          app.setColoringAttributes(ca);
          app.setRenderingAttributes(ra[x - 1][y - 1][z - 1]);

          SingleCell cell = new SingleCell(halfLength);
          box[x - 1][y - 1][z - 1] = new Box(
		halfLength, halfLength, halfLength, app);
          
          Transform3D translate = new Transform3D();
          translate.setTranslation(
		new Vector3f(xPos, yPos, z * dimension - adjustZ));
          TransformGroup tg = new TransformGroup(translate);

          tg.addChild(box[x - 1][y - 1][z - 1]);
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
  { return lifeCube; }

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