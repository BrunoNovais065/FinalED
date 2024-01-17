package Colecoes.Stacks;

import Colecoes.AllExecoes.EmptyCollectionException;

/**
 * Represents a stack data structure implemented using an array.
 *
 * @param <T> the type of elements stored in the stack
 */
public class ArrayStack<T> implements StackADT<T> {
    protected final static int DEFAULT_CAPACITY = 100;
    protected T[] stack;
    protected int top;

    /**
     * Constructs an empty stack with the default initial capacity.
     */
    public ArrayStack() {
        this.stack = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.top = 0;
    }

    /**
     * Constructs an empty stack with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the stack
     */
    public ArrayStack(int initialCapacity) {
        this.stack = (T[]) (new Object[initialCapacity]);
        this.top = 0;
    }

    /**
     * Expands the capacity of the stack by creating a larger array
     * and copying elements from the current array to the new one.
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[stack.length * 2]);
        for (int i = 0; i < stack.length; i++) {
            larger[i] = stack[i];
        }
        stack = larger;
    }

    /**
     * Adds the specified element to the top of the stack.
     * Expands the capacity if the stack is full.
     *
     * @param element the element to be pushed onto the stack
     */
    @Override
    public void push(T element) {
        if (size() == stack.length)
            expandCapacity();
        stack[top] = element;
        top++;
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
        this.top--;
        T result = stack[top];
        stack[top] = null;
        return result;
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
        return stack[top - 1];
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
        return top;
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
        for (int i = 0; i < top; i++) {
            result += stack[i] + " ";
        }
        return result + "}";
    }
}
