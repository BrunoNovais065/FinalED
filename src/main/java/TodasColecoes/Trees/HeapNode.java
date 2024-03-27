package TodasColecoes.Trees;


public class HeapNode<T> extends BinaryTreeNode<T> {
    protected HeapNode<T> parent;

    /**
     * Constrói um nó de heap com o elemento especificado.
     *
     * @param element o elemento a ser armazenado no nó
     */
    public HeapNode(T element) {
        super(element);
        parent = null;
    }

    /**
     * Constrói um nó de heap com o elemento especificado e os filhos esquerdo e direito.
     *
     * @param element o elemento a ser armazenado no nó
     * @param left    o filho esquerdo do nó
     * @param right   o filho direito do nó
     */
    public HeapNode(T element, HeapNode<T> left, HeapNode<T> right) {
        super(element, left, right);
        parent = null;
    }

    /**
     * Constrói um nó de heap com o elemento especificado e o pai.
     *
     * @param element o elemento a ser armazenado no nó
     * @param parent  o nó pai do nó atual
     */
    public HeapNode(T element, HeapNode<T> parent) {
        super(element);
        this.parent = parent;
    }

    /**
     * Constrói um nó de heap com o elemento especificado e os filhos esquerdo, direito e pai.
     *
     * @param element o elemento a ser armazenado no nó
     * @param left    o filho esquerdo do nó
     * @param right   o filho direito do nó
     * @param parent  o nó pai do nó atual
     */
    public HeapNode(T element, HeapNode<T> left, HeapNode<T> right, HeapNode<T> parent) {
        super(element, left, right);
        this.parent = parent;
    }

    /**
     * Retorna o nó pai do nó atual.
     *
     * @return o nó pai do nó atual
     */
    public HeapNode<T> getParent() {
        return parent;
    }

    /**
     * Define o nó pai do nó atual.
     *
     * @param parent o nó pai do nó atual
     */
    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }
}
