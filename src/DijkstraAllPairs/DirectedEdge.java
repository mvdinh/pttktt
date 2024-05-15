package DijkstraAllPairs;
// cạnh có hướng O(1)
public class DirectedEdge {
	private int v, w;  //điểm đầu, điểm cuối
	private double weight;  //trọng số
	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public int from() {
		return v;
	}
	public int to() {
		return w;
	}
	public double Weight() {
		return weight;
	}
	public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
