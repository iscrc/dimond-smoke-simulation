/**
 * 
 */

/**
 * @author Kebang Huang
 *
 */
public class PreLoad
{
	public boolean[][][] preset = new boolean[10][10][10];
	
	/**
	 * 
	 */
	public PreLoad()
	{		
		for (int x = 0; x < 10; x += 10)
			for (int y = 0; y < 10; y += 2)
				for (int z = 0; z < 10; z += 2)
					preset[x][y][z] = true;
	}
	
	public boolean[][][] getLives()
	{	return preset;	}
}
