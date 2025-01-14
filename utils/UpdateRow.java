package utils;

import models.Worker;
import repository.WorkerRepository;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UpdateRow {

    WorkerRepository repository = new WorkerRepository();

    public void updateRow(DefaultTableModel model){
        int countRow = model.getRowCount();
        for (int i = 0; i < countRow; i++) {
            model.removeRow(0);
        }
        List<Worker> workersToRow = repository.getAllInfo();
        for (int i = 0; i < workersToRow.size(); i++) {
            Worker thisWorker = workersToRow.get(i);
            model.addRow(new Object[]{
                    thisWorker.getTabelNumer(),
                    thisWorker.getLogin(),
                    thisWorker.getName(),
                    thisWorker.getSecName(),
                    thisWorker.getDischarge()});
        }
    }

}
