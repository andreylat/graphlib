import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simple Graph library implementation
 * support 2 types of graph edges - directed and undirected with 3 operations: add vertex, add edge and finding path
 *
 * @param <T> describes user defined type for vertices
 * @author Andrey Latyshev
 * @version 1.0.1
 */

public class SimpleGraph<T> implements Graph<T> {
    // Use map of Vertex -> list of edges for storing Graph (Adjacency List)
    // unidirected edges are edded to lists of both vertices
    protected Map<T, LinkedList<GraphEdge<T>>> Vertices = new HashMap<>();
    // possible performance optimizations:
    // switch to vertex indexes instead of links
    // do not store full edges -> use hashmap of vertex index as a key and weight as a value (allows to store only one link between edges in one direction)

    // Autodetectable flag of directed graph
    protected boolean dir = false;
    // Autodetectable flag of weighted graph
    protected boolean weighted = false;

    /**
     * Adds vertex of user type to the graph
     *
     * @param vertex Vertex to add
     */
    public void addVertex(T vertex) {
        if (vertex == null) throw new IllegalArgumentException("Null vertex");
        Vertices.putIfAbsent(vertex, new LinkedList<GraphEdge<T>>());
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
        Vertices.get(edge.getSrc()).add(edge);
        // for non directed edges add second (backward) link from destination to source
        if (edge.isDir()) {
            dir = true;
        } else {
            Vertices.get(edge.getDst()).add(edge);
        }
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
        if (true || !weighted) {
            // use Breadth-first search by default
            return doBFS(from, to);
        } else {
            // possibly switch to Dijkstra’s algorithm if graph is weighted
            // not implemented
            return doDijkstra(from, to);
        }
    }

    /**
     * Simple implementation of Breadth-first search on graph
     *
     * @param from - source point
     * @param to   - destination point
     * @return - returns a list of edges included in path
     */
    private List<GraphEdge<T>> doBFS(T from, T to) {
        checkVertex(from, "From");
        checkVertex(to, "To");

        LinkedList<T> frontier = new LinkedList<>();
        frontier.offer(from);
        Map<T, GraphEdge<T>> came_from = new HashMap<>();
        came_from.put(from, null);

        while (!frontier.isEmpty()) {
            T current = frontier.poll();
            if (current == to) break;
            for (GraphEdge<T> lnk : Vertices.get(current)) {
                // case for working with directed and not directed edges
                T next = lnk.getSrc() == current ? lnk.getDst() : lnk.getSrc();
                if (!came_from.containsKey(next)) {
                    frontier.offer(next);
                    came_from.put(next, lnk);
                }
            }
        }

        // build path
        T current = to;

        // Version with list of edges as a result
        LinkedList<GraphEdge<T>> path = new LinkedList<>();
        if (came_from.containsKey(current)) {
            while (current != from) {
                GraphEdge<T> prev = came_from.get(current);
                path.push(prev);
                // case for working with directed and not directed edges
                current = prev.getDst() == current ? prev.getSrc() : prev.getDst();

            }
        }

        /* Version with list of vertices as a result (not fully implemented)
        LinkedList<T> path = new LinkedList<T>();
        if (came_from.containsKey(current)) {
            path.offer(current);
            while (current != from) {
                current = came_from.get(current);
                path.push(current);
            }
            path.push(from);
        }
        */

        // returns empty list if there is no path from one vertex to another
        return path;
    }

    //Dijkstra’s algorithm (not implemented)

    /**
     * A placeholder for Dijkstra’s algorithm (not implemented)
     * (search for optimal path in weighted graph)
     *
     * @param from - source point
     * @param to   - destination point
     * @return - returns a list of edges included in path
     */
    private List<GraphEdge<T>> doDijkstra(T from, T to) {
        // not implemented
        checkVertex(from, "From");
        checkVertex(to, "To");
        // place for algorithm
        return null;
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
        if (!Vertices.containsKey(vertex)) throw new IllegalArgumentException(addTxt + " Vertex is not in Graph");
    }

    /**
     * Checks whether graph contains exact vertex
     *
     * @param vertex Vertex to check
     * @return returns true if graph contains vertex
     */
    public boolean containsVertex(T vertex) {
        return (Vertices.containsKey(vertex));
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
        if (!Vertices.containsKey(src)) {
            // return false if source vertex is not in graph
            return (false);
        } else {
            LinkedList<GraphEdge<T>> edges = this.Vertices.get(src);
            return (edges.contains(edge));
        }
    }

    /**
     * Returns a set of all graph Vertices
     *
     * @return returns set of graph vertices
     */
    public java.util.Set<T> getVertices() {
        return (Vertices.keySet());
    }
}
