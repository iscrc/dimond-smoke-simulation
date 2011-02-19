import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel
  implements ActionListener
{
  private GamePanel life;

  private JTextField txtRows, txtColumns, txtSteps, txtLives;
  private JButton btnNewGen, btnNext;
  private JRadioButton radAutoOn, radAutoOff;
  private JSlider scrSpeed;
  
  
  public ControlPanel(GamePanel game)
  {
    life = game;

    setLayout(new BorderLayout(5, 5));

    //Input Panel
    JPanel genPanel = new JPanel();
    genPanel.setLayout(new GridLayout(9, 1, 3, 3));

    JLabel lblRows = new JLabel("   Rows: ");
    genPanel.add(lblRows);
    txtRows = new JTextField(10);
    txtRows.addActionListener(this);
    genPanel.add(txtRows);

    JLabel lblColumns = new JLabel("   Columns: ");
    genPanel.add(lblColumns);
    txtColumns = new JTextField(10);
    txtColumns.addActionListener(this);
    genPanel.add(txtColumns);

    JLabel lblSteps = new JLabel("   Steps: ");
    genPanel.add(lblSteps);
    txtSteps = new JTextField(10);
    txtSteps.addActionListener(this);
    genPanel.add(txtSteps);

    JLabel lblLives = new JLabel("   Lives: ");
    genPanel.add(lblLives);
    txtLives = new JTextField(10);
    txtLives.addActionListener(this);
    genPanel.add(txtLives);
    
    btnNewGen = new JButton("New Gen");
    btnNewGen.addActionListener(this);
    genPanel.add(btnNewGen);
    
    add(genPanel, BorderLayout.NORTH);

    //Auto Animate Panel
    JPanel autoPanel = new JPanel();
    autoPanel.setLayout(new GridLayout(4, 1, 3, 3));
    autoPanel.setBorder(BorderFactory.createTitledBorder("Auto Animate"));

    radAutoOn = new JRadioButton("Auto ON");
    radAutoOff = new JRadioButton("Auto OFF");

    ButtonGroup gr = new ButtonGroup();
    gr.add(radAutoOn);
    gr.add(radAutoOff);
    autoPanel.add(radAutoOn);
    autoPanel.add(radAutoOff);

    JLabel lblSpeed = new JLabel("  Speed: ");
    autoPanel.add(lblSpeed);

    scrSpeed = new JSlider(JScrollBar.HORIZONTAL, 100, 250, 175);
    scrSpeed.setPreferredSize(new Dimension(scrSpeed.getHeight(), 500));
    autoPanel.add(scrSpeed);
    
    add(autoPanel,BorderLayout.CENTER);

    //Next Button
    btnNext = new JButton("Next");
    add(btnNext, BorderLayout.SOUTH);    
  }

  public void actionPerformed(ActionEvent e)
  {
    int intRows, intColumns, intSteps, intLives;

    // read all the inputs
    if (e.getSource() == btnNewGen)
    {
      try
      {
        intRows = Integer.parseInt(txtRows.getText());
        if (intRows <= 0)
          intRows = 1;

        intColumns = Integer.parseInt(txtColumns.getText());
        if (intColumns <= 0)
          intRows = 1;

        intSteps = Integer.parseInt(txtSteps.getText());
        if (intSteps <= 0)
          intSteps = 1;

        intLives = Integer.parseInt(txtLives.getText());
        if (intLives > intRows * intColumns * intSteps)
          intLives = intRows * intColumns * intSteps;
        else if (intLives < 0)
          intLives = 0;

        life.createLifeCube(intRows, intColumns, intSteps, intLives);
      }
      catch (NumberFormatException ex)	// catch wrong input
      {
        JOptionPane.showMessageDialog(
		this, "Invalid Number!", "Input Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
