package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList() {
        super();
    }

    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Adds the specified element to the front of this unordered list.
     * If the size of the list is equal to its capacity, the capacity is expanded.
     *
     * @param element the element to be added to the front of the list
     */
    @Override
    public void addToFront(T element) {
        if (size() == list.length)
            expandCapacity();
        for (int shift = size(); shift > 0; shift--) {
            list[shift] = list[shift - 1];
        }
        list[0] = element;
        size++;
        modCount++;
    }

    /**
     * Adds the specified element to the rear of this unordered list.
     * If the size of the list is equal to its capacity, the capacity is expanded.
     *
     * @param element the element to be added to the rear of the list
     */
    @Override
    public void addToRear(T element) {
        if (size() == list.length)
            expandCapacity();
        list[size()] = element;
        size++;
        modCount++;
    }

    /**
     * Adds the specified element after the target element in this unordered list.
     * If the size of the list is equal to its capacity, the capacity is expanded.
     *
     * @param element the element to be added after the target element
     * @param target  the target element after which the new element will be added
     * @throws EmptyCollectionException if the list is empty
     * @throws NoSuchElementException   if the target element is not found in the
     *                                  list
     */
    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        if (size() == list.length)
            expandCapacity();
        int index = 0;
        while (index < size() && !target.equals(list[index])) {
            index++;
        }
        if (index == size) {
            throw new NoSuchElementException();
        }
        for (int shift = size; shift > index; shift--) {
            list[shift] = list[shift - 1];
        }
        list[index + 1] = element;
        size++;
        modCount++;
    }
}
