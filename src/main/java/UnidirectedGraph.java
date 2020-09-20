public class UnidirectedGraph<T> extends AbstractGraph<T> {
    public UnidirectedGraph(GraphSearchAlgorythm<T> searchAlgorythm) {
        super(false, searchAlgorythm);
    }
}
