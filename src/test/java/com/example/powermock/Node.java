package com.example.powermock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node {
    private int num;
    private String name;

    public Node(String name) {
        this.name = name;
    }

    public static Node getStaticNode() {
        return new Node(1, "static node");
    }
}