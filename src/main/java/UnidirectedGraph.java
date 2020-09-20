public class UnidirectedGraph<T> extends AbstractGraph<T> {
    public UnidirectedGraph(GraphVerticesStore<T> vertices, GraphSearchAlgorythm<T> searchAlgorythm) {
        super(false, vertices, searchAlgorythm);
    }
}
