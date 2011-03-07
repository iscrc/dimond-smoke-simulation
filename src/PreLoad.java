/**
 * 
 */

/**
 * @author Kebang Huang
 *
 */
public class PreLoad
{
	public boolean[][][] preset = new boolean[3][3][3];
	
	/**
	 * 
	 */
	public PreLoad()
	{		
//		for (int x = 0; x < 10; x += 10)
//			for (int y = 0; y < 10; y += 2)
//				for (int z = 0; z < 10; z += 2)
//					preset[x][y][z] = true;
		preset[0][0][2] = true;
		preset[1][0][2] = true;
		preset[2][0][2] = true;
		preset[1][1][2] = true;
		
	}
	
	public boolean[][][] getLives()
	{	return preset;	}
}
