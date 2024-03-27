package TodasColecoes.Trees;


public class BinaryTreeNode<T> {
    protected T element;
    protected BinaryTreeNode<T> left, right;

    /**
     * Cria um novo nó da árvore binária com o elemento especificado.
     *
     * @param element o elemento que se tornará parte do novo nó da árvore
     */

    public BinaryTreeNode(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    /**
     * Cria um novo nó da árvore binária com os elementos especificados.
     *
     * @param element o elemento que se tornará parte do novo nó da árvore
     * @param left    o nó que será o filho à esquerda do novo nó
     * @param right   o nó que será o filho à direita do novo nó
     */
    public BinaryTreeNode(T element, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    /**
     * Retorna o elemento armazenado neste nó.
     *
     * @return T o elemento armazenado neste nó
     */
    public T getElement() {
        return element;
    }

    /**
     * Define o elemento armazenado neste nó.
     *
     * @param element o elemento a ser armazenado neste nó
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Retorna o filho à esquerda deste nó.
     *
     * @return BinaryTreeNode o filho à esquerda deste nó
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Define o filho à esquerda deste nó.
     *
     * @param left o nó que será o filho à esquerda deste nó
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Retorna o filho à direita deste nó.
     *
     * @return BinaryTreeNode o filho à direita deste nó
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /**
     * Define o filho à direita deste nó.
     *
     * @param right o nó que será o filho à direita deste nó
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Retorna a altura desta árvore.
     *
     * @return int a altura desta árvore
     */
    public int numChildren() {
        int children = 0;

        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        return children;
    }

    public int getBalanceFactor() {
        return left.numChildren() - right.numChildren();
    }
}
