package vision.frames;

import javax.swing.*;
import java.awt.*;

public class AddEquipmentFrame extends JFrame {
    private JLabel INLabel;
    private JTextField INField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton button;

    public AddEquipmentFrame(){
        setLayout(new GridLayout(3,2,7,7));
        setVisible(true);
        setTitle("Equipment");
        setBounds(850,450,300,200);
        INLabel = new JLabel(" IN");
        INField = new JTextField("-");
        nameLabel = new JLabel(" Name Equipment");
        nameField = new JTextField();
        button = new JButton("Add Equipment");
        add(INLabel);
        add(INField);
        add(nameLabel);
        add(nameField);
        add(button);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JButton getButton() {
        return button;
    }

    public JTextField getINField() {
        return INField;
    }
}
