package org.example;

import Colecoes.Lists.ArrayUnorderedList;
import GAME.Location;
import GAME.Map;
import GAME.Menu;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

         Map map = new Map();
        map.generateRandomMap(10, false, 0.5);
        map.printMap();


    for (int i = 0; i < map.getMap().getVertices().length; i++) {
    }
    Iterator iterator = map.getMap().iteratorShortestPath(map.getMap().getVertices()[0], map.getMap().getVertices()[4]);


    while (iterator.hasNext()) {
         System.out.println(map.getMap().getIndex((Location) iterator.next()));
    }


/*
    Iterator iterator1 = map.getMap().iteratorVerticesWithHighestWeight(map.getMap().getVertices()[12], map.getMap().getVertices()[0]);

    while (iterator1.hasNext()) {
        System.out.println(map.getMap().getIndex((Location) iterator1.next()));
    }
*/

/*
    Iterator iterator2 = map.getMap().iteratorVerticesWithSmallestWeight(map.getMap().getVertices()[12], map.getMap().getVertices()[0]);

    while (iterator2.hasNext()) {
        System.out.println(map.getMap().getIndex((Location) iterator2.next()));
    }
*/

        //Menu menu = new Menu();
        //menu.menuInicial();
}
}