package GAME;

/**
 * Class that represents a player
 */
public class Player implements IPlayer{

    private static int idCount = 0;

    private String name;

    private int id;

    private Location flagLocation;

    private Bot[] bots;

    /**
     * Constructor of the class Player that creates a new player with a name
     * @param name name of the player
     * @param flagLocation location of the flag
     * @param bots array of bots
     */
    public Player(String name, Location flagLocation, Bot[] bots) {
        this.name = name;
        this.id = idCount;
        idCount++;
        this.flagLocation = flagLocation;
        this.bots = bots;
    }

    /**
     * Method that gets the name of the player
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method that gets the id of the player
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method that gets the location of the flag
     * @return the location of the flag
     */
    public Location getFlagLocation() {
        return this.flagLocation;
    }

    /**
     * Method that gets the bots of the player
     * @return the bots of the player
     */
    public Bot[] getBots() {
        return this.bots;
    }

    /**
     * Method that sets the name of the player
     * @param name name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that sets the id of the player
     * @param id of the player
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method that sets the location of the flag
     * @param flagLocation location of the flag
     */
    public void setFlagLocation(Location flagLocation) {
        this.flagLocation = flagLocation;
    }

    /**
     * Method that sets the bots of the player
     * @param bots bots of the player
     */
    public void setBots(Bot[] bots) {
        this.bots = bots;
    }

    /**
     * Method that prints the player
     * @return the player
     */
    public String toString() {
        return "Player(name=" + this.getName() + ", id=" + this.getId() + ", flagLocation=" + this.getFlagLocation() + ", bots=" + java.util.Arrays.deepToString(this.getBots()) + ")";
    }

}
