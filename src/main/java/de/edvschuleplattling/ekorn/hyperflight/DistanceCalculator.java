package de.edvschuleplattling.ekorn.hyperflight;

import java.util.*;
import java.io.FileReader;
import java.util.ArrayList;

public class DistanceCalculator {

    // Properties
    private String[] cities;
    private int[][] distances;
    private int[][] predecessor;
    private int[][] shortestDistances;
    private String path;

    // Constructor
    public DistanceCalculator(String path) {
        this.path = path;
    }

    // Gets the Index from the city
    public int getCityNr(String city) {
        return Arrays.stream(cities).toList().indexOf(city);
    }

    // Gets the String Array with all cities
    public String[] getCities() {
        return cities;
    }

    // Gets the distance between two cities
    public int getDistance(String city1, String city2) {
        int startNr = getCityNr(city1);
        int endNr = getCityNr(city2);
        return distances[startNr][endNr];
    }

    // Loads the Data from the file given in the path
    public void loadData() {
        try {
            FileReader fr = new FileReader(path);
            Scanner sc = new Scanner(fr);
            List<String> list = new ArrayList<>(Arrays.stream(sc.nextLine().split(";")).toList());
            list.remove(0);
            cities = new String[list.size()];
            for (int i = 0; i < cities.length; i++) {
                cities[i] = list.get(i);
            }

            distances = new int[cities.length][cities.length];
            predecessor = new int[cities.length][cities.length];
            shortestDistances = new int[cities.length][cities.length];

            for (int z = 0; sc.hasNextLine(); z++) {
                String zeile = sc.nextLine();
                String[] spalten = zeile.split(";");
                for (int s = 1; s < spalten.length; s++) {
                    distances[z][s - 1] = Integer.parseInt(spalten[s]);
                }
            }
        }
        catch (Throwable e) {
            throw new RuntimeException("Error while getting data", e);
        }
    }

    // Checks, if the chosen stations exist (currently not used)
    /*public boolean cityExist(String city) {
        boolean check = false;

        for (String s : cities) {
            if (Objects.equals(s, city)) {
                check = true;
                break;
            }
        }
        return check;
    }*/

    // The algorithm for the path calculating
    public void calculateShortestPaths() {
        if (distances == null) return;

        int i, j, k, v = distances.length;

        // Predecessor gets filled with values or -1
        for (i = 0; i < v; i++) {
            for (j = 0; j < v; j++) {
                if (distances[i][j] != Integer.MAX_VALUE && distances[i][j] != 0) {
                    predecessor[i][j] = i;
                } else {
                    predecessor[i][j] = -1;
                }
            }
        }

        // Floyd-Warshall-Algorithm
        for (i = 0; i < v; i++) {
            for (j = 0; j < v; j++) {
                if (distances[i][j] == -1) {
                    shortestDistances[i][j] = Integer.MAX_VALUE;
                } else {
                    shortestDistances[i][j] = distances[i][j];
                }
            }
        }

        for (k = 0; k < v; k++) {
            for (i = 0; i < v; i++) {
                for (j = 0; j < v; j++) {
                    if (shortestDistances[i][k] < Integer.MAX_VALUE && shortestDistances[k][j] < Integer.MAX_VALUE) {
                        int possibleShortestPath = shortestDistances[i][k] + shortestDistances[k][j];
                        if (possibleShortestPath < shortestDistances[i][j]) {
                            shortestDistances[i][j] = possibleShortestPath;
                            predecessor[i][j] = predecessor[k][j];
                        }
                    }
                }
            }
        }

        // Matrix has to be updated
        for (i = 0; i < v; i++) {
            for (j = 0; j < v; j++) {
                if (shortestDistances[i][j] != Integer.MAX_VALUE) {
                    distances[i][j] = shortestDistances[i][j];
                } else {
                    distances[i][j] = -1;
                }
            }
        }
    }
}
