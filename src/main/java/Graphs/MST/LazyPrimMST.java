package Graphs.MST;

import Collections.Queue;
import Sorting.MinPQ;

/**
 * Created by prathamt on 8/14/16.
 */
public class LazyPrimMST {

    private boolean[] marked;       //  MST vertices
    private Queue<Edge> mst;        //  MST edges
    private MinPQ<Edge> pq;         //  crossing (and ineligible) edges
    private double weight = 0.0D;          //  MST weight

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>(G.E());
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();

        visit(G, 0);                //  assumes G is connected
        while(!pq.isEmpty()) {
            Edge e = pq.delMin();           //  Get lowest weight edge from cut
            int v = e.either(), w = e.other(v);
            if(marked[v] && marked[w]) continue;    // Skip if ineligible
            mst.enqueue(e);
            weight += e.weight();
            if(!marked[v]) visit(G, v);     //  Add vertex to tree
            if(!marked[w]) visit(G, w);     //  either v or w
        }

    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for(Edge e: G.adj(v)) {
            if(!marked[e.other(v)])
                pq.insert(e);
        }
    }

    public Iterable<Edge> edges() { return mst; }

    public double weight() {
        return weight;
    }
}
