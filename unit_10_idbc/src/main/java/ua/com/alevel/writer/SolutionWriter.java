package ua.com.alevel.writer;

import ua.com.alevel.domain.Node;
import ua.com.alevel.handler.GraphHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SolutionWriter {

    public final String SELECT_PROBLEM_BY_FROM_ID_AND_TO_ID = "SELECT id FROM problem WHERE from_id = ? AND to_id = ?";
    public final String INSERT_SOLUTION = "INSERT INTO solution (problem_id, cost) VALUES (?, ?)";
    private final Connection connection;

    public SolutionWriter(Connection connection) {
        this.connection = connection;
    }

    public void writeSolution(List<Node> graph, Map<Node, List<Node>> problems) {
        for (Node location : problems.keySet()) {
            GraphHandler graphHandler = new GraphHandler();
            graphHandler.computePaths(graph, location);
            List<Node> currentTargetList = problems.get(location);

            for (Node target : currentTargetList) {
                try (PreparedStatement statement1 = connection.prepareStatement(SELECT_PROBLEM_BY_FROM_ID_AND_TO_ID);
                     PreparedStatement statement2 = connection.prepareStatement(INSERT_SOLUTION)) {

                    statement1.setInt(1, location.getIndex());
                    statement1.setInt(2, target.getIndex());
                    ResultSet rs = statement1.executeQuery();
                    int problemId = -1;
                    while (rs.next()) {
                        problemId = rs.getInt("id");
                    }
                    if (problemId == -1) {
                        throw new IllegalArgumentException("incorrect id from problem table.");
                    }

                    statement2.setInt(1, problemId);
                    statement2.setInt(2, (int) target.getMinDistance());
                    statement2.execute();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }

        }

    }

}
