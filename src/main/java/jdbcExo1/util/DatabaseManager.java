package jdbcExo1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URI  = Env.getJdbcUrl();
    private static final String USERNAME = Env.USERNAME;
    private static final String PASSWORD = Env.PASSWORD;

    public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URI,USERNAME,PASSWORD);
    }
}
