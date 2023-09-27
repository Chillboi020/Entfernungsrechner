package de.edvschuleplattling.ekorn.hyperflight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class EntfernungsRechner {

    // Entfernungen und Bahnhöfe hardcodiert
    public static final String[] CITIES = new String[] {
            "Berlin", "Dresden", "Erfurt", "Essen", "Frankfurt",
            "Hamburg", "Hannover", "München", "Plattling", "Stuttgart"
    };
    public static final int[][] DISTANCES = new int[][] {
            {0, 178, 326, 0, 0, 320, 0, 0, 0, 0}, // Berlin
            {178, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Dresden
            {326, 0, 0, 312, 263, 382, 0, 329, 0, 344}, // Erfurt
            {0, 0, 312, 0, 0, 0, 194, 0, 0, 0}, // Essen
            {0, 0, 263, 0, 0, 0, 0, 0, 0, 179}, // Frankfurt
            {320, 0, 382, 0, 0, 0, 0, 0, 0, 0}, // Hamburg
            {0, 0, 0, 194, 0, 0, 0, 0, 0, 0}, // Hannover
            {0, 0, 329, 0, 0, 0, 0, 0, 130, 0}, // München
            {0, 0, 0, 0, 0, 0, 0, 130, 0, 0}, // Plattling
            {0, 0, 344, 0, 179, 0, 0, 0, 0, 0} // Stuttgart
    };

    // Prüft, ob eingegebene Bahnhöfe existieren
    public static boolean cityExist(String city) {
        boolean check = false;

        for (String s : CITIES) {
            if (Objects.equals(s, city)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static int getCityNr(String city) {
        return Arrays.stream(CITIES).toList().indexOf(city);
    }

    public static int getDistance(String city1, String city2) {
        int startNr = getCityNr(city1);
        int endNr = getCityNr(city2);
        return DISTANCES[startNr][endNr];
    }

    public static int[][] getDistances() {
        int[][] distances = new int[0][0];
        try {
            FileReader fr = new FileReader("stationen.csv");
            Scanner sc = new Scanner(fr);

            sc.nextLine();
            while (sc.hasNextLine()) {
                String zeile = sc.nextLine();
                String[] spalten = zeile.split(";");
                for (int i = 0; i < spalten.length; i++) {

                }
            }
        }
        catch (Throwable e) {
            throw new RuntimeException("Fehler beim Dateizugriff", e);
        }
        return distances;
    }

    public static String[] getCities() {
        return CITIES;
    }
}
