package DataPersistence;

import Colecoes.Graphs.Network;
import GAME.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class importMapFromJson {

    public static Network<Location> importMapFromJson(String path) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(path)) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            JSONArray verticesArray = (JSONArray) jsonObject.get("vertices");

            Network<Location> map = new Network<>();

            // Criar e adicionar todas as localizações ao mapa
            for (Object vertexObject : verticesArray) {
                JSONObject vertex = (JSONObject) vertexObject;
                int x = Math.toIntExact((long) vertex.get("x"));
                int yCoordinates = Math.toIntExact((long) vertex.get("ycoordinates"));

                Location location = new Location(yCoordinates);
                map.addVertex(location);
            }

            // Adicionar arestas ao mapa
            for (Location l1 : map.getVertices()) {
                for (Location l2 : map.getVertices()) {
                    if (l1 != l2 && jsonObject.containsKey(getEdgeKey(l1, l2))) {
                        double roadLength = (double) jsonObject.get(getEdgeKey(l1, l2));
                        map.addEdge(l1, l2, roadLength);
                    }
                }
            }

            return map;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método auxiliar para obter a chave de uma aresta no formato "x1_y1_x2_y2"
    private static String getEdgeKey(Location l1, Location l2) {
        return l1.getId() + "_" + l1.getYCoordinates() + "_" + l2.getId() + "_" + l2.getYCoordinates();
    }
}
