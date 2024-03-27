package TodasColecoes.Trees;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NoSuchElementException;
import TodasColecoes.TodasExcecoes.NonComparableElementException;
import TodasColecoes.TodasListasAulas.OrderedListADT;

import java.util.Iterator;


public class LinkedOrderedBinarySearchTree<T> extends LinkedBinarySearchTree<T> implements OrderedListADT<T> {

    /**
     * Constrói uma árvore de busca binária ordenada encadeada vazia.
     */
    public LinkedOrderedBinarySearchTree() {
        super();
    }

    /**
     * Constrói uma árvore de busca binária ordenada encadeada com o elemento especificado como raiz.
     *
     * @param element o elemento que será a raiz da árvore
     */
    public LinkedOrderedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Remove e retorna o primeiro elemento (mínimo) da árvore.
     *
     * @return o primeiro elemento na árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        return removeMin();
    }

    /**
     * Remove e retorna o último elemento (máximo) da árvore.
     *
     * @return o último elemento na árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        return removeMax();
    }

    /**
     * Remove e retorna o elemento especificado da árvore.
     *
     * @param element o elemento a ser removido da árvore
     * @return o elemento removido da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     * @throws NoSuchElementException   se o elemento não for encontrado
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        return removeElement(element);
    }

    /**
     * Retorna o primeiro (mínimo) elemento na árvore.
     *
     * @return o primeiro elemento na árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        return findMin();
    }

    /**
     * Retorna o último (máximo) elemento na árvore.
     *
     * @return o último elemento na árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T last() throws EmptyCollectionException {
        return findMax();
    }

    /**
     * Retorna um iterador que percorre a árvore em ordem.
     *
     * @return um iterador que percorre a árvore em ordem
     */
    @Override
    public Iterator<T> iterator() {
        return iteratorInOrder();
    }

    /**
     * Adiciona o elemento especificado à árvore de busca binária ordenada encadeada. A ordem é
     * mantida com base na ordenação natural dos elementos (usando a interface {@code Comparable}).
     *
     * @param element o elemento a ser adicionado à árvore
     * @throws NonComparableElementException se o elemento não for comparável
     */
    @Override
    public void add(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        Comparable<T> comparableElement = (Comparable<T>) element;
        if (isEmpty()) {
            root = new BinaryTreeNode<>(element);
        } else {
            addElement(root, comparableElement);
        }
        size++;
    }

    /**
     * Adiciona o elemento especificado à árvore de busca binária ordenada encadeada. A ordem é
     * mantida com base na ordenação natural dos elementos (usando a interface {@code Comparable}).
     *
     * @param root               a raiz da árvore
     * @param comparableElement o elemento a ser adicionado à árvore
     */
    private void addElement(BinaryTreeNode<T> root, Comparable<T> comparableElement) {
        if (comparableElement.compareTo(root.getElement()) < 0) {
            if (root.getLeft() == null) {
                root.setLeft((BinaryTreeNode<T>) new BinaryTreeNode<>(comparableElement));
            } else {
                addElement(root.getLeft(), comparableElement);
            }
        } else {
            if (root.getRight() == null) {
                root.setRight((BinaryTreeNode<T>) new BinaryTreeNode<>(comparableElement));
            } else {
                addElement(root.getRight(), comparableElement);
            }
        }
    }
}
