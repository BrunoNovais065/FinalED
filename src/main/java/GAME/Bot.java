package GAME;

public class Bot {


private static int idCount = 0;

    int id;

    String name;

    String algoritmo;

    public Bot(String name, String algoritmo) {
        this.id = idCount;
        idCount++;
        this.name = name;
        this.algoritmo = algoritmo;
    }


}
