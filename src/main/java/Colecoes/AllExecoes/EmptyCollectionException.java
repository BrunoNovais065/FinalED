// The class EmptyCollectionException serves as an exception thrown when an operation
// is attempted on an empty collection.

package Colecoes.AllExecoes;

/**
 * RuntimeException subclass signaling the occurrence of an operation on an
 * empty collection.
 */
public class EmptyCollectionException extends RuntimeException {

    /**
     * Constructs an EmptyCollectionException without any specific message.
     */
    public EmptyCollectionException() {
        super();
    }

    /**
     * Constructs an EmptyCollectionException with a provided message.
     *
     * @param message The message associated with this exception.
     */
    public EmptyCollectionException(String message) {
        super(message);
    }
}
