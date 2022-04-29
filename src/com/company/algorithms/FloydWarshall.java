package com.company.algorithms;

import java.util.List;

public class FloydWarshall {
    public final static int INF = 999999999;
    private int solution[][] = null;
    public FloydWarshall(List<List<Node>> graph, int V)
    {
        int dist[][] = new int[V][V];
        int i, j, k;

        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j]= INF;

        /* Initialize the solution matrix
           same as input graph matrix.
           Or we can say the initial values
           of shortest distances
           are based on shortest paths
           considering no intermediate
           vertex. */
        int ix=0;
        for (List<Node> list : graph) {

            for(Node nod: list){
                if(dist[ix][nod.node] > nod.cost){
                    dist[ix][nod.node] = nod.cost;
                }

            }
            ix++;
        }




        /* Add all vertices one by one
           to the set of intermediate
           vertices.
          ---> Before start of an iteration,
               we have shortest
               distances between all pairs
               of vertices such that
               the shortest distances consider
               only the vertices in
               set {0, 1, 2, .. k-1} as
               intermediate vertices.
          ----> After the end of an iteration,
                vertex no. k is added
                to the set of intermediate
                vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }


        this.solution = dist;
    }
    public void printSolution()
    {

        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<this.solution.length; ++i)
        {
            for (int j=0; j<this.solution.length; ++j)
            {
                if (this.solution[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(this.solution[i][j]+"   ");
            }
            System.out.println();
        }
    }

    public int shortedPath(int x, int y) {
        try {
            return this.solution[x][y];
        }
        catch(Exception e){
            return INF;
        }

    }
}
