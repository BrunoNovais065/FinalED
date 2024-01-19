package GAME;

public class Player {

    private static int idCount = 0;

    private String name;

    private int id;

    private Location flagLocation;

    private Bot[] bots;

    public Player(String name, Location flagLocation, Bot[] bots) {
        this.name = name;
        this.id = idCount;
        idCount++;
        this.flagLocation = flagLocation;
        this.bots = bots;
    }


    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public Location getFlagLocation() {
        return this.flagLocation;
    }

    public Bot[] getBots() {
        return this.bots;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFlagLocation(Location flagLocation) {
        this.flagLocation = flagLocation;
    }

    public void setBots(Bot[] bots) {
        this.bots = bots;
    }

    public String toString() {
        return "Player(name=" + this.getName() + ", id=" + this.getId() + ", flagLocation=" + this.getFlagLocation() + ", bots=" + java.util.Arrays.deepToString(this.getBots()) + ")";
    }

}
