package DijkstraAllPairs;

import java.io.BufferedReader;
import java.io.FileReader;

public class EdgeWeightedDigraph {
	private int v, e;
	private Bag<DirectedEdge>[] adj;
	private int[] indegree;
	public EdgeWeightedDigraph(int v) {
		this.v = v;
		this.e = 0;
		indegree = new int[v];
		adj = (Bag<DirectedEdge>[]) new Bag[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new Bag<DirectedEdge>();     //túi chứa các cạnh đi ra từ đỉnh
		}
	}
	public EdgeWeightedDigraph(String nameFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(nameFile))){
			String tmp = br.readLine();
	        int vertices = Integer.parseInt(tmp.trim());
	        this.v = vertices; 
	        this.e = 0;
	        this.indegree = new int[vertices];
	        this.adj = (Bag<DirectedEdge>[]) new Bag[vertices];
	        for (int i = 0; i < vertices; i++) {
	            adj[i] = new Bag<DirectedEdge>();
	        }
	        String edgesLine = br.readLine();
	        int numEdges = Integer.parseInt(edgesLine.trim());
	        for (int i = 0; i < numEdges; i++) {
	            String[] edgeData = br.readLine().split("\\s+");
	            int v = Integer.parseInt(edgeData[0]);
	            int w = Integer.parseInt(edgeData[1]);
	            double weight = Double.parseDouble(edgeData[2]);
	            DirectedEdge edge = new DirectedEdge(v, w, weight);
	            addEdge(edge); 
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		int w = e.to();
		this.adj[v].add(e);
		this.indegree[w]++;
		this.e++;
	}
	public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }
	public int outdegree(int v) {
        return adj[v].size();
    }
	public int indegree(int v) {
        return indegree[v];
    }
	public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int i = 0; i < this.v; i++) {
            for (DirectedEdge e : adj(i)) {
                list.add(e);
            }
        }
        return list;
    } 
	public int V() {
		return v;
	}
	public int E() {
		return e;
	}
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.v + " " + this.e + "\n");
        for (int i = 0; i < this.v; i++) {
            s.append(i + ": ");
            for (DirectedEdge e : adj[i]) {
                s.append(e + "  ");
            }
            s.append(outdegree(i) + "     " + indegree(i));
            s.append("\n");
        }
        return s.toString();
    }
}