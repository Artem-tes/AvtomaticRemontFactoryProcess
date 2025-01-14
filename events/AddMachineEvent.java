package events;

import Interfaces.ListenersInterface;
import repository.AddEquipmentRepository;
import utils.Utils;
import vision.frames.AddEquipmentFrame;
import vision.panels.AddMachinePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddMachineEvent implements ListenersInterface {

    AddMachinePanel addMachinePanel = new AddMachinePanel();

    public void onCreate(Container container, JPanel refPanel){
        Utils.refreshCehnter(container,refPanel,addMachinePanel);
    }

    public AddMachineEvent(Container container,JPanel panel){
        onCreate(container,panel);
        listen();
    }

    @Override
    public void listen() {
        JButton button = addMachinePanel.getAddButton();
        listenAddButton();
        listenUpdate();
        listenDelete();
        listenQuestion();
    }

    private void listenQuestion(){
        JButton button = addMachinePanel.getQueButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showConfirmDialog(null,"Hi!\n" +
                        "Workers possibilities:\n" +
                        "Update table and check all info from Equipment\n" +
                        "\n" +
                        "Admin possibilities:\n" +
                        "You can add equipment, if factory get new machine, you can add this machine and workers can get task from remont this equipment\n" +
                        "If you need delete equipment you can delete row , select row and apply you action!\n" +
                        "Enjoy you use!","Documentation",JOptionPane.OK_OPTION);
            }
        });
    }

    private void listenDelete(){
        JButton button = addMachinePanel.getDeleteButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddEquipmentRepository addEquipmentRepository = new AddEquipmentRepository();
                JTable model = addMachinePanel.getTable();
                int selectedRow = model.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showConfirmDialog(null,"Please select row in table");
                }else{
                    Object IN = model.getValueAt(selectedRow,0);
                    Object name = model.getValueAt(selectedRow,1);
                    int select = JOptionPane.showConfirmDialog(null,
                            "You read delete row?\n" +
                                    "This action impossible return!\n" +
                                    "IN:"+IN+"\nName Equipment:"+name,"Question",JOptionPane.YES_NO_OPTION);
                    if(select == 0){
                        addEquipmentRepository.deleteRow((String) IN);
                        JOptionPane.showConfirmDialog(null,
                                "Complete delete, update table");
                    }
                }
            }
        });
    }

    private void listenUpdate(){
        JButton button = addMachinePanel.getUpdateButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultTableModel model = addMachinePanel.getModel();
                int countRow = model.getRowCount();
                while (countRow > 0){
                    model.removeRow(0);
                    countRow--;
                }
                addMachinePanel.setColumn(model);
            }
        });
    }

    private void listenAddButton(){
        JButton button = addMachinePanel.getAddButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddEquipmentFrame frame = new AddEquipmentFrame();
                JButton addButton = frame.getButton();
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        AddEquipmentRepository repository = new AddEquipmentRepository();
                        String IN = frame.getINField().getText();
                        String nameEquipment = frame.getNameField().getText();
                        if (IN.equals("-") || nameEquipment.equals(" ")) {
                            JOptionPane.showConfirmDialog(null,
                                    "Please input text in field");
                        } else {
                            boolean isNice = checkIN(IN);
                            if (isNice) {
                                repository.setEquipment(IN, nameEquipment);
                                JOptionPane.showConfirmDialog(null,
                                        "You complete save Equipment why:\nIN:" + IN + "" +
                                                "\nName: " + nameEquipment, "Complete", JOptionPane.OK_OPTION);
                                frame.getINField().setText("-");
                                frame.getNameField().setText("");
                                frame.setVisible(false);
                            } else {
                                JOptionPane.showConfirmDialog(null,
                                        "IN is already used, please try return", "Error", JOptionPane.OK_OPTION);
                                frame.getINField().setText("-");
                            }
                        }
                    }
                });
            }
        });
    }

    private boolean checkIN(String IN){
        AddEquipmentRepository repository = new AddEquipmentRepository();
        boolean nice = true;
        List<String> strings = repository.getAllIN();
        for (String string : strings) {
            if(string != null && strings != null) {
                if (string.equals(IN)){
                    nice = false;
                }
            }
        }
        return nice;
    }
}
