package GAME;

/**
 * This interface defines the contract that a game must follow
 */
public interface IGame {

    /**
     * Method that generates a random map
     * @param size size of the map
     * @param isCircular true if the map is circular, false if not
     * @param obstaclePercentage percentage of obstacles in the map
     */
    void generateRandomMap(int size, boolean isCircular, double obstaclePercentage);

    /**
     * Method that gets the map
     * @return the map
     */
    Map getMap();

    /**
     * Method that prints the map
     */
    void printMap();

    /**
     * Method that adds a player to the array of players
     * @param player player to add
     */
    void addPlayer(Player player);

    /**
     * Method that gets the players
     * @return the players
     */
    Player[] getPlayers();

    /**
     * Method that gets the opponent of a player
     * @param player player to get the opponent
     * @return the opponent of the player
     */
    Player getOpponent(String player);
}
