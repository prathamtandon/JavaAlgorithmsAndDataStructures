package Graphs;

/**
 * Created by prathamt on 8/14/16.
 */
public class StronglyConnectedComponents {

    private boolean[] marked;   //  reached vertices
    private int[] id;           //  component identifiers
    private int count;          //  number of strong components

    public StronglyConnectedComponents(DiGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int s: order.reversePost()) {
            if(!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w: G.adj(v))
            if(!marked[w])
                dfs(G, w);
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}
