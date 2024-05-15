package Ford_Fulkerson_Cut;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FlowNetwork {
	private int V;
    private int E;
    private Bag<FlowEdge>[] adj;
    public FlowNetwork(int V) {
        if (V < 0) throw new IllegalArgumentException();
        this.V = V;
        this.E = 0;
        adj = (Bag<FlowEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<FlowEdge>();
    }
    public FlowNetwork(String nameFile) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(nameFile));
    	String s = br.readLine();
    	this.V = Integer.parseInt(s.trim());
    	this.E = 0;
    	adj = (Bag<FlowEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<FlowEdge>();
        String edgesLine = br.readLine();
        int numEdges = Integer.parseInt(edgesLine.trim());
        for (int i = 0; i < numEdges; i++) {
            String[] edgeData = br.readLine().split("\\s+");
            int v = Integer.parseInt(edgeData[0]);
            int w = Integer.parseInt(edgeData[1]);
            validateVertex(v);
            validateVertex(w);
            double capacity = Double.parseDouble(edgeData[2]);
            FlowEdge edge = new FlowEdge(v, w, capacity);
            addEdge(edge); 
        }
    }
    public void addEdge(FlowEdge e) {
    	int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public int V() {
        return V;
    }
    public int E() {
        return E;
    }
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException();
    }
    public Iterable<FlowEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }
    public Iterable<FlowEdge> edges() {
        Bag<FlowEdge> list = new Bag<FlowEdge>();
        for (int v = 0; v < V; v++)
            for (FlowEdge e : adj(v)) {
                if (e.to() != v)
                    list.add(e);
            }
        return list;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ":  ");
            for (FlowEdge e : adj[v]) {
                if (e.to() != v) s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    public static void main(String[] args) throws IOException {
		String s = "\\Users\\User\\Desktop\\Javapro\\PhanTichThietKeThuatToan\\src\\Ford_Fulkerson_Cut\\test.txt";
		FlowNetwork f = new FlowNetwork(s);
		System.out.println(f.toString());
	}
}