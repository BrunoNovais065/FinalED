package TodasColecoes.Grafos;

import TodasColecoes.TodasListasAulas.ArrayUnorderedList;
import TodasColecoes.Queues.LinkedQueue;
import TodasColecoes.Stacks.LinkedStack;
import TodasColecoes.Trees.LinkedHeap;

import java.util.Iterator;
import java.util.Random;


/**

 Representa uma estrutura de dados de rede, estendendo um grafo com arestas ponderadas.
 @param <T> O tipo dos vértices na rede.
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {

    protected double[][] adjMatrix;

    /**
     * Constrói uma rede vazia com capacidade padrão.
     */
    public Network() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Adiciona um vértice à rede com um valor associado.
     *
     * @param vertex O valor do vértice a ser adicionado.
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    /**
     * Remove um vértice da rede com base em seu valor.
     * Se o vértice não for encontrado, nenhuma ação é realizada.
     *
     * @param vertex O valor do vértice a ser removido.
     */
    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * Remove um vértice da rede com base em seu índice.
     * Se o índice não for válido, nenhuma ação é realizada.
     *
     * @param index O índice do vértice a ser removido.
     */
    @Override
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
     * Retorna um iterador que realiza uma travessia em profundidade
     * começando a partir do vértice com o valor especificado.
     *
     * @param startVertex O valor do vértice de início.
     * @return Um iterador para a travessia em profundidade.
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Retorna um iterador que realiza uma travessia em profundidade
     * começando a partir do vértice com o índice especificado.
     *
     * @param startIndex O índice do vértice de início.
     * @return Um iterador para a travessia em profundidade.
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
                if ((adjMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
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
     * Retorna um iterador que realiza uma travessia em largura
     * começando a partir do vértice com o valor especificado.
     *
     * @param startVertex O valor do vértice de início.
     * @return Um iterador para a travessia em largura.
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {

        return iteratorBFS(getIndex(startVertex));
    }


    /**
     * Retorna um iterador que realiza uma travessia em largura
     * começando a partir do vértice com o índice especificado.
     *
     * @param startIndex O índice do vértice de início.
     * @return Um iterador para a travessia em largura.
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

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);

            int count = 0;
            for (int i = 0; i < numVertices; i++) {
                if ((adjMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                    count++;
                }
            }

            if (count == 0) {
                for (int i = 0; i < numVertices; i++) {
                        if (adjMatrix[x][i] < Double.POSITIVE_INFINITY){
                            count++;
                        }
                }
                if(count == 0) {
                    resultList = new ArrayUnorderedList<>();
                    return resultList.iterator();
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Retorna um iterador que fornece os índices dos vértices no
     * caminho mais curto do vértice com o índice de início especificado para o
     * vértice com o índice de destino especificado.
     *
     * @param startIndex O índice do vértice de início.
     * @param targetIndex O índice do vértice de destino.
     * @return Um iterador para os índices dos vértices no caminho mais curto.
     */
    @Override
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }

        do {
            weight = traversalMinHeap.removeMin();
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                visited[index] = true;
            }

            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }


    /**
     * Retorna um iterador que fornece os índices dos vértices no caminho mais curto
     * @param startIndex O índice do vértice de início.
     * @param targetIndex O índice do vértice de destino.
     * @return Um iterador para os índices dos vértices no caminho mais curto.
     */
    public Iterator<Integer> iteratorShortestPathIndicesDijkstra(int startIndex, int targetIndex) {
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];
        double[] distances = new double[numVertices];
        int[] predecessors = new int[numVertices];

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
            distances[i] = Double.POSITIVE_INFINITY;
            predecessors[i] = -1;
        }

        distances[startIndex] = 0;

        while (true) {
            int minIndex = -1;
            double minDistance = Double.POSITIVE_INFINITY;

            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && distances[i] < minDistance) {
                    minIndex = i;
                    minDistance = distances[i];
                }
            }

            if (minIndex == -1) {
                break;
            }

            visited[minIndex] = true;

            if (minIndex == targetIndex) {

                int current = targetIndex;
                while (current != -1) {
                    resultList.addToFront(current);
                    current = predecessors[current];
                }
                return resultList.iterator();
            }

            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && adjMatrix[minIndex][i] < Double.POSITIVE_INFINITY) {
                    double newDistance = distances[minIndex] + adjMatrix[minIndex][i];
                    if (newDistance < distances[i]) {
                        distances[i] = newDistance;
                        predecessors[i] = minIndex;
                    }
                }
            }
        }

        return resultList.iterator();
    }


    /**
     * Retorna um iterador que fornece os vértices no caminho mais curto
     * do vértice com o índice de início especificado para o vértice com o
     * índice de destino especificado.
     *
     * @param startIndex O índice do vértice de início.
     * @param targetIndex O índice do vértice de destino.
     * @return Um iterador para os vértices no caminho mais curto.
     */
    @Override
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        if (isBidirectional()) {
            Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);
            while (it.hasNext()) {
                templist.addToRear(vertices[it.next()]);
            }
            return templist.iterator();
        }else {
            Iterator<Integer> it = iteratorShortestPathIndicesDijkstra(startIndex, targetIndex);
            while (it.hasNext()) {
                templist.addToRear(vertices[it.next()]);
            }
            return templist.iterator();
        }
    }


    /**
     * Método para verificar se esta Rede é bidirecional.
     * @return true se esta Rede for bidirecional.
     */
    public boolean isBidirectional() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {

                if (adjMatrix[i][j] != adjMatrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Retorna um iterador que fornece os vértices no caminho mais curto
     * do vértice com o valor de início especificado para o vértice com o
     * valor de destino especificado.
     *
     * @param startVertex O vértice de início.
     * @param targetVertex O vértice de destino.
     * @return Um iterador para os vértices no caminho mais curto.
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }


    /**
     * Retorna um iterador contendo os vértices com os pesos mais altos na rede
     * entre os vértices especificados como primeiro e último.
     *
     * @param firstVertex O primeiro vértice no intervalo.
     * @param lastVertex O último vértice no intervalo.
     * @return Um iterador de vértices com os pesos mais altos no intervalo especificado.
     */
    public Iterator<T> iteratorVerticesWithHighestWeight(T firstVertex, T lastVertex) {
        ArrayUnorderedList<T> verticesWithHighestWeight = new ArrayUnorderedList<>();
        double highestWeight = Double.NEGATIVE_INFINITY;

        int startIndex = getIndex(firstVertex);
        int lastIndex = getIndex(lastVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(lastIndex)) {
            return verticesWithHighestWeight.iterator();
        }
        verticesWithHighestWeight.addToRear(vertices[startIndex]);
        int k = startIndex;

        Random random = new Random();

        while (k != lastIndex) {
            int temp = 0;
            for (int j = 0; j <= this.vertices.length - 1; j++) {
                if (adjMatrix[k][j] > highestWeight && adjMatrix[k][j] < Double.POSITIVE_INFINITY &&
                        !verticesWithHighestWeight.contains(vertices[j])) {
                    highestWeight = adjMatrix[k][j];
                    temp = j;
                }
            }

            if (temp == 0) {
                int randomIndex;
                do {
                    randomIndex = random.nextInt(this.vertices.length);
                } while (adjMatrix[k][randomIndex] <= 0);
                temp = randomIndex;
                highestWeight = adjMatrix[k][randomIndex];
            }

            k = temp;
            verticesWithHighestWeight.addToRear(vertices[k]);
            highestWeight = 0;
        }

        return verticesWithHighestWeight.iterator();
    }



    /**
     * Retorna um iterador contendo os vértices com os pesos mais baixos na rede
     * entre os vértices especificados como primeiro e último.
     *
     * @param firstVertex O primeiro vértice no intervalo.
     * @param lastVertex O último vértice no intervalo.
     * @return Um iterador de vértices com os pesos mais baixos no intervalo especificado.
     */
    public Iterator<T> iteratorVerticesWithSmallestWeight(T firstVertex, T lastVertex) {
        ArrayUnorderedList<T> verticesWithSmallestWeight = new ArrayUnorderedList<>();
        double smallestWeight = Double.POSITIVE_INFINITY;

        int startIndex = getIndex(firstVertex);
        int lastIndex = getIndex(lastVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(lastIndex)) {
            return verticesWithSmallestWeight.iterator();
        }

        verticesWithSmallestWeight.addToRear(vertices[startIndex]);
        int k = startIndex;

        Random random = new Random();


        while (k != lastIndex) {
            int temp = 0;
            for (int j = 0; j <= this.vertices.length - 1; j++) {
                if (adjMatrix[k][j] < smallestWeight && adjMatrix[k][j] > 0 && !verticesWithSmallestWeight.contains(vertices[j])) {
                    smallestWeight = adjMatrix[k][j];
                    temp = j;
                }
            }


            if (temp == 0) {
                int randomIndex;
                do {
                    randomIndex = random.nextInt(this.vertices.length);
            } while (adjMatrix[k][randomIndex] <= 0);

                temp = randomIndex;
                smallestWeight = adjMatrix[k][randomIndex];
            }

            k = temp;

            verticesWithSmallestWeight.addToRear(vertices[k]);
            smallestWeight = Double.POSITIVE_INFINITY;
        }

        return verticesWithSmallestWeight.iterator();
    }






    /**
     * Retorna o índice de um vértice adjacente com um peso específico no
     * contexto do algoritmo de Dijkstra.
     *
     * @param visited Um array indicando se um vértice foi visitado.
     * @param pathWeight Um array de pesos de caminho para cada vértice.
     * @param weight O peso a ser encontrado no array de pathWeight.
     * @return O índice de um vértice adjacente com o peso especificado.
     */
    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited, double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((adjMatrix[i][j] < Double.POSITIVE_INFINITY) && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }


    public Network<T> mstNetworkk() {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        LinkedHeap<Double> minHeap = new LinkedHeap<Double>();
        Network<T> resultGraph = new Network<T>();

        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }
        resultGraph.adjMatrix = new double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                resultGraph.adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
            resultGraph.vertices = (T[]) (new Object[numVertices]);
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;


        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[0][i] < Double.POSITIVE_INFINITY) {
                minHeap.addElement(adjMatrix[0][i]);
            }
        }

        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {

            do {
                weight = minHeap.removeMin();
                edge = getEdgeWithWeightOf(weight, visited);
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }


            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;

            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];


            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.adjMatrix[index][i] < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(adjMatrix[index][i]);
                }
            }
        }
        return resultGraph;
    }



    public Iterator<T> shortestPathMTS(T startVertex, T endVertex) {
        Network<T> mst = mstNetworkk();
        ArrayUnorderedList<T> path = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];


        boolean pathExists = dfs(startVertex, endVertex, mst, visited, path);

        if (pathExists) {
            return path.iterator();
        } else {
            return new ArrayUnorderedList<T>().iterator();
        }
    }

    private boolean dfs(T currentVertex, T endVertex, Network<T> graph, boolean[] visited, ArrayUnorderedList<T> path) {
        visited[graph.getIndex(currentVertex)] = true;
        path.addToRear(currentVertex);

        if (currentVertex.equals(endVertex)) {
            return true;
        }

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && graph.adjMatrix[graph.getIndex(currentVertex)][i] < Double.POSITIVE_INFINITY) {
                T nextVertex = graph.vertices[i];
                if (dfs(nextVertex, endVertex, graph, visited, path)) {
                    return true;
                }
            }
        }


        path.removeLast();
        return false;
    }

    /**
     * Retorna o peso da aresta entre dois vértices na rede.
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     * @return O peso da aresta especificada.
     */
    public double getWeight(T vertex1, T vertex2) {
        return getWeight(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Retorna o peso da aresta entre dois vértices na rede.
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     * @return O peso da aresta especificada.
     */
    private double getWeight(int index1, int index2) {
        return adjMatrix[index1][index2];
    }


    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((adjMatrix[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }


        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }


    /**
     * Calcula e retorna o peso do caminho mais curto entre dois vértices
     * na rede. O peso é determinado somando os pesos das arestas
     * ao longo do caminho mais curto.
     *
     * @param startIndex O índice do vértice de início.
     * @param targetIndex O índice do vértice de destino.
     * @return O peso do caminho mais curto entre os vértices especificados. Se
     * os vértices não forem válidos ou não houver caminho, retorna
     * Double.POSITIVE_INFINITY.
     */
    public double shortestPathWeight(int startIndex, int targetIndex) {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);

        if (it.hasNext()) {
            index1 = it.next();
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = it.next();
            result += adjMatrix[index1][index2];
            index1 = index2;
        }

        return result;
    }

    /**
     * Calcula e retorna o peso do caminho mais curto entre dois vértices
     * na rede. O peso é determinado somando os pesos das arestas
     * ao longo do caminho mais curto.
     *
     * @param startVertex O vértice de início.
     * @param targetVertex O vértice de destino.
     * @return O peso do caminho mais curto entre os vértices especificados. Se
     * os vértices não forem válidos ou não houver caminho, retorna
     * Double.POSITIVE_INFINITY.
     */
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        return shortestPathWeight(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Expande a capacidade do grafo dobrando o tamanho dos arrays
     * que representam os vértices e a matriz de adjacência.
     *
     * @throws OutOfMemoryError Se não houver memória suficiente para alocar os
     * arrays expandidos.
     */
    @Override
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        double[][] largerAdjMatrix = new double[vertices.length * 2][vertices.length * 2];

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
     * Adiciona uma aresta não ponderada entre dois vértices na rede.
     *
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 0);
    }

    /**
     * Adiciona uma aresta ponderada entre dois vértices na rede.
     *
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     * @param weight O peso da aresta.
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * Adiciona uma aresta ponderada entre dois vértices na rede usando seus índices.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     * @param weight O peso da aresta.
     */
    public void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            //adjMatrix[index2][index1] = weight;
        }
    }

    /**
     * Adiciona uma aresta ponderada bidirecional entre dois vértices na rede.
     *
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     * @param weight O peso da aresta.
     */
    public void addEdgeBi(T vertex1, T vertex2, double weight) {
        addEdgeBi(getIndex(vertex1), getIndex(vertex2), weight);
    }



    /**
     * Adiciona uma aresta ponderada bidirecional entre dois vértices na rede usando seus índices.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     * @param weight O peso da aresta.
     */
    public void addEdgeBi(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
        }
    }

    /**
     * Remove uma aresta entre dois vértices na rede.
     * O peso da aresta é definido como infinito positivo.
     *
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Remove uma aresta entre dois vértices na rede usando seus índices.
     * O peso da aresta é definido como infinito positivo.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     */
    @Override
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = Double.POSITIVE_INFINITY;
            //adjMatrix[index2][index1] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * Retorna uma representação de string da rede.
     *
     * @return Uma representação de string da rede.
     */
    public String toString() {
        if (numVertices == 0) {
            return "Graph is empty";
        }
        String result = "";

        // Print the adjacency Matrix
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
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result += "1 ";
                } else {
                    result += "0 ";
                }
            }
            result += "\n";
        }

        // Print the vertex values
        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }

        // Print the weights of the edges
        result += "\n\nWeights of Edges";
        result += "\n----------------\n";
        result += "index\tweight\n\n";

        for (int i = 0; i < numVertices; i++) {
            for (int j = numVertices - 1; j > i; j--) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result += i + " to " + j + "\t";
                    result += adjMatrix[i][j] + "\n";
                }
            }
        }

        result += "\n";
        return result;
    }
}
