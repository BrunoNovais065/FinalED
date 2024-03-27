package GAME;

/**
 * Class that represents a location in the map
 */
public class Location implements ILocation{

    private static int idCount = 0;
    private int id;
    private int coordinates;

    /**
     * Constructor of the class Location
     * @param coordinates coordinates of the location
     */
    public Location(int coordinates) {
        this.coordinates = coordinates;
        this.id = idCount++;
    }

    /**
     * Method that resets the counter of the locations
     */
    public static void resetCounter() {
        idCount = 0;
    }

    /**
     * Method that gets the id of the location
     * @return the id of the location
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method that gets the coordinates of the location
     * @return the coordinates of the location
     */
    public int getYCoordinates() {
        return this.coordinates;
    }
}
