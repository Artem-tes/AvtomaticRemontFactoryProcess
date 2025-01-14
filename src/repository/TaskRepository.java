package repository;

import connection.QQQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRepository {

    Connection connection = QQQ.getConnection();

    public void setWorker(int TN){
        int nul = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO WORKERSTASK (TN,COUNTTASK) VALUES (?,?)");
            preparedStatement.setInt(1,TN);
            preparedStatement.setInt(2,nul);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateCountTasks(int TN,int countTask){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE WORKERSTASK SET COUNTTASK = ? WHERE TN = ?");
            preparedStatement.setInt(1,countTask);
            preparedStatement.setInt(2,TN);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCountTask(int TN){
        int countTask = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WORKERSTASK WHERE TN = ?");
            preparedStatement.setInt(1,TN);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            countTask = resultSet.getInt("COUNTTASK");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return countTask;
    }

    public int getCountTaskByAll(){
        int countTask = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WORKERSTASK");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                countTask+=resultSet.getInt("COUNTTASK");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return countTask;
    }

}
