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
        //aqui o minimum é 10 e o maximum é 100
        int obstaclePercentage = scanner.nextInt();
        //turn into percentage decimal
        double obstaclePercentagee = (double) obstaclePercentage / 100;
        this.game.generateRandomMap(size, isCircular, obstaclePercentagee);
        this.game.printMap();
        System.out.println("Introduzir o nome do jogador 1: ");
        String name1 = scanner.next();
        System.out.println("Introduzir o id da sua bandeira: ");
        int id1 = scanner.nextInt();
        game.getMap().getLocation(id1);
        System.out.println("Introduzir o numero de bots: ");
        int numBots = scanner.nextInt();
        System.out.println("Introduzir o nome do jogador 2: ");
        String name2 = scanner.next();
        System.out.println("Introduzir o id da sua bandeira: ");
        int id2 = scanner.nextInt();
        game.getMap().getLocation(id2);
        System.out.println("Introduzir o numero de bots do jogador 2: ");
        int numBots2 = scanner.nextInt();
        Bot[] bots1 = new Bot[numBots];
        for (int i = 0; i < numBots; i++) {
            System.out.println("Introduzir o nome do bot 1: ");
            String nameBot = scanner.next();
            System.out.println("Introduzir o algoritmo do bot 1: ");
            String algoritmo = scanner.next();
            //aqui vai ser verificado se o algoritmo é valido a partir do nome, se calhar introduzir aqui uma enumeração
            bots1[i] = new Bot(nameBot, algoritmo,game.getMap().getLocation(id1), game.getMap().getLocation(id2), this.game, name1);
        }
        Player player1 = new Player(name1,  game.getMap().getLocation(id1), bots1);
        Bot[] bots2 = new Bot[numBots2];
        for (int i = 0; i < numBots2; i++) {
            System.out.println("Introduzir o nome do bot do jogador 2: ");
            String nameBot = scanner.next();
            System.out.println("Introduzir o algoritmo do bot do jogador 2: ");
            String algoritmo = scanner.next();
            //aqui vai ser verificado se o algoritmo é valido a partir do nome, se calhar introduzir aqui uma enumeração
            bots2[i] = new Bot(nameBot, algoritmo,game.getMap().getLocation(id2), game.getMap().getLocation(id1), this.game, name2);
        }
        Player player2 = new Player(name2,  game.getMap().getLocation(id2), bots2);
        this.game.addPlayer(player1);
        this.game.addPlayer(player2);
        this.game.printMap();
        this.menuMover();
    }

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
            System.out.println("Quer continuar a jogar? (s/n)");
            String continuar = scanner.next();
            if (continuar.equals("n")) {
                end = true;
            }
        }
        System.out.println("O jogo acabou");
    }
}
