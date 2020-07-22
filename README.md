Simple Graph library
Support 2 types of graph weighted edges - directed and undirected with 3 operations:
 * addVertex - adding a vertex of user type, 
 * addEdge - adding an edge,
 * getPath - finding a path from source vertex to destination vertex (returns a list of edges, exactly the same objects that were added by addEdge)

Each edge can be directed and unidirected, weighted and not (with default weight).
If all edges in graph are not weighted, then the whole graph considered not weighted

Uses Breadth-first search for not weighted graphs 
and Dijkstra search algorythm (not implemented) for weighted

Uses map of Vertex -> list of edges for storing Graph (Adjacency List)
unidirected edges are edded to lists of both vertices

Possible performance optimizations:
* switch to vertex indexes instead of links
* do not store full edges; instead of lists use hashmaps with vertex index as a key and weight as a value (allows to store only one link between edges in one direction)
