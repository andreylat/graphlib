#Simple Graph library.
Project is incomplete...

Contains interface and 2 implementations: directed and unidirected graphs.
Supports different search algorythms (Breadth-first search - implemented and Dijkstra - not implemented)
and different storage types (hashMap - implemented)
Supports 3 operations:
 * addVertex - adding a vertex of user type, 
 * addEdge - adding an edge,
 * getPath - finding a path from source vertex to destination vertex (returns a list of edges, exactly the same objects that were added by addEdge)

Uses map of Vertex -> list of edges for storing Graph (Adjacency List)

Unidirected edges are added to lists of both vertices (source and destination)

Possible performance optimizations:
* switch to vertex indexes instead of links
* do not store full edges; instead of lists use hashmaps with vertex index as a key and weight as a value (allows to store only one link between edges in one direction)

Maybe it will be a good idea to have an alternative path finding function getVertexPath, that returns a list of vertices instead of edges (added to interface and implementation, but not implemented).
