package Collections.Trees;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.UnsupportedOperationException;
import Collections.Lists.ArrayUnorderedList;

import java.util.Iterator;

/**
 * Represents a binary tree data structure implemented using an array.
 * Elements are organized in a way that maintains the binary tree structure.
 *
 * @param <T> the type of elements stored in the binary tree
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected final int DEFAULT_CAPACITY = 100;
    protected T[] tree;
    protected int size;

    /**
     * Constructs an empty binary tree with the default initial capacity.
     */
    public ArrayBinaryTree() {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        size = 0;
    }

    /**
     * Constructs a binary tree with a single element.
     *
     * @param element the initial element to be added to the tree
     */
    public ArrayBinaryTree(T element) {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        tree[0] = element;
        size = 1;
    }

    /**
     * Returns the element at the root of the binary tree.
     *
     * @return the element at the root
     */
    @Override
    public T getRootElement() {
        return tree[0];
    }

    /**
     * Returns the root node of the binary tree.
     *
     * @return the root node of the tree
     * @throws EmptyCollectionException      if the tree is empty
     * @throws UnsupportedOperationException if the operation is not supported for
     *                                       this implementation
     */
    @Override
    public BinaryTreeNode<T> getRootNode() throws EmptyCollectionException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the binary tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in the binary tree.
     *
     * @return the number of elements in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the binary tree contains the specified element.
     *
     * @param targetElement the element to check for in the tree
     * @return true if the element is found, false otherwise
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
     * Finds and returns the specified element in the binary tree.
     *
     * @param targetElement the element to find in the tree
     * @return the element if found
     * @throws EmptyCollectionException if the tree is empty
     * @throws ElementNotFoundException if the element is not found in the tree
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
     * Finds and returns the index of the specified element in the binary tree.
     *
     * @param targetElement the element to find in the tree
     * @return the index of the element if found
     * @throws EmptyCollectionException if the tree is empty
     * @throws ElementNotFoundException if the element is not found in the tree
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
     * Returns a string representation of the binary tree.
     *
     * @return a string representation of the binary tree
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
     * Returns an iterator that traverses the binary tree in in-order fashion.
     *
     * @return an in-order iterator over the elements in the tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        inOrder(0, tempList);

        return tempList.iterator();
    }

    /**
     * Performs an in-order traversal of the binary tree and adds elements to the
     * provided list.
     *
     * @param node     the current node being processed during traversal
     * @param tempList the list to which elements are added during traversal
     */
    private void inOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            inOrder(node * 2 + 1, tempList);
            tempList.addToRear(tree[node]);
            inOrder(node * 2 + 2, tempList);
        }
    }

    /**
     * Returns an iterator that traverses the binary tree in pre-order fashion.
     *
     * @return a pre-order iterator over the elements in the tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        preOrder(0, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a pre-order traversal of the binary tree and adds elements to the
     * provided list.
     *
     * @param node     the current node being processed during traversal
     * @param tempList the list to which elements are added during traversal
     */
    private void preOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            tempList.addToRear(tree[node]);
            preOrder(node * 2 + 1, tempList);
            preOrder(node * 2 + 2, tempList);
        }
    }

    /**
     * Returns an iterator that traverses the binary tree in post-order fashion.
     *
     * @return a post-order iterator over the elements in the tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        postOrder(0, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a post-order traversal of the binary tree and adds elements to the
     * provided list.
     *
     * @param node     the current node being processed during traversal
     * @param tempList the list to which elements are added during traversal
     */
    private void postOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            postOrder(node * 2 + 1, tempList);
            postOrder(node * 2 + 2, tempList);
            tempList.addToRear(tree[node]);
        }
    }

    /**
     * Returns an iterator that traverses the binary tree in level-order fashion.
     *
     * @return a level-order iterator over the elements in the tree
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
