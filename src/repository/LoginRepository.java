package repository;

import connection.QQQ;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository {
    Connection connection = QQQ.getConnection();

    public boolean checkUser(String login, String password){
        boolean isLog = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM LOGPASSTABLE WHERE LOGIN = ?");
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            String pass = resultSet.getString("PASSWORD");
            if(pass == "" || password == ""){
                System.out.println("password is null");
            }

            try {
                if(pass.equals(password)){
                    isLog = true;
                }
            }catch (NullPointerException nullPointerException){
                JOptionPane.showConfirmDialog(null,"Incorrect login","Error",0);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isLog;
    }

    public int getStatusForLogin(String login){
        int goyda;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM LOGPASSTABLE WHERE LOGIN = ? ");
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            goyda = resultSet.getInt("STATUS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return goyda;
    }

    public List<String> getAllLogin(){
        List<String> logins = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM LOGPASSTABLE");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                logins.add(resultSet.getString("LOGIN"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return logins;
    }

    public String getPassByLogin(String login){
        String pass;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM LOGPASSTABLE WHERE LOGIN  = ?");
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            pass = resultSet.getString("PASSWORD");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pass;
    }
}
