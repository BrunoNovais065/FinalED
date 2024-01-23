package GAME;

import Collections.Graphs.Network;

/**
 * This interface defines the contract that must be
 */
public interface IMap {

    /**
     * Method that generates a random map
     * @param numberOfLocations number of locations
     * @param isDirected if the map is directed
     * @param density density of the map
     */
    void generateRandomMap(int numberOfLocations, boolean isDirected, double density);

    /**
     * Method that returns the map
     * @return map
     */
    Network<Location> getMap();

    /**
     * Method that sets the map
     * @param map map
     */
    void setMap(Network<Location> map);

    /**
     * Method that adds a location to the map
     * @param location location to add
     */
    void addLocation(Location location);

    /**
     * Method that gets all the locations in the map
     */
    void getallLocations();

    /**
     * Method that adds a road to the map
     * @param l1 location 1 of the road
     * @param l2 location 2 of the road
     * @param road road to add to the map
     */
    void addRoad(Location l1, Location l2, double road);

    /**
     * Method that adds a road by to the map (bidirectional)
     * @param l1 location 1 of the road (bidirectional)
     * @param l2 location 2 of the road (bidirectional)
     * @param road road to add to the map (bidirectional)
     */
    void addRoadBy(Location l1, Location l2, double road);

    /**
     * Method that removes a location from the map
     * @param location location to remove
     */
    void removeLocation(Location location);

    /**
     * Method that gets the index of a location
     * @param index index of the location
     * @return location
     */
    Location getLocation(int index);

    /**
     * Method that verifies if the map has a location by location
     * @param location location to verify
     * @return true if the map has the location, false if not
     */
    boolean hasLocation(Location location);

    /**
     * Method that verifies if the map has a location by index
     * @param index index of the location
     * @return true if the map has the location, false if not
     */
    boolean hasLocation(int index);

    /**
     * Method to print the map
     */
    void printMap();

    /**
     * Method that exports the map to a JSON file
     * @param path path of the JSON file
     */
    void exportMapToJson(String path);

    /**
     * Method that imports the map from a JSON file
     * @param path path of the JSON file
     */
    void importMapFromJson(String path);

}
