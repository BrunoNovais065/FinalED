package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.NonComparableElementException;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList() {
        super();
    }

    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Adiciona o elemento especificado a esta lista ordenada. O elemento deve ser
     * uma instância de Comparable. Lança NonComparableElementException se
     * o elemento não for comparável.
     *
     * @param element o elemento a ser adicionado à lista
     * @throws NonComparableElementException se o elemento não for comparável
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
