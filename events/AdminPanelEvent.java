package events;

import Interfaces.ListenersInterface;
import com.jogamp.common.util.Bitfield;
import org.w3c.dom.CDATASection;
import repository.LoginRepository;
import repository.TaskRepository;
import repository.WorkerRepository;
import utils.Utils;
import vision.frames.AddLoginFrame;
import vision.frames.AdminFrame;
import vision.frames.SetReportFrame;
import vision.panels.AdminPanel;
import vision.panels.MainMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AdminPanelEvent implements ListenersInterface {

    AdminPanel adminPanel = new AdminPanel();
    String status;
    int statusInt;
    WorkerRepository workerRepository = new WorkerRepository();
    AddLoginFrame frame = new AddLoginFrame();
    TaskRepository taskRepository = new TaskRepository();
    LoginRepository loginRepository = new LoginRepository();


    public AdminPanelEvent(Container container,JPanel panel){
        onCreate(container,panel);
        listen();
    }

    public void onCreate(Container container, JPanel panel){
        Utils.refreshCehnter(container,panel,adminPanel);
    }

    @Override
    public void listen() {
        listenAddLogin();
        listenComboBox();
        listenReport();
    }

    private void listenReport(){
        JButton button = adminPanel.getReportButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetReportFrame frame = new SetReportFrame();
                frame.setVisible(true);
                JButton jButton = frame.getFormButton();
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        String date = frame.getDataField().getText();
                        if(date == ""){
                            JOptionPane.showConfirmDialog(null,"Please select row in table","Error",0);
                        }else{
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(date);
                                stringBuilder.append(".txt");
                                int countSelect = JOptionPane.showConfirmDialog(null,
                                        "IF YOU FORM REPORT YOU MAY ALL RESPONSIBLE FOR YOU!\nYOU READY?","DANGEROUS"
                                        ,JOptionPane.YES_NO_OPTION);
                                if(countSelect == 0) {
                                    AdminFrame adminFrame = new AdminFrame();
                                    adminFrame.setVisible(true);
                                    JButton jButton1 = adminFrame.getOkayButton();
                                    jButton1.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent actionEvent) {
                                            if(adminFrame.getTextField().getText().equals(loginRepository.getPassByLogin(MainMenuFrame.LOGIN))){
                                                try {
                                                    FileWriter fileWriter = new FileWriter(String.valueOf(stringBuilder));
                                                    fileWriter.write(
                                                            "_________________¶¶¶¶¶¶¶¶\n" +
                                                                    "_______________¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "_________________¶¶¶¶¶¶¶¶¶¶____¶¶¶¶¶¶¶¶¶\n" +
                                                                    "_________________¶¶¶¶¶¶¶¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "_______¶¶¶¶¶¶¶____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "__¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶___________¶¶¶\n" +
                                                                    "¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶______________¶¶\n" +
                                                                    "¶¶¶¶¶¶¶¶¶______¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "___________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "_______¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶__¶¶¶¶¶_______¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "______¶¶¶¶¶¶¶¶¶¶¶________¶¶¶¶¶_________¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "______¶¶¶¶¶¶¶¶¶__________¶¶¶¶¶¶_________¶¶¶¶¶¶¶¶¶\n" +
                                                                    "______¶¶¶¶¶¶¶¶____________¶¶¶¶¶__________¶¶¶¶¶¶¶¶\n" +
                                                                    "______¶¶¶¶¶¶______________¶¶¶¶¶¶__________¶¶¶¶¶¶¶\n" +
                                                                    "______¶¶¶¶¶________________¶¶¶¶¶¶__________¶¶¶¶¶¶\n" +
                                                                    "_______¶¶¶¶_________________¶¶¶¶¶¶__________¶¶¶¶¶\n" +
                                                                    "_______¶¶__________________¶¶¶¶¶¶¶_________¶¶¶¶\n" +
                                                                    "_______¶¶___________________¶¶¶¶¶¶¶_________¶¶\n" +
                                                                    "_______¶____________________¶¶¶¶¶¶¶_________¶\n" +
                                                                    "_____________________________¶¶¶¶¶¶\n" +
                                                                    "_____________________________¶¶¶¶¶¶¶\n" +
                                                                    "_____________________________¶¶¶¶¶¶¶\n" +
                                                                    "___________________________¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "________________________¶¶¶111¶¶¶¶¶¶¶\n" +
                                                                    "_________________¶¶¶¶¶¶111111111¶¶¶¶¶¶¶\n" +
                                                                    "_______________¶¶1111111111111111¶¶¶¶¶¶¶\n" +
                                                                    "_____¶¶¶¶¶1111111111111¶1111¶¶¶¶¶¶¶1111¶¶¶\n" +
                                                                    "__¶¶¶¶¶¶1111111111111111111¶¶11¶¶¶¶¶¶¶11¶¶¶¶\n" +
                                                                    "¶¶1111111111111111111111110n¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
                                                                    "11111111111111111111111111111¶¶¶¶¶¶¶¶¶¶¶¶¶¶"+
                                                                    "\nREPORT FROM DATE:\n"+
                                                                    "("+date+")\n");
                                                    if (frame.getRadioTaskButton().isSelected()) {
                                                        fileWriter.write("\n  Report from date:\n"+
                                                                "  ("+date+")\n"+
                                                                "  Report was ");
                                                        fileWriter.write("   All count task in this moment\n" +
                                                                "   All task have a count: " + taskRepository.getCountTaskByAll());
                                                    }
                                                    if (frame.getRadioWorkerCount().isSelected()) {
                                                        fileWriter.write("\n   Worker in this moment to workers place be today\n" +
                                                                "   Count: " + workerRepository.getCountWorker());
                                                    }
                                                    fileWriter.write("Login who create report\n" +
                                                            "Login: " + MainMenuFrame.LOGIN);
                                                    fileWriter.write("\nFactory Burevestnik\nDate - " + date);
                                                    fileWriter.write("\nEnjoy you use)\nDeveloper: @psvms");
                                                    fileWriter.close();
                                                    JOptionPane.showConfirmDialog(null,"" +
                                                            "You correct create report to date "+date,"Complete",0);
                                                    frame.setVisible(false);
                                                    adminFrame.setVisible(false);
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }else{
                                                JOptionPane.showConfirmDialog(null,
                                                        "Incorrect password","Error",0);
                                            }
                                        }
                                    });
                                }
                            }

                        }
                });

            }
        });
    }

    private void listenComboBox(){
        JComboBox<String> comboBox = frame.getComboBox();
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                status = (String) comboBox.getSelectedItem();
                frame.getComboField().setText(status);
                if(status.equals("Worker")){
                    statusInt = 0;
                }if(status.equals("Admin")){
                    statusInt = 1;
                }
            }
        });
    }

    private void listenAddLogin(){
        JButton button = adminPanel.getAddButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(true);
                JButton buttonSend = frame.getSendButton();
                buttonSend.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        String login = frame.getLogField().getText();
                        String password = frame.getPassField().getText();
                        if(login == "" || password == "" || status == ""){
                            JOptionPane.showConfirmDialog(null,"One or more field is null","Error",0);
                        }else{
                            workerRepository.setLogPass(login,password,statusInt);
                            JOptionPane.showConfirmDialog(null,
                                    "You complete add new user to DB"+
                                            "\nLogin: "+login+
                                            "\nPassword: "+password+
                                            "\nStatus: "+status,"Complete",0);
                            frame.setVisible(false);
                        }
                    }
                });

            }
        });
    }
}
