package Graphs;

/**
 * Created by prathamt on 8/14/16.
 */
public class DirectedDFS {

    private boolean[] marked;

    public DirectedDFS(DiGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(DiGraph G, int source) {
        marked[source] = true;
        for(int v: G.adj(source))
            if(!marked[v])
                dfs(G, v);
    }

    public boolean reachable(int w) {
        return marked[w];
    }
}
