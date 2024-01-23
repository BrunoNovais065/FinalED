package DataPersistence;

import Collections.Graphs.Network;
import GAME.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Class that imports a map from a JSON file
 */
public class importMapFromJson {
    public static Network<Location> importMapFromJson(String path) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(path)) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            JSONArray verticesArray = (JSONArray) jsonObject.get("vertices");
            JSONArray edgesArray = (JSONArray) jsonObject.get("edges");

            Network<Location> map = new Network<>();

            // Criar e adicionar todas as localizações ao mapa
            for (Object vertexObject : verticesArray) {
                JSONObject vertex = (JSONObject) vertexObject;

                // Verificar se os valores não são nulos
                Long idValue = (Long) vertex.get("id");
                Long coordenadasValue = (Long) vertex.get("coordenadas");

                if (idValue != null && coordenadasValue != null) {
                    int id = Math.toIntExact(idValue);
                    int coordenadas = Math.toIntExact(coordenadasValue);

                    Location location = new Location(coordenadas);
                    map.addVertex(location);
                }
            }

            // Adicionar arestas ao mapa
            for (Object edgeObject : edgesArray) {
                JSONObject edge = (JSONObject) edgeObject;

                // Verificar se os valores não são nulos
                Long sourceIdValue = (Long) edge.get("source");
                Long targetIdValue = (Long) edge.get("target");
                Double lengthValue = (Double) edge.get("length");

                if (sourceIdValue != null && targetIdValue != null && lengthValue != null) {
                    int sourceId = Math.toIntExact(sourceIdValue);
                    int targetId = Math.toIntExact(targetIdValue);
                    double length = lengthValue;

                    Location source = getLocationById(map, sourceId);
                    Location target = getLocationById(map, targetId);

                    if (source != null && target != null) {
                        map.addEdge(source, target, length);
                    }
                }
            }

            return map;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método auxiliar para obter a localização pelo ID
    private static Location getLocationById(Network<Location> map, int id) {
        for (Location location : map.getVertices()) {
            if (location.getId() == id) {
                return location;
            }
        }
        return null;
    }
}
