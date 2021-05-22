package ua.com.alevel.handler;


import ua.com.alevel.domain.Edge;
import ua.com.alevel.domain.Node;

import java.util.List;
import java.util.PriorityQueue;

public class GraphHandler {

    public void computePaths(List<Node> graph, Node source) {
        source.setMinDistance(0.);
        PriorityQueue<Node> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Node u = vertexQueue.poll();

            for (Edge e : u.getAdjacencies()) {
                int index = e.getTarget();
                Node node = getNodeByIndex(graph, index);
                double weight = e.getWeight();
                double distanceThroughU = u.getMinDistance() + weight;

                if (distanceThroughU < node.getMinDistance()) {
                    vertexQueue.remove(node);

                    node.setMinDistance(distanceThroughU);
                    node.setPrevious(u);
                    vertexQueue.add(node);
                }
            }
        }
    }

    public Node getNodeByIndex(List<Node> graph, int index) {
        Node result = null;
        for (Node node : graph) {
            if (node.getIndex() == index) {
                result = node;
            }
        }

        if (result == null) {
            throw new IllegalArgumentException("Node with " + index + " index not found.");
        }

        return result;
    }

}
