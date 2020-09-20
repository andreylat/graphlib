import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnidirectedGraphTest {
    private UnidirectedGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new UnidirectedGraph<String>(new GraphVerticesStoreHashMap<String>(), new GraphSearchAlgorythmBFS<String>());
        graph.addVertex("Root");
        graph.addVertex("Level1-1");
        graph.addVertex("Level1-2");
        graph.addVertex("Level1-1-1");
        graph.addVertex("Level1-1-2");
        graph.addVertex("Level1-2-1");
        graph.addVertex("Other-1");
        graph.addVertex("Other-2");
        graph.addEdge(new GraphEdge<String>("Root", "Level1-1"));
        graph.addEdge(new GraphEdge<String>("Root", "Level1-2"));
        graph.addEdge(new GraphEdge<String>("Level1-1", "Level1-1-1"));
        graph.addEdge(new GraphEdge<String>("Level1-1", "Level1-1-2"));
    }

    @AfterEach
    void tearDown() {
        graph = null;
    }

    @Test
    void getPath() {
        List<GraphEdge<String>> edges;
        // Check for existing 2 edges path (way with directed edges)
        edges = graph.getPath("Root", "Level1-1-2");
        assertEquals(2, edges.size());

        // Check for existing (not reacheble in this direction) 2 edges path backward (way with directed edges)
        edges = graph.getPath("Level1-1-2", "Root");
        assertEquals(2, edges.size());

        // Check for existing 2 edges path (non directed edges)
        edges = graph.getPath("Root", "Level1-1-1");
        assertEquals(2, edges.size());

        // Check for existing 2 edges path backward (non directed edges)
        edges = graph.getPath("Level1-1-1", "Root");
        assertEquals(2, edges.size());

        // Check for existing 1 edge path from root
        edges = graph.getPath("Root", "Level1-1");
        assertEquals(1, edges.size());
        // Check for existing 1 edge path not from root
        edges = graph.getPath("Level1-1", "Level1-1-2");
        assertEquals(1, edges.size());
        // check for unreacheble path
        edges = graph.getPath("Root", "Level1-2-1");
        assertEquals(0, edges.size());

        // Checks for exceptions:

        // null source vertex
        assertThrows(IllegalArgumentException.class, () -> graph.getPath(null, "Root"));

        // null destination vertex
        assertThrows(IllegalArgumentException.class, () -> graph.getPath("Root", null));
    }
}