package vision.panels;

import Interfaces.PanelInterface;
import repository.RequestsRepository;
import repository.TaskRepository;
import repository.WorkerRepository;

import javax.swing.*;

public class AccountPanel extends JPanel implements PanelInterface {

    JButton exitButton;
    WorkerRepository workerRepository = new WorkerRepository();
    TaskRepository taskRepository = new TaskRepository();
    RequestsRepository requestsRepository = new RequestsRepository();

    public AccountPanel(){
        createComponents();
    }

    @Override
    public void createComponents() {
        if(MainMenuFrame.STATUS == 0){
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            JLabel label;
            label = new JLabel("Hi, "+workerRepository.getAllName(MainMenuFrame.TN));
            JLabel discLabel = new JLabel("You discharge "+workerRepository.getDischarge(MainMenuFrame.TN));
            JLabel tnLabel = new JLabel("You TN "+MainMenuFrame.TN);
            JLabel lgLabel = new JLabel("You Login "+MainMenuFrame.LOGIN);
            JLabel ctTask = new JLabel("You complete task count the all time "+taskRepository.getCountTask(MainMenuFrame.TN));
            JLabel label1 = new JLabel("You have all count task "+requestsRepository.getCountNotCompleteTask(MainMenuFrame.TN));
            add(label);
            add(discLabel);
            add(tnLabel);
            add(lgLabel);
            add(ctTask);
            add(label1);
        }if(MainMenuFrame.STATUS == 1){
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            JLabel label = new JLabel("Hi, Admin");
            JLabel jLabel = new JLabel("We not save data from admin");
            JLabel jLabel1 = new JLabel("Good work!");
            add(label);
            add(jLabel);
            add(jLabel1);
        }
        exitButton = new JButton("Logout system");
        add(exitButton);

    }

    @Override
    public void addToPanelComponents() {

    }

    public JButton getExitButton() {
        return exitButton;
    }
}
