package TodasColecoes.Trees;


public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode<T>> {
    private static int nextorder = 0;
    private int priority;
    private int order;
    private T element;

    /**
     * Cria um novo nó de fila de prioridade com os dados especificados.
     *
     * @param element  o elemento a ser adicionado à fila de prioridade
     * @param priority a prioridade inteira do elemento
     */
    public PriorityQueueNode(T element, int priority) {
        this.element = element;
        this.priority = priority;
        this.order = nextorder;
        nextorder++;
    }

    /**
     * Retorna o elemento armazenado neste nó.
     *
     * @return o elemento armazenado neste nó
     */
    public T getElement() {
        return element;
    }

    /**
     * Retorna a prioridade deste nó.
     *
     * @return a prioridade deste nó
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Retorna a ordem deste nó.
     *
     * @return a ordem deste nó
     */
    public int getOrder() {
        return order;
    }


    /**
     * Retorna uma representação de string deste nó.
     *
     * @return uma representação de string deste nó
     */
    public String toString() {
        return (element.toString() + priority + order);
    }

    /**
     * Compara este nó com outro nó.
     *
     * @param other o nó com o qual este nó será comparado
     * @return um valor inteiro negativo, zero ou positivo, se este nó for menor, igual ou maior que o nó especificado
     */
    @Override
    public int compareTo(PriorityQueueNode other) {
        int result;
        PriorityQueueNode<T> temp = other;
        if (priority > temp.getPriority())
            result = 1;
        else if (priority < temp.getPriority())
            result = -1;
        else if (order > temp.getOrder())
            result = 1;
        else
            result = -1;
        return result;
    }
}
