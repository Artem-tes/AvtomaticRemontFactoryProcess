package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class QQQ {

    private static final java.sql.Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:FactoryDB.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static java.sql.Connection getConnection() {
        return connection;
    }

}
