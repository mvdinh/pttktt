package BellmanFord_dich;

public class EdgeWeightedDirectedCycle {
	private boolean[] marked;
    private DirectedEdge[] edgeFrom;    
    private boolean[] onStack;  
    private Stack<DirectedEdge> cycle;
    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeFrom  = new DirectedEdge[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }
    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.from();
            if (cycle != null) return;
            else if (!marked[w]) {
                edgeFrom[w] = e;
                dfs(G, w);
            }
            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();
                DirectedEdge f = e;
                while (f.to() != w) {
                    cycle.push(f);
                    f = edgeFrom[f.to()];
                }
                cycle.push(f);
                return;
            }
        }
        onStack[v] = false;
    }
    public boolean hasCycle() {
        return cycle != null;
    }
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
    public static void main(String[] args) {
		String name = "\\Users\\User\\Desktop\\Javapro\\PhanTichThietKeThuatToan\\src\\BellmanFord_dich\\te.txt";
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(name);
		EdgeWeightedDirectedCycle c = new EdgeWeightedDirectedCycle(g);
		for(DirectedEdge e : c.cycle) {
			System.out.println(e.toString());
		}
	}
}