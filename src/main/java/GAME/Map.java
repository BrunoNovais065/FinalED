package GAME;

import Colecoes.Graphs.Network;

import java.util.Random;

public class Map {

    private Network<Location> map;

    public Map() {
        this.map = new Network<Location>();
    }

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

    public void generateRandomMap(int numLocations, boolean bidirectional, double density) {
        Random random = new Random();
        int countConnections = 0;
        if (bidirectional) {
            countConnections = (int) (numLocations * (numLocations - 1) * density) / 2;
        } else {
            countConnections = (int) (numLocations * (numLocations - 1) * density) / 2;
        }


        /*
        // Connect locations with random roads based on density
        for (int i = 0; i <  getMap().getVertices().length; i++) {
            Location location1 = (Location) getMap().getVertices()[i];
            for (int j = i + 1; j < getMap().getVertices().length; j++) {
                Location location2 = (Location) getMap().getVertices()[j];
                if (bidirectional == true) {
                    double numberOfConnections = (double) ((numLocations* (numLocations - 1)) * density);
                    for (int k = 0; k < (int) numberOfConnections; k++) {
                        double roadLength = random.nextDouble() * 15 + 1; // Random distance between 1 and 15 km
                        addRoad(location1, location2, roadLength);
                        addRoad(location2, location1, roadLength);
                        k++;
                    }
                } else {
                    int numberOfConnections = (int) ((numLocations* (numLocations - 1)) * density) / 2;
                    for (int k = 0; k < numberOfConnections; k++) {
                        double roadLength = random.nextDouble() * 15 + 1; // Random distance between 1 and 15 km
                        addRoad(location1, location2, roadLength);
                    }
                }
            }
        }
         */



        int count = 0;

        // Create random locations
        for (int i = 0; i < numLocations; i++) {
            Location location = new Location(random.nextInt(100)); // You can adjust the range as needed
            addLocation(location);
            /*
            //if is bidirectional create a road to the next location
            if (bidirectional && i != numLocations - 1 && i >= 1) {
                Location l1 = (Location) this.map.getVertices()[i - 1];
                Location l2 = (Location) this.map.getVertices()[i ];
                double roadLength = random.nextDouble() * 100.0D;
                addRoad(l1, l2, roadLength);
                addRoad(l2, l1, roadLength);
                count++;
            }

             */
        }

        //countConnections -= count;
if (!bidirectional) {
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



    public Network<Location> getMap() {
        return this.map;
    }

    public void setMap(Network<Location> map) {
        this.map = map;
    }

    public void addLocation(Location location) {
        this.map.addVertex(location);
    }

    public void getallLocations() {
        for (Location location : this.map.getVertices()) {
            System.out.println("Location: " + location.getYCoordinates());
        }
    }

    public void addRoad(Location l1, Location l2, double road) {
        this.map.addEdge(l1, l2, road);
    }

    public void addRoadBy(Location l1, Location l2, double road) {
        this.map.addEdgeBi(l1, l2, road);
    }

    public void removeLocation(Location location) {
        this.map.removeVertex(location);
    }


    public Location getLocation(int x) {
        for (Location location : this.map.getVertices()) {
            if (location.getId() == x) {
                return location;
            }
        }
        return null;
    }


    public boolean hasLocation(Location location) {
        if (location == null) {
            return false;
        }
        if (this.map.getIndex(location) == -1) {
            return false;
        }
        return true;
    }


    public boolean hasLocation(int y) {
        for (Location location : this.map.getVertices()) {
            if (location.getYCoordinates() == y) {
                return true;
            }
        }
        return false;
    }


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

    public void exportMapToJson(String path) {
        DataPersistence.exportMapToJson.exportMapToJson(path, this.map);
    }

    public void importMapFromJson(String path) {
        this.map = DataPersistence.importMapFromJson.importMapFromJson(path);
    }

}

