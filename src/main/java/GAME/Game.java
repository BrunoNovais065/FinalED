package GAME;

public class Game {

    private Map map;

    private Player[] players;

    public Game() {
        this.map = new Map();
        this.players = new Player[2];
    }

    public void generateRandomMap(int size, boolean isCircular, double obstaclePercentage) {
        this.map.generateRandomMap(size, isCircular, obstaclePercentage);
    }

    public Map getMap() {
        return this.map;
    }

    public void printMap() {
        this.map.printMap();
    }

    public void addPlayer(Player player) {
        this.players[player.getId()] = player;
    }
}
