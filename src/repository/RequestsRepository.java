package repository;

import connection.QQQ;
import models.Requests;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestsRepository {
    Connection connection = QQQ.getConnection();

    public void addRequests(int TN,int ID,String login,String INN){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO RUNNINGREQUESTS(TN,ID,LOGIN,INN) VALUES (?,?,?,?)");
            preparedStatement.setInt(1,TN);
            preparedStatement.setInt(2,ID);
            preparedStatement.setString(3,login);
            preparedStatement.setString(4,INN);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getAllRequestsByTN(int TN){
        List<Integer> integers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM RUNNINGREQUESTS WHERE TN = ?");
            preparedStatement.setInt(1,TN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                integers.add(resultSet.getInt("ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return integers;
    }

    public List<Integer> getIDByLogin(String login){
        List<Integer> hashMap = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM RUNNINGREQUESTS WHERE LOGIN  = ?");
            preparedStatement.setString(1,login);
            ResultSet r = preparedStatement.executeQuery();
            while (r.next()){
                hashMap.add(r.getInt("ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hashMap;
    }

    public int getTNByID(int ID){
        int TN = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM RUNNINGREQUESTS WHERE ID = ?");
            preparedStatement.setInt(1,ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            TN = resultSet.getInt("TN");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return TN;
    }

    public int getCountNotCompleteTask(int TN){
        int countTask = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM RUNNINGREQUESTS WHERE TN = ?");
            preparedStatement.setInt(1,TN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                countTask++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countTask;
    }

}
