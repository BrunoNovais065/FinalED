package org.example;

import GAME.Location;
import GAME.Map;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

    Map map = new Map();
        map.generateRandomMap(5, true, 0.4);
    map.printMap();

    for (int i = 0; i < map.getMap().getVertices().length; i++) {
    }
    Iterator iterator = map.getMap().iteratorShortestPath(map.getMap().getVertices()[0], map.getMap().getVertices()[4]);


    while (iterator.hasNext()) {
       System.out.println(map.getMap().getIndex((Location) iterator.next()));
    }
}
}