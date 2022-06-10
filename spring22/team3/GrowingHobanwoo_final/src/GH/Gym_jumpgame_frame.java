package GH;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import util.Resource;

public class Gym_jumpgame_frame extends JFrame {

    private static final Dimension SIZE;

    static {
        int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - 100;
        if (screenHeight > 800) {
            screenHeight = 800;
        }
        SIZE = new Dimension(500, screenHeight);
    }
    private Gym_jumpgame_gamepanel gamePanel;

    public Gym_jumpgame_frame() {
        super();
        this.setIconImage(Resource.getImage("banwoo.png"));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setSizeWithoutInsets(SIZE);
        this.setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Impossibile impostare il look and feel: " + ex);
        }

        this.setTitle(0);
        this.gamePanel = new Gym_jumpgame_gamepanel(SIZE);
        this.gamePanel.setNewGameListener(new NewGameListener() {

            @Override
            public void updateScore(int score) {
                setTitle(score);
            }

            @Override
            public void gameOver() {
                setTitle(0);
            }
        });
        this.add(this.gamePanel);
        this.gamePanel.requestFocus();
    }

    public void start() {
        JOptionPane.showOptionDialog(null, "Ready?",
                "Start", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"GO!"}, 0);
        this.gamePanel.start();
    }

    public static void main(String[] args) {
        Gym_jumpgame_frame f = new Gym_jumpgame_frame();
        f.setVisible(true);
        f.start();
    }

    private void setTitle(int points) {
        this.setTitle("Jumping Hobanwoo - " + points + " points");
    }

    private void setSizeWithoutInsets(Dimension d) {
        Insets i = this.getInsets();
        this.setSize(d.width + i.left + i.right, d.height + i.top + i.bottom);
    }
}

class SwapPanelListener implements ActionListener {

    private JPanel pFrom;
    private JPanel pTo;

    public SwapPanelListener(JPanel from, JPanel to) {
        this.pFrom = from;
        this.pTo = to;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.pTo.setVisible(true);
        this.pFrom.setVisible(false);
    }
}
