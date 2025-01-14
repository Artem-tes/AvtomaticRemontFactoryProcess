package events;

import Interfaces.ListenersInterface;
import main.Main;
import utils.Utils;
import vision.panels.AccountPanel;
import vision.panels.LoginMainPanel;
import vision.panels.MainMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountEvent implements ListenersInterface {
    AccountPanel accountPanel = new AccountPanel();
    MainMenuFrame mainMenuFrame;

    public AccountEvent(MainMenuFrame mainMenuFrame){
        this.mainMenuFrame = mainMenuFrame;
    }

    public void onCreate(Container container, JPanel panel){
        Utils.refreshCehnter(container,panel,accountPanel);
    }

    @Override
    public void listen() {
        JButton button = accountPanel.getExitButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                mainMenuFrame.getMainMenuFrame().setVisible(false);
                Main.frame.setVisible(true);
            }
        });
    }
}
