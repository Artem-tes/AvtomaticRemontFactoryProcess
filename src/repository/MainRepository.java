package repository;

import connection.QQQ;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MainRepository {
    Connection connection = QQQ.getConnection();
    public MainRepository(){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS LOGPASSTABLE (LOGIN TEXT , PASSWORD TEXT, STATUS INTEGER)");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS WORKERDB(TABELNUMBER INTEGER,LOGIN TEXT,NAME TEXT,SECNAME TEXT,DISCHARGE INT,WORKSTATUS INT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS EQUIPMENT(INNUMBER TEXT , NAME TEXT)");
            statement.executeUpdate("" +
                    "CREATE TABLE IF NOT EXISTS EQUIPMENTBRAKES(ID INTEGER PRIMARY KEY AUTOINCREMENT ,INN TEXT,NAME TEXT,TIME TEXT,DESCRIPTION TEXT,STATUSREQUEST INT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS RUNNINGREQUESTS(TN INT, ID INT,INN TEXT,LOGIN TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS WORKERSTASK(TN INT,COUNTTASK INT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
