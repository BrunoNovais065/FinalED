package org.example;

import Colecoes.Lists.ArrayUnorderedList;
import GAME.Location;
import GAME.Map;
import GAME.Menu;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {


        // TODO fazer com que a densidade seja sempre entre 50% e 100%
        // TODO fazer com que o numero de vertices minimo seja 10
        // Map map = new Map();
        //map.generateRandomMap(10, false, 0.5);
        //map.printMap();

/*
    for (int i = 0; i < map.getMap().getVertices().length; i++) {
    }
    Iterator iterator = map.getMap().iteratorShortestPath(map.getMap().getVertices()[0], map.getMap().getVertices()[4]);


    while (iterator.hasNext()) {
         System.out.println(map.getMap().getIndex((Location) iterator.next()));
    }

    System.out.println();

        Iterator iteratorr = map.getMap().iteratorShortestPath(map.getMap().getVertices()[0], map.getMap().getVertices()[4]);

        while (iteratorr.hasNext()) {
            System.out.println(map.getMap().getIndex((Location) iteratorr.next()));
        }


 */



    // TODO aqui é preciso adicionar a verificação se ja foi visitado e no de baixo tambem
        //aqui quando não é possivel o penuultimo valor vai ser -1
        /*
    Iterator iterator1 = map.getMap().iteratorVerticesWithHighestWeight(map.getMap().getVertices()[6], map.getMap().getVertices()[0]);

    while (iterator1.hasNext()) {
        System.out.println(map.getMap().getIndex((Location) iterator1.next()));
    }

         */



/*
    Iterator iterator2 = map.getMap().iteratorVerticesWithSmallestWeight(map.getMap().getVertices()[6], map.getMap().getVertices()[0]);

    while (iterator2.hasNext()) {
        System.out.println(map.getMap().getIndex((Location) iterator2.next()));
    }
*/




        Menu menu = new Menu();
        menu.menuInicial();
}
}