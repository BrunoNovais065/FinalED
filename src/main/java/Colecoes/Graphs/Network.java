package Colecoes.Graphs;

import Colecoes.Lists.ArrayUnorderedList;
import Colecoes.Queues.LinkedQueue;
import Colecoes.Stacks.LinkedStack;
import Colecoes.Trees.LinkedHeap;

import java.util.Iterator;

/**
 * Represents a network data structure, extending a graph with weighted edges.
 *
 * @param <T> The type of the vertices in the network.
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {

    protected double[][] adjMatrix;

    /**
     * Constructs an empty network with default capacity.
     */
    public Network() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Adds an unweighted edge between two vertices in the network.
     *
     * @param vertex1 The first vertex.
     * @param vertex2 The second vertex.
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 0);
    }

    /**
     * Adds a weighted edge between two vertices in the network.
     *
     * @param vertex1 The first vertex.
     * @param vertex2 The second vertex.
     * @param weight  The weight of the edge.
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * Adds a weighted edge between two vertices in the network using their indices.
     *
     * @param index1 The index of the first vertex.
     * @param index2 The index of the second vertex.
     * @param weight The weight of the edge.
     */
    public void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
        }
    }

    /**
     * Removes an edge between two vertices in the network.
     * The edge weight is set to positive infinity.
     *
     * @param vertex1 The first vertex.
     * @param vertex2 The second vertex.
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Removes an edge between two vertices in the network using their indices.
     * The edge weight is set to positive infinity.
     *
     * @param index1 The index of the first vertex.
     * @param index2 The index of the second vertex.
     */
    @Override
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = Double.POSITIVE_INFINITY;
            adjMatrix[index2][index1] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * Adds a vertex to the network with an associated value.
     *
     * @param vertex The value of the vertex to be added.
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
     * Adds an unconnected vertex to the network without an associated value.
     */
    public void addVertex() {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = null;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    /**
     * Removes a vertex from the network based on its value.
     * If the vertex is not found, no action is taken.
     *
     * @param vertex The value of the vertex to be removed.
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
     * Removes a vertex from the network based on its index.
     * If the index is not valid, no action is taken.
     *
     * @param index The index of the vertex to be removed.
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
     * Returns an iterator that performs a depth-first traversal
     * starting from the vertex with the specified value.
     *
     * @param startVertex The value of the starting vertex.
     * @return An iterator for depth-first traversal.
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Returns an iterator that performs a depth-first traversal
     * starting from the vertex with the specified index.
     *
     * @param startIndex The index of the starting vertex.
     * @return An iterator for depth-first traversal.
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

            // Find a vertex adjacent to x that has not been visited and push it
            // on the stack
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
     * Returns an iterator that performs a breadth-first traversal
     * starting from the vertex with the specified value.
     *
     * @param startVertex The value of the starting vertex.
     * @return An iterator for breadth-first traversal.
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {

        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Returns an iterator that performs a breadth-first traversal
     * starting from the vertex with the specified index.
     *
     * @param startIndex The index of the starting vertex.
     * @return An iterator for breadth-first traversal.
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

            // Find all vertices adjacent to x that have not been visited and
            // queue them up
            for (int i = 0; i < numVertices; i++) {
                if ((adjMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns an iterator that provides the indices of vertices in the
     * shortest path from the vertex with the specified start index to the
     * vertex with the specified target index.
     *
     * @param startIndex  The index of the starting vertex.
     * @param targetIndex The index of the target vertex.
     * @return An iterator for the indices of vertices in the shortest path.
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

        // Update the pathWeight for each vertex except the startVertex. Notice
        // that all vertices not adjacent to the startVertex will have a
        // pathWeight of infinity for now
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

            // Update the pathWeight for each vertex that has not been
            // visited and is adjacent to the last vertex that was visited.
            // Also, add each unvisited vertex to the heap
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
     * Returns an iterator that provides the vertices in the shortest path
     * from the vertex with the specified start index to the vertex with the
     * specified target index.
     *
     * @param startIndex  The index of the starting vertex.
     * @param targetIndex The index of the target vertex.
     * @return An iterator for the vertices in the shortest path.
     */
    @Override
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);
        while (it.hasNext()) {
            templist.addToRear(vertices[it.next()]);
        }
        return templist.iterator();
    }

    /**
     * Returns an iterator that provides the vertices in the shortest path
     * from the vertex with the specified start index to the vertex with the
     * specified target index.
     *
     * @param startVertex  The starting vertex.
     * @param targetVertex The target vertex.
     * @return An iterator for the vertices in the shortest path.
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Returns the index of an adjacent vertex with a specific weight in the
     * context of Dijkstra's algorithm.
     *
     * @param visited    An array indicating whether a vertex has been visited.
     * @param pathWeight An array of path weights for each vertex.
     * @param weight     The weight to find in the pathWeight array.
     * @return The index of an adjacent vertex with the specified weight.
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

        return -1; // should never get to here
    }

    // OTHERS METHODS

    /**
     * Creates and returns a minimum spanning tree (MST) of the network using
     * Prim's algorithm. The MST is represented as a new network with the same
     * vertices but only the edges required to form the MST.
     *
     * @return A minimum spanning tree of the network.
     */
    public Network<T> mstNetwork() {
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
            for (int j = 0; j < numVertices; i++) {
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

        /**
         * Add all edges, which are adjacent to the starting vertex, to the heap
         */
        for (int i = 0; i < numVertices; i++) {
            minHeap.addElement(adjMatrix[0][i]);
        }

        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            /**
             * Get the edge with the smallest weight that has exactly one vertex
             * already in the resultGraph
             */
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

            /**
             * Add the new edge and vertex to the resultGraph
             */
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;

            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];

            /**
             * Add all edges, that are adjacent to the newly added vertex, to
             * the heap
             */
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.adjMatrix[i][index] < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(adjMatrix[index][i]);
                }
            }
        }
        return resultGraph;
    }

    /**
     * Finds and returns an edge with a specific weight in the context of Prim's
     * algorithm for constructing a minimum spanning tree (MST). The edge is
     * selected
     * from the adjacency matrix based on the provided weight and the visited status
     * of the vertices.
     *
     * @param weight  The weight of the edge to find.
     * @param visited An array indicating whether a vertex has been visited.
     * @return An array of two integers representing the vertices of the found edge.
     *         If no valid edge is found, the array contains [-1, -1].
     */
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

        /**
         * Will only get to here if a valid edge is not found
         */
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }

    /**
     * Calculates and returns the weight of the shortest path between two vertices
     * in the network. The weight is determined by summing the weights of the edges
     * along the shortest path.
     *
     * @param startIndex  The index of the starting vertex.
     * @param targetIndex The index of the target vertex.
     * @return The weight of the shortest path between the specified vertices. If
     *         the vertices are not valid or there is no path, returns
     *         Double.POSITIVE_INFINITY.
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
     * Calculates and returns the weight of the shortest path between two vertices
     * in the network. The weight is determined by summing the weights of the edges
     * along the shortest path.
     *
     * @param startVertex  The starting vertex.
     * @param targetVertex The target vertex.
     * @return The weight of the shortest path between the specified vertices. If
     *         the vertices are not valid or there is no path, returns
     *         Double.POSITIVE_INFINITY.
     */
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        return shortestPathWeight(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Expands the capacity of the graph by doubling the size of the arrays
     * representing vertices and the adjacency matrix.
     * 
     * @throws OutOfMemoryError If there is not enough memory to allocate the
     *                          expanded arrays.
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
     * Returns a string representation of the graph, including the adjacency matrix,
     * vertex values, and weights of edges.
     *
     * If the graph is empty, the string "Graph is empty" is returned.
     *
     * The format of the string includes the following sections:
     * 1. Adjacency Matrix: Displays the matrix indicating the presence (1) or
     * absence (0)
     * of edges between vertices.
     * 2. Vertex Values: Displays the index and value of each vertex.
     * 3. Weights of Edges: Displays the index, weight, and pair of vertices for
     * each
     * edge with a finite weight in the graph.
     *
     * Example:
     * Adjacency Matrix
     * ----------------
     * index 0 1 2
     * 0 0 1 0
     * 1 1 0 1
     * 2 0 1 0
     *
     * Vertex Values
     * -------------
     * index value
     * 0 A
     * 1 B
     * 2 C
     *
     * Weights of Edges
     * ----------------
     * index weight
     * 0 to 1 2.0
     * 1 to 2 1.5
     *
     * @return A string representation of the graph.
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
