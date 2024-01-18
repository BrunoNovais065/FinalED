package org.example;

import Colecoes.Graphs.Network;
import DataPersistence.importMapFromJson;
import GAME.Location;
import GAME.Map;

public class MainTesterRomulo {
    public static void main(String[] args) {

        Map map4 = new Map();
        map4.importMapFromJson("src/main/java/DataPersistence/map3.json");

        // Verifica se a importação foi bem-sucedida antes de imprimir
        if (map4 != null) {
            System.out.println("Imported Map:");
            map4.printMap();
        } else {
            System.out.println("Falha na importação do mapa.");
        }
    }

}
