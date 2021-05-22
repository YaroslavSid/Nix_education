package ua.com.alevel.domain;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Node implements Comparable<Node> {

    String name;
    private int index;
    private List<Edge> adjacencies = new ArrayList<>();
    private double minDistance = Double.POSITIVE_INFINITY;
    private Node previous;

    @Override
    public String toString() {
        return name;
    }

    public int compareTo(Node other) {
        return Double.compare(minDistance, other.minDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return index == node.index &&
                Objects.equals(name, node.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index);
    }

    public Node(String name, int index) {
        this.name = name;
        this.index = index;
    }

}
