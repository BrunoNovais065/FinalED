package Colecoes.Graphs;

import Colecoes.Lists.ArrayUnorderedList;
import Colecoes.Queues.LinkedQueue;
import Colecoes.Stacks.LinkedStack;
import GAME.Location;

import java.util.Iterator;

// This class represents a generic graph implementation using an adjacency matrix.
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;// Default capacity for the adjacency matrix
    protected int numVertices; // number of vertices in the graph
    protected boolean[][] adjMatrix; // adjacency matrix
    protected T[] vertices; // values of vertices

    // Constructor initializes the graph with default values
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    // Adds a new vertex to the graph. If the current number of vertices equals
    // the array length, the capacity is expanded before adding the new vertex.
    public void addVertex() {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = null;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    /**
     * Adds a new vertex with the specified value to the graph.
     * If the current number of vertices equals the array length,
     * the capacity is expanded before adding the new vertex.
     *
     * The edges involving the new vertex in the adjacency matrix are set to false.
     * Increases the number of vertices in the graph.
     *
     * @param vertex The value of the new vertex to be added.
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
     * Removes the vertex with the specified value from the graph.
     * If the vertex is found, the corresponding row and column in the
     * adjacency matrix are removed, along with the vertex itself.
     *
     * @param vertex The value of the vertex to be removed from the graph.
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
     * Removes the vertex at the specified index from the graph.
     * If the index is valid, the corresponding row and column in the
     * adjacency matrix are removed, along with the vertex itself.
     *
     * @param index The index of the vertex to be removed from the graph.
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
     * Checks whether the specified index is valid within the current
     * number of vertices in the graph.
     *
     * @param index The index to be checked for validity.
     * @return true if the index is valid, false otherwise.
     */
    public boolean indexIsValid(int index) {
        return ((index < numVertices) && (index >= 0));
    }

    /**
     * Adds an edge between the vertices with the specified values in the graph.
     * If the vertices are found, an edge is added between them using their indices.
     *
     * @param vertex1 The value of the first vertex.
     * @param vertex2 The value of the second vertex.
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Adds an undirected edge between the vertices at the specified indices in the
     * graph.
     * If the indices are valid, an edge is added between the vertices in both
     * directions.
     *
     * @param index1 The index of the first vertex.
     * @param index2 The index of the second vertex.
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Retrieves the index of the vertex with the specified value in the graph.
     *
     * @param vertex The value of the vertex whose index is to be retrieved.
     * @return The index of the vertex if found, or -1 if the vertex is not present
     *         in the graph.
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
     * Removes the undirected edge between the vertices with the specified values in
     * the graph.
     * If the vertices are found, the edge is removed in both directions.
     *
     * @param vertex1 The value of the first vertex.
     * @param vertex2 The value of the second vertex.
     */
    public void removeEdge(T vertex1, T vertex2) {

        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Removes the undirected edge between the vertices at the specified indices in
     * the graph.
     * If the indices are valid, the edge is removed in both directions in the
     * adjacency matrix.
     *
     * @param index1 The index of the first vertex.
     * @param index2 The index of the second vertex.
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }

    /**
     * Returns an iterator that performs a breadth-first search (BFS) traversal
     * starting from the vertex with the specified value in the graph.
     *
     * @param startVertex The value of the vertex to start the BFS traversal.
     * @return An iterator over the vertices visited during the BFS traversal.
     */
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Returns an iterator that performs a breadth-first search (BFS) traversal
     * starting from the vertex at the specified index in the graph.
     *
     * @param startIndex The index of the vertex to start the BFS traversal.
     * @return An iterator over the vertices visited during the BFS traversal.
     */
    /*
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
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

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

            // Find all vertices adjacent to x that have not been visited and
            // queue them up
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        // Check if there are any unvisited vertices in the reverse direction
        boolean[] reverseVisited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            reverseVisited[i] = false;
        }

        // Second BFS traversal in the reverse direction
        traversalQueue.enqueue(startIndex);
        reverseVisited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();

            // Find all vertices adjacent to x in reverse direction that have not been visited and
            // queue them up
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[i][x] && !reverseVisited[i]) {
                    traversalQueue.enqueue(i);
                    reverseVisited[i] = true;
                }
            }
        }

        // Check if there are any unvisited vertices in either direction
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && !reverseVisited[i]) {
                // The graph is not strongly connected
                System.out.println("The graph is not strongly connected.");
                return resultList.iterator();
            }
        }

        // The graph is strongly connected
        return resultList.iterator();
    }





    /**
     * Returns an iterator that performs a depth-first search (DFS) traversal
     * starting from the vertex with the specified value in the graph.
     *
     * @param startVertex The value of the vertex to start the DFS traversal.
     * @return An iterator over the vertices visited during the DFS traversal.
     */
    public Iterator<T> iteratorDFS(T startVertex) {

        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Returns an iterator that performs a depth-first search (DFS) traversal
     * starting from the vertex at the specified index in the graph.
     *
     * @param startIndex The index of the vertex to start the DFS traversal.
     * @return An iterator over the vertices visited during the DFS traversal.
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
     * Returns an iterator that represents the shortest path from the start vertex
     * with the specified value to the target vertex with the specified value.
     *
     * @param startVertex  The value of the start vertex.
     * @param targetVertex The value of the target vertex.
     * @return An iterator over the vertices in the shortest path.
     */
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Returns an iterator that represents the shortest path from the start vertex
     * at the specified index to the target vertex at the specified index.
     *
     * @param startIndex  The index of the start vertex.
     * @param targetIndex The index of the target vertex.
     * @return An iterator over the vertices in the shortest path.
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
     * Returns an iterator over the indices representing the shortest path
     * from the start vertex at the specified index to the target vertex
     * at the specified index.
     *
     * @param startIndex  The index of the start vertex.
     * @param targetIndex The index of the target vertex.
     * @return An iterator over the indices in the shortest path.
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

            // Update the pathLength for each unvisited vertex adjacent to the
            // vertex at the current index
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[index][i] && !visited[i]) {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        if (index != targetIndex) { // no path must have been found
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

    // OTHERS FUNCTIONS

    /**
     * Expands the capacity of the graph by doubling the size of the vertices array
     * and the adjacency matrix.
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
     * Computes and returns the length of the shortest path from the start vertex
     * with the specified value to the target vertex with the specified value.
     *
     * @param startVertex  The value of the start vertex.
     * @param targetVertex The value of the target vertex.
     * @return The length of the shortest path, or -1 if no path exists.
     */
    public int shortestPathLength(T startVertex, T targetVertex) {
        return shortestPathLength(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Computes and returns the length of the shortest path from the start vertex
     * at the specified index to the target vertex at the specified index.
     *
     * @param startIndex  The index of the start vertex.
     * @param targetIndex The index of the target vertex.
     * @return The length of the shortest path, or -1 if no path exists.
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
     * Checks if the graph is empty.
     *
     * @return true if the graph is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (numVertices == 0);
    }

    /**
     * Checks if the graph is connected.
     *
     * @return true if the graph is connected, false otherwise.
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
     * Returns the number of vertices in the graph.
     *
     * @return The number of vertices in the graph.
     */
    public int size() {

        return numVertices;
    }

    /**
     * Clears the graph by setting the number of vertices to 0 and resetting
     * the adjacency matrix and vertices array.
     */
    @Override
    public void clear() {
        /*
        numVertices = 0;
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = null;
            for (int j = 0; j < vertices.length; j++) {
                adjMatrix[i][j] = false;
            }
        }
        */
        numVertices = 0;

        // Clear vertices array
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = null;
        }

        // Clear adjacency matrix
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = false;
            }
        }
    }

    /**
     * Returns a string representation of the graph including the adjacency matrix
     * and vertex values.
     *
     * @return A string representation of the graph.
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
     * Returns an array containing the values of the vertices in the graph.
     *
     * @return An array of vertex values.
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
