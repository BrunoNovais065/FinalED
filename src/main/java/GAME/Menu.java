package GAME;

import java.util.Scanner;


public class Menu {

    private Game game;

    private Scanner scanner = new Scanner(System.in);

    public Menu() {
        this.game = new Game();
    }

    public void menuInicial(){
        System.out.println("Bem vindo ao jogo da bandeira");
        System.out.println("1. Jogar");
        System.out.println("2. Sair");
        System.out.println("Introduzir uma opção: ");
        int opcion = 0;
        opcion = scanner.nextInt();
        switch(opcion){
            case 1:
                this.menuJogar();
                break;
            case 2:
                System.out.println("Gracias por jugar");
                break;
            default:
                System.out.println("Opcion invalida");
                this.menuInicial();
                break;
        }
    }

    public void menuJogar(){
        int correto = 0;
        System.out.println("Introduzir o tamanho do mapa: ");
        int size = scanner.nextInt();
        System.out.println("Introduzir se o mapa é circular: ");
        boolean isCircular = scanner.nextBoolean();
        System.out.println("Introduzir a percentagem de obstaculos: ");
        double obstaclePercentage = scanner.nextDouble();
        this.game.generateRandomMap(size, isCircular, obstaclePercentage);
        this.game.printMap();
        System.out.println("Introduzir o nome do jogador 1: ");
        String name1 = scanner.next();
        System.out.println("Introduzir o id da sua bandeira: ");
        int id1 = scanner.nextInt();
        game.getMap().getLocation(id1);
        System.out.println("Introduzir o numero de bots: ");
        int numBots = scanner.nextInt();
        Bot[] bots1 = new Bot[numBots];
        for (int i = 0; i < numBots; i++) {
            System.out.println("Introduzir o nome do bot: ");
            String nameBot = scanner.next();
            System.out.println("Introduzir o algoritmo do bot: ");
            String algoritmo = scanner.next();
            //aqui vai ser verificado se o algoritmo é valido a partir do nome, se calhar introduzir aqui uma enumeração
            bots1[i] = new Bot(nameBot, algoritmo);
        }
        Player player1 = new Player(name1,  new Location(id1), bots1);
        System.out.println("Introduzir o nome do jogador 2: ");
        String name2 = scanner.next();
        System.out.println("Introduzir o id da sua bandeira: ");
        int id2 = scanner.nextInt();
        game.getMap().getLocation(id2);
        System.out.println("Introduzir o numero de bots: ");
        int numBots2 = scanner.nextInt();
        Bot[] bots2 = new Bot[numBots2];
        for (int i = 0; i < numBots2; i++) {
            System.out.println("Introduzir o nome do bot: ");
            String nameBot = scanner.next();
            System.out.println("Introduzir o algoritmo do bot: ");
            String algoritmo = scanner.next();
            //aqui vai ser verificado se o algoritmo é valido a partir do nome, se calhar introduzir aqui uma enumeração
            bots2[i] = new Bot(nameBot, algoritmo);
        }
        Player player2 = new Player(name2,  new Location(id2), bots2);
        this.game.addPlayer(player1);
        this.game.addPlayer(player2);
        this.game.printMap();
        this.menuMover();
    }

    public void menuMover(){

    }
}
