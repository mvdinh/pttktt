package PrimMST;

public class PrimMST {
	private double weight;   //trọng số của cây khung nhỏ nhất
	private Queue<Edge> mst = new Queue<Edge>();   //hàng đợi chứa các cạnh không hướng
	public PrimMST(EdgeWeightedGraph G) {   
        boolean[] marked = new boolean[G.V()];
        MinPQ<Edge> pq = new MinPQ<Edge>();   //delMin trả về giá trị nhỏ nhất và xóa
        visit(G, 0, marked, pq);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();    //gọi pt nhỏ nhất và xóa khỏi pq
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;    //nếu 2 đỉnh được đánh dấu thì bỏ qua
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) visit(G, v, marked, pq);   //xét tiếp đỉnh chưa đánh dấu
            if (!marked[w]) visit(G, w, marked, pq);
        }
	}
	private void visit(EdgeWeightedGraph G, int v, boolean[] marked, MinPQ<Edge> pq) {
        marked[v] = true;   //đánh dấu đỉnh là true
        for (Edge e : G.adj(v)) {     //xét các cạnh có đỉnh v
            if (!marked[e.other(v)]) {   //nếu chưa đánh dấu thì thêm vào MinPQ
                pq.insert(e); 
            }
        }
    }
	public Iterable<Edge> edges() {
        return mst;
    }
    public double weight() {
        return weight;
    }
    public static void main(String[] args) {
    	String nameFile = "F:\\tinyEWD.txt";
    	EdgeWeightedGraph g = new EdgeWeightedGraph(nameFile);
    	PrimMST p = new PrimMST(g);
    	for(Edge e : g.edges()) {    //in ra các cạnh đồ thị
    		System.out.println(e.toString());
    	}
    	System.out.println();
    	for(Edge e : p.mst) {   //in ra cây khung nhỏ nhất
    		System.out.println(e.toString());
    	}
    	System.out.println(p.weight);
	}
}