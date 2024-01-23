package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DoublyLinkedList represents a doubly linked implementation of a list.
 * @param <T> the generic type of data element in this list
 */
public class DoublyLinkedList<T> implements ListADT<T>, Iterable<T> {
    protected DoublyNode<T> head;
    protected DoublyNode<T> tail;
    protected int count, modCount;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }

    /**
     * Removes and returns the first element from this doubly linked list.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = head.getElement();
        head = head.getNext();
        count--;
        if (isEmpty()) {
            tail = null;
        } else {
            head.setPrevious(null);
        }
        modCount++;
        return removed;
    }

    /**
     * Removes and returns the last element from this doubly linked list.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = tail.getElement();
        tail = tail.getPrevious();
        count--;
        if (isEmpty()) {
            head = null;
        } else {
            tail.setNext(null);
        }
        modCount++;
        return removed;
    }

    /**
     * Removes the first occurrence of the specified element from this doubly linked
     * list,
     * if it is present. If the list does not contain the element, it remains
     * unchanged.
     *
     * @param element the element to be removed from the list
     * @return the removed element
     * @throws EmptyCollectionException if the list is empty
     * @throws NoSuchElementException   if the specified element is not found in the
     *                                  list
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        boolean found = false;
        DoublyNode<T> current = head;
        while (current != null && !found) {
            if (element.equals(current.getElement())) {
                found = true;
            } else {
                current = current.getNext();
            }
        }
        if (!found) {
            throw new NoSuchElementException();
        }
        if (size() == 1) {
            head = tail = null;
        } else if (current.equals(head)) {
            head = current.getNext();
            head.setPrevious(null);
        } else if (current.equals(tail)) {
            tail = current.getPrevious();
            tail.setNext(null);
        } else {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
        count--;
        modCount++;
        return current.getElement();
    }

    /**
     * Returns the first element in this doubly linked list.
     *
     * @return the first element in the list
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
     * Returns the last element in this doubly linked list.
     *
     * @return the last element in the list
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
     * Checks whether this doubly linked list contains the specified element.
     *
     * @param target the element to be checked for existence in the list
     * @return {@code true} if the element is found; {@code false} otherwise
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        DoublyNode<T> current = head;
        while (current != null) {
            if (target.equals(current.getElement())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Checks whether this doubly linked list is empty.
     *
     * @return {@code true} if the list is empty; {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in this doubly linked list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of this doubly linked list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            DoublyNode<T> current = head;
            while (current != null) {
                result += current.getElement() + " ";
                current = current.getNext();
            }
        }
        return result + "}";
    }

    /**
     * Returns a new doubly linked unordered list that is a reversed version
     * of this list. The original list remains unchanged.
     *
     * @return a new reversed doubly linked unordered list
     */
    public DoublyLinkedUnorderedList<T> reverse() {
        DoublyLinkedUnorderedList<T> result = new DoublyLinkedUnorderedList<>();
        DoublyNode<T> current = head;
        while (current != null) {
            result.addToFront(current.getElement());
            current = current.getNext();
        }
        return result;
    }

    /**
     * Returns a string representation of this doubly linked unordered list,
     * displaying its elements in the forward order.
     *
     * @return a string representation of the list in the forward order
     */
    public String printForward() {
        return getClass().getSimpleName() + " { " + printForward(head) + "}";
    }

    /**
     * Generates a string representation of the elements in the forward order
     * starting from the specified node.
     *
     * @param node the starting node for generating the string representation
     * @return a string representation of the elements in the forward order
     */
    public String printForward(DoublyNode<T> node) {
        if (node == null)
            return "";
        return node.getElement() + " " + printForward(node.getNext());
    }

    /**
     * Generates a string representation of the elements in reverse order
     * starting from the specified node.
     *
     * @param node the starting node for generating the string representation
     * @return a string representation of the elements in reverse order
     */
    public String printBackwards() {
        return getClass().getSimpleName() + " { " + printBackwards(tail) + "}";
    }

    /**
     * Generates a string representation of the elements in reverse order
     * starting from the specified node and moving backward through the list.
     *
     * @param node the starting node for generating the string representation
     * @return a string representation of the elements in reverse order
     */
    public String printBackwards(DoublyNode<T> node) {
        if (node == null)
            return "";
        return node.getElement() + " " + printBackwards(node.getPrevious());
    }

    /**
     * Returns an iterator over the elements in this doubly linked unordered list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>() {
        };
    }

    /**
     * An abstract class implementing the {@code Iterator} interface for a doubly
     * linked list.
     *
     * @param <E> the type of elements in the iterator
     */
    public abstract class BasicIterator<E> implements Iterator<T> {
        protected DoublyNode<T> current;
        protected int expectedModCount;
        protected boolean okToRemove;

        public BasicIterator() {
            this.current = head;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         */
        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T result = current.getElement();
            current = current.getNext();
            okToRemove = true;
            return result;
        }

        /**
         * Removes from the underlying doubly linked list the last element returned by empty
         */
        @Override
        public void remove() {
            if (modCount != this.expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new java.util.NoSuchElementException();
            }
            DoublyLinkedList.this.remove(current.getPrevious().getElement());
            expectedModCount++;
            okToRemove = false;
        }
    }
}
