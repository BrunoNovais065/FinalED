package Collections.Stacks;

import Collections.Lists.Node;
import Collections.Exceptions.EmptyCollectionException;

/**
 * Represents a stack data structure implemented using a linked list.
 *
 * @param <T> the type of elements stored in the stack
 */
public class LinkedStack<T> implements StackADT<T> {
    protected Node<T> top;
    protected int size;

    /**
     * Constructs an empty linked stack.
     */
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Adds the specified element to the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     */
    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    /**
     * Removes and returns the element from the top of the stack.
     *
     * @return the element removed from the top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = top.getElement();
        top = top.getNext();
        size--;
        return removed;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return top.getElement();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the stack.
     * The string includes the class name and the elements
     * in the stack, enclosed in curly braces.
     *
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = top;
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
