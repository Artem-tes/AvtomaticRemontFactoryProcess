package vision.mainpanels;

import Interfaces.PanelInterface;
import vision.panels.MainMenuFrame;

import javax.swing.*;

public class WestPanel extends JPanel implements PanelInterface {
    JButton buttonWorkers;
    JButton buttonMachine;
    JButton buttonAddMachine;
    JButton buttonRequests;
    JButton buttonAccount;
    JButton adminButton;
    JButton workerButton;
    JButton getTaskButton;

    public JButton getWorkerButton() {
        return workerButton;
    }

    public JButton getButtonAddMachine() {
        return buttonAddMachine;
    }

    public JButton getButtonWorkers() {
        return buttonWorkers;
    }

    public JButton getButtonMachine() {
        return buttonMachine;
    }

    public JButton getButtonRequests() {
        return buttonRequests;
    }

    public WestPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        createComponents();
        addToPanelComponents();
    }

    @Override
    public void createComponents() {
        buttonWorkers = new JButton("Workers");
        buttonAddMachine = new JButton("Equipment");
        buttonMachine = new JButton("Brakes");
        buttonAccount = new JButton("Personal account");
        buttonRequests = new JButton("Monitoring Panel");
        adminButton = new JButton("Admin Panel");
        workerButton = new JButton("Tasks");
        getTaskButton = new JButton("Accept Task");
    }

    @Override
    public void addToPanelComponents() {
        add(buttonAddMachine);
        add(buttonMachine);
        if(MainMenuFrame.getSTATUS() == 0){
            add(workerButton);
        }if(MainMenuFrame.getSTATUS() == 1){
            add(getTaskButton);
            add(buttonWorkers);
            add(buttonRequests);
            add(adminButton);
        }
        add(buttonAccount);
    }

    public JButton getGetTaskButton() {
        return getTaskButton;
    }

    public JButton getAdminButton() {
        return adminButton;
    }

    public JButton getButtonAccount() {
        return buttonAccount;
    }
}
