package org.playTheGame;

import GAME.Location;
import GAME.Map;

import java.util.Iterator;

/**
 * Class that tests the import and export of the map
 */
public class ImportExportTester {
    public static void main(String[] args) {


        try {
            Map map = new Map();
            map.generateRandomMap(15, false,0.5);
            map.printMap();
            System.out.println("\nExporting map to JSON...");
            map.exportMapToJson("src/main/java/data/map.json");
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("\nImporting map from JSON...");
        try {
            Map map2 = new Map();
            map2.importMapFromJson("src/main/java/data/map.json");
            map2.printMap();
            Iterator iterator = map2.getMap().iteratorShortestPath(map2.getMap().getVertices()[4], map2.getMap().getVertices()[9]);
            while (iterator.hasNext()) {
                System.out.println(map2.getMap().getIndex((Location) iterator.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
