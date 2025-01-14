package vision.panels;

import javax.swing.*;

public class WorkerSouthPanel extends JPanel {
    WorkerPanel workerPanel = new WorkerPanel("d");
    JButton addButton = workerPanel.getAddButton();
    JButton deleteButton = workerPanel.getDeleteButton();
    JButton updateButton = workerPanel.getUpdateButton();
    JButton queButton = workerPanel.getQueButton();

    public WorkerSouthPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        if(MainMenuFrame.getSTATUS() == 1){
            add(addButton);
            add(deleteButton);
        }
        add(updateButton);
        add(queButton);
    }
}
