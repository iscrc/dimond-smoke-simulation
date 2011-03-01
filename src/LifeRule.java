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
	
	private RenderingAttributes ra[][][];
	
	/**
	 * 
	 */
	public LifeRule(RenderingAttributes[][][] ra)
	{	
		this.ra = ra;
		
		rows = ra.length;
		columns = ra[0].length;
		steps = ra[0][0].length;
	}
	
	public void nextGen()
	{
		
	}
}
