package com.company;

import com.company.algoritmos.Dijkstra;
import com.company.algoritmos.FloydWarshall;
import com.company.algoritmos.Node;

import java.util.ArrayList;
import java.util.List;

import static com.company.algoritmos.FloydWarshall.INF;

public class Main {

    public static void main(String[] args) {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges by declaring List class object
        // Declaring object of type List<Node>
        List<List<Node>> adj
                = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the GFG(dpq) graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculating the single source shortest path
        Dijkstra dpq = new Dijkstra(V);
        dpq.dijkstra(adj, source);

        // Printing the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");

        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);

        int graph[][] = {
                {0,   5,  INF, 10},
                {INF, 0,   3, INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
        };

        List<List<Node>> graph2
                = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < 4; i++) {
            List<Node> item = new ArrayList<Node>();
            graph2.add(item);
        }

        // Inputs for the GFG(dpq) graph
        graph2.get(0).add(new Node(1, 5));
        graph2.get(0).add(new Node(3, 10));
        graph2.get(1).add(new Node(2, 3));
        graph2.get(2).add(new Node(3, 1));


        FloydWarshall fw = new FloydWarshall();
        fw.floydWarshall(graph2);
    }
}
