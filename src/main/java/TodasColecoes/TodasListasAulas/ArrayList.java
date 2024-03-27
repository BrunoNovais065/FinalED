package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;


public class ArrayList<T> implements ListADT<T>, Iterable<T> {
    private final static int DEFAULT_CAPACITY = 100;
    protected T[] list;
    protected int size, modCount;

    /**
     * Constrói um ArrayList com a capacidade padrão.
     */
    public ArrayList() {
        this.list = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * Constrói um ArrayList com a capacidade inicial especificada.
     *
     * @param initialCapacity a capacidade inicial do ArrayList
     */
    public ArrayList(int initialCapacity) {
        this.list = (T[]) (new Object[initialCapacity]);
        this.size = 0;
        this.modCount = 0;
    }


    /**
     * Remove o elemento especificado do ArrayList e o retorna.
     *
     * @param element o elemento a ser removido
     * @return o elemento removido
     * @throws EmptyCollectionException se o ArrayList estiver vazio
     * @throws NoSuchElementException   se o elemento especificado não for encontrado
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        int index = 0;
        while (index < size() && !list[index].equals(element)) {
            index++;
        }
        if (index == size())
            throw new NoSuchElementException();
        T removed = list[index];
        for (int shift = index; shift < size() - 1; shift++) {
            list[shift] = list[shift + 1];
        }
        list[size() - 1] = null;
        size--;
        modCount++;
        return removed;
    }


    /**
     * Retorna o primeiro elemento no ArrayList. Lança EmptyCollectionException
     * se o ArrayList estiver vazio.
     *
     * @return o primeiro elemento no ArrayList
     * @throws EmptyCollectionException se o ArrayList estiver vazio
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return list[0];
    }

    /**
     * Retorna o último elemento no ArrayList. Lança EmptyCollectionException
     * se o ArrayList estiver vazio.
     *
     * @return o último elemento no ArrayList
     * @throws EmptyCollectionException se o ArrayList estiver vazio
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        return list[size() - 1];
    }


    /**
     * Verifica se o elemento especificado está contido no ArrayList.
     * Lança EmptyCollectionException se o ArrayList estiver vazio.
     *
     * @param target o elemento a ser verificado para contenção
     * @return true se o elemento for encontrado, false caso contrário
     * @throws EmptyCollectionException se o ArrayList estiver vazio
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        int index = 0;
        while (index < size() && !list[index].equals(target)) {
            index++;
        }
        return index < size();
    }

    /**
     * Expande a capacidade do ArrayList criando um novo array com o dobro da
     * capacidade atual e copiando elementos do array antigo para o novo.
     */
    protected void expandCapacity() {
        T[] newList = (T[]) (new Object[list.length * 2]);
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    /**
     * Remove o elemento especificado ao inicio do ArrayList
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T removed = list[0];
        for (int shift = 0; shift < size() - 1; shift++) {
            list[shift] = list[shift + 1];
        }
        list[size() - 1] = null;
        size--;
        modCount++;
        return removed;
    }

    /**
     * Remove o elemento especificado ao final do ArrayList
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException();
        T result = list[size() - 1];
        list[size() - 1] = null;
        size--;
        modCount++;
        return result;
    }

    /**
     * Verifica se o ArrayList está vazio.
     *
     * @return true se o ArrayList estiver vazio, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos no ArrayList.
     *
     * @return o número de elementos no ArrayList
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retorna um iterador sobre os elementos no ArrayList.
     *
     * @return um iterador sobre os elementos no ArrayList
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator() {
        };
    }

    /**
     * Retorna uma representação de string do ArrayList.
     *
     * @return uma representação de string do ArrayList
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                result += list[i] + " ";
            }
        }
        return result + "}";
    }

    /**
     * Classe interna que define um iterador para o ArrayList.
     */
    private abstract class BasicIterator implements Iterator<T> {
        private int current;
        private int expectedModCount;
        private boolean okToRemove;

        /**
         * Constrói um iterador para o ArrayList.
         */
        public BasicIterator() {
            this.current = 0;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        /**
         * Verifica se há um próximo elemento no ArrayList.
         *
         * @return true se houver um próximo elemento, false caso contrário
         */
        @Override
        public boolean hasNext() {
            return current < size();
        }

        /**
         * Retorna o próximo elemento no ArrayList.
         *
         * @return o próximo elemento no ArrayList
         */
        @Override
        public T next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            okToRemove = true;
            return list[current++];
        }

        /**
         * Removes the last element returned by the iterator from the ArrayList.
         */
        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new NoSuchElementException();
            }
            ArrayList.this.remove(list[current - 1]);
            current--;
            expectedModCount++;
            okToRemove = false;
        }
    }
}
