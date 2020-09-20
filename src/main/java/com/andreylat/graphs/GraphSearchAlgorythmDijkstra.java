package com.andreylat.graphs;

import java.util.List;

/**
 * Simple implementation of Breadth-first search on graph
 *
 * @param <T> describes user defined type for vertices
 * @author Andrey Latyshev
 * @version 1.0.1
 */


public class GraphSearchAlgorythmDijkstra<T> implements GraphSearchAlgorythm<T> {

    /**
     * A placeholder for Dijkstra’s algorithm (not implemented)
     * (search for optimal path in weighted graph)
     *
     * @param from - source point
     * @param to   - destination point
     * @return - returns a list of edges included in path
     */
    @Override
    public List<GraphEdge<T>> getPath(GraphVerticesStore<T> Vertices, T from, T to) {
        return null;
    }
}
