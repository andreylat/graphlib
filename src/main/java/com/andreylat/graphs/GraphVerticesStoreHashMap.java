package com.andreylat.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class GraphVerticesStoreHashMap<T> implements GraphVerticesStore<T> {
    private Map<T, LinkedList<GraphEdge<T>>> Vertices = new HashMap<>();

    @Override
    public void putIfAbsent(T vertex) {
        if (vertex == null) throw new IllegalArgumentException("Can not add Null vertex to Graph");
        Vertices.putIfAbsent(vertex, new LinkedList<GraphEdge<T>>());
    }

    @Override
    public boolean containsKey(T vertex) {
        return Vertices.containsKey(vertex);
    }

    @Override
    public void remove(T vertex) {
        // not implemented yet

    }

    @Override
    public LinkedList<GraphEdge<T>> get(T vertex) {
        return Vertices.get(vertex);
    }

    @Override
    public Set<T> keySet() {
        return Vertices.keySet();
    }
}
