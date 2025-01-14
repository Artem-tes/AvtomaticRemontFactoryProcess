package vision.panels;

import javax.swing.*;
import java.awt.*;

public class GetTaskButtonPanel extends JPanel {
    JButton completeButton = new JButton("Accept");
    JButton returnButton = new JButton("Return");
    JButton updateButton = new JButton("Update");
    public GetTaskButtonPanel(){
        setLayout(new FlowLayout());
        add(completeButton);
        add(returnButton);
        add(updateButton);
    }

    public JButton getCompleteButton() {
        return completeButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }
}
