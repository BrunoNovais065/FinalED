package TodasColecoes.Stacks;

import TodasColecoes.TodasListasAulas.Node;
import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class LinkedStack<T> implements StackADT<T> {
    protected Node<T> top;
    protected int size;

    /**
     * Constrói uma pilha encadeada vazia.
     */
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Adiciona o elemento especificado ao topo da pilha.
     *
     * @param element o elemento a ser adicionado ao topo da pilha
     */
    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    /**
     * Remove e retorna o elemento no topo da pilha.
     *
     * @return o elemento removido do topo da pilha
     * @throws EmptyCollectionException se a pilha estiver vazia
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = top.getElement();
        top = top.getNext();
        size--;
        return removed;
    }

    /**
     * Retorna o elemento no topo da pilha sem removê-lo.
     *
     * @return o elemento no topo da pilha
     * @throws EmptyCollectionException se a pilha estiver vazia
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return top.getElement();
    }

    /**
     * Retorna true se a pilha estiver vazia, e false caso contrário.
     *
     * @return true se a pilha estiver vazia, e false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos na pilha.
     *
     * @return o número de elementos na pilha
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retorna uma representação em string da pilha.
     *
     * @return uma representação em string da pilha
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = top;
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
