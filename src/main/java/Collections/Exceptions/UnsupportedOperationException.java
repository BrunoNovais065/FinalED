// The class UnsupportedOperationException serves as an exception thrown when an
// attempt is made to perform an operation that is not supported.

package Collections.Exceptions;

/**
 * RuntimeException subclass indicating the occurrence of an unsupported
 * operation.
 */
public class UnsupportedOperationException extends RuntimeException {

    /**
     * Constructs an UnsupportedOperationException without any specific detail
     * message.
     */
    public UnsupportedOperationException() {
        super();
    }

    /**
     * Constructs an UnsupportedOperationException with a provided detail message.
     *
     * @param message The detailed message associated with this exception.
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }
}
