package Collections.Queues;

import Collections.Lists.Node;
import Collections.Exceptions.EmptyCollectionException;

/**
 * Represents a linked queue data structure.
 * A linked queue is a collection of elements with a
 * first-in, first-out (FIFO) ordering.
 *
 * @param <T> the type of elements stored in the queue
 */
public class LinkedQueue<T> implements QueueADT<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    /**
     * Constructs an empty linked queue.
     * Initializes the front and rear pointers to null.
     * Sets the size of the queue to 0.
     */
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adds the specified element to the rear of the queue.
     *
     * @param element the element to be added to the queue
     */
    @Override
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }
        rear = newNode;
        size++;
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
        T removed = front.getElement();
        front = front.getNext();
        size--;
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
        return front.getElement();
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
        return size;
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
        Node<T> current = front;
        if (!isEmpty()) {
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
