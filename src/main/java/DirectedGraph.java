public class DirectedGraph<T> extends AbstractGraph<T> {
    public DirectedGraph(GraphVerticesStore<T> vertices, GraphSearchAlgorythm<T> searchAlgorythm) {
        super(true, vertices, searchAlgorythm);
    }
}
