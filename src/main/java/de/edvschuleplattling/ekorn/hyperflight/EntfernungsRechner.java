package de.edvschuleplattling.ekorn.hyperflight;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class EntfernungsRechner {

    public String[] cities;
    public int[][] distances;
    private String path;

    public EntfernungsRechner(String path) {
        this.path = path;
    }

    // Holt den Index von der Stadt
    public int getCityNr(String city) {
        return Arrays.stream(cities).toList().indexOf(city);
    }

    // Holt die Entfernung zwischen zwei Stationen
    public int getDistance(String city1, String city2) {
        int startNr = getCityNr(city1);
        int endNr = getCityNr(city2);
        return distances[startNr][endNr];
    }

    // Ladet die Daten aus einer csv Datei
    public void loadData() {
        try {
            FileReader fr = new FileReader(path);
            Scanner sc = new Scanner(fr);
            List<String> list = new ArrayList<>(Arrays.stream(sc.nextLine().split(";")).toList());
            list.remove(0);
            cities = new String[list.size()];
            for (int i = 0; i < cities.length; i++) {
                cities[i] = list.get(i);
                // System.out.print(cities[i] + ", ");
            }
            // System.out.println(" ");

            distances = new int[cities.length][cities.length];

            for (int z = 0; sc.hasNextLine(); z++) {
                String zeile = sc.nextLine();
                String[] spalten = zeile.split(";");
                for (int s = 1; s < spalten.length; s++) {
                    distances[z][s - 1] = Integer.parseInt(spalten[s]);
                    // System.out.print(distances[z][s - 1]+ ",");
                }
                // System.out.println(" ");
            }
        }
        catch (Throwable e) {
            throw new RuntimeException("Fehler beim Dateizugriff", e);
        }
    }

    // Prüft, ob ausgewählte Bahnhöfe existieren (wird aber derzeit nicht verwendet!)
    public boolean cityExist(String city) {
        boolean check = false;

        for (String s : cities) {
            if (Objects.equals(s, city)) {
                check = true;
                break;
            }
        }
        return check;
    }
}
