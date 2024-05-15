package Ford_Fulkerson_Cut;

import java.io.IOException;

public class FordFulkersonCut {
    private static final double FLOATING_POINT_EPSILON = 1E-11;
    private final int V;     
    private boolean[] marked;   
    private boolean[] minCut;   
    private FlowEdge[] edgeTo;  
    private double value;   

    public FordFulkersonCut(FlowNetwork G, int s, int t) {
        V = G.V();
        validate(s);
        validate(t);
        if (s == t) throw new IllegalArgumentException();
        if (!isFeasible(G, s, t)) throw new IllegalArgumentException();
        value = excess(G, t);
        while (hasAugmentingPath(G, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;
        }
        minCut = new boolean[V];
        for (int v = 0; v < V; v++) {
            if (marked[v]) {
                minCut[v] = true;
            }
        }
    }
    public double value() {
        return value;
    }
    public boolean inMinCut(int v) {
        validate(v);
        return minCut[v];
    }
    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty() && !marked[t]) {
            int v = queue.dequeue();
            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
        return marked[t];
    }
    private double excess(FlowNetwork G, int v) {
        double excess = 0.0;
        for (FlowEdge e : G.adj(v)) {
            if (v == e.from()) excess -= e.flow();
            else excess += e.flow();
        }
        return excess;
    }
    private boolean isFeasible(FlowNetwork G, int s, int t) {
        double EPSILON = 1E-11;
        double value = 0.0;
        for (FlowEdge e : G.adj(s)) {
            value += e.flow();
        }
        if (Math.abs(value - excess(G, t)) > EPSILON) {
            System.err.println("Excess at sink = " + excess(G, t));
            System.err.println("Max flow          = " + value);
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if (e.flow() < -EPSILON || e.flow() > e.capacity() + EPSILON) {
                    System.err.println("Edge does not satisfy capacity constraints: " + e);
                    return false;
                }
            }
        }
        return true;
    }
    public void InCut() {
    	System.out.print("các đỉnh tập nguồn: ");
    	for(int i = 0; i < V; i++) {
    		if(minCut[i]) System.out.print(" " + i);
    	}
    	System.out.println();
    	System.out.print("các đỉnh tập đích");
    	for(int i = 0; i < V; i++) {
    		if(!minCut[i]) System.out.print(" " + i);
    	}
    }
    private void validate(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V-1));
        }
    }
    public static void main(String[] args) throws IOException {
    	String s = "F:\\text1.txt";
		FlowNetwork f = new FlowNetwork(s);
		System.out.println(f.toString());
		FordFulkersonCut fc = new FordFulkersonCut(f, 0, 3);
		System.out.println(fc.value);
		fc.InCut();
	}
}
