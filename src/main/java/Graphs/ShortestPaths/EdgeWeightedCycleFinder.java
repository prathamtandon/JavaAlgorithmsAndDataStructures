package Graphs.ShortestPaths;

import Collections.Stack;

/**
 * Created by prathamt on 8/15/16.
 */
public class EdgeWeightedCycleFinder {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if(!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for(DirectedEdge e: G.adj(v)) {
            if(hasCycle()) return;          //      Just need to identify one cycle
            int w = e.to();
            if(!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }
            else if(onStack[w]) {
                cycle = new Stack<DirectedEdge>();
                DirectedEdge x;
                for(x = edgeTo[w]; x.from() != w; x = edgeTo[x.from()])
                    cycle.push(x);
                cycle.push(x);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() { return cycle != null; }
    public Iterable<DirectedEdge> cycle() { return cycle; }

}
