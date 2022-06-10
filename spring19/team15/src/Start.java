import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


//시작화면 창
public class Start extends JFrame implements ActionListener {
	
    int f_width =800 ;
    int f_height =800;
    Toolkit tk = Toolkit.getDefaultToolkit();
    
    Start()
    {
         setTitle("슈팅 게임 시작화면");
         setSize(f_width, f_height);

         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         Dimension screen = tk.getScreenSize();
         int f_xpos = (int)(screen.getWidth() / 2 - f_width / 2);
         int f_ypos = (int)(screen.getHeight() / 2 - f_height / 2);
         
 
         JPanel btn = new JPanel();
         
         ImageIcon startIcon = new ImageIcon(".\\img\\start.png");
         Image startImage = startIcon.getImage(); // ImageIcon을 Image로 변환.
         Image changeStartImage = startImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
         startIcon = new ImageIcon(changeStartImage);
         JButton StartButton = new JButton("Start");
         StartButton.setIcon(startIcon);
         StartButton.addActionListener(this);
         btn.add(StartButton);
         
         ImageIcon scoreIcon = new ImageIcon(".\\img\\score.png");
         Image scoreImage = scoreIcon.getImage(); // ImageIcon을 Image로 변환.
         Image changeScoreImage = scoreImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
         scoreIcon = new ImageIcon(changeScoreImage);
         JButton ScoreButton = new JButton("Score");
         ScoreButton.setIcon(scoreIcon);
         ScoreButton.addActionListener(this);
         btn.add(ScoreButton);
         
         add(btn,BorderLayout.WEST);
         
         ImageIcon DukeIcon = new ImageIcon((new ImageIcon(".\\img\\titleShip.jpg")).getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH));
         JLabel DukeLabel = new JLabel(DukeIcon);
         add(DukeLabel,BorderLayout.CENTER);
         
         setLocation(f_xpos, f_ypos);
         setResizable(false);
         setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		if(btnStr.equals("Start")) {
			game_Frame fms = new game_Frame();
			dispose();
		}
		else if(btnStr.equals("Score")) {
			checkscore_Frame check = new checkscore_Frame(this);
		}
	}
}