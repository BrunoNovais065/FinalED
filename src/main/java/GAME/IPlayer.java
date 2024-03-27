package GAME;

/**
 * This interface defines the contract that all players must follow.
 */
public interface IPlayer {

    /**
     * Method that gets the name of the player
     * @return the name of the player
     */
    String getName();

    /**
     * Method that gets the id of the player
     * @return the id of the player
     */
    int getId();

    /**
     * Method that gets the location of the flag
     * @return the location of the flag
     */
    Location getFlagLocation();

    /**
     * Method that gets the bots of the player
     * @return the bots of the player
     */
    Bot[] getBots();

    /**
     * Method that sets the name of the player
     * @param name name of the player
     */
    void setName(String name);

    /**
     * Method that sets the id of the player
     * @param id of the player
     */
    void setId(int id);

    /**
     * Method that sets the location of the flag
     * @param flagLocation location of the flag
     */
    void setFlagLocation(Location flagLocation);

    /**
     * Method that sets the bots of the player
     * @param bots bots of the player
     */
    void setBots(Bot[] bots);

    /**
     * Method that returns the string representation of the player
     * @return the string representation of the player
     */
    String toString();

}
