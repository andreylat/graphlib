public class DirectedGraph<T> extends AbstractGraph<T> {
    public DirectedGraph(GraphSearchAlgorythm<T> searchAlgorythm) {
        super(true, searchAlgorythm);
    }
}
