package ua.com.alevel.reader;

import ua.com.alevel.domain.Edge;
import ua.com.alevel.domain.Node;
import ua.com.alevel.exception.NodeNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseReader {

    private final Connection connection;
    private  List<Node> graph;

    public DataBaseReader(Connection connection) {
        this.connection = connection;
    }

    public List<Node> readGraph() {
        graph = readLocations();
        setEdges();
        return graph;
    }

    private List<Node> readLocations() {
        List<Node> result = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, name FROM location");

            while (rs.next()) {
                String name = rs.getString("name");
                int index = rs.getInt("id");
                result.add(new Node(name, index));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    private  void setEdges() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT from_id, to_id, cost FROM route");

            while (rs.next()) {
                Node from = getNode(rs.getInt("from_id"));
                Node to = getNode(rs.getInt("to_id"));
                int cost = rs.getInt("cost");
                setEdges(from, to, cost);
                setEdges(to, from, cost);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private  Node getNode(int index) {
        for (Node node : graph) {
            if (index == node.getIndex()) {
                return node;
            }
        }

        String message = String.format("Node with index %s not found.", index);
        throw new NodeNotFoundException(message);
    }

    private void setEdges(Node from, Node to, int cost) {
        List<Edge> edges = from.getAdjacencies();
        edges.add(new Edge(to.getIndex(), cost));
    }

    public Map<Node, List<Node>> readProblems() {
        Map<Node, List<Node>> result = new HashMap<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT from_id, to_id FROM problem");
            while (rs.next()) {
                Node from = getNode(rs.getInt("from_id"));
                Node to = getNode(rs.getInt("to_id"));
                if (result.containsKey(from)) {
                    result.get(from).add(to);
                } else {
                    List<Node> list = new ArrayList<>();
                    list.add(to);
                    result.put(from, list);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

}