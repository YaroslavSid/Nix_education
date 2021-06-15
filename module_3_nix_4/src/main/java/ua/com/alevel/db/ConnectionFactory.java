package ua.com.alevel.db;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.model.Account;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Operation;
import ua.com.alevel.model.User;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ConnectionFactory {

    @NonFinal
    static ConnectionFactory connectionFactory;

    static String login;
    static String password;

    @Getter
    SessionFactory factory;

    public static void initialize(String dbLogin, String dbPassword) {
        login = dbLogin;
        password = dbPassword;
    }

    public static ConnectionFactory getInstance() {
        if (login == null || password == null) {
            throw new RuntimeException("initialize method must be called before getInstance.");
        }

        if (connectionFactory == null) {
            ConnectionFactory cf = new ConnectionFactory();
            if (connectionFactory == null) {
                connectionFactory = cf;
            }
        }
        return connectionFactory;
    }

    private ConnectionFactory() {
        factory = new Configuration()
                .configure()
                .setProperty("hibernate.connection.username", login)
                .setProperty("hibernate.connection.password", password)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Operation.class)
                .addAnnotatedClass(Category.class)
                .buildSessionFactory();
    }

}
