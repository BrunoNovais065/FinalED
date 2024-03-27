package TodasColecoes.Trees;

import TodasColecoes.TodasExcecoes.ElementNotFoundException;
import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NonComparableElementException;


public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Constrói uma árvore de busca binária encadeada vazia.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Constrói uma árvore de busca binária encadeada com um elemento especificado como raiz.
     *
     * @param element o elemento raiz da árvore
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adiciona o elemento especificado à árvore de busca binária, mantendo as propriedades de uma árvore de busca binária.
     *
     * @param element o elemento a ser adicionado à árvore
     * @throws NonComparableElementException se o elemento não for comparável
     */
    @Override
    public void addElement(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        BinaryTreeNode<T> temp = new BinaryTreeNode<>(element);
        if (isEmpty()) {
            root = temp;
        } else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (((Comparable) element).compareTo(current.getElement()) < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(temp);
                        added = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() == null) {
                        current.setRight(temp);
                        added = true;
                    } else {
                        current = current.getRight();
                    }
                }
            }
        }
        size++;
    }

    /**
     * Remove o primeiro elemento que corresponde ao elemento de destino especificado da árvore de busca binária e retorna uma referência a ele.
     *
     * @param targetElement o elemento a ser removido da árvore
     * @return o elemento removido da árvore
     * @throws EmptyCollectionException      se a árvore estiver vazia
     * @throws ElementNotFoundException      se o elemento de destino não for encontrado
     * @throws NonComparableElementException se o elemento não for comparável
     */
    @Override
    public T removeElement(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = null;
        if (targetElement.equals(root.getElement())) {
            removed = root.getElement();
            root = replacement(root);
            size--;
        } else {
            if (!(targetElement instanceof Comparable)) {
                throw new NonComparableElementException();
            }
            BinaryTreeNode<T> current, parent = root;
            boolean found = false;
            if (((Comparable) targetElement).compareTo(root.getElement()) < 0) {
                current = root.getLeft();
            } else {
                current = root.getRight();
            }
            while (current != null && !found) {
                if (targetElement.equals(current.getElement())) {
                    found = true;
                    size--;
                    removed = current.getElement();
                    if (current == parent.getLeft()) {
                        parent.setLeft(replacement(current));
                    } else {
                        parent.setRight(replacement(current));
                    }
                } else {
                    parent = current;
                    if (((Comparable) targetElement).compareTo(current.getElement()) < 0) {
                        current = current.getLeft();
                    } else {
                        current = current.getRight();
                    }
                }
            }
            if (!found) {
                throw new ElementNotFoundException();
            }
        }
        return removed;
    }

    /**
     * Remove o nó especificado da árvore de busca binária e retorna seu nó de substituição.
     *
     * @param node o nó a ser removido
     * @return o nó de substituição para o nó removido
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> replaced;
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            replaced = null;
        } else if ((node.getLeft() != null) && (node.getRight() == null)) {
            replaced = node.getLeft();
        } else if ((node.getLeft() == null) && (node.getRight() != null)) {
            replaced = node.getRight();
        } else {
            BinaryTreeNode<T> current = node.getRight();
            BinaryTreeNode<T> parent = node;
            while (current.getLeft() != null) {
                parent = current;
                current = current.getLeft();
            }
            if (node.getRight() == current) {
                current.setLeft(node.getLeft());
            } else {
                parent.setLeft(current.getRight());
                current.setRight(node.getRight());
                current.setLeft(node.getLeft());
            }
            replaced = current;
        }
        return replaced;
    }

    /**
     * Remove e retorna o elemento especificado desta árvore de busca binária.
     *
     * @param targetElement o elemento a ser removido da árvore
     * @return o elemento removido da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     * @throws ElementNotFoundException se o elemento de destino não for encontrado
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        removeElement(targetElement);
        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    /**
     * Remove e retorna o menor elemento da árvore de busca binária.
     *
     * @return o menor elemento da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        return removeElement(findMin());
    }

    /**
     * Remove e retorna o maior elemento da árvore de busca binária.
     *
     * @return o maior elemento da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        return removeElement(findMax());
    }

    /**
     * Retorna o menor elemento da árvore de busca binária.
     *
     * @return o menor elemento da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            BinaryTreeNode<T> current = root;
            while (current.getLeft() != null) {
                current = current.getLeft();
            }
            return current.getElement();
        }
    }

    /**
     * Retorna o maior elemento da árvore de busca binária.
     *
     * @return o maior elemento da árvore
     * @throws EmptyCollectionException se a árvore estiver vazia
     */
    @Override
    public T findMax() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            BinaryTreeNode<T> current = root;
            while (current.getRight() != null) {
                current = current.getRight();
            }
            return current.getElement();
        }
    }
}
