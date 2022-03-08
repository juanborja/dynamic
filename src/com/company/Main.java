package com.company;

import com.company.algorithms.Dijkstra;
import com.company.algorithms.FloydWarshall;
import com.company.algorithms.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public final static int INF = 999999999;

    public static List<List<Node>> loadGraphFromFile (String filePath){
        File file = new File(filePath);
        BufferedReader br = null;
        List<List<Node>> graph
                = new ArrayList<List<Node> >();
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        boolean finish = false;
        int id =0;
        while (!finish)
            // Print the string
        {
            try {
                if (!((st = br.readLine()) != null)) finish = true;
                System.out.println(st);
                String[] values = st.split(",") ;
                try {
                    graph.get(Integer.parseInt(values[0])).add(new Node(Integer.parseInt(values[1]), Integer.parseInt(values[4]), values[5]));
                }
                catch (Exception e){
                    System.out.println("error");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
    public static void main(String[] args) {
        int V = 5;
        int source = 0;

        HashMap<String, List<Node>> graph
                = new HashMap<String, List<Node> >();

        addToGraph(graph, "0",new Node("1",5, "Richard"));
        addToGraph(graph, "0",new Node("3",10, "Richard"));
        addToGraph(graph, "1",new Node("2",3, "Richard"));
        addToGraph(graph, "2",new Node("3",1, "Richard"));

        Dijkstra dpq = new Dijkstra(V);
        dpq.dijkstra(graph, source);

        // Printing the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        String aux = "";
        System.out.println("-------- Non Dynamic Dijkstra -----------");
        for (int i = 0; i < dpq.dist.length; i++){
            aux = dpq.dist[i]+"";
            if (dpq.dist[i] == INF){
                aux = "INF";
            }
            System.out.println(source + " to " + i + " is "
                    + aux);
        }


        loadGraphFromFile("/Users/juan/code/sp/src/com/company/data/graph.txt");
        FloydWarshall fw = new FloydWarshall(graph, V);
        //fw.printSolution();
        System.out.println("-------- Dynamic Floyd Warshall ---------");
        for (int i = 0; i < dpq.dist.length; i++){
            aux = fw.shortedPath(source,i)+"";
            if(fw.shortedPath(source,i) == INF){
                aux = "INF";
            }
            System.out.println(source + " to " + i + " is "
                    + aux);}
    }

    public static void addToGraph(HashMap<String, List<Node>> graph, String city, Node n){
        List<Node> aux = graph.get(city);
        if(aux == null){
            List<Node> listAux= new ArrayList<Node>();
            listAux.add(n);
            graph.put(city, listAux);
        }
        else {
            aux.add(n);
        }
    }
}
