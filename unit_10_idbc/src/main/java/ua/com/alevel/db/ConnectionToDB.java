package ua.com.alevel.db;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class ConnectionToDB {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "unit_10_idbc";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASS = "root";


    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(CONNECTION_STRING, USER, PASS);
        } catch (SQLException | ClassNotFoundException ex) {
            String message =
                    String.format("Fail to get MySQL connection to %s database. Details: ", DATABASE) + ex.getMessage();
            log.error(message);
            throw new RuntimeException(message);
        }
    }

}
