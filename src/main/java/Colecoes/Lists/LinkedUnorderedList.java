package Colecoes.Lists;

import org.w3c.dom.Node;

import Colecoes.AllExecoes.EmptyCollectionException;
import Colecoes.AllExecoes.NoSuchElementException;

public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedListADT<T> {

    /**
     * Adds the specified element to the front of the linked list.
     *
     * @param element the element to be added to the front of the linked list
     */
    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Adds the specified element to the rear of the linked list.
     *
     * @param element the element to be added to the rear of the linked list
     */
    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
        modCount++;
    }

    /**
     * Adds the specified element after the target element in the linked list.
     *
     * @param element the element to be added
     * @param target  the target element after which the new element will be added
     * @throws EmptyCollectionException if the linked list is empty
     * @throws NoSuchElementException   if the target element is not found in the
     *                                  linked list
     */
    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty())
            throw new EmptyCollectionException();
        if (!contains(target))
            throw new NoSuchElementException();
        Node<T> newNode = new Node<>(element);
        Node<T> current = head;
        while (!current.getElement().equals(target)) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        if (newNode.getNext() == null) {
            tail = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Inverts the linked list in-place.
     */
    public void invert() {
        this.head = invert(head);
    }

    /**
     * Recursively inverts the given node and returns the new head of the inverted
     * list.
     *
     * @param node the current node in the inversion process
     * @return the new head of the inverted list
     */
    private Node<T> invert(Node<T> node) {
        if (node == null || node.getNext() == null)
            return node;
        Node<T> newHead = invert(node.getNext());
        node.getNext().setNext(node);
        node.setNext(null);
        return newHead;
    }
}
