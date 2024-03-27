package TodasColecoes.Queues;

import TodasColecoes.TodasListasAulas.Node;
import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class LinkedQueue<T> implements QueueADT<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;


    /**
     * Constrói uma fila vazia.
     */
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adiciona o elemento especificado à fila.
     *
     * @param element o elemento a ser adicionado à fila
     */
    @Override
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }
        rear = newNode;
        size++;
    }

    /**
     * Remove e retorna o elemento do início da fila.
     *
     * @return o elemento removido do início da fila
     * @throws EmptyCollectionException se a fila estiver vazia
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = front.getElement();
        front = front.getNext();
        size--;
        return removed;
    }

    /**
     * Retorna o elemento no início da fila sem removê-lo.
     *
     * @return o elemento no início da fila
     * @throws EmptyCollectionException se a fila estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return front.getElement();
    }

    /**
     * Verifica se a fila está vazia.
     *
     * @return true se a fila estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos na fila.
     *
     * @return o número de elementos na fila
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retorna uma representação em string da fila.
     *
     * @return uma representação em string da fila
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        Node<T> current = front;
        if (!isEmpty()) {
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
