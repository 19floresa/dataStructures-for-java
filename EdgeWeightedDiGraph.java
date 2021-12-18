/*
 Alexander Flores
 Cs 223
 Edge Weighted Digraph

 This file contains two classes, EdgeWeightedDiGraph and TreeMap. The first
 class, EdgeWeightedDiGraph, creates a graph data structure. The second
 class, TreeMap, only purpose is to hold adjacent vertexes of a specific
 vertex. The graph in this file uses a TreeMaps within an array to store
 data. Each index in the array is a specific vertex (or node). The TreeMap
 in every index in the array holds the vertexes that are adjacent to that
 index (this is called an edge). The length of the array is pre-specified, and
 there can only be v amount of items in the TreeMap (corresponding to the
 length of the array). Every item within the TreeMap contains a key u and a
 value w, which correspond to the adjacent vertex and the weight associated
 with that edge, respectively.

 EdgeWeightedDiGraph (constructor and methods):

    1. EdgeWeightedDiGraph(int V) : Sets-up the class.

    2. E(): returns the number of edges.

    3. addEdge(int v, int u, double w): Adds an edge to the graph.

    4. removeEdge(int v, int u): Removes an edge from the graph.

    5. edgeWeight(int v, int u): Returns the weight of an edge in the graph.

    6. adj(int v): Returns an Iterable object of adjacent vertex to vertex v.

 */

public class EdgeWeightedDiGraph extends WeightedGraph {
    public class TreeMap extends java.util.TreeMap<Integer, Double> {
        // This class is used to store adjacent vertexes of a vertex
    }

    TreeMap[] weightedMatrix;
    // stores a graph

    int edges;


    public EdgeWeightedDiGraph(int V) {
        /**
         This constructor creates the required vertices in the graph. The
         graph is an array with TreeMaps in every index.
         @param V: number of vertices (V*V)
         */
        super(V);
        // super(V) calls the constructor this class extends

        weightedMatrix = new TreeMap[V];
        for (int i = 0; i < V; i++) {
            weightedMatrix[i] = new TreeMap();
        }

        edges = 0;
    }


    @Override public int E() {
        /**
         This method returns the number of edges in the graph. An edge is a
         'path' from a node to another node.

         @return # of edges in the graph
         */
        return edges;
    }


    @Override public void addEdge(int v, int u, double w) {
        /**
         This method adds an edge between two vertices and assigns them a
         weight (it can be a negative or a positive number). An edge consists
         of a staring vertex (v) and end on a different vertex (u). Including
         to this a weight is assign for that specific weight (w). This method
         does not allow loops (v == u) and a starting or ending vertex outside
         of the array length. If an edge already exist then the weight  of that
         edge is changed to the new weight.

         @param v: index in the array or the starting vertex
         @param u: item in the TreeMap or the ending vertex
         @param w: weight of the edge
         */

        if (v >= weightedMatrix.length || u >= weightedMatrix.length
                || v < 0 || u < 0) throw new IndexOutOfBoundsException();

        if (v == u) throw new IllegalArgumentException();

        if (!weightedMatrix[v].containsKey(u)) {
            // adds an edge if there is no edge between v and u
            edges++;
            weightedMatrix[v].put(u,w);
        } else weightedMatrix[v].replace(u,w);
    }


    @Override public void removeEdge(int v, int u) {
        /**
         This method removes an edge from the graph. If the edge exits then
         the edge starting at vertex v and ending at vertex u is removed and
         the instance edges is decreased by 1. If the edge does not exit then
         nothing happens. If v or u is bigger then the graph length or
         less than 0 then an IndexOutOfBounds is thrown.

         @param v: index in the array or the starting vertex
         @param u: item in the TreeMap or the ending vertex
         */
        if (v >= weightedMatrix.length || u >= weightedMatrix.length
                || v < 0 || u < 0) throw new IndexOutOfBoundsException();

        weightedMatrix[v].remove(u);
        edges--;
    }


    @Override public Double edgeWeight(int v, int u) {
        /**
         This method returns the weight between the edge (v,u). If edge (v,u)
         does not exit then null is returned. If v or u is bigger then the
         graph length or less than 0 then an IndexOutOfBounds is thrown.

         @param v: index in the array or the starting vertex
         @param u: item in the TreeMap or the ending vertex

         @return : the weight of edge (v,u)
         */
       if (v >= weightedMatrix.length || u >= weightedMatrix.length
               || v < 0 || u < 0) throw new IndexOutOfBoundsException();

       if (weightedMatrix[v].containsKey(u)) return weightedMatrix[v].get(u);

       return null;
    }


    @Override public Iterable<Integer> adj(int v) {
        /**
         This method returns an Iterable object of the adjacent vertex of a
         specified vertex v (without its weight). If theres is not adjacent
         vertex then an empty iterable object is returned. If v or u is bigger
         then the graph length or less than 0 then an IndexOutOfBounds is
         thrown.

         @param v: index in the array or the starting vertex

         @return : Iterable object of vertices adjacent to v
         */
        if (v >= weightedMatrix.length || v < 0) {
            throw new IndexOutOfBoundsException();
        }

        return weightedMatrix[v].navigableKeySet();
    }
}
