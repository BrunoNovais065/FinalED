package GAME;

/**
 * Class that represents a game
 */
public class Game implements IGame {

    private Map map;

    private Player[] players;

    /**
     * Constructor of the class Game that creates a new map and a new array of players, in this case 2 players
     */
    public Game() {
        this.map = new Map();
        this.players = new Player[2];
    }

    /**
     * Method that generates a random map
     * @param size size of the map
     * @param isCircular true if the map is circular, false if not
     * @param obstaclePercentage percentage of obstacles in the map
     */
    public void generateRandomMap(int size, boolean isCircular, double obstaclePercentage) {
        this.map.generateRandomMap(size, isCircular, obstaclePercentage);
    }

    /**
     * Method that gets the map
     * @return the map
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Method that prints the map
     */
    public void printMap() {
        this.map.printMap();
    }
    /**
     * Method that adds a player to the array of players
     * @param player player to add
     */
    public void addPlayer(Player player) {
        this.players[player.getId()] = player;
    }

    /**
     * Method that gets the players
     * @return the players
     */
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     * Method that gets the opponent of a player
     * @param player player to get the opponent
     * @return the opponent of the player
     */
    public Player getOpponent(String player) {
        if (player == this.players[0].getName()) {
            return this.players[1];
        } else {
            return this.players[0];
        }
    }

}
