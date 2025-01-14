package vision.panels;

import Interfaces.PanelInterface;
import repository.AddEquipmentRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrakesPanel extends JPanel implements PanelInterface {

    public BrakesPanel(){
        createComponents();
        addToPanelComponents();
    }

    String[] time = new String[]{
            "7:00",
            "8:00",
            "9:00",
            "10:00",
            "11:00",
            "12:00",
            "13:00",
            "14:00",
            "15:00",
            "16:00"
    };
    JLabel labelIN;
    JTextField INField;
    JComboBox<Object> boxMachine;
    JTextField machineField;
    JLabel writeLabel;
    JTextArea brakeDesc;
    JComboBox<String> timeBrake;
    JTextField timeField;
    JButton sendButton;
    AddEquipmentRepository repository = new AddEquipmentRepository();

    @Override
    public void createComponents() {
        labelIN = new JLabel("Please input IN");
        INField = new JTextField("-");
        boxMachine = new JComboBox<>(setMachineList());
        machineField = new JTextField();
        writeLabel = new JLabel("Please write description brakes");
        brakeDesc = new JTextArea();
        timeBrake = new JComboBox<>(time);
        timeField = new JTextField();
        sendButton = new JButton("Send");
    }

    @Override
    public void addToPanelComponents() {
        setLayout(new GridLayout(9,1,5,5));
        add(labelIN);
        add(INField);
        add(boxMachine);
        add(machineField);
        add(writeLabel);
        add(brakeDesc);
        add(timeBrake);
        add(timeField);
        add(sendButton);
    }

    private Object[] setMachineList(){
        Set<String> machines = repository.getAllNameEquipment();
        Object[] array = machines.toArray();
        return array;
    }

    public JTextField getMachineField() {
        return machineField;
    }

    public JTextArea getBrakeDesc() {
        return brakeDesc;
    }

    public JTextField getTimeField() {
        return timeField;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JTextField getINField() {
        return INField;
    }

    public JComboBox<Object> getBoxMachine() {
        return boxMachine;
    }

    public JComboBox<String> getTimeBrake() {
        return timeBrake;
    }
}
