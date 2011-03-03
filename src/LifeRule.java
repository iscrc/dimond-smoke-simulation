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
		int lives;
		
		checkNeighbor();

		for (int x = 0; x < rows; x++)
		{
			for (int y = 0; y < columns; y++)
			{
				for (int z = 0; z < steps; z++)
				{
					ra[x][y][z].setVisible(isAlive[x][y][z]);
				}
			}
		}
	}
	
	private void checkNeighbor()
	{
		int neighbor = 0;
		int frontX, backX, frontY, backY, frontZ, backZ;
		
		for (int x = 0; x < rows; x++)
		{
			frontX = setFront(x, rows - 1);
			backX = setBack(x, rows - 1 );

			for (int y = 0; y < columns; y++)
			{
				frontY = setFront(y, columns - 1);
				backY = setBack(y, columns - 1);
				
				for (int z = 0; z < steps; z++)
				{
					frontZ = setFront(z, steps - 1);
					backZ = setBack(z, steps - 1);
					
					if(ra[frontX][frontY][frontZ].getVisible() == true)
						neighbor++;
					if(ra[frontX][frontY][z].getVisible() == true)
						neighbor++;
					if(ra[frontX][frontY][backZ].getVisible() == true)
						neighbor++;
					if(ra[frontX][y][frontZ].getVisible() == true)
						neighbor++;
					if(ra[frontX][y][z].getVisible() == true)
						neighbor++;
					if(ra[frontX][y][backZ].getVisible() == true)
						neighbor++;
					if(ra[frontX][backY][frontZ].getVisible() == true)
						neighbor++;
					if(ra[frontX][backY][z].getVisible() == true)
						neighbor++;
					if(ra[frontX][backY][backZ].getVisible() == true)
						neighbor++;
					
					if(ra[x][frontY][frontZ].getVisible() == true)
						neighbor++;
					if(ra[x][frontY][z].getVisible() == true)
						neighbor++;
					if(ra[x][frontY][backZ].getVisible() == true)
						neighbor++;
					if(ra[x][y][frontZ].getVisible() == true)
						neighbor++;
					if(ra[x][y][backZ].getVisible() == true)
						neighbor++;
					if(ra[x][backY][frontZ].getVisible() == true)
						neighbor++;
					if(ra[x][backY][z].getVisible() == true)
						neighbor++;
					if(ra[x][backY][backZ].getVisible() == true)
						neighbor++;				
					
					if(ra[backX][frontY][frontZ].getVisible() == true)
						neighbor++;
					if(ra[backX][frontY][z].getVisible() == true)
						neighbor++;
					if(ra[backX][frontY][backZ].getVisible() == true)
						neighbor++;
					if(ra[backX][y][frontZ].getVisible() == true)
						neighbor++;
					if(ra[backX][y][z].getVisible() == true)
						neighbor++;
					if(ra[backX][y][backZ].getVisible() == true)
						neighbor++;
					if(ra[backX][backY][frontZ].getVisible() == true)
						neighbor++;
					if(ra[backX][backY][z].getVisible() == true)
						neighbor++;
					if(ra[backX][backY][backZ].getVisible() == true)
						neighbor++;
					
					if (neighbor == 4)
						isAlive[x][y][z] = true;
					else
						isAlive[x][y][z] = false;
					
					neighbor = 0;
				}
			}
		}
	}
	
	private int setFront(int value, int max)
	{
		if (value != 0)
			return value - 1;
		return max;
	}
	
	private int setBack(int value, int max)
	{
		if (value != max)
			return value + 1;	
		return 0;
	}
}
