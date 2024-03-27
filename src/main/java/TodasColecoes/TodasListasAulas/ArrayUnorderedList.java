package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NoSuchElementException;

public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList() {
        super();
    }

    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }


    /**
     * Adiciona o elemento especificado na frente desta lista não ordenada.
     * Se o tamanho da lista for igual à sua capacidade, a capacidade é expandida.
     *
     * @param element o elemento a ser adicionado na frente da lista
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
     * Adiciona o elemento especificado ao final desta lista não ordenada.
     * Se o tamanho da lista for igual à sua capacidade, a capacidade é expandida.
     *
     * @param element o elemento a ser adicionado ao final da lista
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
     * Adiciona o elemento especificado após o elemento de destino especificado.
     * Se o tamanho da lista for igual à sua capacidade, a capacidade é expandida.
     *
     * @param element o elemento a ser adicionado após o elemento de destino
     * @param target  o elemento após o qual o novo elemento será adicionado
     * @throws EmptyCollectionException se a lista estiver vazia
     * @throws NoSuchElementException   se o elemento de destino não for encontrado
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
