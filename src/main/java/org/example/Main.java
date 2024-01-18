package org.example;

import GAME.Map;

public class Main {
    public static void main(String[] args) {

    Map map = new Map();
        map.generateRandomMap(5, true, 0.4);
    map.printMap();

    }
}