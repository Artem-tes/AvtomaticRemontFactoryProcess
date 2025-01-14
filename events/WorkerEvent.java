package events;

import Interfaces.ListenersInterface;
import repository.LoginRepository;
import repository.TaskRepository;
import repository.WorkerRepository;
import utils.UpdateRow;
import utils.Utils;
import vision.panels.WorkerPanel;
import vision.frames.AddWorkerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerEvent implements ListenersInterface {

    WorkerPanel panelWorker = new WorkerPanel();
    LoginRepository loginRepository = new LoginRepository();
    WorkerRepository workerRepository = new WorkerRepository();
    TaskRepository taskRepository = new TaskRepository();

    public void onCreate(Container container, JPanel panelL) {
        Utils.refreshCehnter(container, panelL, panelWorker);
    }

    public WorkerEvent(Container container,JPanel panel){
        onCreate(container,panel);
        listen();
    }

    public WorkerEvent(){}

    @Override
    public void listen() {
        actionAddButton();
        listenUpdateButton();
        listenDeleteButton();
        listenQuestionButton();
    }

    private void listenQuestionButton(){
        JButton button = panelWorker.getQueButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showConfirmDialog(null,
                        "Hi!\n" +
                                "If you worker you can update list worker and you can see yourself in this list.\n" +
                                "If you Administrator you can add and delete workers.\n" +
                                "If you want delete worker, please select need row and pump DELETE button and apply yourself action.\n" +
                                "If you want add worker, in main you forgot know what login is login why worker included system!, its unical key!.\n" +
                                "Enjoy you use!","Documentation",0);
            }
        });
    }

    private void listenDeleteButton(){
        JButton button = panelWorker.getDeleteButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = panelWorker.getTable().getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showConfirmDialog(null,
                            "Please select row in table","Error",JOptionPane.OK_OPTION);
                }else {
                    Object TN = panelWorker.getTable().getValueAt(selectedRow, 0);
                    Object login = panelWorker.getTable().getValueAt(selectedRow, 1);
                    Object name = panelWorker.getTable().getValueAt(selectedRow, 2);
                    Object secName = panelWorker.getTable().getValueAt(selectedRow, 3);
                    Object discharge = panelWorker.getTable().getValueAt(selectedRow, 4);
                    int resultUserDelete = JOptionPane.showConfirmDialog(null,
                            "You read delete worker?\n" +
                                    "TN = " + TN + "" +
                                    "\nLogin: " + login +
                                    "\nAll name: " + name + " " + secName +
                                    "\nDischarge: " + discharge,
                            "Plane", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (resultUserDelete == 0) {
                        workerRepository.deleteWorkerForTN((int)TN);
                    }
                }

            }
        });
    }

    private void listenUpdateButton(){
        JButton button = panelWorker.getUpdateButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UpdateRow updateRow = new UpdateRow();
                updateRow.updateRow(panelWorker.getModel());
            }
        });
    }

    private void actionAddButton(){
        JButton button = panelWorker.getAddButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddWorkerFrame addWorkerFrame = new AddWorkerFrame();
                JButton jButton = addWorkerFrame.getRegButton();
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        String login = addWorkerFrame.getLoginField().getText();
                        String name = addWorkerFrame.getNameField().getText();
                        String secName = addWorkerFrame.getSecNameField().getText();
                        int discharge = Integer.parseInt(addWorkerFrame.getDischargeField().getText());
                        if(login == null||name == null||secName == null||discharge == 0){
                            JOptionPane.showConfirmDialog(null,
                                    "One or more fields is null!","Error",JOptionPane.OK_OPTION);
                        }else {
                            boolean isGood = true;
                            List<String> loginsWorker = workerRepository.getAllLoginsForWorker();
                            for (int i = 0; i < loginsWorker.size(); i++) {
                                if (loginsWorker.get(i).equals(login)) {
                                    isGood = false;
                                }
                            }
                            if (isGood) {
                                if (discharge <= 7) {
                                    int tNum = setTabelNumber();
                                    workerRepository.setWorker(tNum, login, name, secName, discharge);
                                    JOptionPane.showConfirmDialog(null,
                                            "You complete add new worker:" +
                                                    "\nTABELNUMBER: " + tNum + "" +
                                                    "\nLOGIN: " + login + "" +
                                                    "\nALL NAME: " + name + " " + secName + "" +
                                                    "\nDISCHARGE: " + discharge + ".", "Registration",
                                            JOptionPane.OK_OPTION);
                                    taskRepository.setWorker(tNum);
                                    addWorkerFrame.setVisible(false);
                                } else {
                                    JOptionPane.showConfirmDialog(null,
                                            "Discharge not be bigger 7!", "Error", JOptionPane.OK_OPTION);
                                }
                            } else {
                                JOptionPane.showConfirmDialog(null,
                                        "Login is already used!", "Error", JOptionPane.OK_OPTION);
                            }
                        }
                    }
                });

            }
        });
    }

    private int setTabelNumber(){
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i < 10001; i++) {
            numbers.add(i);
        }

        List<Integer> notGoodNumbers = workerRepository.getAllTNumbers();
        for (int i = 0; i < notGoodNumbers.size(); i++) {
            numbers.remove(notGoodNumbers.get(i));
        }

        int randomCount = random.nextInt(0,numbers.size());

        return numbers.get(randomCount);
    }

    private boolean checkLogin(String login){
        boolean all = true;
        boolean loginCheck = false;
        boolean workerCheck = true;
        List<String> logins = loginRepository.getAllLogin();
        List<String> loginsWorker = workerRepository.getAllLoginsForWorker();
        if(login == null){
            JOptionPane.showConfirmDialog(null,"login is null!");
        }
        for (int i = 0; i < logins.size(); i++) {
            if(login.equals(logins.get(i))){
                loginCheck = true;
            }
        }

        for (int i = 0; i < loginsWorker.size(); i++) {
            if(login.equals(loginsWorker.get(i))){
                workerCheck = false;
            }
        }

        if(loginCheck && workerCheck){
            all = true;
        }

        return all;
    }

    public List<Object> isTN(String login){
        boolean isCool = false;
        int TN = 0;
        List<Object> objects = new ArrayList<>();
        List<String> logins = workerRepository.getAllLoginsForWorker();
        for (int i = 0; i < logins.size(); i++) {
            if(login.equals(logins.get(i))){
                isCool = true;
                TN = workerRepository.getTNForLogin(logins.get(i));
            }
        }
        objects.add(isCool);
        objects.add(TN);
        return objects;
    }
}
