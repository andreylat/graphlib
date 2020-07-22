import java.util.List;

/**
 * Simple Graph library interface
 * support 2 types of graph edges - directed and undirected with 3 operations:
 * adding vertex, adding edge and finding path
 *
 * @param <T> describes user defined type for vertices
 * @author Andrey Latyshev
 * @version 1.0.1
 */
public interface Graph<T> {
    void addVertex(T vertex);

    void addEdge(GraphEdge<T> edge);

    List<GraphEdge<T>> getPath(T from, T to);

    List<T> getVertexPath(T from, T to);
}
