// This class, ConcurrentModificationException, represents an exception thrown
// when a collection undergoes modification while an iterator is actively in use.

package Collections.Exceptions;

/**
 * RuntimeException subclass representing a situation where concurrent
 * modification
 * of a collection occurs during iterator usage.
 */
public class ConcurrentModificationException extends RuntimeException {

    /**
     * Constructs a new ConcurrentModificationException without any specific
     * message.
     */
    public ConcurrentModificationException() {
        super();
    }

    /**
     * Constructs a new ConcurrentModificationException with a provided message.
     *
     * @param message The message associated with this exception.
     */
    public ConcurrentModificationException(String message) {
        super(message);
    }
}
