package events;

import Interfaces.ListenersInterface;
import models.Requests;
import repository.BrakesRepository;
import repository.RequestsRepository;
import utils.Utils;
import vision.panels.MainMenuFrame;
import vision.panels.TaskCenterPanel;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TaskEvent implements ListenersInterface {

    TaskCenterPanel taskCenterPanel = new TaskCenterPanel();
    BrakesRepository brakesRepository = new BrakesRepository();
    RequestsRepository requestsRepository = new RequestsRepository();

    public TaskEvent(Container container,JPanel panel){
        onCreate(container,panel);
        listen();
    }

    private void onCreate(Container container, JPanel panel){
        Utils.refreshCehnter(container,panel,taskCenterPanel);
    }

    @Override
    public void listen() {
        listenUpdateStatus();
        listenUpdate();
        listenGetDesc();
    }

    private void listenGetDesc(){
        JButton button = taskCenterPanel.getDescButton();
        JTable table = taskCenterPanel.getTable();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectRow = table.getSelectedRow();
                if(selectRow == -1){
                    JOptionPane.showConfirmDialog(null,"Please select row in table");
                }else {
                    int ID = (int) table.getValueAt(selectRow, 0);
                    JOptionPane.showConfirmDialog(null, brakesRepository.getDescByID(ID), "", 0);
                }
            }
        });
    }


    private void listenUpdate(){
        JButton button = taskCenterPanel.getUpdateSt();
        DefaultTableModel model = taskCenterPanel.getModel();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String statusText = "";
                int countRow = model.getRowCount();
                while (countRow > 0){
                    model.removeRow(0);
                    countRow--;
                }
                java.util.List<Integer> IDS = requestsRepository.getAllRequestsByTN(MainMenuFrame.getTN());
                for (int i = 0; i < IDS.size(); i++) {
                    List<Requests> requests = brakesRepository.getAllByID(IDS.get(i));
                    for (int i1 = 0; i1 < requests.size(); i1++) {
                        if(requests.get(i1).getStatus() == 1){
                            statusText = "Running";
                        }if(requests.get(i1).getStatus() == 2){
                            statusText = "Under review";
                        }if (requests.get(i1).getStatus() == 3){
                            statusText = "Complete";
                        }
                        model.addRow(new Object[]{
                                requests.get(i1).getID(),
                                requests.get(i1).getINN(),
                                requests.get(i1).getName(),
                                requests.get(i1).getTimeBrake(),
                                statusText
                        });
                    }
                }
            }
        });

    }

    private void listenUpdateStatus(){
        JButton button = taskCenterPanel.getUpdateButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JTable table = taskCenterPanel.getTable();
                int selectedRow = table.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showConfirmDialog(null,"Please select row in table","Error",0);
                }else {
                    int ID = (int) table.getValueAt(selectedRow, 0);
                    if (brakesRepository.getStatusById(ID) == 2) {
                        JOptionPane.showConfirmDialog(null,"You already send task to admin please wait","",0);
                    }if (brakesRepository.getStatusById(ID) == 3){
                        JOptionPane.showConfirmDialog(null,"Ypu complete this task","",0);
                    }if(brakesRepository.getStatusById(ID) == 1) {
                        int selectVar = JOptionPane.showConfirmDialog(null,
                                "You sure read complete task where:\n" +
                                        "ID " + ID +
                                        "\nINN " + table.getValueAt(selectedRow, 1) +
                                        "\nName " + table.getValueAt(selectedRow, 2) +
                                        "\nIf you pump OK button task be status - In review," +
                                        "\nand dispather checking completes task", "Warning", JOptionPane.YES_NO_OPTION);
                        if (selectVar == 0) {
                            brakesRepository.updateStatus(2, ID);
                            JOptionPane.showConfirmDialog(null,"Complete update","Complete",0);
                        }
                    }
                }
            }
        });
    }
}
