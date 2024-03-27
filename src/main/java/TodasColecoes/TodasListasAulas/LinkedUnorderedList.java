package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NoSuchElementException;


public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedListADT<T> {

    /**
     * Adiciona o elemento especificado à frente da lista encadeada.
     *
     * @param element o elemento a ser adicionado à frente da lista encadeada
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
     * Adiciona o elemento especificado à retaguarda da lista encadeada.
     *
     * @param element o elemento a ser adicionado à retaguarda da lista encadeada
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
     * Adiciona o elemento especificado após o elemento de destino especificado.
     *
     * @param element o elemento a ser adicionado após o elemento de destino
     * @param target  o elemento após o qual o novo elemento será adicionado
     * @throws EmptyCollectionException se a lista estiver vazia
     * @throws NoSuchElementException   se o elemento de destino não for encontrado
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
     * Inverte a lista encadeada.
     */
    public void invert() {
        this.head = invert(head);
    }

    /**
     * Inverte a lista encadeada.
     *
     * @param node o nó atual
     * @return o nó invertido
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
