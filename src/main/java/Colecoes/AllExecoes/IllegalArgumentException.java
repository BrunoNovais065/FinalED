// The class IllegalArgumentException represents an exception thrown when an argument
// is determined to be invalid.

package Colecoes.AllExecoes;

/**
 * RuntimeException subclass indicating the presence of an invalid argument.
 */
public class IllegalArgumentException extends RuntimeException {

    /**
     * Constructs an IllegalArgumentException without any specific detail message.
     */
    public IllegalArgumentException() {
        super();
    }

    /**
     * Constructs an IllegalArgumentException with a provided detail message.
     *
     * @param message The detailed message associated with this exception.
     */
    public IllegalArgumentException(String message) {
        super(message);
    }
}
