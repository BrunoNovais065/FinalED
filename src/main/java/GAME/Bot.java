package GAME;

import java.util.Iterator;

/**
 * Class that represents a bot in the game
 */
public class Bot implements IBot{

    private static int drawCount = 0;

    private static int idCount = 0;

    private int jogadas = 0;

    Boolean hasFlag = false;

    Game map;

    int id;

    String name;

    String owner;

    String algoritmo;
    String algoritmoVolta;

    Location locationActual;

    Location locationIn;

    Location locationOut;

    /**
     * Constructor of the class Bot
     * @param name name of the bot
     * @param locationnIn location where the bot will start
     * @param locationnOut location where the bot will end
     * @param map map where the bot will be
     * @param owner owner of the bot
     */
    public Bot(String name, String algoritmoIda, String algoritmoVolta, Location locationnIn, Location locationnOut, Game map, String owner) {
        this.id = idCount;
        idCount++;
        this.name = name;
        this.algoritmo = algoritmoIda;
        this.algoritmoVolta = algoritmoVolta;
        locationIn = locationnIn;
        locationOut = locationnOut;
        locationActual = locationnIn;
        this.map = map;
        this.owner = owner;
        this.hasFlag = false;
    }

    public Boolean mts(Location location){
        Map tempMap = this.map.getMap();
        System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
        for (int i = 0; i < this.map.getOpponent(owner).getBots().length; i++) {
            Iterator iteratorr = tempMap.getMap().shortestPathMTS(this.locationActual, location);
            if (iteratorr.hasNext()) {
                iteratorr.next();
                if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorr.next() && this.map.getOpponent(owner).getBots()[i].getJogadas() != 0) {
                    //tempMap.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == true && this.hasFlag == true) {
                        this.hasFlag = false;
                        this.map.getOpponent(owner).getBots()[i].setHasFlag(false);
                        System.out.println("O jogador " + this.owner + " com o bot " + this.name + " perdeu a bandeira");
                        System.out.println("O jogador " + this.map.getOpponent(owner).getBots()[i].getOwner() + " com o bot " + this.map.getOpponent(owner).getBots()[i].getName() + " perdeu a bandeira");
                    }
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == true && this.hasFlag == false) {
                        this.map.getOpponent(owner).getBots()[i].setHasFlag(false);
                        System.out.println("O jogador " + this.map.getOpponent(owner).getBots()[i].getOwner() + " com o bot " + this.map.getOpponent(owner).getBots()[i].getName() + " perdeu a bandeira");
                    }
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == false && this.hasFlag == true) {
                    }
                }
            }
        }
        Iterator iterator = tempMap.getMap().shortestPathMTS(this.locationActual, location);
        if (iterator.hasNext()) {
            iterator.next();
            this.locationActual = (Location) iterator.next();
            this.jogadas++;
        }
        System.out.println("--> " + this.locationActual.getId());
        System.out.println();
        if (this.locationActual == this.locationIn && this.hasFlag == true && this.jogadas != 0) {
            System.out.println("O jogador " + this.owner + " com o bot " + this.name + " chegou ao destino");
            return true;
        } else if (this.locationActual == this.locationOut && this.hasFlag == false && this.jogadas != 0) {
            this.hasFlag = true;
            System.out.println("O jogador " + this.owner + " com o bot " + this.name + " apanhou a bandeira");
            return false;
        }
        return false;
    }

    public Boolean smallestWeight(Location location){
        Map tempMap = this.map.getMap();
        System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
        for (int i = 0; i < this.map.getOpponent(owner).getBots().length; i++) {
            Iterator iteratorr = tempMap.getMap().iteratorVerticesWithSmallestWeight(this.locationActual, location);
            if (iteratorr.hasNext()) {
                iteratorr.next();
                if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorr.next() && this.map.getOpponent(owner).getBots()[i].getJogadas() != 0) {
                    //tempMap.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == true && this.hasFlag == true) {
                        this.hasFlag = false;
                        this.map.getOpponent(owner).getBots()[i].setHasFlag(false);
                        System.out.println("O jogador " + this.owner + " com o bot " + this.name + " perdeu a bandeira");
                        System.out.println("O jogador " + this.map.getOpponent(owner).getBots()[i].getOwner() + " com o bot " + this.map.getOpponent(owner).getBots()[i].getName() + " perdeu a bandeira");
                    }
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == true && this.hasFlag == false) {
                        this.map.getOpponent(owner).getBots()[i].setHasFlag(false);
                        System.out.println("O jogador " + this.map.getOpponent(owner).getBots()[i].getOwner() + " com o bot " + this.map.getOpponent(owner).getBots()[i].getName() + " perdeu a bandeira");
                    }
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == false && this.hasFlag == true) {
                    }
                }
            }
        }
        Iterator iterator = tempMap.getMap().iteratorVerticesWithSmallestWeight(this.locationActual, location);
        if (iterator.hasNext()) {
            iterator.next();
            this.locationActual = (Location) iterator.next();
            this.jogadas++;
        }
        System.out.println("--> " + this.locationActual.getId());
        System.out.println();
        if (this.locationActual == this.locationIn && this.hasFlag == true && this.jogadas != 0) {
            System.out.println("O jogador " + this.owner + " com o bot " + this.name + " chegou ao destino");
            return true;
        } else if (this.locationActual == this.locationOut && this.hasFlag == false && this.jogadas != 0) {
            this.hasFlag = true;
            System.out.println("O jogador " + this.owner + " com o bot " + this.name + " apanhou a bandeira");
            return false;
        }
        return false;
    }

    public Boolean highestWeight(Location location){
        Map tempMap = this.map.getMap();
        System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
        for (int i = 0; i < this.map.getOpponent(owner).getBots().length; i++) {
            Iterator iteratorr = tempMap.getMap().iteratorVerticesWithHighestWeight(this.locationActual, location);
            if (iteratorr.hasNext()) {
                iteratorr.next();
                if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorr.next() && this.map.getOpponent(owner).getBots()[i].getJogadas() != 0) {
                    //tempMap.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == true && this.hasFlag == true) {
                        this.hasFlag = false;
                        this.map.getOpponent(owner).getBots()[i].setHasFlag(false);
                        System.out.println("O jogador " + this.owner + " com o bot " + this.name + " perdeu a bandeira");
                        System.out.println("O jogador " + this.map.getOpponent(owner).getBots()[i].getOwner() + " com o bot " + this.map.getOpponent(owner).getBots()[i].getName() + " perdeu a bandeira");
                    }
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == true && this.hasFlag == false) {
                        this.map.getOpponent(owner).getBots()[i].setHasFlag(false);
                        System.out.println("O jogador " + this.map.getOpponent(owner).getBots()[i].getOwner() + " com o bot " + this.map.getOpponent(owner).getBots()[i].getName() + " perdeu a bandeira");

                    }
                    if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == false && this.hasFlag == true) {
                    }
                }
            }
        }
        Iterator iterator = tempMap.getMap().iteratorVerticesWithHighestWeight(this.locationActual, location);
        if (iterator.hasNext()) {
            iterator.next();
            this.locationActual = (Location) iterator.next();
            this.jogadas++;
        }
        System.out.println("--> " + this.locationActual.getId());
        System.out.println();
        if (this.locationActual == this.locationIn && this.hasFlag == true && this.jogadas != 0) {
            System.out.println("O jogador " + this.owner + " com o bot " + this.name + " chegou ao destino");
            return true;
        } else if (this.locationActual == this.locationOut && this.hasFlag == false && this.jogadas != 0) {
            this.hasFlag = true;
            System.out.println("O jogador " + this.owner + " com o bot " + this.name + " apanhou a bandeira");
            return false;
        }
        return false;
    }

    public Boolean shortestPath(Location location) {
            Map tempMap = this.map.getMap();
            System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
            for (int i = 0; i < this.map.getOpponent(owner).getBots().length; i++) {
                Iterator iteratorr = tempMap.getMap().iteratorShortestPath(this.locationActual, location);
                if (iteratorr.hasNext()) {
                    iteratorr.next();
                    if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorr.next() && this.map.getOpponent(owner).getBots()[i].getJogadas() != 0) {
                        //tempMap.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                        if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == true && this.hasFlag == true) {
                            this.hasFlag = false;
                            this.map.getOpponent(owner).getBots()[i].setHasFlag(false);
                            System.out.println("O jogador " + this.owner + " com o bot " + this.name + " perdeu a bandeira");
                            System.out.println("O jogador " + this.map.getOpponent(owner).getBots()[i].getOwner() + " com o bot " + this.map.getOpponent(owner).getBots()[i].getName() + " perdeu a bandeira");
                        }
                        if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == true && this.hasFlag == false) {
                            this.map.getOpponent(owner).getBots()[i].setHasFlag(false);
                            System.out.println("O jogador " + this.map.getOpponent(owner).getBots()[i].getOwner() + " com o bot " + this.map.getOpponent(owner).getBots()[i].getName() + " perdeu a bandeira");

                        }
                        if (this.map.getOpponent(owner).getBots()[i].getHasFlag() == false && this.hasFlag == true) {
                        }
                    }
                }
            }
            Iterator iterator = tempMap.getMap().iteratorShortestPath(this.locationActual, location);
            if (iterator.hasNext()) {
                iterator.next();
                this.locationActual = (Location) iterator.next();
                this.jogadas++;
            }
            System.out.println("--> " + this.locationActual.getId());
            System.out.println();
            if (this.locationActual == this.locationIn && this.hasFlag == true && this.jogadas != 0) {
                System.out.println("O jogador " + this.owner + " com o bot " + this.name + " chegou ao destino");
                return true;
            } else if (this.locationActual == this.locationOut && this.hasFlag == false && this.jogadas != 0) {
                this.hasFlag = true;
                System.out.println("O jogador " + this.owner + " com o bot " + this.name + " apanhou a bandeira");
                return false;
            }
            return false;
    }

    /**
     * Method that moves the bot one position
     * @return true if the bot arrived to the destination, false if not
     */
    public Boolean moveOnePosition() {
        switch (this.algoritmo) {
            case "shortestPath":
                if (this.locationOut == this.locationActual) {
                    System.out.println("O jogador " + this.owner + " com o bot " + this.name + " ja estava na bandeira enimiga entao volta a apanhar a bandeira e seguio caminho para a sua base");
                    this.hasFlag = true;
                }
                if (this.hasFlag == false) {
                            return shortestPath(this.locationOut);
                //return shortestPath(this.locationOut);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("highestWeight")){
                    return highestWeight(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("smallestWeight")){
                    return smallestWeight(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("mts")){
                    return mts(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("shortestPath")){
                    return shortestPath(this.locationIn);
                }
            case "highestWeight":
                if (this.locationOut == this.locationActual) {
                    System.out.println("O jogador " + this.owner + " com o bot " + this.name + " ja estava na bandeira enimiga entao volta a apanhar a bandeira e seguio caminho para a sua base");
                    this.hasFlag = true;
                }
                if (this.hasFlag == false) {
                return highestWeight(this.locationOut);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("shortestPath")){
                    return shortestPath(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("smallestWeight")){
                    return smallestWeight(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("mts")){
                    return mts(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("highestWeight")){
                    return highestWeight(this.locationIn);
                }
            case "smallestWeight":
                if (this.locationOut == this.locationActual) {
                    System.out.println("O jogador " + this.owner + " com o bot " + this.name + " ja estava na bandeira enimiga entao volta a apanhar a bandeira e seguio caminho para a sua base");
                    this.hasFlag = true;
                }
                if (this.hasFlag == false) {
                return smallestWeight(this.locationOut);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("shortestPath")){
                    return shortestPath(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("highestWeight")){
                    return highestWeight(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("mts")){
                    return mts(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("smallestWeight")){
                    return smallestWeight(this.locationIn);
                }
            case "mts":
                if (this.locationOut == this.locationActual) {
                    System.out.println("O jogador " + this.owner + " com o bot " + this.name + " ja estava na bandeira enimiga entao volta a apanhar a bandeira e seguio caminho para a sua base");
                    this.hasFlag = true;
                }
                if (this.hasFlag == false) {
                return mts(this.locationOut);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("shortestPath")){
                    return shortestPath(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("highestWeight")){
                    return highestWeight(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("smallestWeight")){
                    return smallestWeight(this.locationIn);
                }else if (this.hasFlag == true && this.algoritmoVolta.equals("mts")){
                    return mts(this.locationIn);
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


    public Boolean getHasFlag() {
        return this.hasFlag;
    }

    public void setHasFlag(Boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }
}
