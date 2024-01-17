package Colecoes.Trees;

import Colecoes.AllExecoes.ElementNotFoundException;
import Colecoes.AllExecoes.EmptyCollectionException;
import Colecoes.AllExecoes.NonComparableElementException;

/**
 * Represents a linked binary search tree, which is a specialized binary tree
 * where the left subtree of a node contains only nodes with elements less than
 * the node's element, and the right subtree contains only nodes with elements
 * greater than the node's element.
 *
 * @param <T> the type of elements stored in the binary search tree
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Constructs an empty linked binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Constructs a linked binary search tree with a specified root element.
     *
     * @param element the root element of the tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adds an element to the binary search tree while maintaining the
     * properties of a binary search tree.
     *
     * @param element the element to be added to the tree
     * @throws NonComparableElementException if the element is not comparable
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
     * Removes the specified element from the binary search tree, maintaining
     * the properties of a binary search tree.
     *
     * @param targetElement the element to be removed
     * @return the removed element
     * @throws EmptyCollectionException      if the tree is empty
     * @throws ElementNotFoundException      if the target element is not found
     * @throws NonComparableElementException if the element is not comparable
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
     * Removes the specified node from the binary search tree and returns its
     * replacement node.
     *
     * @param node the node to be removed
     * @return the replacement node for the removed node
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
     * Removes all occurrences of the specified element from the binary search
     * tree.
     *
     * @param targetElement the element to be removed
     * @throws EmptyCollectionException      if the tree is empty
     * @throws ElementNotFoundException      if the target element is not found
     * @throws NonComparableElementException if the element is not comparable
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        removeElement(targetElement);
        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    /**
     * Removes the minimum element from the binary search tree.
     *
     * @return the minimum element
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        return removeElement(findMin());
    }

    /**
     * Removes the maximum element from the binary search tree.
     *
     * @return the maximum element
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        return removeElement(findMax());
    }

    /**
     * Finds and returns the minimum element in the binary search tree.
     *
     * @return the minimum element
     * @throws EmptyCollectionException if the tree is empty
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
     * Finds and returns the maximum element in the binary search tree.
     *
     * @return the maximum element
     * @throws EmptyCollectionException if the tree is empty
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
