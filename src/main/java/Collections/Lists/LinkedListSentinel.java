package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;

/**
 * A linked list implementation with a sentinel node.
 * @param <T> the type of elements in the linked list
 */
public class LinkedListSentinel<T> {
    private final Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Constructs an empty linked list with a sentinel node.
     * The sentinel node is initially set as both the head and tail of the list.
     */
    public LinkedListSentinel() {
        this.tail = new Node<>(null);
        this.head = tail;
        this.size = 0;
    }

    /**
     * Adds the specified element to the end of the linked list.
     *
     * @param element the element to be added to the list
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    /**
     * Removes the element at the specified index from the linked list.
     *
     * @param index the index of the element to be removed
     * @throws EmptyCollectionException  if the linked list is empty
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void remove(int index) throws EmptyCollectionException, IndexOutOfBoundsException {
        if (isEmpty())
            throw new EmptyCollectionException();
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        size--;
    }

    /**
     * Checks if the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the size of the linked list.
     *
     * @return the number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Converts the linked list to an array.
     *
     * @return an array containing the elements of the linked list
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[size()];
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            current = current.getNext();
            array[i] = current.getElement();
        }
        return array;
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return a string representation of the linked list
     */
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = head.getNext();
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
