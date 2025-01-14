package vision.frames;

import javax.swing.*;
import java.awt.*;

public class AddLoginFrame extends JFrame {
    JLabel logLabel;
    JLabel pasLabel;
    String[] strings = new String[]{"Admin","Worker"};
    JComboBox<String> comboBox = new JComboBox<>(strings);
    JTextField logField;
    JTextField passField;
    JTextField comboField;
    JButton sendButton;

    public AddLoginFrame(){
        setBounds(1000,500,250,300);
        setTitle("Registration");
        setVisible(false);
        setLayout(new GridLayout(10,1));
        logLabel = new JLabel("Login");
        pasLabel = new JLabel("Password");
        logField = new JTextField();
        passField = new JTextField();
        comboField = new JTextField();
        sendButton = new JButton("Send");
        add(logLabel);
        add(logField);
        add(pasLabel);
        add(passField);
        add(comboBox);
        add(comboField);
        add(sendButton);
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JTextField getComboField() {
        return comboField;
    }

    public JTextField getPassField() {
        return passField;
    }

    public JTextField getLogField() {
        return logField;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
