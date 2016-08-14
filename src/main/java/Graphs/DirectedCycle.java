package Graphs;

import Collections.Stack;

/**
 * Created by prathamt on 8/13/16.
 */
public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;   //  vertices on a cycle (if one exists)
    private boolean[] onStack;      //  vertices on recursive call stack

    public DirectedCycle(DiGraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if(!marked[i]) dfs(G, i);
        }
    }

    private void dfs(DiGraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for(int w: G.adj(v)) {
            if(this.hasCycle()) return;
            else if(!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
            else if(onStack[w]) {
                cycle = new Stack<Integer>();
                for(int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);      //  repeated vertex in cycle
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
