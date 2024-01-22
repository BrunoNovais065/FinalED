package GAME;

import java.util.Iterator;

public class Bot {


private static int idCount = 0;

Game map;

    int id;

    String name;

    String owner;

    String algoritmo;

    Location locationActual;

    Location locationIn;

    Location locationOut;

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


        public Boolean moveOnePosition() {
            /*
            if (this.algoritmo == "shortestPath") {
                Map tempMap = this.map.getMap();
                System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
                for (int i = 0; i< this.map.getOpponent(owner).getBots().length; i++) {
                    if (this.map.getOpponent(owner).getBots()[i].locationActual == tempMap.getMap().iteratorShortestPath(this.locationActual, this.locationOut).next()) {
                        tempMap.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                    }
                }
                this.locationActual = tempMap.getMap().iteratorShortestPath(this.locationActual, this.locationOut).next();
                System.out.println("--> " + this.locationActual.getId());
                System.out.println();
                if (this.locationActual == this.locationOut) {
                    System.out.println("O jogador " + this.owner + " com o bot " + this.name + " chegou ao destino");
                    return true;
                } else {
                    return false;
                }
            } else if (this.algoritmo == "highestWeight") {
                this.locationActual = this.map.getMap().getMap().iteratorVerticesWithHighestWeight(this.locationActual, this.locationOut).next();
                if (this.locationActual == this.locationOut) {
                    return true;
                } else {
                    return false;
                }
            } else if (this.algoritmo == "smallestWeight") {
                this.locationActual = this.map.getMap().getMap().iteratorVerticesWithSmallestWeight(this.locationActual, this.locationOut).next();
                if (this.locationActual == this.locationOut) {
                    return true;
                } else {
                    return false;
                }
            }
             */
            switch (this.algoritmo) {
                case "shortestPath":
                    Map tempMap = this.map.getMap();
                    System.out.println("O jogador " + this.owner + "com o bot " + this.name + " esta na localização " + this.locationActual.getId() + " e vai para a localização em baixo");
                    for (int i = 0; i< this.map.getOpponent(owner).getBots().length; i++) {
                        Iterator iteratorr = tempMap.getMap().iteratorShortestPath(this.locationActual, this.locationOut);
                        if (iteratorr.hasNext()) {
                            iteratorr.next();
                            if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorr.next()) {
                                tempMap.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                            }
                        }else {
                            System.out.println(("Este bot não tem caminho para o destino"));
                            return false;
                        }
                    }
                    Iterator iterator = tempMap.getMap().iteratorShortestPath(this.locationActual, this.locationOut);
                    if (iterator.hasNext()) {
                    iterator.next();
                    }
                    this.locationActual = (Location) iterator.next();
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
                    for (int i = 0; i< this.map.getOpponent(owner).getBots().length; i++) {
                        Iterator iteratorr = tempMapp.getMap().iteratorVerticesWithHighestWeight(this.locationActual, this.locationOut);
                        if (iteratorr.hasNext()) {
                            iteratorr.next();
                            if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorr.next()) {
                                tempMapp.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                            }
                        }else {
                            System.out.println(("Este bot não tem caminho para o destino"));
                            return false;
                        }
                    }
                    Iterator iteratorr = tempMapp.getMap().iteratorShortestPath(this.locationActual, this.locationOut);
                    if (iteratorr.hasNext()) {
                        iteratorr.next();
                    }
                    this.locationActual = (Location) iteratorr.next();
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
                    for (int i = 0; i< this.map.getOpponent(owner).getBots().length; i++) {
                        Iterator iteratorrr = tempMappp.getMap().iteratorVerticesWithHighestWeight(this.locationActual, this.locationOut);
                        if (iteratorrr.hasNext()) {
                            iteratorrr.next();
                            if (this.map.getOpponent(owner).getBots()[i].locationActual == iteratorrr.next()) {
                                tempMappp.removeLocation(this.map.getOpponent(owner).getBots()[i].locationActual);
                            }
                        }else {
                            System.out.println(("Este bot não tem caminho para o destino"));
                            return false;
                        }
                    }
                    Iterator iteratorrr = tempMappp.getMap().iteratorShortestPath(this.locationActual, this.locationOut);
                    if (iteratorrr.hasNext()) {
                        iteratorrr.next();
                    }
                    this.locationActual = (Location) iteratorrr.next();
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


}
