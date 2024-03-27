package GAME;

import java.util.Scanner;

/**
 * Class that represents a menu in the game
 */
public class Menu {

    private Game game;

    private Scanner scanner = new Scanner(System.in);

    public Menu() {
        this.game = new Game();
    }

    /**
     * Method that starts the menu
     */
    public void menuInicial(){
        System.out.println("Bem vindo ao jogo da bandeira");
        System.out.println("1. Jogar");
        System.out.println("2. Jogar com mapa importado");
        System.out.println("3. Sair");
        System.out.println("Introduzir uma opção: ");
        int opcion = 0;
        opcion = scanner.nextInt();
        switch(opcion){
            case 1:
                this.menuJogar();
                break;
            case 2:
                this.menuMoverImportado();
                break;
            case 3:
                System.out.println("Obrigado por jogar");
                break;
            default:
                System.out.println("Opção invalida");
                this.menuInicial();
                break;
        }
    }

    /**
     * Method that starts the menu to play the game
     */
    public void menuJogar(){
        int correto = 0;
        System.out.println("Introduzir o tamanho do mapa: ");
        int size = scanner.nextInt();
        do {
            if (size < 10) {
                System.out.println("O tamanho do mapa tem de ser maior que 10");
                System.out.println("Introduzir o tamanho do mapa: ");
                size = scanner.nextInt();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        System.out.println("Introduzir se o mapa é bidirecional: ");
        boolean isCircular = scanner.nextBoolean();
        do {
            if (isCircular != false && isCircular != true) {
                System.out.println("Tem de escolher entre true ou false");
                System.out.println("Introduzir se o mapa é circular: ");
                isCircular = scanner.nextBoolean();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        System.out.println("Introduzir a percentagem de obstaculos: ");
        //aqui o minimum é 10 e o maximum é 100
        int obstaclePercentage = scanner.nextInt();
        do {
            if (obstaclePercentage < 50 || obstaclePercentage > 100) {
                System.out.println("A percentagem de obstaculos tem de ser entre 50 e 100");
                System.out.println("Introduzir a percentagem de obstaculos: ");
                obstaclePercentage = scanner.nextInt();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        //turn into percentage decimal
        double obstaclePercentagee = (double) obstaclePercentage / 100;
        this.game.generateRandomMap(size, isCircular, obstaclePercentagee);
        this.game.printMap();
        System.out.println("Introduzir o nome do jogador 1: ");
        String name1 = scanner.next();
        System.out.println("Introduzir o id da sua bandeira: ");
        int id1 = scanner.nextInt();
        do {
            if (id1 < 0 || id1 > size - 1) {
                System.out.println("O id da bandeira tem de ser entre 0 e " + (size - 1));
                System.out.println("Introduzir o id da sua bandeira: ");
                id1 = scanner.nextInt();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        game.getMap().getLocation(id1);
        System.out.println("Introduzir o numero de bots: ");
        int numBots = scanner.nextInt();
        //numero de bots minimos é 1 e o maximo é de 3
        do {
            if (numBots < 1 || numBots > 3) {
                System.out.println("O numero de bots tem de ser entre 1 e 3");
                System.out.println("Introduzir o numero de bots: ");
                numBots = scanner.nextInt();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        System.out.println("Introduzir o nome do jogador 2: ");
        String name2 = scanner.next();
        do {
            if (name2.equals(name1)) {
                System.out.println("O nome do jogador 2 tem de ser diferente do jogador 1");
                System.out.println("Introduzir o nome do jogador 2: ");
                name2 = scanner.next();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        System.out.println("Introduzir o id da sua bandeira: ");
        int id2 = scanner.nextInt();
        do {
            if (id2 < 0 || id2 > size - 1 || id2 == id1) {
                System.out.println("O id da bandeira tem de ser entre 0 e " + (size - 1));
                System.out.println("Introduzir o id da sua bandeira: ");
                id2 = scanner.nextInt();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        game.getMap().getLocation(id2);
        System.out.println("Introduzir o numero de bots do jogador 2: ");
        int numBots2 = scanner.nextInt();
        Bot[] bots1 = new Bot[numBots];
        for (int i = 0; i < numBots; i++) {
            System.out.println("Introduzir o nome do bot 1: ");
            String nameBot = scanner.next();
            System.out.println("Introduzir o algoritmo de ida do bot 1: ");
            String algoritmo = scanner.next();
            do{
                if (!algoritmo.equals("shortestPath") && !algoritmo.equals("highestWeight") && !algoritmo.equals("smallestWeight") && !algoritmo.equals("mts")) {
                    System.out.println("O algoritmo tem de ser shortestPath ou highestWeight ou smallestWeight ou mts");
                    System.out.println("Introduzir o algoritmo de ida do bot 1: ");
                    algoritmo = scanner.next();
                } else {
                    boolean algoritmoIgual = false;

                    for (int j = 0; j < numBots; j++) {
                        if (bots1[j] != null && bots1[j].getAlgoritmo().equals(algoritmo)) {
                            algoritmoIgual = true;
                            break;
                        }
                    }

                    if (algoritmoIgual) {
                        System.out.println("O algoritmo tem de ser diferente dos outros bots");
                        System.out.println("Introduzir o algoritmo de ida do bot 1: ");
                        algoritmo = scanner.next();
                    } else {
                        correto = 1;
                    }
                }
            }while (correto == 0);
            correto = 0;
            System.out.println("Introduzir o algoritmo de volta do bot 1: ");
            String algoritmoVolta = scanner.next();
            do{
                if (!algoritmoVolta.equals("shortestPath") && !algoritmoVolta.equals("highestWeight") && !algoritmoVolta.equals("smallestWeight") && !algoritmoVolta.equals("mts")) {
                    System.out.println("O algoritmo tem de ser shortestPath ou highestWeight ou smallestWeight");
                    System.out.println("Introduzir o algoritmo de volta do bot 1: ");
                    algoritmoVolta = scanner.next();
                } else {
                    boolean algoritmoIgual = false;

                    for (int j = 0; j < numBots; j++) {
                        if (bots1[j] != null && bots1[j].getAlgoritmo().equals(algoritmoVolta)) {
                            algoritmoIgual = true;
                            break;
                        }
                    }

                    if (algoritmoIgual) {
                        System.out.println("O algoritmo tem de ser diferente dos outros bots");
                        System.out.println("Introduzir o algoritmo de volta do bot 1: ");
                        algoritmoVolta = scanner.next();
                    } else {
                        correto = 1;
                    }
                }
            }while (correto == 0);
            correto = 0;
            //aqui vai ser verificado se o algoritmo é valido a partir do nome, se calhar introduzir aqui uma enumeração
            bots1[i] = new Bot(nameBot, algoritmo,algoritmoVolta,game.getMap().getLocation(id1), game.getMap().getLocation(id2), this.game, name1);
        }
        Player player1 = new Player(name1,  game.getMap().getLocation(id1), bots1);
        Bot[] bots2 = new Bot[numBots2];
        for (int i = 0; i < numBots2; i++) {
            System.out.println("Introduzir o nome do bot do jogador 2: ");
            String nameBot = scanner.next();
            System.out.println("Introduzir o algoritmo de ida do bot do jogador 2: ");
            String algoritmo = scanner.next();
            do{
                if (!algoritmo.equals("shortestPath") && !algoritmo.equals("highestWeight") && !algoritmo.equals("smallestWeight") && !algoritmo.equals("mts")) {
                    System.out.println("O algoritmo tem de ser shortestPath ou highestWeight ou smallestWeight ou mts");
                    System.out.println("Introduzir o algoritmo de ida do bot 2: ");
                    algoritmo = scanner.next();
                } else {
                    boolean algoritmoIgual = false;

                    for (int j = 0; j < numBots2; j++) {
                        if (bots2[j] != null && bots2[j].getAlgoritmo().equals(algoritmo) ) {
                            algoritmoIgual = true;
                            break;
                        }
                    }

                    if (algoritmoIgual) {
                        System.out.println("O algoritmo tem de ser diferente dos outros bots");
                        System.out.println("Introduzir o algoritmo de ida do bot 2: ");
                        algoritmo = scanner.next();
                    } else {
                        correto = 1;
                    }
                }
            }while (correto == 0);
            correto = 0;
            System.out.println("Introduzir o algoritmo de volta do bot do jogador 2: ");
            String algoritmoVolta = scanner.next();
            do{
                if (!algoritmoVolta.equals("shortestPath") && !algoritmoVolta.equals("highestWeight") && !algoritmoVolta.equals("smallestWeight") && !algoritmoVolta.equals("mts")) {
                    System.out.println("O algoritmo tem de ser shortestPath ou highestWeight ou smallestWeight");
                    System.out.println("Introduzir o algoritmo de Volta do bot 2: ");
                    algoritmoVolta = scanner.next();
                } else {
                    boolean algoritmoIgual = false;

                    for (int j = 0; j < numBots2; j++) {
                        if (bots2[j] != null && bots2[j].getAlgoritmo().equals(algoritmoVolta)) {
                            algoritmoIgual = true;
                            break;
                        }
                    }

                    if (algoritmoIgual) {
                        System.out.println("O algoritmo tem de ser diferente dos outros bots");
                        System.out.println("Introduzir o algoritmo de Volta do bot 2: ");
                        algoritmoVolta = scanner.next();
                    } else {
                        correto = 1;
                    }
                }
            }while (correto == 0);
            correto = 0;
            //aqui vai ser verificado se o algoritmo é valido a partir do nome, se calhar introduzir aqui uma enumeração
            bots2[i] = new Bot(nameBot, algoritmo,algoritmoVolta,game.getMap().getLocation(id2), game.getMap().getLocation(id1), this.game, name2);
        }
        Player player2 = new Player(name2,  game.getMap().getLocation(id2), bots2);
        this.game.addPlayer(player1);
        this.game.addPlayer(player2);
        this.game.printMap();
        this.menuMover();
    }

    /**
     * Method that starts the menu to move the bots
     */
    public void menuMover(){
        Boolean end = false;

        //give me random number between 1 and 2
        int randomNum = (int)(Math.random() * 2 + 1);

        int botP1 = 0;
        int botP2 = 0;

        //mover cada bot de cada jogador 1 de cada vez
        while (!end) {
            switch (randomNum) {
                case 1:
                    end = this.game.getPlayers()[0].getBots()[botP1].moveOnePosition();
                    randomNum = 2;
                    botP1++;
                    if (botP1 == this.game.getPlayers()[0].getBots().length) {
                        botP1 = 0;
                    }
                    break;
                case 2:
                    end = this.game.getPlayers()[1].getBots()[botP2].moveOnePosition();
                    randomNum = 1;
                    botP2++;
                    if (botP2 == this.game.getPlayers()[1].getBots().length) {
                        botP2 = 0;
                    }
                    break;
            }
            if (end) {
                System.out.println("Deseja exportar este mapa? (s/n)");
                String exportar = scanner.next();
                if (exportar.equals("s")) {
                    this.game.getMap().exportMapToJson("src/main/java/data/map.json");
                    System.out.println("Mapa exportado com sucesso");
                }
                System.exit(0);
            }
            System.out.println("Quer continuar a jogar? (s/n)");
            String continuar = scanner.next();
            if (continuar.equals("n")) {
                end = true;
            }
        }
    }

    /**
     * Method that starts the menu to move the bots of the imported map
     */
    public void menuMoverImportado(){
        int correto = 0;
        this.game.getMap().importMapFromJson("src/main/java/data/map.json");
        this.game.printMap();
        System.out.println("Introduzir o nome do jogador 1: ");
        String name1 = scanner.next();
        System.out.println("Introduzir o id da sua bandeira: ");
        int id1 = scanner.nextInt();
        do {
            if (id1 < 0 || id1 > this.game.getMap().getMap().getVertices().length) {
                System.out.println("O id da bandeira tem de ser entre 0 e " + this.game.getMap().getMap().getVertices().length);
                System.out.println("Introduzir o id da sua bandeira: ");
                id1 = scanner.nextInt();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        game.getMap().getLocation(id1);
        System.out.println("Introduzir o numero de bots: ");
        int numBots = scanner.nextInt();
        //numero de bots minimos é 1 e o maximo é de 3
        do {
            if (numBots < 1 || numBots > 3) {
                System.out.println("O numero de bots tem de ser entre 1 e 3");
                System.out.println("Introduzir o numero de bots: ");
                numBots = scanner.nextInt();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        System.out.println("Introduzir o nome do jogador 2: ");
        String name2 = scanner.next();
        do {
            if (name2.equals(name1)) {
                System.out.println("O nome do jogador 2 tem de ser diferente do jogador 1");
                System.out.println("Introduzir o nome do jogador 2: ");
                name2 = scanner.next();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        System.out.println("Introduzir o id da sua bandeira: ");
        int id2 = scanner.nextInt();
        do {
            if (id2 < 0 || id2 > this.game.getMap().getMap().getVertices().length && id2 != id1) {
                System.out.println("O id da bandeira tem de ser entre 0 e " + this.game.getMap().getMap().getVertices().length);
                System.out.println("Introduzir o id da sua bandeira: ");
                id2 = scanner.nextInt();
            } else {
                correto = 1;
            }
        }while (correto == 0);
        correto = 0;
        game.getMap().getLocation(id2);
        System.out.println("Introduzir o numero de bots do jogador 2: ");
        int numBots2 = scanner.nextInt();
        Bot[] bots1 = new Bot[numBots];
        for (int i = 0; i < numBots; i++) {
            System.out.println("Introduzir o nome do bot 1: ");
            String nameBot = scanner.next();
            System.out.println("Introduzir o algoritmo de ida do bot 1: ");
            String algoritmo = scanner.next();
            do{
                if (!algoritmo.equals("shortestPath") && !algoritmo.equals("highestWeight") && !algoritmo.equals("smallestWeight") && !algoritmo.equals("mts")) {
                    System.out.println("O algoritmo tem de ser shortestPath ou highestWeight ou smallestWeight");
                    System.out.println("Introduzir o algoritmo de ida do bot 1: ");
                    algoritmo = scanner.next();
                } else {
                    boolean algoritmoIgual = false;

                    for (int j = 0; j < numBots; j++) {
                        if (bots1[j] != null && bots1[j].getAlgoritmo().equals(algoritmo)) {
                            algoritmoIgual = true;
                            break;
                        }
                    }

                    if (algoritmoIgual) {
                        System.out.println("O algoritmo tem de ser diferente dos outros bots");
                        System.out.println("Introduzir o algoritmo de ida do bot 1: ");
                        algoritmo = scanner.next();
                    } else {
                        correto = 1;
                    }
                }
            }while (correto == 0);
            correto = 0;

            System.out.println("Introduzir o algoritmo de Volta do bot 1: ");
            String algoritmoVolta = scanner.next();
            do{
                if (!algoritmoVolta.equals("shortestPath") && !algoritmoVolta.equals("highestWeight") && !algoritmoVolta.equals("smallestWeight") && !algoritmoVolta.equals("mts")) {
                    System.out.println("O algoritmo tem de ser shortestPath ou highestWeight ou smallestWeight");
                    System.out.println("Introduzir o algoritmo de Volta do bot 1: ");
                    algoritmoVolta = scanner.next();
                } else {
                    boolean algoritmoIgual = false;

                    for (int j = 0; j < numBots; j++) {
                        if (bots1[j] != null && bots1[j].getAlgoritmo().equals(algoritmoVolta)) {
                            algoritmoIgual = true;
                            break;
                        }
                    }

                    if (algoritmoIgual) {
                        System.out.println("O algoritmo tem de ser diferente dos outros bots");
                        System.out.println("Introduzir o algoritmo de Volta do bot 1: ");
                        algoritmoVolta = scanner.next();
                    } else {
                        correto = 1;
                    }
                }
            }while (correto == 0);
            correto = 0;
            //aqui vai ser verificado se o algoritmo é valido a partir do nome, se calhar introduzir aqui uma enumeração
            bots1[i] = new Bot(nameBot, algoritmo,algoritmoVolta,game.getMap().getLocation(id1), game.getMap().getLocation(id2), this.game, name1);
        }
        Player player1 = new Player(name1,  game.getMap().getLocation(id1), bots1);
        Bot[] bots2 = new Bot[numBots2];
        for (int i = 0; i < numBots2; i++) {
            System.out.println("Introduzir o nome do bot do jogador 2: ");
            String nameBot = scanner.next();
            System.out.println("Introduzir o algoritmo do bot de Ida do jogador 2: ");
            String algoritmo = scanner.next();
            do{
                if (!algoritmo.equals("shortestPath") && !algoritmo.equals("highestWeight") && !algoritmo.equals("smallestWeight") && !algoritmo.equals("mts")) {
                    System.out.println("O algoritmo tem de ser shortestPath ou highestWeight ou smallestWeight");
                    System.out.println("Introduzir o algoritmo de Ida do bot 2: ");
                    algoritmo = scanner.next();
                } else {
                    boolean algoritmoIgual = false;

                    for (int j = 0; j < numBots2; j++) {
                        if (bots2[j] != null && bots2[j].getAlgoritmo().equals(algoritmo)) {
                            algoritmoIgual = true;
                            break;
                        }
                    }

                    if (algoritmoIgual) {
                        System.out.println("O algoritmo tem de ser diferente dos outros bots");
                        System.out.println("Introduzir o algoritmo de Ida do bot 2: ");
                        algoritmo = scanner.next();
                    } else {
                        correto = 1;
                    }
                }
            }while (correto == 0);
            correto = 0;
            System.out.println("Introduzir o algoritmo de Volta do bot do jogador 2: ");
            String algoritmoVolta = scanner.next();
            do{
                if (!algoritmoVolta.equals("shortestPath") && !algoritmoVolta.equals("highestWeight") && !algoritmoVolta.equals("smallestWeight") && !algoritmoVolta.equals("mts")) {
                    System.out.println("O algoritmo tem de ser shortestPath ou highestWeight ou smallestWeight");
                    System.out.println("Introduzir o algoritmo de Volta do bot 2: ");
                    algoritmoVolta = scanner.next();
                } else {
                    boolean algoritmoIgual = false;

                    for (int j = 0; j < numBots2; j++) {
                        if (bots2[j] != null && bots2[j].getAlgoritmo().equals(algoritmoVolta)) {
                            algoritmoIgual = true;
                            break;
                        }
                    }

                    if (algoritmoIgual) {
                        System.out.println("O algoritmo tem de ser diferente dos outros bots");
                        System.out.println("Introduzir o algoritmo de Volta do bot 2: ");
                        algoritmoVolta = scanner.next();
                    } else {
                        correto = 1;
                    }
                }
            }while (correto == 0);
            correto = 0;
            //aqui vai ser verificado se o algoritmo é valido a partir do nome, se calhar introduzir aqui uma enumeração
            bots2[i] = new Bot(nameBot, algoritmo,algoritmoVolta,game.getMap().getLocation(id2), game.getMap().getLocation(id1), this.game, name2);
        }
        Player player2 = new Player(name2,  game.getMap().getLocation(id2), bots2);
        this.game.addPlayer(player1);
        this.game.addPlayer(player2);
        this.game.printMap();
        this.menuMover();
    }
}
