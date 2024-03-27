package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NoSuchElementException;

import java.util.Iterator;


public class CircularDoublyLinkedList<T> implements ListADT<T> {
    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int count;


    /**
     * Adiciona o elemento especificado ao final da lista duplamente encadeada circular.
     *
     * @param element o elemento a ser adicionado
     */
    public void add(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
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
     * Remove e retorna o primeiro elemento da lista duplamente encadeada circular.
     *
     * @return o elemento que foi removido
     * @throws EmptyCollectionException se a lista estiver vazia
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
     * Remove e retorna o último elemento da lista duplamente encadeada circular.
     *
     * @return o elemento que foi removido
     * @throws EmptyCollectionException se a lista estiver vazia
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
     * Remove o elemento especificado da lista duplamente encadeada circular e o retorna.
     *
     * @param element o elemento a ser removido
     * @return o elemento que foi removido
     * @throws EmptyCollectionException se a lista estiver vazia
     * @throws NoSuchElementException   se o elemento especificado não for encontrado
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        DoubleNode<T> current = head;
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
     * Retorna o primeiro elemento na lista duplamente encadeada circular.
     *
     * @return o primeiro elemento na lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return head.getElement();
    }

    /**
     * Retorna o último elemento na lista duplamente encadeada circular.
     *
     * @return o último elemento na lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return tail.getElement();
    }

    /**
     * Retorna true se a lista duplamente encadeada circular contiver o elemento especificado.
     *
     * @param target o elemento a ser verificado
     * @return true se o elemento estiver na lista, false caso contrário
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        DoubleNode<T> current = head;
        while (current != tail && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        return current.getElement().equals(target);
    }

    /**
     * Retorna true se a lista duplamente encadeada circular estiver vazia.
     *
     * @return true se a lista estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos na lista duplamente encadeada circular.
     *
     * @return o número de elementos na lista
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Retorna uma representação de string da lista duplamente encadeada circular.
     *
     * @return uma representação de string da lista
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            DoubleNode<T> current = head;
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != head);
        }
        return result + "}";
    }

    /**
     * Retorna um iterador para a lista duplamente encadeada circular.
     *
     * @return um iterador para a lista
     */
    @Override
    public Iterator<T> iterator() {
        DoubleLinkedUnorderedList<T> list = new DoubleLinkedUnorderedList<>();
        DoubleNode<T> current = head;
        do {
            list.addToRear(current.getElement());
        } while ((current = current.getNext()) != head);
        return list.iterator();
    }
}
