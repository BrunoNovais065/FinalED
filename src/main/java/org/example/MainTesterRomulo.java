package org.example;

import Colecoes.Graphs.Network;
import DataPersistence.importMapFromJson;
import GAME.Location;
import GAME.Map;

public class MainTesterRomulo {
    public static void main(String[] args) {


        //generate map
        /*
        Map map = new Map();
        map.generateRandomMap(10, true, 10);
        map.printMap();
*/
        //export map
        //map.exportMapToJson("src/main/java/data/map.json");

        //import map
        Map map2 = new Map();
        map2.importMapFromJson("src/main/java/data/map.json");
        map2.printMap();



    }
}
