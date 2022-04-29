package com.company;

import com.company.algorithms.Dijkstra;
import com.company.algorithms.FloydWarshall;
import com.company.algorithms.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.data.dataLoader.*;

public class Main {
    public final static int INF = 999999999;
    public static void main(String[] args) {

        // Generate sub-graphs from main graphs
        long[] graphsSizes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 1000, 5000, 6000, 7000, 8000, 9000, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 200000, 300000, 400000, 500000, 1000000};
        generateRandomConnections(1000000, "/Users/juan/code/sp/src/com/company/data/graph.txt");
        generateFiles("/Users/juan/code/sp/src/com/company/data/generated/graph-base", graphsSizes);

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
            long t1 = System.currentTimeMillis();
            dpq.dijkstra(graph, sourceAux);
            System.out.println("The shorted path from node :"+graphsSizes[i]);
            String aux = "";
            System.out.println("-------- Non Dynamic Dijkstra -----------"+graphsSizes[i]);
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
        List<long[]> ys = new ArrayList<>();
        ys.add(fwTimes);
        ys.add(dTimes);
        String[] titles = {"Floyd Warshall", "Dijkstra" };
        Grafica ex = new Grafica(graphsSizes,ys, "Tiempo de consulta", titles);
        ex.setVisible(true);

        List<long[]> ys1 = new ArrayList<>();
        ys1.add(fwLoadTimes);
        ys1.add(dLoadTimes);
        Grafica ex1 = new Grafica(graphsSizes,ys1, "Tiempo de carga", titles);
        ex1.setVisible(true);
}}
