package view;

import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton btnLogin;

    public LoginView(){
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel usernameJLabel = new JLabel("username");
        JLabel passwordJLabel = new JLabel("password");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        btnLogin = new JButton();

        btnLogin.setText("Login");
        btnLogin.addActionListener(this);
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        // tạo đối tượng panel để chứa các thành phần của màn hình login
        panel.setSize(400, 300);
        panel.setLayout(layout);
        panel.add(usernameJLabel);
        panel.add(passwordJLabel);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(btnLogin);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, usernameJLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameJLabel, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordJLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordJLabel, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, usernameField, 80, SpringLayout.WEST, usernameJLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameField, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordField, 80, SpringLayout.WEST, passwordJLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnLogin, 80, SpringLayout.WEST, passwordJLabel);
        layout.putConstraint(SpringLayout.NORTH, btnLogin, 130, SpringLayout.NORTH, panel);

        // add panel tới JFrame
        this.add(panel);
        this.pack();
        // cài đặt các thuộc tính cho JFrame
        this.setTitle("Login");
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(this,message);
    }

    public User getUser(){
        return new User(usernameField.getText(),String.copyValueOf(passwordField.getPassword()));
    }

    public void addLoginListener(ActionListener listener){
        btnLogin.addActionListener(listener);
    }

}
