package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class DoubleLinkedList<T> implements ListADT<T>, Iterable<T> {
    protected DoubleNode<T> head;
    protected DoubleNode<T> tail;
    protected int count, modCount;

    /**
     * Constrói uma lista duplamente encadeada vazia.
     */
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }


    /**
     * Remove e retorna o primeiro elemento desta lista duplamente encadeada.
     *
     * @return o primeiro elemento na lista
     * @throws EmptyCollectionException se a lista estiver vazia
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
     * Remove e retorna o último elemento desta lista duplamente encadeada.
     *
     * @return o último elemento na lista
     * @throws EmptyCollectionException se a lista estiver vazia
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
     * Remove e retorna o elemento especificado desta lista duplamente encadeada.
     *
     * @param element o elemento a ser removido
     * @return o elemento removido
     * @throws EmptyCollectionException se a lista estiver vazia
     * @throws NoSuchElementException   se o elemento especificado não for encontrado
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        boolean found = false;
        DoubleNode<T> current = head;
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
     * Retorna o primeiro elemento nesta lista duplamente encadeada.
     *
     * @return o primeiro elemento na lista
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
     * Retorna o último elemento nesta lista duplamente encadeada.
     *
     * @return o último elemento na lista
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
     * Verifica se esta lista duplamente encadeada contém o elemento especificado.
     *
     * @param target o elemento a ser verificado quanto à existência na lista
     * @return {@code true} se o elemento for encontrado; {@code false} caso contrário
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        DoubleNode<T> current = head;
        while (current != null) {
            if (target.equals(current.getElement())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Verifica se esta lista duplamente encadeada está vazia.
     *
     * @return {@code true} se a lista estiver vazia; {@code false} caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos nesta lista duplamente encadeada.
     *
     * @return o número de elementos na lista
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Retorna uma representação de string desta lista duplamente encadeada.
     *
     * @return uma representação de string da lista
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            DoubleNode<T> current = head;
            while (current != null) {
                result += current.getElement() + " ";
                current = current.getNext();
            }
        }
        return result + "}";
    }


    /**
     * Retorna um iterador sobre os elementos nesta lista duplamente encadeada.
     *
     * @return um iterador sobre os elementos na lista
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>() {
        };
    }

    /**
     * Classe interna para iteradores básicos.
     */
    public abstract class BasicIterator<E> implements Iterator<T> {
        protected DoubleNode<T> current;
        protected int expectedModCount;
        protected boolean okToRemove;

        public BasicIterator() {
            this.current = head;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        /**
         * Verifica se há um próximo elemento na iteração.
         *
         * @return {@code true} se houver um próximo elemento; {@code false} caso contrário
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Retorna o próximo elemento na iteração.
         *
         * @return o próximo elemento na iteração
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
         * Remove o último elemento retornado pela iteração da lista.
         */
        @Override
        public void remove() {
            if (modCount != this.expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new java.util.NoSuchElementException();
            }
            DoubleLinkedList.this.remove(current.getPrevious().getElement());
            expectedModCount++;
            okToRemove = false;
        }
    }
}
