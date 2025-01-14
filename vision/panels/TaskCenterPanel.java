package vision.panels;

import Interfaces.PanelInterface;
import models.Requests;
import repository.BrakesRepository;
import repository.RequestsRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaskCenterPanel extends JPanel implements PanelInterface {
    DefaultTableModel model = new DefaultTableModel();
    JTable table;
    RequestsRepository requestsRepository = new RequestsRepository();
    TaskButtonsPanel taskButtonsPanel = new TaskButtonsPanel();
    JButton updateButton = taskButtonsPanel.getUpb();
    JButton updateSt = taskButtonsPanel.getUpdateButton();
    JButton descButton = taskButtonsPanel.getDescButton();
    BrakesRepository brakesRepository = new BrakesRepository();
    JLabel labelGetDesc = new JLabel();


    public TaskCenterPanel(){
        createComponents();
        addToPanelComponents();
    }

    @Override
    public void createComponents() {
        table = new JTable(setStatementModel(model));
    }

    @Override
    public void addToPanelComponents() {
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH,taskButtonsPanel);
        add(BorderLayout.CENTER,new JScrollPane(table));
        add(BorderLayout.SOUTH,new JScrollPane(labelGetDesc));
    }

    private DefaultTableModel setStatementModel(DefaultTableModel model){
        model.addColumn(("ID"));
        model.addColumn("INN");
        model.addColumn("Name");
        model.addColumn("Time");
        model.addColumn("Status");
        String statusText = "";
        List<Integer> IDS = requestsRepository.getAllRequestsByTN(MainMenuFrame.getTN());
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
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JLabel getLabelGetDesc() {
        return labelGetDesc;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JButton getUpdateSt() {
        return updateSt;
    }

    public JButton getDescButton() {
        return descButton;
    }
}
