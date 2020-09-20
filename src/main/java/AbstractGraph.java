import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Simple Graph library implementation
 * support 2 types of graph edges - directed and undirected with 3 operations: add vertex, add edge and finding path
 *
 * @param <T> describes user defined type for vertices
 * @author Andrey Latyshev
 * @version 1.0.1
 */

abstract class AbstractGraph<T> implements Graph<T> {
    // Use map of Vertex -> list of edges for storing Graph (Adjacency List)
    // unidirected edges are edded to lists of both vertices
    //private Map<T, LinkedList<GraphEdge<T>>> Vertices = new HashMap<>();
    private GraphVerticesStore<T> vertices;
    private GraphSearchAlgorythm<T> searchAlgorythm;


    // possible performance optimizations:
    // switch to vertex indexes instead of links
    // do not store full edges -> use hashmap of vertex index as a key and weight as a value (allows to store only one link between edges in one direction)

    // Autodetectable flag of directed graph
    private boolean directed = false;
    // Autodetectable flag of weighted graph
    private boolean weighted = false;

    public AbstractGraph(boolean dir, GraphVerticesStore<T> vertices, GraphSearchAlgorythm<T> searchAlgorythm) {
        directed = dir;
        this.vertices = vertices;
        this.searchAlgorythm = searchAlgorythm;
    }

    /**
     * Adds vertex of user type to the graph
     *
     * @param vertex Vertex to add
     */
    public void addVertex(T vertex) {
        if (vertex == null) throw new IllegalArgumentException("Can not add Null vertex to Graph");
        vertices.putIfAbsent(vertex);
    }

    /**
     * Adds edge to the graph
     *
     * @param edge Edge to add
     */
    public void addEdge(GraphEdge<T> edge) {
        if (edge == null) throw new IllegalArgumentException("Null edge");
        checkVertex(edge.getSrc(), "Src");
        checkVertex(edge.getDst(), "Dst");

        // add link from source to destination
        vertices.get(edge.getSrc()).add(edge);
        // for non directed edges add second (backward) link from destination to source
        if (!directed) vertices.get(edge.getDst()).add(edge);
        if (edge.isWeighted()) weighted = true;
    }

    /**
     * returns a list of edges between 2 vertices
     *
     * @param from - source point
     * @param to   - destination point
     * @return - returns a list of edges included in path
     */
    public List<GraphEdge<T>> getPath(T from, T to) {
        checkVertex(from, "From");
        checkVertex(to, "To");
        return (searchAlgorythm.getPath(vertices, from, to));
    }


    /**
     * Get path from source vertex to destination vertex as a list of veertices
     *
     * @param from source vertex
     * @param to   destination vertex
     * @return returns a list of vertices in path
     */
    public List<T> getVertexPath(T from, T to) {
        // not implemented (returns empty list)
        return new LinkedList<>();
    }

    /**
     * Apply Consumer function to all vertices
     * (not tested)
     *
     * @param operation function to apply
     */
    public void traverse(Consumer<T> operation) {
        for (T vertex : vertices.keySet()) {
            operation.accept(vertex);
        }
    }


    /**
     * Utility method for checking vertices
     * (throws IllegalArgumentException with additional text for "bad" vertices)
     *
     * @param vertex Vertex to check
     * @param addTxt Additional text for error messages
     */
    private void checkVertex(T vertex, String addTxt) {
        if (vertex == null) throw new IllegalArgumentException(addTxt + " Vertex is null");
        if (!vertices.containsKey(vertex)) throw new IllegalArgumentException(addTxt + " Vertex is not in Graph");
    }

    /**
     * Checks whether graph contains exact vertex
     *
     * @param vertex Vertex to check
     * @return returns true if graph contains vertex
     */
    public boolean containsVertex(T vertex) {
        return (vertices.containsKey(vertex));
    }

    /**
     * Checks whether graph contains exact edge
     *
     * @param edge Edge to check
     * @return returns true if graph contains edge
     */
    public boolean containsEdge(GraphEdge<T> edge) {
        if (edge == null) throw new IllegalArgumentException("Null edge");
        T src = edge.getSrc();
        if (src == null) throw new IllegalArgumentException("Null edge.src");
        if (!vertices.containsKey(src)) {
            // return false if source vertex is not in graph
            return (false);
        } else {
            List<GraphEdge<T>> edges = this.vertices.get(src);
            return (edges.contains(edge));
        }
    }

    /**
     * Returns a set of all graph Vertices
     *
     * @return returns set of graph vertices
     */
    public java.util.Set<T> getVertices() {
        return (vertices.keySet());
    }
}
