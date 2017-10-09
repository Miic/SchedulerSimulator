package Process;


public class Process {
	private int pid;
	private int burst_time;
	private int priority;
	private int quantum;
	
	public Process(int pid, int burst_time, int priority) {
		this.pid = pid;
		this.burst_time = burst_time;
		this.priority = priority;
		this.quantum = 0;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getBurst_time() {
		return burst_time;
	}
	public void setBurst_time(int burst_time) {
		this.burst_time = burst_time;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	public int getQuantum() {
		return quantum;
	}
	public boolean isCompleted() {
		return (quantum >= burst_time);
	}
}
