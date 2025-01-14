package vision.panels;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {
    JButton addB = new JButton("Add Machine");
    JButton upB = new JButton("Update");
    JButton delB = new JButton("Delete");
    JButton queB = new JButton("?");

    public NorthPanel(){
        setLayout(new FlowLayout());
        if(MainMenuFrame.getSTATUS() == 1) {
            add(addB);
            add(delB);
        }
        add(upB);
        add(queB);
    }

    public JButton getAddB() {
        return addB;
    }

    public JButton getUpB() {
        return upB;
    }

    public JButton getDelB() {
        return delB;
    }

    public JButton getQueB() {
        return queB;
    }
}
