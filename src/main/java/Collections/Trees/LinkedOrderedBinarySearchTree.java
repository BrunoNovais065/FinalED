package Collections.Trees;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Exceptions.NonComparableElementException;
import Collections.Lists.OrderedListADT;

import java.util.Iterator;

/**
 * Represents a linked ordered binary search tree, a hierarchical data structure
 * composed of nodes where each node has at most two children, and elements are
 * ordered based on their natural ordering.
 *
 * @param <T> the type of elements stored in the tree
 */
public class LinkedOrderedBinarySearchTree<T> extends LinkedBinarySearchTree<T> implements OrderedListADT<T> {

    /**
     * Constructs an empty linked ordered binary search tree.
     */
    public LinkedOrderedBinarySearchTree() {
        super();
    }

    /**
     * Constructs a linked ordered binary search tree with a specified root element.
     *
     * @param element the root element of the tree
     */
    public LinkedOrderedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Removes and returns the first (minimum) element from the tree.
     *
     * @return the first element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        return removeMin();
    }

    /**
     * Removes and returns the last (maximum) element from the tree.
     *
     * @return the last element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        return removeMax();
    }

    /**
     * Removes the specified element from the tree.
     *
     * @param element the element to be removed
     * @return the removed element
     * @throws EmptyCollectionException if the tree is empty
     * @throws NoSuchElementException   if the specified element is not found
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        return removeElement(element);
    }

    /**
     * Returns the first (minimum) element in the tree.
     *
     * @return the first element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        return findMin();
    }

    /**
     * Returns the last (maximum) element in the tree.
     *
     * @return the last element in the tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        return findMax();
    }

    /**
     * Returns an iterator for in-order traversal of the tree.
     *
     * @return an iterator for in-order traversal
     */
    @Override
    public Iterator<T> iterator() {
        return iteratorInOrder();
    }

    /**
     * Adds the specified element to the tree while maintaining order.
     *
     * @param element the element to be added
     * @throws NonComparableElementException if the element is not comparable
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
     * Recursive helper method to add an element while maintaining order.
     *
     * @param root              the current root node
     * @param comparableElement the element to be added
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
