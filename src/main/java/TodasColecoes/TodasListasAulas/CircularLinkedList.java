package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NoSuchElementException;

import java.util.Iterator;


public class CircularLinkedList<T> implements ListADT<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public CircularLinkedList() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adiciona um novo nó contendo o elemento especificado à lista encadeada circular.
     *
     * @param element o elemento a ser adicionado à lista
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            front.setNext(rear);
            rear.setNext(front);
        } else {
            newNode.setNext(front);
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }


    /**
     * Remove e retorna o primeiro elemento da lista encadeada circular.
     *
     * @return o elemento que foi removido da frente da lista
     * @throws EmptyCollectionException se a lista estiver vazia e a operação não for permitida
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = front.getElement();
        front = front.getNext();
        rear.setNext(front);
        size--;
        return removed;
    }

    /**
     * Remove e retorna o último elemento da lista encadeada circular.
     *
     * @return o elemento que foi removido do final da lista
     * @throws EmptyCollectionException se a lista estiver vazia e a operação não for permitida
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = rear.getElement();
        Node<T> current = front;
        while (current.getNext() != rear) {
            current = current.getNext();
        }
        current.setNext(front);
        rear = current;
        size--;
        return removed;
    }

    /**
     * Remove e retorna o elemento especificado da lista encadeada circular.
     *
     * @param element o elemento a ser removido da lista
     * @return o elemento que foi removido da lista
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        Node<T> current = front;
        while (current != rear && !current.getElement().equals(element)) {
            current = current.getNext();
        }
        if (current == rear && !current.getElement().equals(element))
            throw new NoSuchElementException();
        if (current == front)
            return removeFirst();
        if (current == rear)
            return removeLast();
        T result = current.getElement();
        Node<T> previous = front;
        while (previous.getNext() != current) {
            previous = previous.getNext();
        }
        previous.setNext(current.getNext());
        size--;
        return result;
    }

    /**
     * Retorna o primeiro elemento da lista encadeada circular.
     *
     * @return o primeiro elemento da lista encadeada circular
     * @throws EmptyCollectionException se a lista estiver vazia e a operação não for permitida
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return front.getElement();
    }

    /**
     * Retorna o último elemento da lista encadeada circular.
     *
     * @return o último elemento da lista encadeada circular
     * @throws EmptyCollectionException se a lista estiver vazia e a operação não for permitida
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return rear.getElement();
    }

    /**
     * Verifica se a lista encadeada circular contém o elemento especificado.
     *
     * @param target o elemento a ser verificado na lista
     * @return true se o elemento estiver na lista, false caso contrário
     * @throws EmptyCollectionException se a lista estiver vazia e a operação não for permitida
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        Node<T> current = front;
        while (current != rear && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        return current.getElement().equals(target);
    }

    /**
     * Verifica se a lista encadeada circular está vazia.
     *
     * @return true se a lista estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos na lista encadeada circular.
     *
     * @return o número de elementos na lista encadeada circular
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retorna um iterador para a lista encadeada circular.
     *
     * @return um iterador para a lista encadeada circular
     */
    @Override
    public Iterator<T> iterator() {
        LinkedUnorderedList<T> list = new LinkedUnorderedList<>();
        Node<T> current = front;
        while (current != rear) {
            list.addToRear(current.getElement());
            current = current.getNext();
        }
        list.addToRear(current.getElement());
        return list.iterator();
    }

    /**
     * Retorna uma representação de string da lista encadeada circular.
     *
     * @return uma representação de string da lista encadeada circular
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = front;
            while (current != rear) {
                result += current.getElement() + " ";
                current = current.getNext();
            }
            result += current.getElement() + " ";
        }
        return result + "}";
    }
}
