package events;

import Interfaces.ListenersInterface;
import repository.AddEquipmentRepository;
import repository.BrakesRepository;
import utils.Utils;
import vision.panels.BrakesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BrakesEvent implements ListenersInterface {

    BrakesPanel brakesPanel = new BrakesPanel();
    BrakesRepository brakesRepository = new BrakesRepository();
    AddEquipmentRepository addEquipmentRepository = new AddEquipmentRepository();

    public BrakesEvent(Container container,JPanel panel){
        onCreate(container,panel);
        listen();
    }

    public void onCreate(Container container, JPanel panel){
        Utils.refreshCehnter(container,panel,brakesPanel);
    }

    @Override
    public void listen() {
        listenSendButton();
        listenSelectItem();
    }

    private void listenSelectItem(){
        brakesPanel.getTimeField().setText((String) brakesPanel.getTimeBrake().getSelectedItem());
        brakesPanel.getMachineField().setText((String) brakesPanel.getBoxMachine().getSelectedItem());
        brakesPanel.getBoxMachine().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                brakesPanel.getMachineField().setText((String) brakesPanel.getBoxMachine().getSelectedItem());
            }
        });
        JComboBox<String> stringJComboBox = brakesPanel.getTimeBrake();
        stringJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectView = (String) stringJComboBox.getSelectedItem();
                brakesPanel.getTimeField().setText(selectView);
            }
        });
    }

    private void listenSendButton(){
        JButton sendButton = brakesPanel.getSendButton();
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String IN = brakesPanel.getINField().getText();
                String name = brakesPanel.getMachineField().getText();
                String desc = brakesPanel.getBrakeDesc().getText();
                String time = brakesPanel.getTimeField().getText();
                if(name.isEmpty()|| desc.isEmpty() || time.isEmpty()|| IN == "-"||IN.isEmpty()){
                    JOptionPane.showConfirmDialog(null,"One or more fields is null");
                }else{
                    if(checkINNFromBrakes(IN)){
                        if(checkINNFromEquipment(IN)){
                            if(checkNameForINN(IN,name)) {
                                brakesRepository.addBrakeMachine(IN, name, desc, time);
                                JOptionPane.showConfirmDialog(null, "You complete left a request\n" +
                                                "IN: " + IN +
                                                "\nName: " + name +
                                                "\nTime brake: " + time,
                                        "Complete", 0);
                                brakesPanel.getINField().setText("-");
                                brakesPanel.getMachineField().setText("");
                                brakesPanel.getBrakeDesc().setText("");
                                brakesPanel.getTimeField().setText("");
                            }else{
                                JOptionPane.showConfirmDialog(null,
                                        "You select in box why under this INN "+IN+
                                        "\nBut you select "+name+
                                        "\nDo you mean "+addEquipmentRepository.getNameForIN(IN)+"?"
                                        ,"Dangerous",0);
                            }
                        }else{
                            JOptionPane.showConfirmDialog(null,
                                    "This IN not found, please call administrator","Error",0);
                        }
                    }else{
                        JOptionPane.showConfirmDialog(null,
                                "Task from this IN is already running","Error",0);
                    }
                }
            }
        });
    }

    private boolean checkINNFromBrakes(String INN){
        boolean isNice = true;
        List<String> strings = brakesRepository.getAllINN();
        for (int i = 0; i < strings.size(); i++) {
            if(strings.get(i).equals(INN)){
                isNice = false;
            }
        }

        return isNice;
    }

    private boolean checkINNFromEquipment(String INN){
        boolean isNice = false;
        List<String> strings = addEquipmentRepository.getAllIN();
        for (int i = 0; i < strings.size(); i++) {
            if(strings.get(i).equals(INN)){
                isNice = true;
            }
        }
        return isNice;
    }

    private boolean checkNameForINN(String INN,String name){
        boolean isNice = false;
        String s = addEquipmentRepository.getNameForIN(INN);
        if(name.equals(s)){
            isNice = true;
        }
        return isNice;
    }

}
