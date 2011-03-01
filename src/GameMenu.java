/**
 * 
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author Kaito
 *
 */
public class GameMenu extends JMenuBar
	implements ActionListener
{
	private JMenuItem load;
	
	private GamePanel myGame;
	
	/**
	 * 
	 */
	public GameMenu(GamePanel game)
	{
		myGame = game;
		
		load = new JMenuItem("Load");
		load.addActionListener(this);
		
		add(load);		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == load)
		{
			myGame.createLifeCube(10, 10, 10, 0);
			myGame.LoadPreset();
		}
	}
}
