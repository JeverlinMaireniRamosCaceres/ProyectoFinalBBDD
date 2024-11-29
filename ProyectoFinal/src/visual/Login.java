package visual;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPanel panel;

    public Login() {
        setTitle("Login");
        setBounds(100, 100, 400, 243);
        getContentPane().setLayout(new BorderLayout());
        
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(4, 2));

        JLabel lblUsername = new JLabel("User:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPanel.add(lblUsername);

        usernameField = new JTextField();
        contentPanel.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Contrase\u00F1a:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPanel.add(lblPassword);

        passwordField = new JPasswordField();
        contentPanel.add(passwordField);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        buttonPane.add(btnLogin);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancel);
    }

    
    public static void main(String[] args) {
        try {
            Login dialog = new Login();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
