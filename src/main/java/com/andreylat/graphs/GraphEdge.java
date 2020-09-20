package com.andreylat.graphs;

/**
 * Class for graph edge
 * (Custom equals+hashCode are not implemented for simplicity, comparing by link values)
 *
 * @param <T> user class for vertex
 */
public class GraphEdge<T> {
    private final T src; // source (from) vertex
    private final T dst; // destination (to) vertex
    private final int weight; // weight (typed int for simplicity)
    public final static int EDGE_DEFAULT_WEIGHT = 1; // default weight = 1

    /**
     * Full constructor
     *
     * @param src    source (from) vertex
     * @param dst    destination (to) vertex
     * @param weight weight (typed int for simplicity)
     */
    public GraphEdge(T src, T dst, int weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public GraphEdge(T src, T dst) {
        this(src, dst, EDGE_DEFAULT_WEIGHT);
    }

    public T getSrc() {
        return src;
    }

    public T getDst() {
        return dst;
    }

    public int getWeight() {
        return weight;
    }


    /**
     * Check if current edge is weighted (weight of this edge is not default, makes all graph weighted)
     *
     * @return Returns true if weight of this edge is not default
     */
    public boolean isWeighted() {
        return (weight != EDGE_DEFAULT_WEIGHT);
    }

    @Override
    public String toString() {
        return "GraphEdge{" + src + " --(" + weight + ")-- " + " " + dst + "}";
    }
}
