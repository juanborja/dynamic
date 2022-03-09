package com.company.algorithm;

import java.util.Comparator;

public class Node implements Comparator<Node> {

    // Member variables of this class
    public int node;
    public int cost;
    public String person;

    // Constructors of this class

    // Constructor 1
    public Node() {}

    // Constructor 2
    public Node(int node, int cost, String person)
    {

        // This keyword refers to current instance itself
        this.node = node;
        this.cost = cost;
        this.person = person;
    }

    // Method 1
    @Override public int compare(Node node1, Node node2)
    {

        if (node1.cost < node2.cost)
            return -1;

        if (node1.cost > node2.cost)
            return 1;

        return 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node=" + node +
                ", cost=" + cost +
                ", person='" + person + '\'' +
                '}';
    }
}
