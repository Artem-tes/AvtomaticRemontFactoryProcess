package repository;

import connection.QQQ;
import models.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerRepository {
    Connection connection = QQQ.getConnection();

    public void setWorker(int TableNumber,String login,String name,String secondName,int discharge){
        int nul = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO WORKERDB (TABELNUMBER,LOGIN,NAME,SECNAME,DISCHARGE,WORKSTATUS) VALUES (? , ? , ? , ? , ?, ?)");
            preparedStatement.setInt(1,TableNumber);
            preparedStatement.setString(2,login);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,secondName);
            preparedStatement.setInt(5,discharge);
            preparedStatement.setInt(6,nul);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Worker> getAllInfo(){
        List<Worker> workers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM WORKERDB");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Worker worker = new Worker();
                worker.setTabelNumer(resultSet.getInt("TABELNUMBER"));
                worker.setLogin(resultSet.getString("LOGIN"));
                worker.setName(resultSet.getString("NAME"));
                worker.setSecName(resultSet.getString("SECNAME"));
                worker.setDischarge(resultSet.getInt("DISCHARGE"));
                workers.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }

    public List<Worker> getAllInfoStatus(){
        List<Worker> workers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM WORKERDB WHERE WORKSTATUS = 0");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Worker worker = new Worker();
                worker.setTabelNumer(resultSet.getInt("TABELNUMBER"));
                worker.setLogin(resultSet.getString("LOGIN"));
                worker.setName(resultSet.getString("NAME"));
                worker.setSecName(resultSet.getString("SECNAME"));
                worker.setDischarge(resultSet.getInt("DISCHARGE"));
                workers.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }

    public List<String> getAllLoginsForWorker(){
        List<String> logins = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WORKERDB");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                logins.add(resultSet.getString("LOGIN"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return logins;
    }

    public List<Integer> getAllTNumbers(){
        List<Integer> integers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WORKERDB");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                integers.add(resultSet.getInt("TABELNUMBER"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return integers;
    }

    public int getTNForLogin(String login){
        int TN = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM WORKERDB WHERE LOGIN = ?");
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            TN = resultSet.getInt("TABELNUMBER");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return TN;
    }

    public void deleteWorkerForTN(int TN){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM WORKERDB WHERE TABELNUMBER = ?");
            preparedStatement.setInt(1,TN);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatusWorker(int TN,int STATUS){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE WORKERDB " +
                    "SET WORKSTATUS = ? " +
                    "WHERE TABELNUMBER = ?");
            preparedStatement.setInt(1,STATUS);
            preparedStatement.setInt(2,TN);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCountWorker(){
        int counter = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WORKERDB");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                counter++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counter;
    }

    public StringBuilder getAllName(int TN){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            PreparedStatement p = connection.prepareStatement("SELECT * FROM WORKERDB WHERE TABELNUMBER = ?");
            p.setInt(1,TN);
            ResultSet resultSet =p.executeQuery();
            resultSet.next();
            stringBuilder.append(resultSet.getString("NAME"));
            stringBuilder.append(' ');
            stringBuilder.append(resultSet.getString("SECNAME"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder;
    }

    public int getDischarge(int TN){
        int discharge = 0;
        try {
            PreparedStatement p = connection.prepareStatement("SELECT * FROM WORKERDB WHERE TABELNUMBER = ?");
            p.setInt(1,TN);
            ResultSet resultSet = p.executeQuery();
            resultSet.next();
            discharge = resultSet.getInt("DISCHARGE");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return discharge;
    }

    public void setLogPass(String login,String pass,int status){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO LOGPASSTABLE(LOGIN, PASSWORD, STATUS) VALUES (?,?,?)");
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,pass);
            preparedStatement.setInt(3, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
