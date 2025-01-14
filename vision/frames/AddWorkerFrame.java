package vision.frames;

import javax.swing.*;
import java.awt.*;

public class AddWorkerFrame extends JFrame {
    JLabel labelPres;
    JLabel loginLabel;
    JLabel nameLabel;
    JLabel secNameLabel;
    JLabel dischargeLabel;
    JTextField loginField;
    JTextField nameField;
    JTextField secNameField;
    JTextField dischargeField;
    JButton regButton;



    public AddWorkerFrame(){
        setFrame();
        setComponents();
        addComponents();
    }

    private void setFrame(){
        setTitle("Add Worker");
        setBounds(700,700,300,200);
        setLayout(new GridLayout(5,2,3,3));
        setVisible(true);
    }

    private void setComponents(){
        labelPres = new JLabel("TN - AUTO");
        loginLabel = new JLabel("LOGIN");
        nameLabel = new JLabel("NAME");
        secNameLabel = new JLabel("SECNAME");
        dischargeLabel = new JLabel("DISCHARGE");
        loginField = new JTextField();
        nameField = new JTextField();
        secNameField = new JTextField();
        dischargeField = new JTextField();
        regButton = new JButton("REGISTRATION");
    }

    private void addComponents(){
        add(loginLabel);
        add(loginField);
        add(nameLabel);
        add(nameField);
        add(secNameLabel);
        add(secNameField);
        add(dischargeLabel);
        add(dischargeField);
        add(labelPres);
        add(regButton);
    }

    public JButton getRegButton() {
        return regButton;
    }

    public JTextField getDischargeField() {
        return dischargeField;
    }

    public JTextField getSecNameField() {
        return secNameField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getLoginField() {
        return loginField;
    }
}
