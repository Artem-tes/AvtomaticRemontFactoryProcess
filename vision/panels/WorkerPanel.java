package vision.panels;

import Interfaces.PanelInterface;
import models.Worker;
import repository.WorkerRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class WorkerPanel extends JPanel implements PanelInterface {

    JButton updateButton;
    JButton deleteButton;
    JButton addButton;
    JButton queButton;
    DefaultTableModel model = new DefaultTableModel();
    JTable table;



    @Override
    public void createComponents() {
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        queButton = new JButton("?");
        addButton = new JButton("Add");
        table = new JTable(setDefaultModel(model));
    }
    @Override
    public void addToPanelComponents() {
        if(MainMenuFrame.getSTATUS() == 1) {
            add(addButton);
            add(deleteButton);
        }
        add(updateButton);
        add(queButton);

        add(BorderLayout.CENTER, new JScrollPane(table));
    }

    private DefaultTableModel setDefaultModel(DefaultTableModel model){
        model.addColumn("TabelNumber");
        model.addColumn("Login");
        model.addColumn("Name");
        model.addColumn("SecondName");
        model.addColumn("Discharge");
        setRow(model);
        return model;
    }


    private void setRow(DefaultTableModel model){
        WorkerRepository repository = new WorkerRepository();
        List<Worker> workers = repository.getAllInfo();
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
    }

    public JTable getTable() {
        return table;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getQueButton() {
        return queButton;
    }

    public WorkerPanel(){
        createComponents();
        addToPanelComponents();
    }
    public WorkerPanel(String a){

    }
}
