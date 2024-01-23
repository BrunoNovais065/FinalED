package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;

/**
 * Represents a circular array queue data structure.
 * @param <T> the type of elements stored in the queue
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

    private final static int DEFAULT_CAPACITY = 100;
    private T[] queue;
    private int front, rear, count;

    /**
     * CircularArrayQueue represents a circular array implementation of a queue.
     *
     * @param <T> the type of elements stored in the queue
     */
    public CircularArrayQueue() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    /**
     * Constructs an empty circular array queue with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the queue
     */
    public CircularArrayQueue(int initialCapacity) {
        this.queue = (T[]) (new Object[initialCapacity]);
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    /**
     * Expands the capacity of the circular array queue.
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);
        for (int index = front; index < rear; index++) {
            larger[index] = queue[index];
        }
        queue = larger;
    }

    /**
     * Adds the specified element to the rear of the queue.
     *
     * @param element the element to be added to the queue
     */
    @Override
    public void enqueue(T element) {
        if (size() == queue.length - 1)
            expandCapacity();
        queue[rear] = element;
        rear = (rear + 1) % queue.length;
        count++;
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
        T removed = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;
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
        return queue[front];
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
        return count;
    }

    /**
     * Returns a string representation of the elements in the queue.
     *
     * @return a string representation of the elements in the queue
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            int current = front;
            do {
                result += queue[current] + " ";
                current = (current + 1) % queue.length;
            } while (current != rear);
        }
        return result + "}";
    }
}
