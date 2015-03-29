import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;

import javax.swing.JApplet;

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
    Frame frame = new MainFrame(new LifeGame3D(), 534, 420);
  }
}
