package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Class ArrayList represents an array implementation of a list.
 * @param <T> the type of elements in the ArrayList
 */
public class ArrayList<T> implements ListADT<T>, Iterable<T> {
    private final static int DEFAULT_CAPACITY = 100;
    protected T[] list;
    protected int size, modCount;

    /**
     * Constructs an ArrayList with the default capacity.
     */
    public ArrayList() {
        this.list = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * Constructs an ArrayList with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the ArrayList
     */
    public ArrayList(int initialCapacity) {
        this.list = (T[]) (new Object[initialCapacity]);
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * Expands the capacity of the ArrayList by creating a new array with double the
     * current capacity and copying elements from the old array to the new one.
     */
    protected void expandCapacity() {
        T[] newList = (T[]) (new Object[list.length * 2]);
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    /**
     * Removes and returns the first element in the ArrayList. Throws
     * EmptyCollectionException if the ArrayList is empty.
     *
     * @return the removed element
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = list[0];
        for (int shift = 0; shift < size() - 1; shift++) {
            list[shift] = list[shift + 1];
        }
        list[size() - 1] = null;
        size--;
        modCount++;
        return removed;
    }

    /**
     * Removes and returns the last element in the ArrayList. Throws
     * EmptyCollectionException if the ArrayList is empty.
     *
     * @return the removed last element
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T result = list[size() - 1];
        list[size() - 1] = null;
        size--;
        modCount++;
        return result;
    }

    /**
     * Removes the specified element from the ArrayList and returns it.
     * Throws EmptyCollectionException if the ArrayList is empty, and
     * NoSuchElementException if the specified element is not found.
     *
     * @param element the element to be removed
     * @return the removed element
     * @throws EmptyCollectionException if the ArrayList is empty
     * @throws NoSuchElementException   if the specified element is not found
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        int index = 0;
        while (index < size() && !list[index].equals(element)) {
            index++;
        }
        if (index == size())
            throw new NoSuchElementException();
        T removed = list[index];
        for (int shift = index; shift < size() - 1; shift++) {
            list[shift] = list[shift + 1];
        }
        list[size() - 1] = null;
        size--;
        modCount++;
        return removed;
    }

    /**
     * Returns the first element in the ArrayList. Throws EmptyCollectionException
     * if the ArrayList is empty.
     *
     * @return the first element in the ArrayList
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return list[0];
    }

    /**
     * Returns the last element in the ArrayList. Throws EmptyCollectionException
     * if the ArrayList is empty.
     *
     * @return the last element in the ArrayList
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return list[size() - 1];
    }

    /**
     * Checks whether the specified element is contained in the ArrayList.
     * Throws EmptyCollectionException if the ArrayList is empty.
     *
     * @param target the element to check for containment
     * @return true if the element is found, false otherwise
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        int index = 0;
        while (index < size() && !list[index].equals(target)) {
            index++;
        }
        return index < size();
    }

    /**
     * Checks whether the ArrayList is empty.
     *
     * @return true if the ArrayList is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return the number of elements in the ArrayList
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in the ArrayList.
     *
     * @return an iterator over the elements in the ArrayList
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator() {
        };
    }

    /**
     * Returns a string representation of the ArrayList.
     * The string contains the class name followed by the elements in the ArrayList.
     *
     * @return a string representation of the ArrayList
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                result += list[i] + " ";
            }
        }
        return result + "}";
    }

    /**
     * An iterator over the elements in the ArrayList.
     * Implements the Iterator interface.
     *
     * @param <T> the type of elements in the ArrayList
     */
    private abstract class BasicIterator implements Iterator<T> {
        private int current;
        private int expectedModCount;
        private boolean okToRemove;

        /**
         * Constructs a BasicIterator with initial values.
         * Initializes the iterator to start at the beginning of the ArrayList.
         */
        public BasicIterator() {
            this.current = 0;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        /**
         * Checks if there is a next element in the ArrayList.
         *
         * @return true if there is a next element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current < size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws ConcurrentModificationException if the ArrayList is modified during
         *                                         iteration
         * @throws NoSuchElementException          if there is no next element
         */
        @Override
        public T next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            okToRemove = true;
            return list[current++];
        }

        /**
         * Removes the last element returned by the iterator from the ArrayList.
         *
         * @throws ConcurrentModificationException if the ArrayList is modified during
         *                                         iteration
         * @throws NoSuchElementException          if remove() is called without a
         *                                         preceding call to next()
         */
        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new NoSuchElementException();
            }
            ArrayList.this.remove(list[current - 1]);
            current--;
            expectedModCount++;
            okToRemove = false;
        }
    }
}
