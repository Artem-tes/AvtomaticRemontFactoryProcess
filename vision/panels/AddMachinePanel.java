package vision.panels;

import Interfaces.PanelInterface;
import models.Equipment;
import repository.AddEquipmentRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AddMachinePanel extends JPanel implements PanelInterface {

    JButton addButton;
    JButton updateButton;
    JButton deleteButton;
    JButton queButton;
    DefaultTableModel model = new DefaultTableModel();
    JTable table;
    NorthPanel northPanel = new NorthPanel();

    public AddMachinePanel(){
        createComponents();
        addToPanelComponents();
    }

    @Override
    public void createComponents() {
        addButton = northPanel.getAddB();
        updateButton = northPanel.getUpB();
        deleteButton = northPanel.getDelB();
        queButton = northPanel.getQueB();
        table = new JTable(setModel(model));
    }

    @Override
    public void addToPanelComponents(){
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH,northPanel);
        add(BorderLayout.CENTER,new JScrollPane(table));
    }

    private DefaultTableModel setModel(DefaultTableModel model){
        model.addColumn("InventoryNumber");
        model.addColumn("Name");
        setColumn(model);
        return model;
    }

    public void setColumn(DefaultTableModel model){
        AddEquipmentRepository repository = new AddEquipmentRepository();
        List<Equipment> equipment = repository.getAllEquipment();
        for (int i = 0; i < equipment.size(); i++) {
            Equipment equipmentThis = equipment.get(i);
            model.addRow(new Object[]{equipmentThis.getIN(),equipmentThis.getName()});
        }
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getQueButton() {
        return queButton;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }
}
