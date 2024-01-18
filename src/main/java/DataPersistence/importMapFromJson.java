package DataPersistence;

import Colecoes.Graphs.Network;
import GAME.Location;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class importMapFromJson {

    public static Network<Location> importMapFromJson(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), new TypeReference<Network<Location>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
