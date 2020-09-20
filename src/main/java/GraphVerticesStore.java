import java.util.List;
import java.util.Set;

public interface GraphVerticesStore<T> {
    void putIfAbsent(T vertex);

    boolean containsKey(T vertex);

    void remove(T vertex);

    List<GraphEdge<T>> get(T vertex);

    Set<T> keySet();
}
