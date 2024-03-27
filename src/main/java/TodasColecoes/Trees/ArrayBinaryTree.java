package TodasColecoes.Trees;

import TodasColecoes.TodasExcecoes.ElementNotFoundException;
import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.UnsupportedOperationException;
import TodasColecoes.TodasListasAulas.ArrayUnorderedList;

import java.util.Iterator;


public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected final int DEFAULT_CAPACITY = 100;
    protected T[] tree;
    protected int size;

    /**
     * Constroi uma árvore binária vazia.
     */
    public ArrayBinaryTree() {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        size = 0;
    }

    /**
     * Constroi uma árvore binária com o elemento especificado como raiz.
     *
     * @param element o elemento a ser armazenado na raiz
     */
    public ArrayBinaryTree(T element) {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        tree[0] = element;
        size = 1;
    }

    /**
     * Expande a capacidade da árvore binária quando necessário.
     */
    @Override
    public T getRootElement() {
        return tree[0];
    }

    /**
     * Retorna o elemento armazenado na raiz da árvore binária.
     *
     * @return o elemento armazenado na raiz da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public BinaryTreeNode<T> getRootNode() throws EmptyCollectionException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Verifica se a árvore binária está vazia.
     *
     * @return true se a árvore estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos na árvore binária.
     *
     * @return o número de elementos na árvore
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Verifica se a árvore binária contém o elemento especificado.
     *
     * @param targetElement o elemento a ser verificado quanto à existência na árvore
     * @return true se o elemento for encontrado na árvore, false caso contrário
     */
    @Override
    public boolean contains(T targetElement) {
        try {
            find(targetElement);
            return true;
        } catch (ElementNotFoundException ignored) {
            return false;
        }
    }

    /**
     * Retorna o elemento armazenado na árvore binária que corresponde ao elemento
     * especificado.
     *
     * @param targetElement o elemento a ser encontrado na árvore
     * @return o elemento correspondente na árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     * @throws ElementNotFoundException  se o elemento não for encontrado na árvore
     */
    @Override
    public T find(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        T element = null;
        boolean found = false;

        for (int i = 0; i < size && !found; i++) {
            if (tree[i].equals(targetElement)) {
                found = true;
                element = tree[i];
            }
        }

        if (!found) {
            throw new ElementNotFoundException();
        }

        return element;
    }

    /**
     * Retorna uma string que representa a árvore binária.
     *
     * @return uma string que representa a árvore binária
     */
    public int findIndex(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        int index = 0;
        boolean found = false;

        for (int i = 0; i < size && !found; i++) {
            if (tree[i].equals(targetElement)) {
                found = true;
                index = i;
            }
        }

        if (!found) {
            throw new ElementNotFoundException();
        }

        return index;
    }

    /**
     * Retorna uma string que representa a árvore binária.
     *
     * @return uma string que representa a árvore binária
     */
    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        for (int i = 0; i < size; i++) {
            result += tree[i] + " ";
        }
        return result + "}";

    }

    /**
     * Retorna um iterador sobre os elementos na árvore binária.
     *
     * @return um iterador sobre os elementos na árvore binária
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        inOrder(0, tempList);

        return tempList.iterator();
    }

    /**
     * Realiza uma travessia em ordem da árvore binária e adiciona elementos à lista fornecida.
     *
     * @param node     o nó atual sendo processado durante a travessia
     * @param tempList a lista à qual os elementos são adicionados durante a travessia
     */
    private void inOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            inOrder(node * 2 + 1, tempList);
            tempList.addToRear(tree[node]);
            inOrder(node * 2 + 2, tempList);
        }
    }

    /**
     * Retorna um iterador que percorre a árvore binária em ordem pré.
     *
     * @return um iterador em ordem pré sobre os elementos na árvore
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        preOrder(0, tempList);

        return tempList.iterator();
    }

    /**
     * Realiza uma travessia pré-ordem da árvore binária e adiciona elementos à lista fornecida.
     *
     * @param node     o nó atual sendo processado durante a travessia
     * @param tempList a lista à qual os elementos são adicionados durante a travessia
     */
    private void preOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            tempList.addToRear(tree[node]);
            preOrder(node * 2 + 1, tempList);
            preOrder(node * 2 + 2, tempList);
        }
    }

    /**
     * Retorna um iterador que percorre a árvore binária em ordem pós.
     *
     * @return um iterador em ordem pós sobre os elementos na árvore
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        postOrder(0, tempList);

        return tempList.iterator();
    }

    /**
     * Realiza uma travessia pós-ordem da árvore binária e adiciona elementos à lista fornecida.
     *
     * @param node     o nó atual sendo processado durante a travessia
     * @param tempList a lista à qual os elementos são adicionados durante a travessia
     */
    private void postOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            postOrder(node * 2 + 1, tempList);
            postOrder(node * 2 + 2, tempList);
            tempList.addToRear(tree[node]);
        }
    }

    /**
     * Retorna um iterador que percorre a árvore binária em ordem de nível.
     *
     * @return um iterador em ordem de nível sobre os elementos na árvore
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        for (int i = 0; i < size; i++) {
            tempList.addToRear(tree[i]);
        }

        return tempList.iterator();
    }
}
