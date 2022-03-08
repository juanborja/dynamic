package com.company.algorithms;

import java.util.Comparator;

public class Node implements Comparator<Node> {


    public int cost;
    public String city;
    public String person;
    // Constructors of this class

    // Constructor 1
    public Node() {}




    public Node( String city,  int cost, String person )
    {


        this.cost = cost;
        this.city = city;
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
}
