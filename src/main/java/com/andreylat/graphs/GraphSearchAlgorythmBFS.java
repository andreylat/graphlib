package com.andreylat.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simple implementation of Breadth-first search on graph
 *
 * @param <T> describes user defined type for vertices
 * @author Andrey Latyshev
 * @version 1.0.1
 */

public class GraphSearchAlgorythmBFS<T> implements GraphSearchAlgorythm<T> {

    /**
     * Simple implementation of Breadth-first search on graph
     *
     * @param from - source point
     * @param to   - destination point
     * @return - returns a list of edges included in path
     */
    @Override
    public List<GraphEdge<T>> getPath(GraphVerticesStore<T> Vertices, T from, T to) {
        //checkVertex(from, "From");
        //checkVertex(to, "To");

        LinkedList<T> frontier = new LinkedList<>();
        frontier.offer(from);
        Map<T, GraphEdge<T>> came_from = new HashMap<>();
        came_from.put(from, null);

        while (!frontier.isEmpty()) {
            T current = frontier.poll();
            if (current == to) break;
            for (GraphEdge<T> lnk : Vertices.get(current)) {
                // case for working with directed and not directed edges
                T next = lnk.getSrc() == current ? lnk.getDst() : lnk.getSrc();
                if (!came_from.containsKey(next)) {
                    frontier.offer(next);
                    came_from.put(next, lnk);
                }
            }
        }

        // build path
        T current = to;

        // Version with list of edges as a result
        LinkedList<GraphEdge<T>> path = new LinkedList<>();
        if (came_from.containsKey(current)) {
            while (current != from) {
                GraphEdge<T> prev = came_from.get(current);
                path.push(prev);
                // case for working with directed and not directed edges
                current = prev.getDst() == current ? prev.getSrc() : prev.getDst();

            }
        }

        /* Version with list of vertices as a result (not fully implemented)
        LinkedList<T> path = new LinkedList<T>();
        if (came_from.containsKey(current)) {
            path.offer(current);
            while (current != from) {
                current = came_from.get(current);
                path.push(current);
            }
            path.push(from);
        }
        */

        // returns empty list if there is no path from one vertex to another
        return path;
    }
}
