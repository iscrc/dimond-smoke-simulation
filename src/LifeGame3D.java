import java.awt.*;
import javax.swing.*;

import com.sun.j3d.utils.applet.MainFrame;

public class LifeGame3D extends JApplet
{
	private GamePanel game;
	private ControlPanel control;

	// Initiating
	public void init()
	{
		game = new GamePanel();

		control = new ControlPanel(game);

		// add panels to the applet windows
		Container c = getContentPane();
		c.add(control, BorderLayout.EAST);
		c.add(game, BorderLayout.CENTER);
	}

	public static void main(String[] args)
	{
		// convert applet into application
		new MainFrame(new LifeGame3D(), 534, 420);
	}
}
