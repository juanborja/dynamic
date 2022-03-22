package com.company;

import com.company.algorithm.Dijkstra;
import com.company.algorithm.FloydWarshall;
import com.company.algorithm.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.data.dataLoader.*;
import static com.sun.tools.attach.VirtualMachine.list;

public class Main {
    public final static int INF = 999999999;
    public static void main(String[] args) {

        // Generate sub-graphs from main graphs
        int[] graphsSizes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        generateFiles("/Users/juan/code/sp/src/com/company/data/graph.txt", graphsSizes);

        int sourceAux = 0;
        long[] dLoadTimes = new long[graphsSizes.length];
        long[] fwLoadTimes = new long[graphsSizes.length];
        long[] dTimes = new long[graphsSizes.length];
        long[] fwTimes = new long[graphsSizes.length];
        // Run both options for each graph
        for(int i=0; i<graphsSizes.length; i++){
            long t0 = System.currentTimeMillis();
            List<List<Node>> graph = loadGraphFromFile("/Users/juan/code/sp/src/com/company/data/generated/graph-"+graphsSizes[i],graphsSizes[i]);
            int V = getCityNumbers().size();
            sourceAux = findCityNumber("NEUQUEN");
            System.out.println(sourceAux);
            Dijkstra dpq = new Dijkstra(V);
            dpq.dijkstra(graph, sourceAux);
            System.out.println("The shorted path from node :"+graphsSizes[i]);
            String aux = "";
            long t1 = System.currentTimeMillis();
            System.out.println("-------- Non Dynamic Dijkstra -----------");
            for (int j = 0; j < dpq.dist.length; j++){
                aux = dpq.dist[j]+"";
                if (dpq.dist[j] == INF){
                    aux = "INF";
                }

                System.out.println(getCityByNumber(sourceAux) + " to " + getCityByNumber(j) + " is $"
                        + aux);
            }
            long t2 = System.currentTimeMillis();
            dLoadTimes[i] = t1-t0;
            dTimes[i] = t2-t1;
        }

        for(int i=0; i<graphsSizes.length; i++){
            long t0 = System.currentTimeMillis();
            List<List<Node>> graph = loadGraphFromFile("/Users/juan/code/sp/src/com/company/data/generated/graph-"+graphsSizes[i],graphsSizes[i] );
            int V = getCityNumbers().size();
            String aux = "";
            FloydWarshall fw = new FloydWarshall(graph, V);
            long t1 = System.currentTimeMillis();
            System.out.println("-------- Dynamic Floyd Warshall ---------"+graphsSizes[i]);
            for (int h = 0; h <V; h++){
                aux = fw.shortedPath(sourceAux,h)+"";

                System.out.println(getCityByNumber(sourceAux) + " to " + getCityByNumber(h) + " is $"
                        + aux);



            }
            long t2 = System.currentTimeMillis();
            fwLoadTimes[i] = t1 - t0;
            fwTimes[i] = t2 - t1;
        }

        System.out.println(Arrays.toString(fwTimes));
        System.out.println(Arrays.toString(fwLoadTimes));
        System.out.println(Arrays.toString(dTimes));
        System.out.println(Arrays.toString(dLoadTimes));

}}
