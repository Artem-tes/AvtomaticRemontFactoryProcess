package vision.frames;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    JButton okayButton;
    JTextField textField;
    public AdminFrame(){
        setBounds(1000,500,300,200);
        setTitle("Prof");
        setLayout(new GridLayout(3,1));
        add(new JLabel("Please input your admin password"));
        textField = new JTextField();
        okayButton = new JButton("Check");
        add(textField);
        add(okayButton);
    }

    public JButton getOkayButton() {
        return okayButton;
    }

    public JTextField getTextField() {
        return textField;
    }
}
