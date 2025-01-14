package vision.panels;

import Interfaces.PanelInterface;

import javax.swing.*;
import java.awt.*;

public class AdminPanelButton extends JPanel{
    JButton addButton;
    JButton reportButton;
    public AdminPanelButton(){
        setLayout(new FlowLayout());
        addButton = new JButton("AddWorkerLogin");
        reportButton = new JButton("Report");
        add(addButton);
        add(reportButton);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getReportButton() {
        return reportButton;
    }
}
