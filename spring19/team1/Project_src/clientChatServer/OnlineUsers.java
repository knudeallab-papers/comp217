package clientChatServer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OnlineUsers extends JFrame {
    private JPanel contentPane;
    private JList list;

    public OnlineUsers() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(200, 320);
        setTitle("Online Users");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        //Just to format the Jpanel to be a bit wider and longer with the user names
        gbl_contentPane.columnWidths = new int[] { 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);

        list = new JList();
        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.fill = GridBagConstraints.BOTH;
        JScrollPane p = new JScrollPane();
        p.setViewportView(list);
        contentPane.add(p, gbc_list);
        list.setFont(new Font("Verdana", Font.PLAIN, 24));
    }

    public void update(String[] users) {
        list.setListData(users);
    }

}
