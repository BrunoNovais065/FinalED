package TodasColecoes.Stacks;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class ArrayStack<T> implements StackADT<T> {
    protected final static int DEFAULT_CAPACITY = 100;
    protected T[] stack;
    protected int top;

    /**
     * Constrói uma pilha vazia com a capacidade inicial padrão.
     */
    public ArrayStack() {
        this.stack = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.top = 0;
    }

    /**
     * Constrói uma pilha vazia com a capacidade inicial especificada.
     *
     * @param initialCapacity a capacidade inicial da pilha
     */
    public ArrayStack(int initialCapacity) {
        this.stack = (T[]) (new Object[initialCapacity]);
        this.top = 0;
    }

    /**
     * Expande a capacidade da pilha criando um novo array com o dobro
     * da capacidade atual e copiando os elementos do array antigo para o novo.
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[stack.length * 2]);
        for (int i = 0; i < stack.length; i++) {
            larger[i] = stack[i];
        }
        stack = larger;
    }

    /**
     * Adiciona o elemento especificado ao topo da pilha.
     *
     * @param element o elemento a ser empurrado para a pilha
     */
    @Override
    public void push(T element) {
        if (size() == stack.length)
            expandCapacity();
        stack[top] = element;
        top++;
    }

    /**
     * Remove e retorna o elemento do topo da pilha.
     *
     * @return o elemento removido do topo da pilha
     * @throws EmptyCollectionException se a pilha estiver vazia
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        this.top--;
        T result = stack[top];
        stack[top] = null;
        return result;
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
        return stack[top - 1];
    }

    /**
     * Verifica se a pilha está vazia.
     *
     * @return true se a pilha estiver vazia, false caso contrário
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
        return top;
    }

    /**
     * Retorna uma representação de string da pilha.
     *
     * @return uma representação de string da pilha
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        for (int i = 0; i < top; i++) {
            result += stack[i] + " ";
        }
        return result + "}";
    }
}
