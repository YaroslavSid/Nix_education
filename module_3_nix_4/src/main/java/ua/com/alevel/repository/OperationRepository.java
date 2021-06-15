package ua.com.alevel.repository;

import ua.com.alevel.db.JdbcConnectionFactory;
import ua.com.alevel.model.Account;
import ua.com.alevel.model.Operation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OperationRepository {

    private static final String SELECT_OPERATIONS_BETWEEN_DATES = "SELECT * FROM operation WHERE account_id = ? AND timestamp > ? AND timestamp < ?";

    public List<Operation> findOperationBetweenDates(Account account, String from, String to) {

        List<Operation> incomeList = new ArrayList<>();
        try (PreparedStatement getListOperations = JdbcConnectionFactory.getInstance()
                .getConnection().prepareStatement(SELECT_OPERATIONS_BETWEEN_DATES)) {

            getListOperations.setLong(1, account.getId());
            getListOperations.setTimestamp(2, Timestamp.valueOf(from));
            getListOperations.setTimestamp(3, Timestamp.valueOf(to));

            ResultSet resultSet = getListOperations.executeQuery();

            while (resultSet.next()) {
                Operation operation = new Operation();

                int amount = resultSet.getInt("amount");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");

                operation.setAmount(amount);
                operation.setAccount(account);
                operation.setTimestamp(timestamp.toInstant());

                incomeList.add(operation);
            }

            return incomeList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
