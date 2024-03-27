package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class LinkedListSentinel<T> {
    private final Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Controi uma linked list vazia.
     */
    public LinkedListSentinel() {
        this.tail = new Node<>(null);
        this.head = tail;
        this.size = 0;
    }

    /**
     * Adiciona o elemento especificado à linked list.
     *
     * @param element o elemento a ser adicionado à linked list
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    /**
     * Remove o elemento no índice especificado da lista encadeada.
     *
     * @param index o índice do elemento a ser removido
     * @throws EmptyCollectionException  se a lista encadeada estiver vazia
     * @throws IndexOutOfBoundsException se o índice estiver fora dos limites
     */
    public void remove(int index) throws EmptyCollectionException, IndexOutOfBoundsException {
        if (isEmpty())
            throw new EmptyCollectionException();
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        size--;
    }

    /**
     * Verifica se a lista encadeada está vazia.
     *
     * @return true se a lista encadeada estiver vazia, false caso contrário
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos na lista encadeada.
     *
     * @return o número de elementos na lista encadeada
     */
    public int size() {
        return size;
    }

    /**
     * Retorna um array que contém todos os elementos na lista encadeada na ordem em que aparecem.
     *
     * @return um array que contém todos os elementos na lista encadeada na ordem em que aparecem
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[size()];
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            current = current.getNext();
            array[i] = current.getElement();
        }
        return array;
    }

    /**
     * Retorna uma representação em string da lista encadeada.
     *
     * @return uma representação em string da lista encadeada
     */
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = head.getNext();
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
