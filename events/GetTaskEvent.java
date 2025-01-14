package events;

import Interfaces.ListenersInterface;
import models.Requests;
import repository.BrakesRepository;
import repository.RequestsRepository;
import repository.TaskRepository;
import utils.Utils;
import vision.panels.GetTaskPanel;
import vision.panels.MainMenuFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GetTaskEvent implements ListenersInterface {

    GetTaskPanel getTaskPanel = new GetTaskPanel();
    BrakesRepository brakesRepository = new BrakesRepository();
    TaskRepository taskRepository = new TaskRepository();
    RequestsRepository requestsRepository = new RequestsRepository();

    public GetTaskEvent(Container container, JPanel panel){
        onCreate(container,panel);
        listen();
    }

    public void onCreate(Container container, JPanel panel){
        Utils.refreshCehnter(container,panel,getTaskPanel);
    }

    @Override
    public void listen() {
        listenCompleteButton();
        listenReturnButton();
        listenUpdateButton();
    }

    private void listenUpdateButton(){
        JButton button = getTaskPanel.getUpdateButton();
        DefaultTableModel model = getTaskPanel.getModel();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = model.getRowCount();
                while (row>0){
                    model.removeRow(0);
                    row--;
                }
                java.util.List<Integer> idByLogin = requestsRepository.getIDByLogin(MainMenuFrame.LOGIN);
                for (int i = 0; i < idByLogin.size(); i++) {
                    int TN = requestsRepository.getTNByID(idByLogin.get(i));
                    List<Requests> requests = brakesRepository.getAllByIDStatus(idByLogin.get(i));
                    for (int i1 = 0; i1 < requests.size(); i1++) {
                        Requests request = requests.get(i1);
                        model.addRow(new Object[]{
                                TN,
                                request.getID(),
                                request.getINN(),
                                request.getName(),
                                request.getTimeBrake()
                        });
                    }
                }
            }
        });
    }

    private void listenReturnButton(){
        JButton button = getTaskPanel.getReturnButton();
        JTable table = getTaskPanel.getTable();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showConfirmDialog(null,"Please select row in table","",0);
                }else{
                    int ID = (int) table.getValueAt(selectedRow,1);
                    int select = JOptionPane.showConfirmDialog(null,
                            "You sure return request for ID "+ID+"?","DNG",0);
                    if(select == 0){
                        brakesRepository.updateStatus(1,ID);
                        JOptionPane.showConfirmDialog(null,
                                "Complete return requests to status - Running","Complete",0);
                    }
                }
            }
        });
    }

    private void listenCompleteButton(){
        JButton button = getTaskPanel.getCompleteButton();
        JTable table = getTaskPanel.getTable();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showConfirmDialog(null,"Please select row in table","",0);
                }else{
                    int ID = (int) table.getValueAt(selectedRow,1);
                    int TN = (int) table.getValueAt(selectedRow,0);
                    int select = JOptionPane.showConfirmDialog(null,
                            "You sure accept task where ID "+ID+"?");
                    if(select == 0){
                        brakesRepository.updateStatus(3,ID);
                        taskRepository.updateCountTasks(TN,taskRepository.getCountTask(TN)+1);
                        JOptionPane.showConfirmDialog(null,
                                "Complete, update table please","",0);
                    }
                }
            }
        });
    }
}
