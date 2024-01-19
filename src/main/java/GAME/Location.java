package GAME;


public class Location {

    private static int idCount = 0;
    private int id = 0;

    private int coordinates;


    public Location(int y) {
        this.coordinates = y;
        this.id = idCount;
        idCount++;
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


    public void setId(int x) {
        this.id = x;
    }

    public void setYCoordinates(int y) {
        this.coordinates = y;
    }

}
