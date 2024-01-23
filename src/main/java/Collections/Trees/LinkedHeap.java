package Collections.Trees;

import Collections.Exceptions.EmptyCollectionException;

/**
 * LinkedHeap represents a linked implementation of a heap.
 * @param <T> the type of elements stored in the heap
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {
    public HeapNode<T> lastNode;

    /**
     * Contructor to create an empty heap.
     */
    public LinkedHeap() {
        super();
        lastNode = null;
    }

    /**
     * Constructor to create a heap with the specified element as the root.
     * @param element the element to be stored in the root
     */
    public LinkedHeap(T element) {
        super(element);
        lastNode = (HeapNode<T>) root;
    }

    /**
     * Adds the specified element to this heap in the appropriate position according to its key value
     * @param element the element to be added to this heap
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
     * Returns the node that will be the parent of the new node
     *
     * @return the node that will be a parent of the new node
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
     * Reorders this heap after adding a node.
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
     * Removes element with the lowest value from this heap
     * @return the element with the lowest value from this heap
     * @throws EmptyCollectionException if the heap is empty
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
     * Returns the node that will be the new last node after
     * a remove.
     *
     * @return the node that will be the new last node after
     * a remove
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
     * Reorders this heap after removing the root element.
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
     * Returns a reference to the element with the lowest value in this heap
     * @return a reference to the element with the lowest value in this heap
     * @throws EmptyCollectionException if the heap is empty
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return root.getElement();
    }

    /**
     * Removes all the elements from this heap
     */
    public void removeAllElements() {
        root = null;
        lastNode = null;
        size = 0;
    }
}