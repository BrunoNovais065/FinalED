package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;

/**
 * Represents a queue data structure implemented using an array with two
 * indices.
 * Elements are added to the rear and removed from the front.
 *
 * @param <T> the type of elements stored in the queue
 */
public class QueueArrayTwoIndices<T> implements QueueADT<T> {
    private final static int DEFAULT_CAPACITY = 100;
    private T[] queue;
    private int toAdd, toRemove;

    /**
     * Constructs a queue with the default initial capacity.
     */
    public QueueArrayTwoIndices() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.toAdd = 0;
        this.toRemove = 0;
    }

    /**
     * Constructs a queue with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the queue
     */
    public QueueArrayTwoIndices(int initialCapacity) {
        this.queue = (T[]) (new Object[initialCapacity]);
        this.toAdd = 0;
        this.toRemove = 0;
    }

    /**
     * Expands the capacity of the queue by creating a larger array
     * and copying elements from the current array to the new one.
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);
        for (int i = 0; i < queue.length; i++) {
            larger[i] = queue[i];
        }
        queue = larger;
    }

    /**
     * Adds the specified element to the rear of the queue.
     * Expands the capacity if the queue is almost full.
     *
     * @param element the element to be added to the rear of the queue
     */
    @Override
    public void enqueue(T element) {
        if (size() + 1 == queue.length)
            expandCapacity();
        queue[toAdd] = element;
        toAdd = (toAdd + 1) % queue.length;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element removed from the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = queue[toRemove];
        queue[toRemove] = null;
        toRemove = (toRemove + 1) % queue.length;
        return removed;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return queue[toRemove];
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return toAdd == toRemove;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return (toAdd - toRemove + queue.length) % queue.length;
    }

    /**
     * Returns a string representation of the queue.
     * The string includes the class name and the elements
     * in the queue, enclosed in curly braces.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            int index = toRemove;
            while (index != toAdd) {
                result += queue[index] + " ";
                index = (index + 1) % queue.length;
            }
        }
        return result + "}";
    }
}
