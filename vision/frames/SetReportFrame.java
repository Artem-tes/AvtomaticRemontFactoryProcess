package vision.frames;

import javax.swing.*;
import java.awt.*;

public class SetReportFrame extends JFrame {
    JTextField dataField;
    JButton formButton;
    JRadioButton radioWorkerCount;
    JRadioButton radioTaskButton;

    public SetReportFrame(){
        setTitle("Form");
        setBounds(1000,500,250,300);
        setLayout(new GridLayout(5,1));
        add(new JLabel("Please select date"));
        dataField = new JTextField();
        radioTaskButton = new JRadioButton("Get all complete task");
        radioWorkerCount = new JRadioButton("get worker count");
        add(dataField);
        add(radioTaskButton);
        add(radioWorkerCount);
        formButton = new JButton("Create");
        add(formButton);
    }

    public JTextField getDataField() {
        return dataField;
    }

    public JButton getFormButton() {
        return formButton;
    }

    public JRadioButton getRadioWorkerCount() {
        return radioWorkerCount;
    }

    public JRadioButton getRadioTaskButton() {
        return radioTaskButton;
    }
}
