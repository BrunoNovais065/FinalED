package Colecoes.Queues;

import Colecoes.Stacks.LinkedStack;
import Colecoes.AllExecoes.EmptyCollectionException;

/**
 * Represents a queue data structure implemented using two stacks.
 * Elements are enqueued onto one stack and dequeued from another.
 *
 * @param <T> the type of elements stored in the queue
 */
public class StackQueue<T> implements QueueADT<T> {
    private final LinkedStack<T> stack1;
    private final LinkedStack<T> stack2;

    /**
     * Constructs an empty stack-based queue.
     */
    public StackQueue() {
        this.stack1 = new LinkedStack<>();
        this.stack2 = new LinkedStack<>();
    }

    /**
     * Transfers elements from stack1 to stack2 if stack2 is empty.
     * Throws an EmptyCollectionException if the queue is empty.
     *
     * @throws EmptyCollectionException if the queue is empty
     */
    private void transfer() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    /**
     * Enqueues the specified element onto the stack-based queue.
     *
     * @param element the element to be enqueued
     */
    @Override
    public void enqueue(T element) {
        stack1.push(element);
    }

    /**
     * Dequeues and returns the element from the front of the queue.
     * Transfers elements from stack1 to stack2 if necessary.
     *
     * @return the element dequeued from the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        transfer();
        return stack2.pop();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * Transfers elements from stack1 to stack2 if necessary.
     *
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        transfer();
        return stack2.peek();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }

    /**
     * Returns a string representation of the queue, including the
     * contents of both stacks.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " +
                "stack1= " + stack1 +
                ", stack2= " + stack2 +
                " }";
    }
}
