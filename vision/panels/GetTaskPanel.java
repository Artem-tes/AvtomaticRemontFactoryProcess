package vision.panels;

import Interfaces.PanelInterface;
import models.Requests;
import repository.BrakesRepository;
import repository.RequestsRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetTaskPanel extends JPanel implements PanelInterface {
    DefaultTableModel model = new DefaultTableModel();
    JTable table;
    RequestsRepository requestsRepository = new RequestsRepository();
    BrakesRepository brakesRepository = new BrakesRepository();
    GetTaskButtonPanel getTaskButtonPanel = new GetTaskButtonPanel();
    JButton completeButton = getTaskButtonPanel.getCompleteButton();
    JButton returnButton = getTaskButtonPanel.getReturnButton();
    JButton updateButton = getTaskButtonPanel.getUpdateButton();


    public GetTaskPanel(){
        createComponents();
        addToPanelComponents();
    }

    @Override
    public void createComponents() {
        table = new JTable(setModel(model));
    }

    @Override
    public void addToPanelComponents() {
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH,getTaskButtonPanel);
        add(BorderLayout.CENTER,new JScrollPane(table));
    }

    private DefaultTableModel setModel(DefaultTableModel model){
        model.addColumn("TNWorker");
        model.addColumn("ID");
        model.addColumn("INN");
        model.addColumn("Name");
        model.addColumn("TimeBrake");

        List<Integer> idByLogin = requestsRepository.getIDByLogin(MainMenuFrame.LOGIN);
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

        return model;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getCompleteButton() {
        return completeButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }
}
