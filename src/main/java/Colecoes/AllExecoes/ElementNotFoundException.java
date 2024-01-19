// The class ElementNotFoundException represents an exception thrown when a specific
// element is not found within a collection.

package Colecoes.AllExecoes;

/**
 * RuntimeException subclass indicating the absence of a desired element in a
 * collection.
 */
public class ElementNotFoundException extends RuntimeException {

    /**
     * Constructs a new ElementNotFoundException without any specific message.
     */
    public ElementNotFoundException() {
        super();
    }

    /**
     * Constructs a new ElementNotFoundException with a provided message.
     *
     * @param message The message associated with this exception.
     */
    public ElementNotFoundException(String message) {
        super(message);
    }
}
