package main;

import events.LoginMainEvent;
import repository.MainRepository;
import vision.panels.LoginMainPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame frame = new JFrame();
    public static Container container = frame.getContentPane();
    static LoginMainPanel loginMainPanel = new LoginMainPanel();

    public static void main(String[] args){
        new MainRepository();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(600,350,700,400);
        JLabel jLabel = new JLabel("" +
                "App was create by using Java 17,and FrameWork Swing, Responsible for the Database - SQLite");
        JLabel label = new JLabel("Developer app: @psvms");
        container.add(BorderLayout.CENTER,loginMainPanel);
        container.add(BorderLayout.NORTH,label);
        container.add(BorderLayout.SOUTH,jLabel);
        frame.setVisible(true);
        listenRegistration();


    }

    private static void listenRegistration(){
        LoginMainEvent loginMainEvent = new LoginMainEvent(loginMainPanel.getButton(),loginMainPanel.getLoginField(),loginMainPanel.getPasswordField());
        loginMainEvent.listen();
    }
}