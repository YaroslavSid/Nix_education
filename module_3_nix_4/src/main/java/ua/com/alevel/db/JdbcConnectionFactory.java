package ua.com.alevel.db;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class JdbcConnectionFactory {

    @NonFinal
    static JdbcConnectionFactory connectionFactory;
    static String login;
    static String password;
    String url = "jdbc:mysql://localhost:3306/hibernate";
    Properties properties;

    public static void initialize(String dbLogin, String dbPassword) {
        login = dbLogin;
        password = dbPassword;
    }

    public static JdbcConnectionFactory getInstance() {
        if (login == null || password == null) {
            throw new RuntimeException("initialize method must be called before getInstance.");
        }

        if (connectionFactory == null) {
            JdbcConnectionFactory cf = new JdbcConnectionFactory();
            if (connectionFactory == null) {
                connectionFactory = cf;
            }
        }
        return connectionFactory;
    }

    private JdbcConnectionFactory() {
        properties = new Properties();
        properties.setProperty("user", login);
        properties.setProperty("password", password);
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
