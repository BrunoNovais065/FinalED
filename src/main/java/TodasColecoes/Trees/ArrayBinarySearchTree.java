package TodasColecoes.Trees;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NoSuchElementException;
import TodasColecoes.TodasExcecoes.NonComparableElementException;


public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {
    protected int height;
    protected int maxIndex;

    /**
     * Controi um array binary tree vazio.
     */
    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    /**
     * Constrói um array binary tree com um único elemento.
     *
     * @param element o elemento inicial a ser adicionado à árvore
     */
    public ArrayBinarySearchTree(T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    /**
     * Expanda a capacidade da árvore binária quando necessário.
     */
    private void expandCapacity() {
        T[] newTree = (T[]) (new Object[tree.length * 2]);
        for (int i = 0; i < tree.length; i++) {
            newTree[i] = tree[i];
        }
        tree = newTree;
    }

    /**
     * Adiciona o elemento especificado à árvore binária de pesquisa.
     *
     * @param element o elemento a ser adicionado à árvore
     * @throws NonComparableElementException se o elemento não for comparável
     */
    @Override
    public void addElement(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        if (tree.length < maxIndex * 2 + 3) {
            expandCapacity();
        }
        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;
            while (!added) {
                if (((Comparable<T>) element).compareTo((tree[currentIndex])) < 0) {
                    // go left
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;
                        if (currentIndex * 2 + 1 > maxIndex) {
                            maxIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else {
                    // go right
                    if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;
                        if (currentIndex * 2 + 2 > maxIndex) {
                            maxIndex = currentIndex * 2 + 2;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        size++;
    }

    /**
     * Remove e retorna o elemento especificado da árvore binária de pesquisa.
     *
     * @param targetElement o elemento a ser removido da árvore
     * @return o elemento removido da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     * @throws NoSuchElementException   se o elemento especificado não for encontrado
     */
    @Override
    public T removeElement(T targetElement) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        T removed = null;
        boolean found = false;

        for (int i = 0; (i <= maxIndex) && !found; i++) {
            if ((tree[i] != null) && targetElement.equals(tree[i])) {
                try {
                    found = true;
                    removed = tree[i];
                    try {
                        replacement(i);
                    } catch (NoSuchElementException e) {
                        tree[i] = null;
                    }
                    size--;
                } catch (NoSuchElementException e) {
                    tree[i] = null;
                }
            }
        }
        if (!found) {
            throw new NoSuchElementException();
        }
        return removed;
    }

    /**
     * Executa uma substituição para remover um elemento da árvore binária de pesquisa.
     *
     * @param targetIndex o índice do elemento a ser removido
     * @throws NoSuchElementException se o elemento não for encontrado
     */
    private void replacement(int targetIndex) throws NoSuchElementException {
        if (tree[targetIndex * 2 + 2] != null) {
            tree[targetIndex] = tree[targetIndex * 2 + 2];
            replacement(targetIndex * 2 + 2);
        } else if (tree[targetIndex * 2 + 1] != null) {
            tree[targetIndex] = tree[targetIndex * 2 + 1];
            replacement(targetIndex * 2 + 1);
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Remove e retorna o elemento especificado da árvore binária de pesquisa.
     *
     * @param targetElement o elemento a ser removido da árvore
     * @return o elemento removido da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     * @throws NoSuchElementException   se o elemento especificado não for encontrado
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, NoSuchElementException {
        removeElement(targetElement);
        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    /**
     * Remove e retorna o menor elemento da árvore binária de pesquisa.
     *
     * @return o menor elemento da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return removeElement(findMin());
    }

    /**
     * Remove e retorna o maior elemento da árvore binária de pesquisa.
     *
     * @return o maior elemento da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return removeElement(findMax());
    }

    /**
     * Retorna o menor elemento da árvore binária de pesquisa.
     *
     * @return o menor elemento da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T min = tree[0];
        for (int i = 0; i <= maxIndex; i++) {
            if ((tree[i] != null) && (((Comparable<T>) tree[i]).compareTo(min) < 0)) {
                min = tree[i];
            }
        }
        return min;
    }

    /**
     * Retorna o maior elemento da árvore binária de pesquisa.
     *
     * @return o maior elemento da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T max = tree[0];
        for (int i = 0; i <= maxIndex; i++) {
            if ((tree[i] != null) && (((Comparable<T>) tree[i]).compareTo(max) > 0)) {
                max = tree[i];
            }
        }
        return max;
    }
}
