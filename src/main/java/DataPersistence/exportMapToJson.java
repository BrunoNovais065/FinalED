package DataPersistence;

import Colecoes.Graphs.Network;
import GAME.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class exportMapToJson {
    public static void exportMapToJson(String path, Network<Location> map) {
        JSONObject mapObject = new JSONObject();
        mapObject.put("connected", map.isConnected());
        mapObject.put("empty", map.isEmpty());

        JSONArray verticesArray = new JSONArray();
        for (Location location : map.getVertices()) {
            JSONObject vertexObject = new JSONObject();
            vertexObject.put("id", location.getId());
            vertexObject.put("coordenadas", location.getYCoordinates());
            verticesArray.add(vertexObject);
        }
        mapObject.put("vertices", verticesArray);

        JSONArray edgesArray = new JSONArray();
        for (Location l1 : map.getVertices()) {
            for (Location l2 : map.getVertices()) {
                if (l1 != l2 && map.getWeight(l1, l2) != Double.POSITIVE_INFINITY) {
                    JSONObject edgeObject = new JSONObject();
                    edgeObject.put("source", l1.getId());
                    edgeObject.put("target", l2.getId());
                    edgeObject.put("length", map.getWeight(l1, l2));
                    edgesArray.add(edgeObject);
                }
            }
        }
        mapObject.put("edges", edgesArray);

        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(mapObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
