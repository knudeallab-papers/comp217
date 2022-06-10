package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class codePage extends JFrame implements ActionListener
{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 150;

    public codePage()
    {
        super("TeamProject");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}
