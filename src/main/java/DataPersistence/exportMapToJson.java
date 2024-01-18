package DataPersistence;

import Colecoes.Graphs.Network;
import GAME.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class exportMapToJson {

    public static void exportMapToJson(String path, Network<Location> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(path), map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
