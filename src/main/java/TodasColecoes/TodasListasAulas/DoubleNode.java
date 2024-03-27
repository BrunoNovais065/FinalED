package TodasColecoes.TodasListasAulas;


public class DoubleNode<T> {
    private T element;
    private DoubleNode<T> next;
    private DoubleNode<T> previous;



    /**
     * Cria um nó armazenando o elemento especificado.
     *
     * @param element o elemento a ser armazenado dentro do novo nó
     */
    public DoubleNode(T element) {
        this.element = element;
        this.next = null;
        this.previous = null;
    }


    /**
     * Cria um nó armazenando o elemento, nó anterior e próximo especificados.
     *
     * @param element o elemento a ser armazenado dentro do novo nó
     * @param previous o nó anterior
     * @param next o próximo nó
     */
    public DoubleNode(T element, DoubleNode<T> previous, DoubleNode<T> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    /**
     * Retorna o elemento armazenado neste nó.
     *
     * @return T o elemento armazenado neste nó
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Define o elemento armazenado neste nó.
     *
     * @param node o elemento a ser armazenado neste nó
     */
    public void setElement(T node) {
        this.element = node;
    }

    /**
     * Retorna o nó anterior a este nó.
     *
     * @return DoubleNode o nó anterior a este nó
     */
    public DoubleNode<T> getPrevious() {
        return this.previous;
    }

    /**
     * Define o nó anterior a este nó.
     *
     * @param previous o nó anterior a este nó
     */
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Retorna o próximo nó neste nó.
     *
     * @return DoubleNode o próximo nó neste nó
     */
    public DoubleNode<T> getNext() {
        return this.next;
    }

    /**
     * Define o próximo nó neste nó.
     *
     * @param next o próximo nó neste nó
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
}
