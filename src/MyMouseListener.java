import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.picking.*;

/**
 * 
 */

/**
 * @author Kaito
 *
 */
public class MyMouseListener
	implements MouseListener, MouseMotionListener
{
	private PickCanvas picking;
	private LifeCube lifeCube;
	private int x, y, z;
	private boolean selected = false;
	
	/**
	 * 
	 */
	public MyMouseListener(PickCanvas picking)
	{	this.picking = picking;		}

	public void newLifeCube(LifeCube lifeCube)
	{	this.lifeCube = lifeCube;	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		// active when left mouse is used
		if (e.getModifiers() == InputEvent.BUTTON1_MASK)
		{
			picking.setShapeLocation(e);
			PickResult result = picking.pickAny();
		
			if (result != null)
			{
				Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

				if (p != null)
				{
					String s = p.getName();
				
					int x2 = Integer.parseInt(s.substring(0, 3));
					int y2 = Integer.parseInt(s.substring(3, 6));
					int z2 = Integer.parseInt(s.substring(6));
				
					if (x != x2 || y != y2 || z != z2)
					{
						lifeCube.cellFrame(x, y, z, 1.0f);	// restore the wire frame width
						lifeCube.cellFrame(x2, y2, z2, 3.0f);	// set the currently selected cube width to 3.0f
					
						x = x2;
						y = y2;
						z = z2;
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		// active when left mouse is used
		if (e.getModifiers() == InputEvent.BUTTON1_MASK)
		{
			picking.setShapeLocation(e);
			PickResult result = picking.pickAny();
		
			if (result != null)
			{
				Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

				if (p != null)
				{
					String s = p.getName();
				
					x = Integer.parseInt(s.substring(0, 3));
					y = Integer.parseInt(s.substring(3, 6));
					z = Integer.parseInt(s.substring(6));
				
					lifeCube.cellFrame(x, y, z, 3.0f);	// set the currently selected cube width to 3.0f
				
					selected = true;	// cell is selected
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// active when left mouse is used
		if (e.getModifiers() == InputEvent.BUTTON1_MASK)
		{
			if (selected = true)
			{
				lifeCube.cellLife(x, y, z);	// fill/unfill the selected cell
				lifeCube.cellFrame(x, y, z, 1.0f);	// restore the width of the wire frame
			
				selected = false;
			}
		}
	}

}
