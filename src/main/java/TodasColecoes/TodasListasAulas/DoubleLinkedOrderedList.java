package TodasColecoes.TodasListasAulas;

import TodasColecoes.TodasExcecoes.NonComparableElementException;


public class DoubleLinkedOrderedList<T> extends DoubleLinkedList<T> implements OrderedListADT<T> {

    public DoubleLinkedOrderedList() {
        super();
    }


    /**
     * Adiciona o elemento especificado à lista duplamente encadeada ordenada. A ordem é
     * mantida com base na ordenação natural dos elementos (usando a interface {@code Comparable}).
     *
     * @param element o elemento a ser adicionado à lista
     */
    @Override
    public void add(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        if (this.isEmpty()) {
            head = tail = new DoubleNode<>(element);
        } else {
            DoubleNode<T> current = head;
            DoubleNode<T> previous = null;
            while (current != null && ((Comparable<T>) element).compareTo(current.getElement()) > 0) {
                previous = current;
                current = current.getNext();
            }
            DoubleNode<T> newNode = new DoubleNode<>(element);
            if (previous == null) {
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
            } else {
                newNode.setNext(current);
                newNode.setPrevious(previous);
                previous.setNext(newNode);
                if (current != null) {
                    current.setPrevious(newNode);
                } else {
                    tail = newNode;
                }
            }
        }
        count++;
        modCount++;
    }
}
