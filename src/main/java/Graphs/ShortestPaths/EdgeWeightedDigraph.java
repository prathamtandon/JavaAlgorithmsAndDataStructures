package Graphs.ShortestPaths;

import Collections.Bag;

/**
 * Created by prathamt on 8/15/16.
 */
public class EdgeWeightedDigraph {

    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<DirectedEdge>[])new Bag[V];
        for (int v = 0; v < V; v++) {
            this.adj[v] = new Bag<DirectedEdge>();
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) { return adj[v]; }
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> b = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for(DirectedEdge e: adj[v])
                b.add(e);
        }
        return b;
    }
}
