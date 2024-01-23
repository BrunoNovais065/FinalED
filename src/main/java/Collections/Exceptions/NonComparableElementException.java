// The class NonComparableElementException serves as an exception thrown when an element
// lacks the ability to be compared.

package Collections.Exceptions;

/**
 * RuntimeException subclass indicating the occurrence of an operation requiring
 * comparability on a non-comparable element.
 */
public class NonComparableElementException extends RuntimeException {

    /**
     * Constructs a NonComparableElementException without any specific message.
     */
    public NonComparableElementException() {
        super();
    }

    /**
     * Constructs a NonComparableElementException with a provided message.
     *
     * @param message The message associated with this exception.
     */
    public NonComparableElementException(String message) {
        super(message);
    }
}
