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

    // place the component in the applet
    Container c = getContentPane();
    c.add(control, BorderLayout.EAST);
    c.add(game, BorderLayout.CENTER);
  }

  //  The following allows this to be run as an application
  //  as well as an applet
 
  public static void main(String[] args)
  {
    Frame frame = new MainFrame(new LifeGame3D(), 534, 420);
  }
}
