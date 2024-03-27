package TodasColecoes.Trees;


public class PriorityQueue<T> extends ArrayHeap<PriorityQueueNode<T>> {

    public PriorityQueue() {
        super();
    }

    /**
     * Adiciona um novo elemento à fila de prioridade.
     *
     * @param object   o elemento a ser adicionado à fila de prioridade
     * @param priority a prioridade do elemento a ser adicionado
     */
    public void addElement(T object, int priority) {
        PriorityQueueNode<T> node = new PriorityQueueNode<T>(object, priority);
        super.addElement(node);
    }

    /**
     * Remove o próximo elemento da fila de prioridade, retornando uma referência a ele.
     *
     * @return uma referência ao próximo elemento da fila de prioridade
     */
    public T removeNext() {
        PriorityQueueNode<T> temp = super.removeMin();
        return temp.getElement();
    }
}
