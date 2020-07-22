import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {

    SimpleGraph<String> graph;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        graph = new SimpleGraph<String>();
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
        graph.addEdge(new GraphEdge<String>("Level1-1", "Level1-1-2", true));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        graph = null;
    }

    @org.junit.jupiter.api.Test
    void addVertex() {
        assertFalse(graph.containsVertex("some"));
        graph.addVertex("some");
        assertTrue(graph.containsVertex("some"));

        // Checks for exceptions:

        // null vertex
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(null));
    }

    @org.junit.jupiter.api.Test
    void addEdge() {
        GraphEdge<String> edge = new GraphEdge<String>("Other-1", "Other-2");
        assertFalse(graph.containsEdge(edge));
        graph.addEdge(edge);
        assertTrue(graph.containsEdge(edge));

        // Checks for exceptions:

        // null edge
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(null));

        // edge with null vertex
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(new GraphEdge<String>(null, null)));

        // edge with vertex not from graph
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(new GraphEdge<String>("Something-not-in-graph", "Another-not-in-graph")));
    }

    @org.junit.jupiter.api.Test
    void getPath() {
        List<GraphEdge<String>> edges;
        // Check for existing 2 edges path (way with directed edges)
        edges = graph.getPath("Root", "Level1-1-2");
        System.out.println(edges);
        assertEquals(2, edges.size());

        // Check for existing (not reacheble in this direction) 2 edges path backward (way with directed edges)
        edges = graph.getPath("Level1-1-2", "Root");
        System.out.println(edges);
        assertEquals(0, edges.size());

        // Check for existing 2 edges path (non directed edges)
        edges = graph.getPath("Root", "Level1-1-1");
        System.out.println(edges);
        assertEquals(2, edges.size());

        // Check for existing 2 edges path backward (non directed edges)
        edges = graph.getPath("Level1-1-1", "Root");
        System.out.println(edges);
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