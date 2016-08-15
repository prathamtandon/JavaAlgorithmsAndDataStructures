package Graphs.MST;

import Collections.Bag;

/**
 * Created by prathamt on 8/14/16.
 */
public class EdgeWeightedGraph {

    private final int V;        //  number of vertices
    private int E;        //  number of edges
    private Bag<Edge>[] adj;    //  adjacency lists

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[])new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            for(Edge e: adj[v])
                if(e.other(v) > v) b.add(e);
        }
        return b;
    }
}
