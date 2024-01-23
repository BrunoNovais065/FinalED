package GAME;

/**
 * This interface defines the contract that a location must follow
 */
public interface ILocation {

    /**
     * Method that gets the id of the location
     * @return the id of the location
     */
    int getId();

    /**
     * Method that gets the coordinates of the location
     * @return the coordinates of the location
     */
    int getYCoordinates();

}
