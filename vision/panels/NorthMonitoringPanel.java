package vision.panels;

import javax.swing.*;
import java.awt.*;

public class NorthMonitoringPanel extends JPanel {
    JButton gd = new JButton("GetDescription");
    JButton arb = new JButton("StateRequest");
    JButton ub = new JButton("Update");
    public NorthMonitoringPanel(){
        setLayout(new FlowLayout());
        add(gd);
        add(arb);
        add(ub);
    }

    public JButton getGd() {
        return gd;
    }

    public JButton getArb() {
        return arb;
    }

    public JButton getUb() {
        return ub;
    }
}
