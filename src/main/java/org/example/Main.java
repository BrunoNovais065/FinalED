package org.example;

import GAME.Map;

public class Main {
    public static void main(String[] args) {

        // Map map = new Map();
        //map.generateRandomMap(5, true, 0.4);
        //map.printMap();

        //map.exportMapToJson("src/main/java/data/map.json");


        //Map map2 = new Map();
        /*
        Map map3 = new Map();
        map3.generateRandomMap(10, false, 1);
        map3.printMap();
        map3.exportMapToJson("src/main/java/data/map3.json");
        */

        Map map4 = new Map();
        map4.importMapFromJson("src/main/java/data/map3.json");
        map4.printMap();


    }
}