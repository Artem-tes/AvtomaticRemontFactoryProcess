package repository;

import connection.QQQ;
import models.Equipment;
import org.sqlite.jdbc4.JDBC4PreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddEquipmentRepository {
    Connection connection = QQQ.getConnection();

    public List<Equipment> getAllEquipment(){
        List<Equipment> equipments = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EQUIPMENT");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Equipment equipment = new Equipment();
                equipment.setIN(resultSet.getString("INNUMBER"));
                equipment.setName(resultSet.getString("NAME"));
                equipments.add(equipment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipments;
    }

    public void setEquipment(String IN, String name){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO EQUIPMENT(INNUMBER , NAME) VALUES (? , ?)");
            preparedStatement.setString(1,IN);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllIN(){
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EQUIPMENT");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString("INNUMBER"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void deleteRow(String IN){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM EQUIPMENT WHERE INNUMBER = ?");
            preparedStatement.setString(1,IN);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getAllNameEquipment(){
        Set<String> strings = new HashSet<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM EQUIPMENT");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                strings.add(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return strings;
    }

    public String getNameForIN(String IN){
        String name;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM EQUIPMENT WHERE INNUMBER = ?");
            preparedStatement.setString(1,IN);
            ResultSet resultSet = preparedStatement.executeQuery();
            name = resultSet.getString("NAME");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}
