package Colecoes.Trees;

import Colecoes.AllExecoes.EmptyCollectionException;
import Colecoes.AllExecoes.NoSuchElementException;
import Colecoes.AllExecoes.NonComparableElementException;

/**
 * Represents a binary search tree data structure implemented using an array.
 * Elements are organized in a way that allows for efficient search, insertion,
 * and removal.
 * The tree maintains a sorted order based on the natural ordering of elements.
 *
 * @param <T> the type of elements stored in the binary search tree, must be
 *            comparable
 */
public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {
    protected int height;
    protected int maxIndex;

    /**
     * Constructs an empty binary search tree with the default initial capacity.
     */
    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    /**
     * Constructs a binary search tree with a single element.
     *
     * @param element the initial element to be added to the tree
     */
    public ArrayBinarySearchTree(T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    /**
     * Expands the capacity of the array representing the binary search tree.
     * Used when the tree needs to accommodate more elements.
     */
    private void expandCapacity() {
        T[] newTree = (T[]) (new Object[tree.length * 2]);
        for (int i = 0; i < tree.length; i++) {
            newTree[i] = tree[i];
        }
        tree = newTree;
    }

    /**
     * Adds the specified element to the binary search tree.
     *
     * @param element the element to be added to the tree
     * @throws NonComparableElementException if the element is not comparable
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
     * Removes all occurrences of the specified element from the binary search tree.
     *
     * @param targetElement the element to be removed from the tree
     * @throws EmptyCollectionException if the tree is empty
     * @throws NoSuchElementException   if the element is not found in the tree
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
     * Replaces the current node at the specified index with its successor in the
     * binary search tree.
     * If the node has a right child, the replacement is the leftmost node in its
     * right subtree.
     * If the node doesn't have a right child but has a left child, the replacement
     * is the rightmost
     * node in its left subtree. If the node has no children, it is removed by
     * throwing a NoSuchElementException.
     *
     * @param targetIndex the index of the node to be replaced
     * @throws NoSuchElementException if the specified node has no children
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
     * Removes all occurrences of the specified element from the binary search tree.
     *
     * @param targetElement the element to be removed from the tree
     * @throws EmptyCollectionException if the tree is empty
     * @throws NoSuchElementException   if the element is not found in the tree
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, NoSuchElementException {
        removeElement(targetElement);
        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    /**
     * Removes and returns the smallest element in the binary search tree.
     *
     * @return the smallest element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return removeElement(findMin());
    }

    /**
     * Removes and returns the largest element in the binary search tree.
     *
     * @return the largest element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return removeElement(findMax());
    }

    /**
     * Finds and returns the smallest element in the binary search tree.
     *
     * @return the smallest element in the tree
     * @throws EmptyCollectionException if the tree is empty
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
     * Finds and returns the largest element in the binary search tree.
     *
     * @return the largest element in the tree
     * @throws EmptyCollectionException if the tree is empty
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
