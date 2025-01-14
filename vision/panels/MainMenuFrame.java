package vision.panels;

import events.*;
import vision.mainpanels.WestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {
    public static Container container;
    public static int STATUS = 0;
    public static int TN = 0;
    public static String LOGIN;
    WestPanel westPanel = new WestPanel();
    JPanel centerPanel = new JPanel();

    public MainMenuFrame getMainMenuFrame(){
        return this;
    }

    private void setBounds(){
        setBounds(500,350,1110,400);
        setTitle("Main Action");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setFalseVisible(){

    }


    private void addComponent(){
        JLabel label = new JLabel("If program work and you see bag and error please write me in TG @psvms");
        label.setFont(new Font("arial",Font.BOLD,12));
        container = getContentPane();
        container.add(BorderLayout.CENTER,centerPanel);
        container.add(BorderLayout.WEST,westPanel);
        container.add(BorderLayout.SOUTH,label);
    }

    public MainMenuFrame(){
        setBounds();
        addComponent();
        listenWorker();
        listenAddEquipment();
        listenAddBrakes();
        listenRequestsButton();
        listenListTask();
        listenGetTask();
        listenAccountButton();
        listenAdminButton();
    }

    public void listenWorker(){
        JButton button = westPanel.getButtonWorkers();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new WorkerEvent(container,centerPanel);
            }
        });
    }

    private void listenAddEquipment() {
        JButton button = westPanel.getButtonAddMachine();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddMachineEvent(container, centerPanel);
            }
        });
    }

    private void listenAddBrakes(){
        JButton button = westPanel.getButtonMachine();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new BrakesEvent(container,centerPanel);
            }
        });
    }

    private void listenRequestsButton(){
        JButton buttonRequests = westPanel.getButtonRequests();
        buttonRequests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MonitoringPanelEvent(container,centerPanel);
            }
        });
    }

    private void listenListTask(){
        JButton button = westPanel.getWorkerButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TaskEvent(container,centerPanel);
            }
        });
    }

    private void listenGetTask(){
        JButton button = westPanel.getGetTaskButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new GetTaskEvent(container,centerPanel);
            }
        });
    }

    private void listenAccountButton(){
        JButton button = westPanel.getButtonAccount();
        AccountEvent accountEvent = new AccountEvent(this);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                accountEvent.onCreate(container,centerPanel);
                accountEvent.listen();
            }
        });
    }

    private void listenAdminButton(){
        JButton button = westPanel.getAdminButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AdminPanelEvent(container,centerPanel);
            }
        });
    }

    public static int getTN() {
        return TN;
    }

    public static void setTN(int TN) {
        MainMenuFrame.TN = TN;
    }

    public static void setSTATUS(int STATUS) {
        MainMenuFrame.STATUS = STATUS;
    }

    public static int getSTATUS(){
        return STATUS;
    }

}
