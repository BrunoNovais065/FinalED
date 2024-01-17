package Colecoes.Lists;

import Colecoes.AllExecoes.EmptyCollectionException;
import Colecoes.AllExecoes.NoSuchElementException;

import java.util.Iterator;

import org.w3c.dom.Node;

public class CircularLinkedList<T> implements ListADT<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public CircularLinkedList() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adds a new node containing the specified element to the circular linked list.
     *
     * @param element the element to be added to the list
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            front.setNext(rear);
            rear.setNext(front);
        } else {
            newNode.setNext(front);
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the first element from the circular linked list.
     *
     * @return the element that was removed from the front of the list
     * @throws EmptyCollectionException if the list is empty and the operation is
     *                                  not allowed
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = front.getElement();
        front = front.getNext();
        rear.setNext(front);
        size--;
        return removed;
    }

    /**
     * Removes and returns the last element from the circular linked list.
     *
     * @return the element that was removed from the end of the list
     * @throws EmptyCollectionException if the list is empty and the operation is
     *                                  not allowed
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = rear.getElement();
        Node<T> current = front;
        while (current.getNext() != rear) {
            current = current.getNext();
        }
        current.setNext(front);
        rear = current;
        size--;
        return removed;
    }

    /**
     * Removes the first occurrence of the specified element from the circular
     * linked list.
     *
     * @param element the element to be removed from the list
     * @return the element that was removed
     * @throws EmptyCollectionException if the list is empty and the operation is
     *                                  not allowed
     * @throws NoSuchElementException   if the specified element is not found in the
     *                                  list
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        Node<T> current = front;
        while (current != rear && !current.getElement().equals(element)) {
            current = current.getNext();
        }
        if (current == rear && !current.getElement().equals(element))
            throw new NoSuchElementException();
        if (current == front)
            return removeFirst();
        if (current == rear)
            return removeLast();
        T result = current.getElement();
        Node<T> previous = front;
        while (previous.getNext() != current) {
            previous = previous.getNext();
        }
        previous.setNext(current.getNext());
        size--;
        return result;
    }

    /**
     * Returns the first element of the circular linked list.
     *
     * @return the first element of the circular linked list
     * @throws EmptyCollectionException if the list is empty and the operation is
     *                                  not allowed
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return front.getElement();
    }

    /**
     * Returns the last element of the circular linked list.
     *
     * @return the last element of the circular linked list
     * @throws EmptyCollectionException if the list is empty and the operation is
     *                                  not allowed
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return rear.getElement();
    }

    /**
     * Checks if the circular linked list contains the specified target element.
     *
     * @param target the element to be checked for existence in the circular linked
     *               list
     * @return true if the circular linked list contains the target element, false
     *         otherwise
     * @throws EmptyCollectionException if the list is empty and the operation is
     *                                  not allowed
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        Node<T> current = front;
        while (current != rear && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        return current.getElement().equals(target);
    }

    /**
     * Checks if the circular linked list is empty.
     *
     * @return true if the circular linked list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the current size of the circular linked list.
     *
     * @return the number of elements in the circular linked list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in the circular linked list.
     *
     * @return an iterator over the elements in the circular linked list
     */
    @Override
    public Iterator<T> iterator() {
        LinkedUnorderedList<T> list = new LinkedUnorderedList<>();
        Node<T> current = front;
        while (current != rear) {
            list.addToRear(current.getElement());
            current = current.getNext();
        }
        list.addToRear(current.getElement());
        return list.iterator();
    }

    /**
     * Returns a string representation of the circular linked list.
     *
     * @return a string representation of the circular linked list
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = front;
            while (current != rear) {
                result += current.getElement() + " ";
                current = current.getNext();
            }
            result += current.getElement() + " ";
        }
        return result + "}";
    }
}
