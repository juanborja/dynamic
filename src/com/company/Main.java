package com.company;

import com.company.algorithm.Dijkstra;
import com.company.algorithm.FloydWarshall;
import com.company.algorithm.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.data.dataLoader.*;

public class Main {
    public final static int INF = 999999999;
    public static void main(String[] args) {
        List<List<Node>> auxG = loadGraphFromFile("/Users/juan/code/sp/src/com/company/data/graph.txt");
        /*String listString = auxG.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));

        System.out.println(listString);*/
        int V = getCityNumbers().size();
        //System.out.println(V);
        generateFiles("/Users/juan/code/sp/src/com/company/data/graph.txt", new int[]{3, 4, 5});
        //
        Dijkstra dpq = new Dijkstra(V);
        int sourceAux = findCityNumber("NEUQUEN");
        dpq.dijkstra(auxG, sourceAux);

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

            System.out.println(getCityByNumber(sourceAux) + " to " + getCityByNumber(i) + " is $"
                    + aux);
        }



        FloydWarshall fw = new FloydWarshall(auxG, V);
        //fw.printSolution();
        System.out.println("-------- Dynamic Floyd Warshall ---------");
        for (int i = 0; i < dpq.dist.length; i++){
            aux = fw.shortedPath(sourceAux,i)+"";

            System.out.println(getCityByNumber(sourceAux) + " to " + getCityByNumber(i) + " is $"
                    + aux);



    }


}}
