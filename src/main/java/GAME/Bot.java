package GAME;

import java.util.Iterator;

/**
 * Class that represents a bot in the game
 */
public class Bot implements IBot{

    private static int drawCount = 0;

    private static int idCount = 0;

    private int jogadas = 0;

    Game map;

    int id;

    String name;

    String owner;

    String algoritmo;

    Location locationActual;

    Location locationIn;

    Location locationOut;

    /**
     * Constructor of the class Bot
     * @param name name of the bot
     * @param algoritmo algorithm that the bot will use
     * @param locationnIn location where the bot will start
     * @param locationnOut location where the bot will end
     * @param map map where the bot will be
     * @param owner owner of the bot
     */
    public Bot(String name, String algoritmo, Location locationnIn, Location locationnOut, Game map, String owner) {
        this.id = idCount;
        idCount++;
        this.name = name;
        this.algoritmo = algoritmo;
        locationIn = locationnIn;
        locationOut = locationnOut;
        locationActual = locationnIn;
        this.map = map;
        this.owner = owner;
    }

    /**
     * Method that moves the bot one position
     * @return true if the bot arrived to the destination, false if not
     */
    public Boolean moveOnePosition() {
        switch (this.algoritmo) {
            case "shortestPath":
                Map tempMap = this.map.getMap();
                System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
                for (int i = 0; i < this.map.getOpponent(owner).getBots().length; i++) {
                    Iterator iteratorr = tempMap.getMap().iteratorShortestPath(this.locationActual, this.locationOut);
                    if (iteratorr.hasNext()) {
                        iteratorr.next();
                        if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorr.next() && this.map.getOpponent(owner).getBots()[i].getJogadas() != 0) {
                            tempMap.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                        }
                    }
                }
                Iterator iterator = tempMap.getMap().iteratorShortestPath(this.locationActual, this.locationOut);
                if (iterator.hasNext()) {
                    iterator.next();
                    this.locationActual = (Location) iterator.next();
                    this.jogadas++;
                } else {
                    drawCount++;
                    if (drawCount == (this.map.getPlayers()[0].getBots().length + this.map.getPlayers()[1].getBots().length)) {
                        System.out.println("Empate");
                        return true;
                    }
                }
                System.out.println("--> " + this.locationActual.getId());
                System.out.println();
                if (this.locationActual == this.locationOut) {
                    System.out.println("O jogador " + this.owner + " com o bot " + this.name + " chegou ao destino");
                    return true;
                } else {
                    return false;
                }
            case "highestWeight":
                Map tempMapp = this.map.getMap();
                System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
                for (int i = 0; i < this.map.getOpponent(owner).getBots().length; i++) {
                    Iterator iteratorr = tempMapp.getMap().iteratorVerticesWithHighestWeight(this.locationActual, this.locationOut);
                    if (iteratorr.hasNext()) {
                        iteratorr.next();
                        if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorr.next() && this.map.getOpponent(owner).getBots()[i].getJogadas() != 0) {
                            tempMapp.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                        }
                    }
                }
                Iterator iteratorr = tempMapp.getMap().iteratorVerticesWithHighestWeight(this.locationActual, this.locationOut);
                if (iteratorr.hasNext()) {
                    iteratorr.next();
                    this.locationActual = (Location) iteratorr.next();
                    this.jogadas++;
                    drawCount = 0;
                } else {
                    drawCount++;
                    if (drawCount == (this.map.getPlayers()[0].getBots().length + this.map.getPlayers()[1].getBots().length)) {
                        System.out.println("Empate");
                        return true;
                    }
                }

                System.out.println("--> " + this.locationActual.getId());
                System.out.println();
                if (this.locationActual == this.locationOut) {
                    System.out.println("O jogador " + this.owner + " com o bot " + this.name + " chegou ao destino");
                    return true;
                } else {
                    return false;
                }
            case "smallestWeight":
                Map tempMappp = this.map.getMap();
                System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
                for (int i = 0; i < this.map.getOpponent(owner).getBots().length; i++) {
                    Iterator iteratorrr = tempMappp.getMap().iteratorVerticesWithSmallestWeight(this.locationActual, this.locationOut);
                    if (iteratorrr.hasNext()) {
                        iteratorrr.next();
                        if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorrr.next() && this.map.getOpponent(owner).getBots()[i].getJogadas() != 0) {
                            tempMappp.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                        }
                    }
                }
                Iterator iteratorrr = tempMappp.getMap().iteratorVerticesWithSmallestWeight(this.locationActual, this.locationOut);
                if (iteratorrr.hasNext()) {
                    iteratorrr.next();
                    this.locationActual = (Location) iteratorrr.next();
                    this.jogadas++;
                } else {
                    drawCount++;
                    if (drawCount == (this.map.getPlayers()[0].getBots().length + this.map.getPlayers()[1].getBots().length)) {
                        System.out.println("Empate");
                        return true;
                    }
                }
                System.out.println("--> " + this.locationActual.getId());
                System.out.println();
                if (this.locationActual == this.locationOut) {
                    System.out.println("O jogador " + this.owner + " com o bot " + this.name + " chegou ao destino");
                    return true;
                } else {
                    return false;
                }
        }

        return false;
    }

    /**
     * Method that returns the number of plays that the bot did
     * @return number of plays that the bot did
     */
    public int getJogadas() {
        return this.jogadas;
    }

    /**
     * Method that returns the algorithm that the bot is using
     * @return algorithm that the bot is using
     */
    public String getAlgoritmo() {
        return this.algoritmo;
    }

}
