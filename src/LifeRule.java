/**
 * 
 */

import javax.media.j3d.RenderingAttributes;

/**
 * @author Kaito
 *
 */
public class LifeRule
{
	private int rows, columns, steps;
	private boolean[][][] isAlive;
	
	private RenderingAttributes[][][] ra;
	
	/**
	 * 
	 */
	public LifeRule(RenderingAttributes[][][] ra)
	{	
		this.ra = ra;
		
		rows = ra.length;
		columns = ra[0].length;
		steps = ra[0][0].length;
		
		isAlive = new boolean[rows][columns][steps];
	}
	
	public void nextGen()
	{
		int rows2, columns2, steps2;
		int lives;
		
		checkInside();

	}
	
	private void checkInside()
	{
		int neighbor = 0;
		
		for (int x = 1; x < rows - 1; x++)
		{
			for (int y = 1; y < columns - 1; y++)
			{
				for (int z = 1; z < steps - 1; z++)
				{
					if(ra[x + 1][y + 1][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x + 1][y + 1][z].getVisible() == true)
						neighbor++;
					if(ra[x + 1][y + 1][z - 1].getVisible() == true)
						neighbor++;
					if(ra[x + 1][y][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x + 1][y][z].getVisible() == true)
						neighbor++;
					if(ra[x + 1][y][z - 1].getVisible() == true)
						neighbor++;
					if(ra[x + 1][y - 1][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x + 1][y - 1][z].getVisible() == true)
						neighbor++;
					if(ra[x + 1][y - 1][z - 1].getVisible() == true)
						neighbor++;
					
					if(ra[x][y + 1][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x][y + 1][z].getVisible() == true)
						neighbor++;
					if(ra[x][y + 1][z - 1].getVisible() == true)
						neighbor++;
					if(ra[x][y][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x][y][z - 1].getVisible() == true)
						neighbor++;
					if(ra[x][y - 1][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x][y - 1][z].getVisible() == true)
						neighbor++;
					if(ra[x][y - 1][z - 1].getVisible() == true)
						neighbor++;				
					
					if(ra[x - 1][y + 1][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x - 1][y + 1][z].getVisible() == true)
						neighbor++;
					if(ra[x - 1][y + 1][z - 1].getVisible() == true)
						neighbor++;
					if(ra[x - 1][y][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x - 1][y][z].getVisible() == true)
						neighbor++;
					if(ra[x - 1][y][z - 1].getVisible() == true)
						neighbor++;
					if(ra[x - 1][y - 1][z + 1].getVisible() == true)
						neighbor++;
					if(ra[x - 1][y - 1][z].getVisible() == true)
						neighbor++;
					if(ra[x - 1][y - 1][z - 1].getVisible() == true)
						neighbor++;
					
					if (neighbor == 4)
						isAlive[x][y][z] = true;
					else
						isAlive[x][y][z] = false;
				}
			}
		}
	}
}
