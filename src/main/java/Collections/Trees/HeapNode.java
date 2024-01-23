package Collections.Trees;

/**
 * Represents a node in a binary heap.
 *
 * @param <T> the type of elements stored in the heap node
 */
public class HeapNode<T> extends BinaryTreeNode<T> {
    protected HeapNode<T> parent;

    /**
     * Constructs a heap node with the specified element.
     *
     * @param element the element to be stored in the node
     */
    public HeapNode(T element) {
        super(element);
        parent = null;
    }

    /**
     * Constructs a heap node with the specified element, left child, and right
     * child.
     *
     * @param element the element to be stored in the node
     * @param left    the left child of the node
     * @param right   the right child of the node
     */
    public HeapNode(T element, HeapNode<T> left, HeapNode<T> right) {
        super(element, left, right);
        parent = null;
    }

    /**
     * Constructs a heap node with the specified element and parent.
     *
     * @param element the element to be stored in the node
     * @param parent  the parent node of the current node
     */
    public HeapNode(T element, HeapNode<T> parent) {
        super(element);
        this.parent = parent;
    }

    /**
     * Constructs a heap node with the specified element, left child, right child,
     * and parent.
     *
     * @param element the element to be stored in the node
     * @param left    the left child of the node
     * @param right   the right child of the node
     * @param parent  the parent node of the current node
     */
    public HeapNode(T element, HeapNode<T> left, HeapNode<T> right, HeapNode<T> parent) {
        super(element, left, right);
        this.parent = parent;
    }

    /**
     * Gets the parent node of the current node.
     *
     * @return the parent node of the current node
     */
    public HeapNode<T> getParent() {
        return parent;
    }

    /**
     * Sets the parent node of the current node.
     *
     * @param parent the parent node to be set
     */
    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }
}
