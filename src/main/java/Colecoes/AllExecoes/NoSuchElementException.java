// The class NoSuchElementException represents an exception thrown when an attempt
// is made to access or retrieve an element that is not present.

package Colecoes.AllExecoes;

/**
 * RuntimeException subclass indicating the absence of a expected element in a
 * collection.
 */
public class NoSuchElementException extends RuntimeException {

    /**
     * Constructs a NoSuchElementException without any specific detail message.
     */
    public NoSuchElementException() {
        super();
    }

    /**
     * Constructs a NoSuchElementException with a provided detail message.
     *
     * @param message The detailed message associated with this exception.
     */
    public NoSuchElementException(String message) {
        super(message);
    }
}
