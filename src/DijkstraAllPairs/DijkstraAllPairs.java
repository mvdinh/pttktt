package DijkstraAllPairs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DijkstraAllPairs {
	private int v;
	private EdgeWeightedDigraph g;
	public DijkstraAllPairs(EdgeWeightedDigraph g) {
		this.v = g.V();	
		this.g = g;
	}
	public void DFS(int v, int w, boolean visited[], LinkedList<Integer> path) {
		visited[v] = true;
		path.add(v);
		if(v == w) {
			output(path);
		} else {
			for(DirectedEdge i : g.adj(v)) {
				if(!visited[i.to()]) {
					DFS(i.to(), w, visited, path);
				}
			}
		}
		path.removeLast();
        visited[v] = false;
	}
	void findAllPaths(int v, int w) {
        boolean visited[] = new boolean[this.v];
        LinkedList<Integer> path = new LinkedList<Integer>();
        DFS(v, w, visited, path);
    }
	public void output(LinkedList<Integer> path) {
		for(int i : path) {
			if(i != path.getLast()) {
				System.out.print(i + " -> ");
			} else {
				System.out.print(i);
			}
		}
		System.out.println();
	}
	public static void main(String[] args) {
		String nameFile = "text.txt";
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(nameFile);
		DijkstraAllPairs d = new DijkstraAllPairs(g);
		d.findAllPaths(1, 6);
	}
}