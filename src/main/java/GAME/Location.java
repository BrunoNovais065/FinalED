package GAME;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Location {

    private static int idCount = 0;
    private int x = 0;

    private int coordinates;


    public Location(int y) {
        this.coordinates = y;
        this.x = idCount;
        idCount++;
    }




    public static void resetCounter() {
        idCount = 0;
    }


    public int getX() {
        return this.x;
    }

    public int getYCoordinates() {
        return this.coordinates;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setYCoordinates(int y) {
        this.coordinates = y;
    }

}
