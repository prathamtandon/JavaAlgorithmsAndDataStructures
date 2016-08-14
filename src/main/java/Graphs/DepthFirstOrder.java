package Graphs;

import Collections.Queue;
import Collections.Stack;

/**
 * Created by prathamt on 8/13/16.
 */
public class DepthFirstOrder {
    private boolean[] marked;

    private Queue<Integer> pre;         //  vertices in preorder - order in which recursion starts
    private Queue<Integer> post;        //  vertices in postorder - order in which recursion ends
    private Stack<Integer> reversePost; //  vertice in reverse postorder

    public DepthFirstOrder(DiGraph G) {
        marked = new boolean[G.V()];
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();

        for (int v = 0; v < G.V(); v++) {
            if(!marked[v])
                dfs(G,v);
        }
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        pre.enqueue(v);

        for(int w: G.adj(v))
            if(!marked[w])
                dfs(G, w);

        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() { return pre; }
    public Iterable<Integer> post() { return post; }
    public Iterable<Integer> reversePost() { return reversePost; }
}
