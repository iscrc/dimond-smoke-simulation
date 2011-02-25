import java.awt.*;
import javax.swing.*;

import com.sun.j3d.utils.applet.MainFrame;

public class LifeGame3D extends JApplet
{
	private GamePanel game;
	private ControlPanel control;

	public void init()
	{
		game = new GamePanel(); 

		control = new ControlPanel(game);

		Container c = getContentPane();
		c.add(control, BorderLayout.EAST);
		c.add(game, BorderLayout.CENTER);
	}

	public static void main(String[] args)
	{
		new MainFrame(new LifeGame3D(), 534, 420);
	}
}
