package vision.panels;

import javax.swing.*;
import java.awt.*;

public class TaskButtonsPanel extends JPanel {
    JButton upb = new JButton("UpdateStatusTask");
    JButton updateButton = new JButton("Update");
    JButton getDesc = new JButton("GetDescription");
    public TaskButtonsPanel(){
        setLayout(new FlowLayout());
        add(upb);
        add(getDesc);
        add(updateButton);
    }

    public JButton getUpb() {
        return upb;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDescButton() {
        return getDesc;
    }
}
