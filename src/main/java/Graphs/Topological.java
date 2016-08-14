package Graphs;

/**
 * Created by prathamt on 8/13/16.
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(DiGraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if(!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order == null;
    }
}
