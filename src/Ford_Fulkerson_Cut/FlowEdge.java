package Ford_Fulkerson_Cut;

public class FlowEdge {
	private static final double FLOATING_POINT_EPSILON = 1E-10;
	private int v;
	private int w;
	private double flow;
	private double capacity;
	public FlowEdge(int v, int w, double capacity) {
        if (v < 0) throw new IllegalArgumentException();
        if (w < 0) throw new IllegalArgumentException();
        if (!(capacity >= 0.0)) throw new IllegalArgumentException();
        this.v         = v;
        this.w         = w;  
        this.capacity  = capacity;
        this.flow      = 0.0;
    }
	public FlowEdge(int v, int w, double capacity, double flow) {
        if (v < 0) throw new IllegalArgumentException();
        if (w < 0) throw new IllegalArgumentException();
        if (!(capacity >= 0.0))  throw new IllegalArgumentException();
        if (!(flow <= capacity)) throw new IllegalArgumentException();
        if (!(flow >= 0.0))      throw new IllegalArgumentException();
        this.v         = v;
        this.w         = w;  
        this.capacity  = capacity;
        this.flow      = flow;
    }
	public FlowEdge(FlowEdge e) {
        this.v         = e.v;
        this.w         = e.w;
        this.capacity  = e.capacity;
        this.flow      = e.flow;
    }
	public int from() {
        return v;
    }
	public int to() {
        return w;
    }
	public double capacity() {
        return capacity;
    }
	public double flow() {
        return flow;
    }
	public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException();
    }
	public double residualCapacityTo(int vertex) {
        if      (vertex == v) return flow;           
        else if (vertex == w) return capacity - flow;  
        else throw new IllegalArgumentException("invalid endpoint");
    }
	public void addResidualFlowTo(int vertex, double delta) {
        if (!(delta >= 0.0)) throw new IllegalArgumentException("Delta must be nonnegative");

        if      (vertex == v) flow -= delta;    
        else if (vertex == w) flow += delta;     
        else throw new IllegalArgumentException("invalid endpoint");
        if (Math.abs(flow) <= FLOATING_POINT_EPSILON)
            flow = 0;
        if (Math.abs(flow - capacity) <= FLOATING_POINT_EPSILON)
            flow = capacity;
        if (!(flow >= 0.0))      throw new IllegalArgumentException("Flow is negative");
        if (!(flow <= capacity)) throw new IllegalArgumentException("Flow exceeds capacity");
    }
	public String toString() {
        return v + "->" + w + " " + flow + "/" + capacity;
    }
}