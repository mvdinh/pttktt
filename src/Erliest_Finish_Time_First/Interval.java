package Erliest_Finish_Time_First;

public class Interval implements  Comparable<Interval>{
	public int time_start;
	public int time_finish;
	public Interval(int time_start, int time_finish) {
		super();
		this.time_start = time_start;
		this.time_finish = time_finish;
	}

	public int compareTo(Interval i) {
		return this.time_finish - i.time_finish;
	}

	@Override
	public String toString() {
		return "Interval [time_start=" + time_start + ", time_finish=" + time_finish + "]";
	}
	
}
