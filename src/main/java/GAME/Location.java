package GAME;

public class Location {

    private static int idCount = 0;
    private int id;
    private int coordinates;

    public Location(int coordinates) {
        this.coordinates = coordinates;
        this.id = idCount++;
    }

    public static void resetCounter() {
        idCount = 0;
    }

    public int getId() {
        return this.id;
    }

    public int getYCoordinates() {
        return this.coordinates;
    }
}
