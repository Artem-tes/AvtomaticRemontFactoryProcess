package repository;

import connection.QQQ;
import models.Requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrakesRepository {
    Connection connection = QQQ.getConnection();

    public void addBrakeMachine(String IN,String name,String description,String time){
        int nol = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO EQUIPMENTBRAKES(INN,NAME,TIME,DESCRIPTION,STATUSREQUEST) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,IN);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,time);
            preparedStatement.setString(4,description);
            preparedStatement.setInt(5,nol);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllINN(){
        List<String> strings = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EQUIPMENTBRAKES");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                strings.add(resultSet.getString("INN"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return strings;
    }

    public List<Requests> getAllRequests(){
        List<Requests> requests = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM EQUIPMENTBRAKES WHERE STATUSREQUEST = 0");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Requests request = new Requests();
                request.setID(resultSet.getInt("ID"));
                request.setINN(resultSet.getString("INN"));
                request.setName(resultSet.getString("NAME"));
                request.setTimeBrake(resultSet.getString("TIME"));
                requests.add(request);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requests;
    }

    public void updateStatus(int status, int ID){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE EQUIPMENTBRAKES SET STATUSREQUEST = ? " +
                            "WHERE ID = ? ");

            preparedStatement.setInt(1,status);
            preparedStatement.setInt(2,ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDescByID(int ID){
        String name;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM EQUIPMENTBRAKES WHERE ID = ?");
            preparedStatement.setInt(1,ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            name = resultSet.getString("DESCRIPTION");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return name;

    }

    public List<Requests> getAllByID(int ID){
        List<Requests> requests = new ArrayList<>();
        try {
            PreparedStatement preparedStatement  = connection.prepareStatement("" +
                    "SELECT * FROM EQUIPMENTBRAKES WHERE ID = ?");
            preparedStatement.setInt(1,ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Requests request = new Requests();
                request.setID(resultSet.getInt("ID"));
                request.setINN(resultSet.getString("INN"));
                request.setName(resultSet.getString("NAME"));
                request.setTimeBrake(resultSet.getString("TIME"));
                request.setStatus(resultSet.getInt("STATUSREQUEST"));
                requests.add(request);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requests;
    }

    public int getStatusById(int ID){
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM EQUIPMENTBRAKES WHERE ID = ?");
            preparedStatement.setInt(1,ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            status = resultSet.getInt("STATUSREQUEST");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public List<Requests> getAllByIDStatus(int ID){
        List<Requests> requests = new ArrayList<>();
        try {
            PreparedStatement preparedStatement  = connection.prepareStatement("" +
                    "SELECT * FROM EQUIPMENTBRAKES WHERE ID = ? AND STATUSREQUEST  = 2");
            preparedStatement.setInt(1,ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Requests request = new Requests();
                request.setID(resultSet.getInt("ID"));
                request.setINN(resultSet.getString("INN"));
                request.setName(resultSet.getString("NAME"));
                request.setTimeBrake(resultSet.getString("TIME"));
                request.setStatus(resultSet.getInt("STATUSREQUEST"));
                requests.add(request);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requests;
    }


}
