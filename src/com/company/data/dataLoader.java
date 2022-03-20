package com.company.data;

import com.company.algorithm.Node;

import java.io.*;
import java.util.*;


public class dataLoader {
    public static Set<String> cityNumbers = new HashSet<>();
    public static void generateFiles(String filePath, int[] sizes){
        File dir = new File("/Users/juan/code/sp/src/com/company/data/generated/");
        for(File file: dir.listFiles())
            if (!file.isDirectory())
                file.delete();
        for(int i=0; i< sizes.length; i++){
        File file = new File(filePath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st;
        boolean finish = false;
        int lines =0;
        File graphAux = new File("/Users/juan/code/sp/src/com/company/data/generated/graph-"+sizes[i]);
        while (!finish && lines<sizes[i])
        // Print the string
        {
            try {
                if (!((st = br.readLine()) != null)) finish = true;
                if(st!= null){
                    BufferedWriter out = new BufferedWriter(
                            new FileWriter(graphAux, true));
                    out.write(st+"\n");
                    out.close();
                }
            }
            catch(Exception e){

            }
        lines++;
        }

        }

    }
    private static void loadCityNumbers(String filePath){
        File file = new File(filePath);
        BufferedReader br = null;
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
        while (!finish)
        // Print the string
        {
            try {
                if (!((st = br.readLine()) != null)) finish = true;
                if(st!=null){
                    String[] values = st.split(",") ;
                    try {
                        String cityFrom = values[0].trim().toUpperCase();
                        String cityTo = values[1].trim().toUpperCase();
                        // Set do not allow duplications if is already present nothing happend
                        cityNumbers.add(cityFrom);
                        cityNumbers.add(cityTo);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public static List<List<Node>> loadGraphFromFile (String filePath){
        loadCityNumbers(filePath);
        File file = new File(filePath);
        BufferedReader br = null;
        List<List<Node>> graph
                = new ArrayList<List<Node> >();
        for (int i = 0; i < 30; i++) {
            List<Node> item = new ArrayList<Node>();
            graph.add(item);
        }
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
        while (!finish)
        // Print the string
        {
            try {
                if (!((st = br.readLine()) != null)) finish = true;
                // System.out.println(st);
                if(st!=null){
                    String[] values = st.split(",") ;
                    try {
                        String cityFrom = values[0].trim().toUpperCase();
                        String cityTo = values[1].trim().toUpperCase();
                        List<Node> aux = graph.get(findCityNumber(cityFrom));
                        if(aux==null){
                            List<Node> item = new ArrayList<Node>();
                            item.add(new Node(findCityNumber(cityTo), Integer.parseInt(values[2].trim()), values[3]));
                            graph.add(graph.size(), item);
                        }
                        else{
                            aux.add(new Node(findCityNumber(cityTo), Integer.parseInt(values[2].trim()), values[3]));
                        }

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return graph;

    }

    public static int findCityNumber(String city){
        int result = 0;

        for (Object entry:cityNumbers) {
            if (entry.equals(city)) return result;
            result++;
        }
        return -1;

    }

    public static String getCityByNumber (int i){
         List<String> cityList = new ArrayList<>(cityNumbers);
         try{
             return cityList.get(i);
         }
         catch(Exception e){
             return "INF";
         }

    }

    public static Set<String> getCityNumbers() {
        return cityNumbers;
    }
}
