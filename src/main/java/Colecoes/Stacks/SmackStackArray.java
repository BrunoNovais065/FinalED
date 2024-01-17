package Colecoes.Stacks;

import Colecoes.AllExecoes.EmptyCollectionException;

/**
 * Represents a stack data structure implemented using an array
 * with an additional "smack" operation to remove and return the bottom element.
 *
 * @param <T> the type of elements stored in the stack
 */
public class SmackStackArray<T> extends ArrayStack<T> implements SmackStackADT<T> {

    /**
     * Constructs an empty smack stack with the default initial capacity.
     */

    public SmackStackArray() {
        super();
    }

    /**
     * Constructs an empty smack stack with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the smack stack
     */
    public SmackStackArray(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Removes and returns the bottom element from the smack stack.
     *
     * @return the bottom element removed from the smack stack
     * @throws EmptyCollectionException if the smack stack is empty
     */
    @Override
    public T smack() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T result = stack[0];
        for (int i = 0; i < top - 1; i++)
            stack[i] = stack[i + 1];
        top--;
        return result;
    }
}
