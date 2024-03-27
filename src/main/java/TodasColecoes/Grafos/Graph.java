package TodasColecoes.Grafos;

import TodasColecoes.TodasListasAulas.ArrayUnorderedList;
import TodasColecoes.Queues.LinkedQueue;
import TodasColecoes.Stacks.LinkedStack;
import GAME.Location;

import java.util.Iterator;


/**

 Classe que representa uma implementação de grafo usando uma matriz de adjacência.
 @param <T> O tipo genérico do grafo.
 */
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected boolean[][] adjMatrix;
    protected T[] vertices;

    /**
     * Construtor que inicializa o grafo com valores padrão.
     */
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }


    /**
     * Adiciona uma aresta entre os vértices com os valores especificados no grafo.
     * Se os vértices forem encontrados, uma aresta é adicionada entre eles usando seus índices.
     *
     * @param vertex1 O valor do primeiro vértice.
     * @param vertex2 O valor do segundo vértice.
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }


    /**
     * Adiciona uma aresta não direcionada entre os vértices nos índices especificados no
     * grafo.
     * Se os índices forem válidos, uma aresta é adicionada entre os vértices em ambas as
     * direções.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Recupera o índice do vértice com o valor especificado no grafo.
     *
     * @param vertex O valor do vértice cujo índice deve ser recuperado.
     * @return O índice do vértice se encontrado, ou -1 se o vértice não estiver presente
     * no grafo.
     */
    public int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Remove a aresta não direcionada entre os vértices com os valores especificados no
     * grafo.
     * Se os vértices forem encontrados, a aresta é removida em ambas as direções.
     *
     * @param vertex1 O valor do primeiro vértice.
     * @param vertex2 O valor do segundo vértice.
     */
    public void removeEdge(T vertex1, T vertex2) {

        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }


    /**
     * Remove a aresta não direcionada entre os vértices nos índices especificados no
     * grafo.
     * Se os índices forem válidos, a aresta é removida em ambas as direções na
     * matriz de adjacência.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }

    /**
     * Retorna um iterador que realiza uma travessia em largura (BFS) começando
     * a partir do vértice com o valor especificado no grafo.
     *
     * @param startVertex O valor do vértice para iniciar a travessia BFS.
     * @return Um iterador sobre os vértices visitados durante a travessia BFS.
     */
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Retorna um iterador que realiza uma travessia em largura (BFS) começando
     * a partir do vértice no índice especificado no grafo.
     *
     * @param startIndex O índice do vértice para iniciar a travessia BFS.
     * @return Um iterador sobre os vértices visitados durante a travessia BFS.
     */
    public Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        // First BFS traversal
        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }


        boolean[] reverseVisited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            reverseVisited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        reverseVisited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[i][x] && !reverseVisited[i]) {
                    traversalQueue.enqueue(i);
                    reverseVisited[i] = true;
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && !reverseVisited[i]) {
                System.out.println("The graph is not strongly connected.");
                return resultList.iterator();
            }
        }

        boolean connected = false;
        for (int i = 0; i < numVertices; i++) {
            if (!adjMatrix[i][0]) {
                connected = true;
            }
        }
        if (!connected) {
            return resultList.iterator();
        }

        return resultList.iterator();
    }





    /**
     * Retorna um iterador que realiza uma travessia em profundidade (DFS) começando
     * a partir do vértice com o valor especificado no grafo.
     *
     * @param startVertex O valor do vértice para iniciar a travessia DFS.
     * @return Um iterador sobre os vértices visitados durante a travessia DFS.
     */
    public Iterator<T> iteratorDFS(T startVertex) {

        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Retorna um iterador que realiza uma travessia em profundidade (DFS) começando
     * a partir do vértice no índice especificado no grafo.
     *
     * @param startIndex O índice do vértice para iniciar a travessia DFS.
     * @return Um iterador sobre os vértices visitados durante a travessia DFS.
     */
    public Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;


            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     * Retorna um iterador que representa o caminho mais curto do vértice de início
     * com o valor especificado para o vértice de destino com o valor especificado.
     *
     * @param startVertex O valor do vértice de início.
     * @param targetVertex O valor do vértice de destino.
     * @return Um iterador sobre os vértices no caminho mais curto.
     */
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Retorna um iterador que representa o caminho mais curto do vértice de início
     * no índice especificado para o vértice de destino no índice especificado.
     *
     * @param startIndex O índice do vértice de início.
     * @param targetIndex O índice do vértice de destino.
     * @return Um iterador sobre os vértices no caminho mais curto.
     */
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return resultList.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);
        while (it.hasNext()) {
            resultList.addToRear(vertices[it.next()]);
        }
        return resultList.iterator();
    }

    /**
     * Adiciona um novo vértice com o valor especificado ao grafo.
     * Se o número atual de vértices for igual ao comprimento da matriz,
     * a capacidade é expandida antes de adicionar o novo vértice.
     *
     * As arestas envolvendo o novo vértice na matriz de adjacência são definidas como falso.
     * Aumenta o número de vértices no grafo.
     *
     * @param vertex O valor do novo vértice a ser adicionado.
     */
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i < numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    /**
     * Remove o vértice com o valor especificado do grafo.
     * Se o vértice for encontrado, a linha e a coluna correspondentes na
     * matriz de adjacência são removidas, juntamente com o próprio vértice.
     *
     * @param vertex O valor do vértice a ser removido do grafo.
     */
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }


    /**
     * Remove o vértice no índice especificado do grafo.
     * Se o índice for válido, a linha e a coluna correspondentes na
     * matriz de adjacência são removidas, juntamente com o próprio vértice.
     *
     * @param index O índice do vértice a ser removido do grafo.
     */
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j <= numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[j][i] = adjMatrix[j][i + 1];
                }
            }
        }
    }


    /**
     * Verifica se o índice especificado é válido dentro do número atual
     * de vértices no grafo.
     *
     * @param index O índice a ser verificado quanto à validade.
     * @return true se o índice for válido, false caso contrário.
     */
    public boolean indexIsValid(int index) {
        return ((index < numVertices) && (index >= 0));
    }


    /**
     * Retorna um iterador sobre os índices representando o caminho mais curto
     * do vértice de início no índice especificado para o vértice de destino
     * no índice especificado.
     *
     * @param startIndex O índice do vértice de início.
     * @param targetIndex O índice do vértice de destino.
     * @return Um iterador sobre os índices no caminho mais curto.
     */
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        int index = startIndex;
        int[] pathLength = new int[numVertices];
        int[] predecessor = new int[numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        predecessor[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (index != targetIndex)) {
            index = traversalQueue.dequeue();


            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[index][i] && !visited[i]) {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        if (index != targetIndex) {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<>();
        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear(stack.pop());
        }

        return resultList.iterator();
    }

    /**
     * Expande a capacidade do grafo, duplicando o tamanho da matriz de adjacência
     * e o tamanho do array de vértices.
     */
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        boolean[][] largerAdjMatrix = new boolean[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    /**
     * Retorna um iterador que representa o caminho mais curto do vértice de início
     * com o valor especificado para o vértice de destino com o valor especificado.
     *
     * @param startVertex  O valor do vértice de início.
     * @param targetVertex O valor do vértice de destino.
     * @return Um iterador sobre os vértices no caminho mais curto.
     */
    public int shortestPathLength(T startVertex, T targetVertex) {
        return shortestPathLength(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Retorna o comprimento do caminho mais curto do vértice de início no índice
     * especificado para o vértice de destino no índice especificado.
     *
     * @param startIndex  O índice do vértice de início.
     * @param targetIndex O índice do vértice de destino.
     * @return O comprimento do caminho mais curto.
     */
    public int shortestPathLength(int startIndex, int targetIndex) {
        int result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return 0;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);

        if (it.hasNext()) {
            index1 = it.next();
        } else {
            return 0;
        }

        while (it.hasNext()) {
            result++;
            it.next();
        }

        return result;
    }

    /**
     * Verifica se o grafo esta vazio.
     *
     * @return retorna true se estiver vazio.
     */
    public boolean isEmpty() {
        return (numVertices == 0);
    }

    /**
     * Verifica se o grafo e conexo.
     *
     * @return se o grafo for conexo vai returnar true.
     */
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }

        Iterator<T> it = iteratorBFS(0);
        int count = 0;

        while (it.hasNext()) {
            it.next();
            count++;
        }
        return (count == numVertices);
    }

    /**
     * Retorna o numero de vertices do grafo.
     *
     * @return Numero de vertices do grafo.
     */
    public int size() {

        return numVertices;
    }

    /**
     * Remove todos os vertices do grafo.
     */
    @Override
    public void clear() {

        numVertices = 0;

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = null;
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = false;
            }
        }
    }

    /**
     * Retorna uma representação do grafo como uma string.
     *
     * @return Uma representação do grafo como uma string.
     */
    public String toString() {
        if (numVertices == 0) {
            return "Graph is empty";
        }

        String result = "";

        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\t";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i;
            if (i < 10) {
                result += " ";
            }
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j]) {
                    result += "1 ";
                } else {
                    result += "0 ";
                }
            }
            result += "\n";
        }

        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }
        result += "\n";
        return result;
    }

    /**
     * Retorna as localizações.
     *
     * @return Um array de localizações.
     */
    public Location[] getVertices() {
        Location[] vertices = new Location[numVertices];
        Location vertex;

        for (int i = 0; i < numVertices; i++) {
            vertex = (Location) this.vertices[i];
            vertices[i] = vertex;
        }
        return (Location[]) vertices;
    }
}
