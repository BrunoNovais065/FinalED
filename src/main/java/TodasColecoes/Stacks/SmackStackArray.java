package TodasColecoes.Stacks;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class SmackStackArray<T> extends ArrayStack<T> implements SmackStackADT<T> {



    public SmackStackArray() {
        super();
    }


    /**
     * Cria uma pilha vazia de "smack" com a capacidade inicial especificada.
     *
     * @param initialCapacity a capacidade inicial
     */
    public SmackStackArray(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Remove e retorna o elemento no topo da pilha.
     *
     * @return o elemento no topo da pilha
     * @throws EmptyCollectionException se a pilha estiver vazia
     */
    @Override
    public T smack() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T result = stack[0];
        for (int i = 0; i < top - 1; i++)
            stack[i] = stack[i + 1];
        top--;
        return result;
    }
}
