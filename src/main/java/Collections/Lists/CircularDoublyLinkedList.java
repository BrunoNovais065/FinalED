package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

import java.util.Iterator;

/**
 * CircularDoublyLinkedList represents a doubly linked circular list.
 * @param <T> the generic type of the list
 */
public class CircularDoublyLinkedList<T> implements ListADT<T> {
    private DoublyNode<T> head;
    private DoublyNode<T> tail;
    private int count;

    /**
     * Adds the specified element to the end of the doubly linked circular list.
     *
     * @param element the element to be added
     */
    public void add(T element) {
        DoublyNode<T> newNode = new DoublyNode<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            head.setNext(tail);
            tail.setPrevious(head);
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            tail.setNext(head);
            head.setPrevious(tail);
        }
        count++;
    }

    /**
     * Removes and returns the first element from the doubly linked circular list.
     *
     * @return the element that was removed
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = head.getElement();
        head = head.getNext();
        head.setPrevious(tail);
        tail.setNext(head);
        count--;
        return removed;
    }

    /**
     * Removes and returns the last element from the doubly linked circular list.
     *
     * @return the element that was removed
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = tail.getElement();
        tail = tail.getPrevious();
        tail.setNext(head);
        head.setPrevious(tail);
        count--;
        return removed;
    }

    /**
     * Removes the specified element from the doubly linked circular list.
     *
     * @param element the element to be removed
     * @return the element that was removed
     * @throws EmptyCollectionException if the list is empty
     * @throws NoSuchElementException   if the specified element is not found in the
     *                                  list
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        DoublyNode<T> current = head;
        while (current != tail && !current.getElement().equals(element)) {
            current = current.getNext();
        }
        if (current.getElement().equals(element)) {
            if (current == head) {
                return removeFirst();
            } else if (current == tail) {
                return removeLast();
            } else {
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());
                count--;
                return current.getElement();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns the first element in the doubly linked circular list.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return head.getElement();
    }

    /**
     * Returns the last element in the doubly linked circular list.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return tail.getElement();
    }

    /**
     * Checks whether the doubly linked circular list contains a specific element.
     *
     * @param target the element to check for
     * @return true if the element is found, false otherwise
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        DoublyNode<T> current = head;
        while (current != tail && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        return current.getElement().equals(target);
    }

    /**
     * Checks whether the doubly linked circular list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in the doubly linked circular list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of the doubly linked circular list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            DoublyNode<T> current = head;
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != head);
        }
        return result + "}";
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        DoublyLinkedUnorderedList<T> list = new DoublyLinkedUnorderedList<>();
        DoublyNode<T> current = head;
        do {
            list.addToRear(current.getElement());
        } while ((current = current.getNext()) != head);
        return list.iterator();
    }
}
