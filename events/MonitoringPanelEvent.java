package events;

import Interfaces.ListenersInterface;
import models.Requests;
import models.Worker;
import repository.BrakesRepository;
import repository.RequestsRepository;
import repository.WorkerRepository;
import utils.Utils;
import vision.panels.MainMenuFrame;
import vision.panels.MonitoringPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MonitoringPanelEvent implements ListenersInterface {
    MonitoringPanel monitoringPanel = new MonitoringPanel();
    RequestsRepository repository = new RequestsRepository();
    BrakesRepository brakesRepository = new BrakesRepository();
    WorkerRepository workerRepository = new WorkerRepository();

    public MonitoringPanelEvent(Container container, JPanel panel){
        onCreate(container,panel);
        listen();
    }

    private void onCreate(Container container,JPanel panel){
        Utils.refreshCehnter(container,panel,monitoringPanel);
    }

    @Override
    public void listen() {
        listenAddRequests();
        listenUpdate();
        listenGetDesc();
    }

    private void listenGetDesc(){
        JButton button = monitoringPanel.getGetDescriptionButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = monitoringPanel.getRequestsTable().getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showConfirmDialog(null,"Please select row in table");
                }else{
                    int ID = (int) monitoringPanel.getRequestsModel().getValueAt(selectedRow,0);
                    JOptionPane.showConfirmDialog(null,brakesRepository.getDescByID(ID));
                }
            }
        });
    }

    private void listenUpdate(){
        JButton buttonUpdate = monitoringPanel.getUpdateButton();
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int rowCountWorker = monitoringPanel.getWorkerModel().getRowCount();
                while (rowCountWorker > 0){
                    monitoringPanel.getWorkerModel().removeRow(0);
                    rowCountWorker--;
                }
                int rowCountRequest = monitoringPanel.getRequestsModel().getRowCount();
                while (rowCountRequest > 0){
                    monitoringPanel.getRequestsModel().removeRow(0);
                    rowCountRequest--;
                }
                List<Requests> requests = brakesRepository.getAllRequests();
                List<Worker> workers = workerRepository.getAllInfo();
                for (int i = 0; i < requests.size(); i++) {
                    Requests request = requests.get(i);
                    monitoringPanel.getRequestsModel().addRow(new Object[]{
                            request.getID(),
                            request.getINN(),
                            request.getName(),
                            request.getTimeBrake()
                    });
                }
                for (int i = 0; i < workers.size(); i++) {
                    Worker worker = workers.get(i);
                    monitoringPanel.getWorkerModel().addRow(new Object[]{
                            worker.getTabelNumer(),
                            worker.getLogin(),
                            worker.getName(),
                            worker.getSecName(),
                            worker.getDischarge()
                    });
                }
            }
        });
    }

    private void listenAddRequests(){
        JButton buttonAdd = monitoringPanel.getAddRequestsButton();
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRowRequests = monitoringPanel.getRequestsTable().getSelectedRow();
                int selectedRowWorker = monitoringPanel.getWorkerTable().getSelectedRow();
                if(selectedRowWorker == -1 || selectedRowRequests == -1){
                    JOptionPane.showConfirmDialog(null,
                            "Please select row in Worker table and requests table");
                }else{
                    int TN = (int) monitoringPanel.getWorkerTable().getValueAt(selectedRowWorker,0);
                    int IDReq = (int) monitoringPanel.getRequestsTable().getValueAt(selectedRowRequests,0);
                    String INN = (String) monitoringPanel.getRequestsTable().getValueAt(selectedRowRequests,1) ;
                    repository.addRequests(TN,IDReq, MainMenuFrame.LOGIN,INN);
                    brakesRepository.updateStatus(1,IDReq);
                    workerRepository.updateStatusWorker(TN,1);
                    JOptionPane.showConfirmDialog(null,"" +
                            "You complete add request to worker where him TN "+TN);
                }
            }
        });
    }
}
