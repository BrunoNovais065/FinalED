package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.EmptyCollectionException;
import TodasColecoes.TodasExcecoes.NoSuchElementException;


public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    public DoubleLinkedUnorderedList() {
        super();
    }


    /**
     * Adiciona o elemento especificado à frente da lista duplamente encadeada não ordenada.
     *
     * @param element o elemento a ser adicionado à frente da lista
     */
    @Override
    public void addToFront(T element) {
        if (isEmpty()) {
            head = tail = new DoubleNode<>(element);
        } else {
            DoubleNode<T> newNode = new DoubleNode<>(element);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        count++;
        modCount++;
    }

    /**
     * Adiciona o elemento especificado à retaguarda da lista duplamente encadeada não ordenada.
     *
     * @param element o elemento a ser adicionado à retaguarda da lista
     */
    @Override
    public void addToRear(T element) {
        if (isEmpty()) {
            head = tail = new DoubleNode<>(element);
        } else {
            DoubleNode<T> newNode = new DoubleNode<>(element);
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        count++;
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
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        DoubleNode<T> current = head;
        while (current != null && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        if (current == null)
            throw new NoSuchElementException();
        if (current == tail) {
            addToRear(element);
        } else {
            DoubleNode<T> newNode = new DoubleNode<>(element);
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
            count++;
            modCount++;
        }
    }
}
