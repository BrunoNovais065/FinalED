package Colecoes.Trees;

import Colecoes.Queues.LinkedQueue;
import Colecoes.AllExecoes.EmptyCollectionException;
import Colecoes.AllExecoes.UnsupportedOperationException;
import Colecoes.Lists.LinkedUnorderedList;
import Colecoes.AllExecoes.ElementNotFoundException;

import java.util.Iterator;

/**
 * Represents a linked binary tree, a hierarchical data structure composed of
 * nodes, where each node has at most two children, referred to as the left
 * and right children.
 *
 * @param <T> the type of elements stored in the binary tree
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected BinaryTreeNode<T> root;
    protected int size;

    /**
     * Constructs an empty linked binary tree.
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Constructs a linked binary tree with a specified root element.
     *
     * @param element the root element of the tree
     */
    public LinkedBinaryTree(T element) {
        root = new BinaryTreeNode<>(element);
        size = 1;
    }

    /**
     * Sets the root of the tree to the specified node.
     *
     * @param root the new root node
     * @throws EmptyCollectionException if the tree is empty
     */
    public void setRoot(BinaryTreeNode<T> root) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        this.root = root;
    }

    /**
     * Returns the element of the root node.
     *
     * @return the element of the root node
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T getRootElement() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return root.getElement();
    }

    /**
     * Returns the root node of the tree.
     *
     * @return the root node of the tree
     * @throws EmptyCollectionException      if the tree is empty
     * @throws UnsupportedOperationException if the operation is not supported
     */
    @Override
    public BinaryTreeNode<T> getRootNode() throws EmptyCollectionException, UnsupportedOperationException {
        if (root == null) {
            throw new EmptyCollectionException();
        }
        return root;
    }

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in the tree.
     *
     * @return the size of the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the tree contains the specified element.
     *
     * @param targetElement the element to be checked
     * @return true if the element is found, false otherwise
     * @throws EmptyCollectionException if the tree is empty
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
     * Finds and returns the element matching the specified target element.
     *
     * @param targetElement the element to be found
     * @return the found element
     * @throws EmptyCollectionException if the tree is empty
     * @throws ElementNotFoundException if the target element is not found
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
     * Recursive helper method to find the element in the tree.
     *
     * @param targetElement the element to be found
     * @param next          the current node in the search
     * @return the found node containing the element
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
     * Returns a string representation of the tree using an in-order traversal.
     *
     * @return a string representation of the tree
     */
    @Override
    public String toString() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        inOrder(root, tempList);

        return tempList.toString();
    }

    /**
     * Returns an iterator for in-order traversal of the tree.
     *
     * @return an iterator for in-order traversal
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
     * Returns an iterator for pre-order traversal of the tree.
     *
     * @return an iterator for pre-order traversal
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        preOrder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Recursive helper method for pre-order traversal.
     *
     * @param node     the current node in the traversal
     * @param tempList the list to store elements in traversal order
     */
    private void preOrder(BinaryTreeNode<T> node, LinkedUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preOrder(node.getLeft(), tempList);
            preOrder(node.getRight(), tempList);
        }
    }

    /**
     * Returns an iterator for post-order traversal of the tree.
     *
     * @return an iterator for post-order traversal
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        postOrder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Recursive helper method for post-order traversal.
     *
     * @param node     the current node in the traversal
     * @param tempList the list to store elements in traversal order
     */
    private void postOrder(BinaryTreeNode<T> node, LinkedUnorderedList<T> tempList) {
        if (node != null) {
            postOrder(node.getLeft(), tempList);
            postOrder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    /**
     * Returns an iterator for level-order traversal of the tree.
     *
     * @return an iterator for level-order traversal
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
