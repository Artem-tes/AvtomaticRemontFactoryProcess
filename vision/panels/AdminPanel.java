package vision.panels;

import Interfaces.PanelInterface;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel implements PanelInterface  {

    JLabel label;
    JLabel label1;
    JLabel label2;
    AdminPanelButton adminPanelButton = new AdminPanelButton();
    JButton addButton = adminPanelButton.getAddButton();
    JButton reportButton = adminPanelButton.getReportButton();

    @Override
    public void createComponents() {
        label = new JLabel("This menu is intended for administrators");
        label1 = new JLabel("and for adding new workers, and you can also generate a report on completed tasks at any time");
        label2 = new JLabel("please be careful");
    }

    @Override
    public void addToPanelComponents() {
        setLayout(new BorderLayout());
        add(label);
        add(label1);
        add(label2);
        add(BorderLayout.NORTH,adminPanelButton);
    }

    public AdminPanel(){
        createComponents();
        addToPanelComponents();
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getReportButton() {
        return reportButton;
    }
}
