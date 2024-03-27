package TodasColecoes.Queues;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class QueueArrayTwoIndices<T> implements QueueADT<T> {
    private final static int DEFAULT_CAPACITY = 100;
    private T[] queue;
    private int toAdd, toRemove;

    /**
     * Constrói uma fila com a capacidade inicial padrão.
     */
    public QueueArrayTwoIndices() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.toAdd = 0;
        this.toRemove = 0;
    }

    /**
     * Constrói uma fila com a capacidade inicial especificada.
     *
     * @param initialCapacity a capacidade inicial da fila
     */
    public QueueArrayTwoIndices(int initialCapacity) {
        this.queue = (T[]) (new Object[initialCapacity]);
        this.toAdd = 0;
        this.toRemove = 0;
    }

    /**
     * Expande a capacidade da fila criando um novo array com o dobro
     * da capacidade atual e copiando os elementos do array antigo para o novo.
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);
        for (int i = 0; i < queue.length; i++) {
            larger[i] = queue[i];
        }
        queue = larger;
    }

    /**
     * Adiciona o elemento especificado à fila.
     *
     * @param element o elemento a ser adicionado
     */
    @Override
    public void enqueue(T element) {
        if (size() + 1 == queue.length)
            expandCapacity();
        queue[toAdd] = element;
        toAdd = (toAdd + 1) % queue.length;
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
        T removed = queue[toRemove];
        queue[toRemove] = null;
        toRemove = (toRemove + 1) % queue.length;
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
        return queue[toRemove];
    }

    /**
     * Verifica se a fila está vazia.
     *
     * @return true se a fila estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return toAdd == toRemove;
    }

    /**
     * Retorna o número de elementos na fila.
     *
     * @return o número de elementos na fila
     */
    @Override
    public int size() {
        return (toAdd - toRemove + queue.length) % queue.length;
    }

    /**
     * Retorna uma representação em string da fila.
     *
     * @return uma representação em string da fila
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            int index = toRemove;
            while (index != toAdd) {
                result += queue[index] + " ";
                index = (index + 1) % queue.length;
            }
        }
        return result + "}";
    }
}
