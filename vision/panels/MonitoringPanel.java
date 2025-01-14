package vision.panels;

import Interfaces.PanelInterface;
import models.Requests;
import models.Worker;
import repository.BrakesRepository;
import repository.WorkerRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MonitoringPanel extends JPanel implements PanelInterface {

    JLabel workerLabel;
    DefaultTableModel workerModel = new DefaultTableModel();
    JTable workerTable;
    JLabel requestsLabel;
    DefaultTableModel requestsModel = new DefaultTableModel();
    JTable requestsTable;
    WorkerRepository workerRepository = new WorkerRepository();
    BrakesRepository brakesRepository = new BrakesRepository();
    JButton addRequestsButton;
    JButton getDescriptionButton;
    JButton updateButton;
    NorthMonitoringPanel northMonitoringPanel = new NorthMonitoringPanel();

    @Override
    public void createComponents(){
        addRequestsButton = northMonitoringPanel.getArb();
        getDescriptionButton = northMonitoringPanel.getGd();
        updateButton = northMonitoringPanel.getUb();
        workerLabel = new JLabel("Free Workers");
        workerTable = new JTable(setModelWorker(workerModel));
        requestsLabel = new JLabel("Open Requests");
        requestsTable = new JTable(setModelRequests(requestsModel));
    }

    @Override
    public void addToPanelComponents() {
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH,northMonitoringPanel);
        add(BorderLayout.CENTER,new JScrollPane(workerTable));
        add(BorderLayout.WEST,new JScrollPane(requestsTable));
    }

    public MonitoringPanel(){
        createComponents();
        addToPanelComponents();
    }

    private DefaultTableModel setModelRequests(DefaultTableModel model){
        model.addColumn("ID");
        model.addColumn("INN");
        model.addColumn("Name");
        model.addColumn("TimeBrake");
        List<Requests> requests = brakesRepository.getAllRequests();
        for (int i = 0; i < requests.size(); i++) {
            Requests request = requests.get(i);
            model.addRow(new Object[]{
                    request.getID(),
                    request.getINN(),
                    request.getName(),
                    request.getTimeBrake()
            });
        }
        return model;
    }

    private DefaultTableModel setModelWorker(DefaultTableModel model){
        model.addColumn("TN");
        model.addColumn("Login");
        model.addColumn("Name");
        model.addColumn("SecName");
        model.addColumn("Discharge");
        List<Worker> workers = workerRepository.getAllInfo();
        for (int i = 0; i < workers.size(); i++) {
            Worker worker = workers.get(i);
            model.addRow(new Object[]{
                    worker.getTabelNumer(),
                    worker.getLogin(),
                    worker.getName(),
                    worker.getSecName(),
                    worker.getDischarge()
            });
        }
        return model;
    }

    public DefaultTableModel getWorkerModel() {
        return workerModel;
    }

    public DefaultTableModel getRequestsModel() {
        return requestsModel;
    }

    public JTable getWorkerTable() {
        return workerTable;
    }

    public JTable getRequestsTable() {
        return requestsTable;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getGetDescriptionButton() {
        return getDescriptionButton;
    }

    public JButton getAddRequestsButton() {
        return addRequestsButton;
    }
}
