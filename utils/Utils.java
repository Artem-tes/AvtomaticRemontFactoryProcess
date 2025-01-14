package utils;

import javax.swing.*;
import java.awt.*;

public class Utils {
    public static void refreshCehnter(Container container, JPanel centerPanel, JComponent componentNew){
        container.remove(centerPanel);
        centerPanel.removeAll();
        centerPanel.add(componentNew);
        centerPanel.repaint();
        centerPanel.revalidate();
        container.add(BorderLayout.CENTER,centerPanel);
        container.repaint();
        container.revalidate();
    }
}