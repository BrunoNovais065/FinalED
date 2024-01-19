package org.example;

import GAME.Map;

public class MainTesterRomulo {
    public static void main(String[] args) {

        /*
        try {
            Map map = new Map();
            map.generateRandomMap(2, true,2);
            map.printMap();
            System.out.println("\nExporting map to JSON...");
            map.exportMapToJson("src/main/java/data/map.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
         */



        System.out.println("\nImporting map from JSON...");
        try {
            Map map2 = new Map();
            map2.importMapFromJson("src/main/java/data/map.json");
            map2.printMap();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
