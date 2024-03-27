package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NoSuchElementException;

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
     * Remove e retorna o primeiro elemento na lista encadeada.
     *
     * @return o elemento removido
     * @throws EmptyCollectionException se a lista estiver vazia
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
     * Remove e retorna o último elemento na lista encadeada.
     *
     * @return o elemento removido
     * @throws EmptyCollectionException se a lista estiver vazia
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
     * Remove e retorna o elemento especificado da lista encadeada.
     *
     * @param element o elemento a ser removido da lista encadeada
     * @return o elemento removido
     * @throws EmptyCollectionException se a lista estiver vazia
     * @throws NoSuchElementException   se o elemento especificado não for encontrado na lista encadeada
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
     * Retorna o primeiro elemento na lista encadeada.
     *
     * @return o primeiro elemento na lista encadeada
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return head.getElement();
    }

    /**
     * Retorna o último elemento na lista encadeada.
     *
     * @return o último elemento na lista encadeada
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return tail.getElement();
    }

    /**
     * Verifica se a lista encadeada contém o elemento especificado como alvo.
     *
     * @param target o elemento a ser verificado quanto à existência na lista encadeada
     * @return true se o elemento for encontrado, false caso contrário
     * @throws EmptyCollectionException se a lista estiver vazia
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
     * Retorna o número de elementos na lista encadeada.
     *
     * @return o número de elementos na lista encadeada
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Verifica se a lista encadeada está vazia.
     *
     * @return true se a lista estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Limpa todos os elementos da lista encadeada, tornando-a vazia.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Retorna uma representação em string da lista encadeada.
     *
     * @return uma representação em string da lista encadeada
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
     * Retorna um iterador sobre os elementos nesta lista encadeada na sequência adequada.
     *
     * @return um iterador sobre os elementos nesta lista encadeada na sequência adequada
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>() {
        };
    }

    /**
     * An iterator over the elements in the linked list.
     * Implements the Iterator interface.
     *
     * @param <E> the type of elements in the linked list
     */
    private abstract class BasicIterator<E> implements Iterator<T> {
        private Node<T> current;
        private int expectedModCount;
        private boolean okToRemove;

        /**
         * Creates an iterator over the elements in the linked list.
         */
        public BasicIterator() {
            this.current = head;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        /**
         * Returns true if the iteration has more elements.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
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
         * Removes from the underlying collection the last element returned by this iterator.
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
