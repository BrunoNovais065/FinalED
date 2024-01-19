package DataPersistence;

import Colecoes.Graphs.Network;
import GAME.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class exportMapToJson {

    public static void exportMapToJson(String path, Network<Location> map) {
        JSONArray verticesArray = new JSONArray();

        for (Location location : map.getVertices()) {
            JSONObject vertexObject = new JSONObject();
            vertexObject.put("x", location.getId()); // Assuming 'x' represents the id
            vertexObject.put("ycoordinates", location.getYCoordinates());
            verticesArray.add(vertexObject);
        }

        JSONObject mapObject = new JSONObject();
        mapObject.put("vertices", verticesArray);
        mapObject.put("empty", false); // Assuming 'empty' property
        mapObject.put("connected", true); // Assuming 'connected' property

        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(mapObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
