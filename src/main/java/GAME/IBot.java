package GAME;

/**
 * This interface defines the contract that a bot must follow
 */
public interface IBot {
    /**
     * Method that moves the bot one position
     * @return true if the bot arrived to the destination, false if not
     */
    Boolean moveOnePosition();

    /**
     * Method that returns the number of moves that the bot has done
     * @return number of moves that the bot has done
     */
    int getJogadas();

    /**
     * Method that returns the name of the bot
     * @return name of the bot
     */
    String getAlgoritmo();
}
