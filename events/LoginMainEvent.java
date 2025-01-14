package events;

import Interfaces.ListenersInterface;
import main.Main;
import repository.LoginRepository;
import repository.WorkerRepository;
import vision.panels.MainMenuFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginMainEvent implements ListenersInterface {

    JButton OK;
    JTextField loginField;
    JTextField passwordField;
    LoginRepository repository = new LoginRepository();

    public LoginMainEvent(JButton button,JTextField loginField, JTextField passwordField){
        OK = button;
        this.loginField = loginField;
        this.passwordField = passwordField;
    }

    @Override
    public void listen() {
        listenOKButton();
    }

    private void listenOKButton(){
        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String login = loginField.getText();
                String password = passwordField.getText();
                boolean isLogin = repository.checkUser(login, password);
                WorkerEvent workerEvent = new WorkerEvent();
                List<Object> objects = workerEvent.isTN(login);
                if (isLogin) {
                    int status = repository.getStatusForLogin(login);
                    MainMenuFrame.setSTATUS(status);
                    MainMenuFrame.LOGIN = login;
                    if (MainMenuFrame.STATUS == 0) {
                        JFrame frame = Main.frame;
                        frame.setVisible(false);
                        WorkerRepository repository1 = new WorkerRepository();
                        int tnUser = repository1.getTNForLogin(login);
                        MainMenuFrame.setTN(tnUser);
                        MainMenuFrame mainMenuFrame = new MainMenuFrame();
                        mainMenuFrame.setVisible(true);
                        if ((boolean) objects.get(0)) {
                            JOptionPane.showConfirmDialog(null,
                                    "You login why worker, good day job!\nYou TN is " + tnUser, "Complete", JOptionPane.OK_OPTION);
                        } else {
                            JOptionPane.showConfirmDialog(null,
                                    "You can use system but you not have a TN please call Administrator");
                        }
                        loginField.setText("");
                        passwordField.setText("");
                    } else {
                        JFrame frame = Main.frame;
                        frame.setVisible(false);
                        MainMenuFrame mainMenuFrame = new MainMenuFrame();
                        mainMenuFrame.setVisible(true);
                        JOptionPane.showConfirmDialog(null,
                                "You login why Admin,good day!", "Complete", JOptionPane.OK_OPTION);
                        loginField.setText("");
                        passwordField.setText("");
                    }

                } else {
                    JOptionPane.showConfirmDialog(null, "Incorrect password or login");
                }
            }
        });
    }
}


