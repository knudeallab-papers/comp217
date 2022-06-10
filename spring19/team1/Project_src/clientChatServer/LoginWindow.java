package clientChatServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame{
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPort;
    private JLabel lblIpAddress;
    private JLabel lblPort;
    private JLabel lblAddressDesc;
    private JLabel lblPortDesc;

    public LoginWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 380);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtName = new JTextField();
        txtName.setBounds(67, 50, 165, 28);
        contentPane.add(txtName);
        txtName.setColumns(10);

        JLabel lblName = new JLabel("Username:");
        lblName.setBounds(127, 34, 55, 16);
        contentPane.add(lblName);

        txtAddress = new JTextField();
        txtAddress.setBounds(67, 116, 165, 28);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);

        lblIpAddress = new JLabel("IP Address:");
        lblIpAddress.setBounds(111, 96, 77, 16);
        contentPane.add(lblIpAddress);

        txtPort = new JTextField();
        txtPort.setColumns(10);
        txtPort.setBounds(67, 191, 165, 28);
        contentPane.add(txtPort);

        lblPort = new JLabel("Port:");
        lblPort.setBounds(133, 171, 34, 16);
        contentPane.add(lblPort);

        lblAddressDesc = new JLabel("(Just type 'localhost')");
        lblAddressDesc.setBounds(94, 142, 112, 16);
        contentPane.add(lblAddressDesc);

        lblPortDesc = new JLabel("(it's '5555')");
        lblPortDesc.setBounds(116, 218, 68, 16);
        contentPane.add(lblPortDesc);

        //Good practice (at least for readability) to include the action listener just
        //Below the actual button it belongs to!
        JButton btnLogin = new JButton("Login");
        //Lambda expression, instead of creating another class with one method
        btnLogin.addActionListener((ActionEvent e) -> {
            String name = txtName.getText();
            String address = txtAddress.getText();
            int port = Integer.parseInt(txtPort.getText());
            login(name, address, port);
        });
        btnLogin.setBounds(91, 311, 117, 29);
        contentPane.add(btnLogin);
    }

    private void login(String name, String address, int port) {
        dispose(); //Get rid of our login window and instead make a clientWindow(where chatting takes place)
        new ClientWindow(name, address, port);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginWindow frame = new LoginWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
