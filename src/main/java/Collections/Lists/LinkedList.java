package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

import java.util.Iterator;

/**
 * Linked list implementation of the List ADT.
 * @param <T> the type of elements in the linked list
 */
public class LinkedList<T> implements ListADT<T>, Iterable<T> {
    protected Node<T> head, tail;
    protected int size, modCount;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Removes and returns the first element in the linked list.
     *
     * @return the removed element
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = head.getElement();
        head = head.getNext();
        size--;
        modCount++;
        return removed;
    }

    /**
     * Removes and returns the last element in the linked list.
     *
     * @return the removed element
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = tail.getElement();
        Node<T> current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        current.setNext(null);
        tail = current;
        size--;
        modCount++;
        return removed;
    }

    /**
     * Removes the specified element from the linked list.
     *
     * @param element the element to be removed
     * @return the removed element
     * @throws EmptyCollectionException if the list is empty
     * @throws NoSuchElementException   if the specified element is not found in the
     *                                  list
     */
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (!contains(element)) {
            throw new NoSuchElementException();
        }
        T removed;
        Node<T> current = head;
        if (current.getElement().equals(element)) {
            removed = removeFirst();
        } else if (tail.getElement().equals(element)) {
            removed = removeLast();
        } else {
            while (!current.getNext().getElement().equals(element)) {
                current = current.getNext();
            }
            removed = current.getNext().getElement();
            current.setNext(current.getNext().getNext());
            size--;
            modCount++;
        }
        return removed;
    }

    /**
     * Returns the first element in the linked list.
     *
     * @return the first element in the linked list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return head.getElement();
    }

    /**
     * Returns the last element in the linked list.
     *
     * @return the last element in the linked list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return tail.getElement();
    }

    /**
     * Checks if the linked list contains the specified target element.
     *
     * @param target the element to be checked for existence in the linked list
     * @return true if the element is found, false otherwise
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        Node<T> current = head;
        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Returns the number of elements in the linked list.
     *
     * @return the number of elements in the linked list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Clears all elements from the linked list, making it empty.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return a string containing the elements of the linked list
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = head;
            while (current != null) {
                result += current.getElement() + " ";
                current = current.getNext();
            }
        }
        return result + "}";
    }

    /**
     * Returns a string representation of the linked list using recursion.
     *
     * @return a string containing the elements of the linked list
     */
    public String print() {
        return getClass().getSimpleName() + " { " + print(head) + "}";
    }

    /**
     * Helper method for recursively printing the elements of the linked list.
     *
     * @param current the current node in the recursion
     * @return a string containing the elements of the linked list
     */
    private String print(Node<T> current) {
        if (current == null) {
            return "";
        }
        return current.getElement() + " " + print(current.getNext());
    }

    /**
     * Converts the linked list to an array.
     *
     * @return an array containing the elements of the linked list
     */
    public T[] toArray() {
        T[] result = (T[]) new Comparable[size()];
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            result[i] = current.getElement();
            current = current.getNext();
        }
        return result;
    }

    /**
     * Returns the element at the specified index in the linked list.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws EmptyCollectionException  if the linked list is empty
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public T get(int index) throws EmptyCollectionException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    /**
     * Sets the element at the specified index in the linked list to the specified
     * target element.
     *
     * @param index  the index at which to set the element
     * @param target the element to set at the specified index
     * @throws EmptyCollectionException  if the linked list is empty
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void set(int index, T target) throws EmptyCollectionException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setElement(target);
    }

    /**
     * Replaces occurrences of the specified existing element with the new element
     * in the linked list.
     *
     * @param existingElement the element to be replaced
     * @param newElement      the element to replace the existing element
     * @throws EmptyCollectionException if the linked list is empty
     * @throws NoSuchElementException   if the existing element is not found in the
     *                                  linked list
     */
    public void replace(T existingElement, T newElement) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        if (!contains(existingElement))
            throw new NoSuchElementException();
        Node<T> current = head;
        while (current != null) {
            if (current.getElement().equals(existingElement)) {
                current.setElement(newElement);
            }
            current = current.getNext();
        }
    }

    /**
     * Returns an iterator over the elements in this linked list in proper sequence.
     *
     * @return an iterator over the elements in this linked list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>() {
        };
    }

    /**
     * Basic iterator implementation for traversing a linked list.
     *
     * @param <E> the type of elements in the iterator
     */
    private abstract class BasicIterator<E> implements Iterator<T> {
        private Node<T> current;
        private int expectedModCount;
        private boolean okToRemove;

        /**
         * Constructs a new iterator starting from the head of the linked list.
         */
        public BasicIterator() {
            this.current = head;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        /**
         * Returns true if there are more elements in the iteration.
         *
         * @return true if the iteration has more elements
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration
         */
        public T next() {
            if (expectedModCount != modCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T next = current.getElement();
            current = current.getNext();
            okToRemove = true;
            return next;
        }

        /**
         * Removes the last element returned by the iterator from the underlying linked
         * list.
         *
         * @throws java.util.ConcurrentModificationException if the list was modified
         *                                                   during iteration
         * @throws IllegalStateException                     if the {@code next} method
         *                                                   has not yet been called,
         *                                                   or the {@code remove}
         *                                                   method has already been
         *                                                   called after the last call
         *                                                   to the {@code next} method
         */
        public void remove() {
            if (expectedModCount != modCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            LinkedList.this.remove(current.getElement());
            expectedModCount++;
            okToRemove = false;
        }
    }
}
