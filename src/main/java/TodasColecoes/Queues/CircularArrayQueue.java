package TodasColecoes.Queues;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class CircularArrayQueue<T> implements QueueADT<T> {

    private final static int DEFAULT_CAPACITY = 100;
    private T[] queue;
    private int front, rear, count;

    /**
     * Constrói uma fila circular com arrays vazia com a capacidade padrão.
     */
    public CircularArrayQueue() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    /**
     * Constrói uma fila circular com arrays vazia com a capacidade especificada.
     *
     * @param initialCapacity a capacidade inicial da fila
     */
    public CircularArrayQueue(int initialCapacity) {
        this.queue = (T[]) (new Object[initialCapacity]);
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    /**
     * Expande a capacidade da fila criando um novo array com o dobro da
     * capacidade atual e copiando os elementos do array antigo para o novo.
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);
        for (int index = front; index < rear; index++) {
            larger[index] = queue[index];
        }
        queue = larger;
    }

    /**
     * Adiciona o elemento especificado ao final da fila.
     * Expande a capacidade se a fila estiver quase cheia.
     *
     * @param element o elemento a ser adicionado ao final da fila
     */
    @Override
    public void enqueue(T element) {
        if (size() == queue.length - 1)
            expandCapacity();
        queue[rear] = element;
        rear = (rear + 1) % queue.length;
        count++;
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
        T removed = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;
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
        return queue[front];
    }

    /**
     * Retorna true se a fila estiver vazia e false caso contrário.
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
        return count;
    }

    /**
     * Retorna uma representação de string da fila.
     *
     * @return uma representação de string da fila
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            int current = front;
            do {
                result += queue[current] + " ";
                current = (current + 1) % queue.length;
            } while (current != rear);
        }
        return result + "}";
    }
}
