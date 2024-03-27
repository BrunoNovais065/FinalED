package GAME;

import TodasColecoes.Grafos.Network;

import java.util.Random;

/**
 * Class that represents a map
 */
public class Map implements IMap{

    private Network<Location> map;

    /**
     * Constructor of the class Map that creates a new map with a new network of locations
     */
    public Map() {
        this.map = new Network<Location>();
    }

    /**
     * Constructor of the class Map that creates a new map with a number of locations, map type and edge density
     * @param numLocations number of locations
     * @param bidirectional true if the map is bidirectional, false if not
     * @param edgeDensity density of the edges
     */
    //create map whit this specifications
    // : 1) a quantidade de localizações existentes no mapa,
    // 2) o tipo de caminhos que serão gerados, i.e., bidirecionais ou não e
    // 3) a densidade das arestas, p.e., 50% num grafo direcionado indica que (N* (N -1)) * 0.5 arestas devem estar presentes2
    public Map(int numLocations, boolean bidirectional, double edgeDensity) {
        this.map = new Network<Location>();

        // Create locations
        for (int i = 0; i < numLocations; i++) {
            Location location = new Location(i);
            addLocation(location);
        }
        // Connect locations with roads based on specifications
        Random random = new Random();
        for (Location l1 : this.map.getVertices()) {
            for (Location l2 : this.map.getVertices()) {
                if (l1 != l2 && random.nextDouble() < edgeDensity) {
                    double roadLength = random.nextDouble() * 100.0D;
                    addRoad(l1, l2, roadLength);
                    if (bidirectional) {
                        addRoad(l2, l1, roadLength);
                    }
                }
            }
        }
    }

    /**
     * Method that generates a random map
     * @param numLocations number of locations
     * @param bidirectional true if the map is bidirectional, false if not
     * @param density density of the edges
     */
    public void generateRandomMap(int numLocations, boolean bidirectional, double density) {
        Random random = new Random();
        int countConnections = 0;
        if (bidirectional) {
            countConnections = (int) (numLocations * (numLocations - 1) * density) / 2;
        } else {
            countConnections = (int) (numLocations * (numLocations - 1) * density) / 2;
        }

        int count = 0;
        // Create random locations
        for (int i = 0; i < numLocations; i++) {
            Location location = new Location(random.nextInt(100)); // You can adjust the range as needed
            addLocation(location);
        }

        //countConnections -= count;
if (!bidirectional) {
    addRoad((Location) this.map.getVertices()[1], (Location) this.map.getVertices()[0], random.nextDouble() * 100.0D);
    countConnections--;
        //connect random locations whit roads based on specifications
        for (int i = 0; i < countConnections; i++) {
            Location l1 = null;
            Location l2 = null;
            while (l1 == l2) {
                l1 = (Location) this.map.getVertices()[random.nextInt(numLocations)];
                l2 = (Location) this.map.getVertices()[random.nextInt(numLocations)];
               if (this.map.getWeight(l2, l1) != Double.POSITIVE_INFINITY || this.map.getWeight(l1, l2) != Double.POSITIVE_INFINITY) {
                    l1 = null;
                    l2 = null;
                }

            }
            double roadLength = random.nextDouble() * 100.0D;
            addRoad(l1, l2, roadLength);
            if (bidirectional) {
                addRoad(l2, l1, roadLength);
            }
        }
} else {
    for (int i = 0; i < countConnections; i++) {
        Location l1 = null;
        Location l2 = null;
        while (l1 == l2) {
            l1 = (Location) this.map.getVertices()[random.nextInt(numLocations)];
            l2 = (Location) this.map.getVertices()[random.nextInt(numLocations)];
            if (this.map.getWeight(l2, l1) != Double.POSITIVE_INFINITY || this.map.getWeight(l1, l2) != Double.POSITIVE_INFINITY) {
                l1 = null;
                l2 = null;
            }
        }
        double roadLength = random.nextDouble() * 100.0D;
        addRoadBy(l1, l2, roadLength);
    }
}

        //verify if the map is connected if not generate a new map
        if (!getMap().isConnected()) {
            getMap().clear();
            Location.resetCounter();
            generateRandomMap(numLocations, bidirectional, density);
        }

    }

    /**
     * Method that gets the map of the network of locations
     * @return the map of the network of locations
     */
    public Network<Location> getMap() {
        return this.map;
    }

    /**
     * Method that sets the map of the network of locations
     * @param map map of the network of locations
     */
    public void setMap(Network<Location> map) {
        this.map = map;
    }

    /**
     * Method that adds a location to the network of locations
     * @param location location to add
     */
    public void addLocation(Location location) {
        this.map.addVertex(location);
    }

    /**
     * Method that gets the number of locations in the network of locations
     */
    public void getallLocations() {
        for (Location location : this.map.getVertices()) {
            System.out.println("Location: " + location.getYCoordinates());
        }
    }

    /**
     * Method that adds a road to the network of locations
     * @param l1 location 1 of the road
     * @param l2 location 2 of the road
     * @param road road to add to the network of locations
     */
    public void addRoad(Location l1, Location l2, double road) {
        this.map.addEdge(l1, l2, road);
    }

    /**
     * Method that adds a road by to the network of locations (bidirectional)
     * @param l1 location 1 of the road (bidirectional)
     * @param l2 location 2 of the road (bidirectional)
     * @param road road to add to the network of locations (bidirectional)
     */
    public void addRoadBy(Location l1, Location l2, double road) {
        this.map.addEdgeBi(l1, l2, road);
    }

    /**
     * Method that removes a location from the network of locations
     * @param location location to remove
     */
    public void removeLocation(Location location) {
        this.map.removeVertex(location);
    }

    /**
     * Method that gets a location from the network of locations
     * @param x id of the location
     * @return the location
     */
    public Location getLocation(int x) {
        for (Location location : this.map.getVertices()) {
            if (location.getId() == x) {
                return location;
            }
        }
        return null;
    }

    /**
     * Method that verifies if a location exists in the network of locations
     * @param location location to verify
     * @return true if the location exists, false if not
     */
    public boolean hasLocation(Location location) {
        if (location == null) {
            return false;
        }
        if (this.map.getIndex(location) == -1) {
            return false;
        }
        return true;
    }

    /**
     * Method that verifies if a location exists by coordinates in the network of locations
     * @param y coordinates of the location
     * @return true if the location exists, false if not
     */
    public boolean hasLocation(int y) {
        for (Location location : this.map.getVertices()) {
            if (location.getYCoordinates() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to print the map in console in graph format whit boble and lines boble are the location and lines are the roads
     */
    //print the map in console in graph format whit boble and lines boble are the location and lines are the roads
    public void printMap() {
        System.out.println("Map: ");
        for (Location location : this.map.getVertices()) {
            System.out.println("Location: " + location.getId());
            for (Location location1 : this.map.getVertices()) {
                if (location != location1) {
                    if (this.map.getWeight(location, location1) != Double.POSITIVE_INFINITY) {
                        System.out.println("Road: " + location.getId() + " -> " + location1.getId() + " = " + this.map.getWeight(location, location1));
                    }
                }
            }
        }
    }

    /**
     * Method that exports the map to a json file
     * @param path path of the json file
     */
    public void exportMapToJson(String path) {
        DataPersistence.exportMapToJson.exportMapToJson(path, this.map);
    }


    /**
     * Method that imports the map from a json file
     * @param path
     */
    public void importMapFromJson(String path) {
        this.map = DataPersistence.importMapFromJson.importMapFromJson(path);
    }


}

