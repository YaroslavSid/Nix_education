package ua.com.alevel.domain;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Edge {
    private int target;
    private double weight;

}
