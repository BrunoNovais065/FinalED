package Colecoes.Trees;

import Colecoes.AllExecoes.EmptyCollectionException;

/**
 * Represents a heap data structure implemented using an array.
 *
 * @param <T> the type of elements stored in the heap
 */
public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {

    /**
     * Constructs an empty heap.
     */
    public ArrayHeap() {
        super();
    }

    /**
     * Adds an element to the heap.
     *
     * @param element the element to be added to the heap
     */
    @Override
    public void addElement(T element) {
        if (size == tree.length)
            expandCapacity();
        tree[size] = element;
        size++;
        if (size > 1) {
            heapifyAdd();
        }
    }

    /**
     * Expands the capacity of the heap when needed.
     */
    private void expandCapacity() {
        T[] newTree = (T[]) (new Object[tree.length * 2]);
        for (int i = 0; i < tree.length; i++) {
            newTree[i] = tree[i];
        }
        tree = newTree;
    }

    /**
     * Adjusts the heap after adding an element to maintain the heap property.
     */
    private void heapifyAdd() {
        T temp;
        int next = size - 1;

        temp = tree[next];

        while ((next != 0) && (((Comparable) temp).compareTo(tree[(next - 1) / 2]) < 0)) {
            tree[next] = tree[(next - 1) / 2];
            next = (next - 1) / 2;
        }
        tree[next] = temp;
    }

    /**
     * Removes and returns the minimum element from the heap.
     *
     * @return the minimum element in the heap
     * @throws EmptyCollectionException if the heap is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T minElement = tree[0];
        tree[0] = tree[size - 1];
        heapifyRemove();
        size--;

        return minElement;
    }

    /**
     * Adjusts the heap after removing the minimum element to maintain the heap
     * property.
     */
    private void heapifyRemove() {
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null)) {
            next = size;
        } else if (tree[left] == null) {
            next = right;
        } else if (tree[right] == null) {
            next = left;
        } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = tree[node];
        while ((next < size) && (((Comparable) tree[next]).compareTo(temp) < 0)) {
            tree[node] = tree[next];
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null)) {
                next = size;
            } else if (tree[left] == null) {
                next = right;
            } else if (tree[right] == null) {
                next = left;
            } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
        tree[node] = temp;
    }

    /**
     * Returns the minimum element in the heap without removing it.
     *
     * @return the minimum element in the heap
     * @throws EmptyCollectionException if the heap is empty
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return tree[0];
    }
}
