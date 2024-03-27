package TodasColecoes.Trees;

import TodasColecoes.Queues.LinkedQueue;
import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.UnsupportedOperationException;
import TodasColecoes.TodasListasAulas.LinkedUnorderedList;
import TodasColecoes.TodasExcecoes.ElementNotFoundException;

import java.util.Iterator;


public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected BinaryTreeNode<T> root;
    protected int size;

    /**
     * Constrói uma árvore binária encadeada vazia.
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Constrói uma árvore binária encadeada com o elemento especificado como raiz.
     *
     * @param element o elemento a ser armazenado na raiz
     */
    public LinkedBinaryTree(T element) {
        root = new BinaryTreeNode<>(element);
        size = 1;
    }

    /**
     * Define a raiz da árvore para o nó especificado.
     *
     * @param root o novo nó raiz
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    public void setRoot(BinaryTreeNode<T> root) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        this.root = root;
    }

    /**
     * Retorna a raiz da árvore.
     *
     * @return a raiz da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T getRootElement() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return root.getElement();
    }

    /**
     * Retorna a raiz da árvore.
     *
     * @return a raiz da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public BinaryTreeNode<T> getRootNode() throws EmptyCollectionException, UnsupportedOperationException {
        if (root == null) {
            throw new EmptyCollectionException();
        }
        return root;
    }

    /**
     * Retorna verdadeiro se a árvore estiver vazia e falso caso contrário.
     *
     * @return true se a árvore estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos na árvore.
     *
     * @return o número de elementos na árvore
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retorna verdadeiro se a árvore contiver um elemento correspondente ao
     * elemento especificado e falso caso contrário.
     *
     * @param targetElement o elemento a ser verificado quanto à existência na árvore
     * @return true se a árvore contiver o elemento, false caso contrário
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public boolean contains(T targetElement) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        try {
            find(targetElement);
            return true;
        } catch (ElementNotFoundException ignored) {
            return false;
        }
    }

    /**
     * Retorna uma referência ao elemento especificado se ele for encontrado na árvore.
     *
     * @param targetElement o elemento a ser encontrado na árvore
     * @return uma referência ao elemento especificado
     * @throws EmptyCollectionException se a árvore estiver vazia
     * @throws ElementNotFoundException se o elemento não for encontrado
     */
    @Override
    public T find(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null) {
            throw new ElementNotFoundException(getClass().getSimpleName());
        }

        return current.getElement();
    }

    /**
     * Retorna uma referência ao elemento especificado se ele for encontrado na árvore.
     *
     * @param targetElement o elemento a ser encontrado na árvore
     * @param next         o nó a ser verificado
     * @return uma referência ao elemento especificado
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.getElement().equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null) {
            temp = findAgain(targetElement, next.getRight());
        }

        return temp;
    }

    /**
     * Retorna uma representação de string desta árvore binária encadeada.
     *
     * @return uma representação de string da árvore
     */
    @Override
    public String toString() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        inOrder(root, tempList);

        return tempList.toString();
    }

    /**
     * Retorna um iterador para a travessia em ordem da árvore.
     *
     * @return um iterador para a travessia em ordem
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        inOrder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Recursive helper method for in-order traversal.
     *
     * @param node     the current node in the traversal
     * @param tempList the list to store elements in traversal order
     */
    private void inOrder(BinaryTreeNode<T> node, LinkedUnorderedList<T> tempList) {
        if (node != null) {
            inOrder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inOrder(node.getRight(), tempList);
        }
    }

    /**
     * Retorna um iterador para a travessia pré-ordem da árvore.
     *
     * @return um iterador para a travessia pré-ordem
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        preOrder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Método auxiliar recursivo para a travessia pré-ordem.
     *
     * @param node     o nó atual na travessia
     * @param tempList a lista para armazenar elementos na ordem da travessia
     */
    private void preOrder(BinaryTreeNode<T> node, LinkedUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preOrder(node.getLeft(), tempList);
            preOrder(node.getRight(), tempList);
        }
    }

    /**
     * Retorna um iterador para a travessia pós-ordem da árvore.
     *
     * @return um iterador para a travessia pós-ordem
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        postOrder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Método auxiliar recursivo para a travessia pós-ordem.
     *
     * @param node     o nó atual na travessia
     * @param tempList a lista para armazenar elementos na ordem da travessia
     */
    private void postOrder(BinaryTreeNode<T> node, LinkedUnorderedList<T> tempList) {
        if (node != null) {
            postOrder(node.getLeft(), tempList);
            postOrder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    /**
     * Retorna um iterador para a travessia em ordem de nível da árvore.
     *
     * @return um iterador para a travessia em ordem de nível
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();
        LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<>();

        if (!isEmpty()) {
            queue.enqueue(root);

            while (!queue.isEmpty()) {
                BinaryTreeNode<T> next = queue.dequeue();

                if (next.getLeft() != null) {
                    queue.enqueue(next.getLeft());
                }

                if (next.getRight() != null) {
                    queue.enqueue(next.getRight());
                }

                tempList.addToRear(next.getElement());
            }
        }

        return tempList.iterator();
    }
}
