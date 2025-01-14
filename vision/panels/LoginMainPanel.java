package vision.panels;

import Interfaces.PanelInterface;

import javax.swing.*;
import java.awt.*;

public class LoginMainPanel extends JPanel implements PanelInterface {
    JLabel loginLabel;
    JTextField loginField;
    JLabel passwordLabel;
    JTextField passwordField;
    JButton button;


    public JTextField getLoginField() {
        return loginField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JButton getButton() {
        return button;
    }

    public LoginMainPanel(){
        createComponents();
        addToPanelComponents();
    }

    @Override
    public void createComponents() {
        loginLabel = new JLabel(" LOGIN:");
        loginField = new JTextField();
        passwordLabel = new JLabel(" PASSWORD");
        passwordField = new JTextField();
        button = new JButton("OK");
    }

    @Override
    public void addToPanelComponents() {
        setLayout(new GridLayout(3,2));
        add(loginLabel);
        add(loginField);
        add(passwordLabel);
        add(passwordField);
        add(button);
    }
}
