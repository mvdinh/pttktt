package Weighted_Interval_Scheduling;

class Job implements Comparable<Job>{
	public int start;
	public int finish;
	public String name;

	public Job(int start, int finish, String name){
		this.start=start;
		this.finish=finish;
		this.name=name;
	}

	//Compare jobs by finish time
	@Override
	public int compareTo(Job job) {
		return this.finish - job.finish;
	}
	
	@Override
	public String toString(){
		return "[" + name + ": (" + start + ", " + finish + ")]";
	}
}
