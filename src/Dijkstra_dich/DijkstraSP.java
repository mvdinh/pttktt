package Dijkstra_dich;


public class DijkstraSP {
	private double[] distFrom;
	private DirectedEdge[] edgeFrom;
	private IndexMinPQ<Double> pq;
	public DijkstraSP(EdgeWeightedDigraph g, int s) {
		this.distFrom = new double[g.V()];
		this.edgeFrom = new DirectedEdge[g.V()];
		for(int i = 0; i < g.V(); i++) {
			this.distFrom[i] = Double.POSITIVE_INFINITY;
		}
		this.distFrom[s] = 0;
		pq = new IndexMinPQ<Double>(g.V());
		pq.insert(s, this.distFrom[s]);
		while(!pq.isEmpty()) {
			int x = pq.delMin();
			for(DirectedEdge i : g.adj(x)) {
				relax(i);
			}
		}
	}
	public void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if(this.distFrom[v] > this.distFrom[w] + e.Weight()) {
			this.distFrom[v] = this.distFrom[w] + e.Weight();
			this.edgeFrom[v] = e;
			if(pq.contains(v)) {
				pq.decreaseKey(v, distFrom[v]);
			} else {
				pq.insert(v, distFrom[v]);
			}
		}
	}
	public double distFrom(int v) {
		return this.distFrom[v];
	}
	public boolean hasPathTo(int v) {
		return this.distFrom[v] < Double.POSITIVE_INFINITY;
	}
	public Iterable<DirectedEdge> pathTo(int v){
		if (!hasPathTo(v)) return null;
		Stack<DirectedEdge> tmp = new Stack<DirectedEdge>();
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeFrom[v]; e != null; e = edgeFrom[e.to()]) {
            tmp.push(e);
        }
        while(!tmp.isEmpty()) {
        	path.push(tmp.pop());
        }
        return path;
	}
	public static void main(String[] args) {
		String nameFile = "\\Users\\User\\Desktop\\Javapro\\baitapTet\\src\\text.txt";
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(nameFile);
		int s = 0;
		DijkstraSP sp = new DijkstraSP(g, s);
		System.out.println(s + " to " + s + "( " + sp.distFrom(s) + "): ");
		System.out.println(s + "->" + s);
		for(int i = 1; i < g.V(); i++) {
			System.out.println(i + " to " + s + "( " + sp.distFrom(i) + "): ");
			if(sp.hasPathTo(i)) {
				for(DirectedEdge x : sp.pathTo(i)) {
					System.out.print(x + "    ");
				}
			} else {
				System.out.println("Không thấy đường đi");
			}
			System.out.println();
		}
	}
}