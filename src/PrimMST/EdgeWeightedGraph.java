package PrimMST;
//đồ thị các cạnh không hướng
import java.io.BufferedReader;
import java.io.FileReader;

public class EdgeWeightedGraph {
    private int V;   //số đỉnh
    private int E;   //số cạnh
    private Bag<Edge>[] adj;    //túi chứa các cạnh của 1 đỉnh
    public EdgeWeightedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException();
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }
    public EdgeWeightedGraph(String nameFile) {    //đọc đồ thị từ file
		try (BufferedReader br = new BufferedReader(new FileReader(nameFile))){
			String tmp = br.readLine();
			int vertices = Integer.parseInt(tmp.trim());
			this.V = vertices; 
	        this.E = 0;
	        adj = (Bag<Edge>[]) new Bag[V];
	        for (int v = 0; v < V; v++) {
	            adj[v] = new Bag<Edge>();
	        }
	        String edgesLine = br.readLine();
	        int numEdges = Integer.parseInt(edgesLine.trim());
	        for (int i = 0; i < numEdges; i++) {
	            String[] edgeData = br.readLine().split("\\s+");
	            int v = Integer.parseInt(edgeData[0]);
	            int w = Integer.parseInt(edgeData[1]);
	            validateVertex(v);
	            validateVertex(w);
	            double weight = Double.parseDouble(edgeData[2]);
	            Edge e = new Edge(v, w, weight);
	            addEdge(e);   //thêm cạnh
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public EdgeWeightedGraph(EdgeWeightedGraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            Stack<Edge> reverse = new Stack<Edge>();
            for (Edge e : G.adj[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adj[v].add(e);
            }
        }
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
    public void addEdge(Edge e) {  //thêm cạnh
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);  //kiểm tra xem có ngoài phạm vi không
        validateVertex(w);
        adj[v].add(e);    //thêm cạnh vào cả 2 đầu mút
        adj[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v) {   //trả về danh sách các cạnh có đỉnh là v
        validateVertex(v);
        return adj[v];
    }
    public int degree(int v) {     //trả về kích thước của danh sách  
        validateVertex(v);
        return adj[v].size();
    }
    public Iterable<Edge> edges() {   //trả về danh sách 
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(" ");
        }
        return s.toString();
    }
}