package TodasColecoes.TodasListasAulas;


public class Node<T> {
    private T element;
    private Node<T> next;

    /**
     * Constrói um novo nó com o elemento fornecido.
     *
     * @param element o elemento a ser armazenado no nó
     */

    public Node(T element) {
        this.element = element;
        this.next = null;
    }

    /**
     * Retorna o elemento armazenado no nó.
     *
     * @return o elemento armazenado no nó
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Define o elemento armazenado no nó.
     *
     * @param element o elemento a ser armazenado no nó
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Retorna o próximo nó na lista encadeada.
     *
     * @return o próximo nó na lista encadeada
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Define o próximo nó na lista encadeada.
     *
     * @param nextNode o próximo nó na lista encadeada
     */
    public void setNext(Node<T> nextNode) {
        this.next = nextNode;
    }
}
