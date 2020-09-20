import java.util.List;

/**
 * Simple Graph Search Algorythm interface
 *
 * @param <T> describes user defined type for vertices
 * @author Andrey Latyshev
 * @version 1.0.1
 */
public interface GraphSearchAlgorythm<T> {
    List<GraphEdge<T>> getPath(GraphVerticesStore<T> Vertices, T from, T to);
}
