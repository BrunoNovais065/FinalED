package TodasColecoes.Queues;

import TodasColecoes.Stacks.LinkedStack;
import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class StackQueue<T> implements QueueADT<T> {
    private final LinkedStack<T> stack1;
    private final LinkedStack<T> stack2;

    /**
     * Constrói uma fila baseada em pilha vazia.
     */
    public StackQueue() {
        this.stack1 = new LinkedStack<>();
        this.stack2 = new LinkedStack<>();
    }


    /**
     * Transfere elementos da pilha1 para a pilha2, se necessário.
     *
     * @throws EmptyCollectionException se a fila estiver vazia
     */
    private void transfer() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    /**
     * Enfileira um elemento na fila.
     *
     * @param element o elemento a ser enfileirado
     */
    @Override
    public void enqueue(T element) {
        stack1.push(element);
    }

    /**
     * Remove e retorna o elemento na frente da fila.
     * Transfere elementos da pilha1 para a pilha2, se necessário.
     *
     * @return o elemento na frente da fila
     * @throws EmptyCollectionException se a fila estiver vazia
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        transfer();
        return stack2.pop();
    }

    /**
     * Retorna o elemento na frente da fila sem remover-lo.
     * Transfere elementos da pilha1 para a pilha2, se necessário.
     * @return o elemento na frente da fila
     * @throws EmptyCollectionException se a fila estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        transfer();
        return stack2.peek();
    }

    /**
     * Retorna true se a fila estiver vazia, e false caso contrário.
     *
     * @return true se a fila estiver vazia, e false caso contrário
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
        return stack1.size() + stack2.size();
    }


    /**
     * Retorna uma representação em string da fila.
     *
     * @return uma representação em string da fila
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " +
                "stack1= " + stack1 +
                ", stack2= " + stack2 +
                " }";
    }
}
