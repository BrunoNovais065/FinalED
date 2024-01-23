package Collections.Lists;

import Collections.Exceptions.NonComparableElementException;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList() {
        super();
    }

    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Adds the specified element to this ordered list. The element must be
     * an instance of Comparable. Throws NonComparableElementException if
     * the element is not comparable.
     *
     * @param element the element to be added to the list
     * @throws NonComparableElementException if the element is not comparable
     */
    @Override
    public void add(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable))
            throw new NonComparableElementException();
        if (size() == list.length)
            expandCapacity();
        int index = 0;
        while (index < size && ((Comparable<T>) element).compareTo(list[index]) > 0) {
            index++;
        }
        for (int shift = size; shift > index; shift--) {
            list[shift] = list[index - 1];
        }
        list[index] = element;
        size++;
        modCount++;
    }
}
