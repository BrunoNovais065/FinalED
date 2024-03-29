package TodasColecoes.Trees;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;


public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {
    public HeapNode<T> lastNode;

    /**
     * Construtor para criar um heap vazio.
     */
    public LinkedHeap() {
        super();
        lastNode = null;
    }

    /**
     * Constrói um heap com o elemento especificado como raiz.
     *
     * @param element o elemento que será a raiz deste heap
     */
    public LinkedHeap(T element) {
        super(element);
        lastNode = (HeapNode<T>) root;
    }

    /**
     * Adiciona o elemento especificado a este heap.
     *
     * @param element o elemento a ser adicionado a este heap
     */
    @Override
    public void addElement(T element) {
        HeapNode<T> node = new HeapNode<>(element);
        if (root == null) {
            root = node;
        } else {
            HeapNode<T> next_parent = getNextParentAdd();
            if (next_parent.getLeft() == null) {
                next_parent.setLeft(node);
            } else {
                next_parent.setRight(node);
            }
            node.setParent(next_parent);
        }
        lastNode = node;
        size++;
        if (size > 1) {
            heapifyAdd();
        }
    }

    /**
     * Retorna o nó pai para o próximo nó a ser adicionado.
     *
     * @return o nó pai para o próximo nó a ser adicionado
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;
        while ((result != root) && (result.getParent().getLeft() != result)) {
            result = result.getParent();
        }
        if (result != root) {
            if (result.getParent().getRight() == null) {
                result = result.getParent();
            } else {
                result = (HeapNode<T>) result.getParent().getRight();
                while (result.getLeft() != null) {
                    result = (HeapNode<T>) result.getLeft();
                }
            }
        } else {
            while (result.getLeft() != null) {
                result = (HeapNode<T>) result.getLeft();
            }
        }
        return result;
    }

    /**
     * Reorganiza este heap após adicionar um nó.
     */
    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = lastNode;

        temp = next.getElement();

        while ((next != root) && (((Comparable)temp).compareTo(next.getParent().getElement()) < 0)) {
            next.setElement(next.getParent().getElement());
            next = next.getParent();
        }
        next.setElement(temp);
    }

    /**
     * Remove o elemento com a menor prioridade neste heap e o retorna.
     *
     * @return o elemento com a menor prioridade neste heap
     * @throws EmptyCollectionException se o heap estiver vazio
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        T minElement = root.getElement();
        if (size == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (lastNode.getParent().getLeft() == lastNode) {
                lastNode.getParent().setLeft(null);
            } else {
                lastNode.getParent().setRight(null);
            }
            root.setElement(lastNode.getElement());
            lastNode = next_last;
            heapifyRemove();
        }
        size--;

        return minElement;
    }

    /**
     * Retorna uma referência ao último nó adicionado a este heap.
     *
     * @return uma referência ao último nó adicionado a este heap
     */
    private HeapNode<T> getNewLastNode(){
        HeapNode<T> result = lastNode;
        while ((result != root) && (result.getParent().getLeft() == result)) {
            result = result.getParent();
        }
        if (result != root) {
            result = (HeapNode<T>) result.getParent().getLeft();
        }
        while (result.getRight() != null) {
            result = (HeapNode<T>) result.getRight();
        }
        return result;
    }

    /**
     * Reorganiza este heap após remover o nó raiz.
     */
    private void heapifyRemove(){
        T temp;
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.getLeft();
        HeapNode<T> right = (HeapNode<T>) node.getRight();
        HeapNode<T> next;

        if ((left == null) && (right == null)) {
            next = null;
        } else if (left == null) {
            next = right;
        } else if (right == null) {
            next = left;
        } else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = node.getElement();
        while ((next != null) && (((Comparable) next.getElement()).compareTo(temp) < 0)) {
            node.setElement(next.getElement());
            node = next;
            left = (HeapNode<T>) node.getLeft();
            right = (HeapNode<T>) node.getRight();

            if ((left == null) && (right == null)) {
                next = null;
            } else if (left == null) {
                next = right;
            } else if (right == null) {
                next = left;
            } else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
        node.setElement(temp);
    }

    /**
     * Retorna o elemento com a menor prioridade neste heap.
     *
     * @return o elemento com a menor prioridade neste heap
     * @throws EmptyCollectionException se o heap estiver vazio
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return root.getElement();
    }

    /**
     * Remove todos os elementos deste heap.
     */
    public void removeAllElements() {
        root = null;
        lastNode = null;
        size = 0;
    }
}
