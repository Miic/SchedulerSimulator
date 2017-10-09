package schedulers;

import java.util.ArrayList;
import java.util.List;

import Process.Process;

public class RoundRobin implements Scheduler {

	private List<Process> queue;
	private int quantum;	
	
	public RoundRobin(int quantum) {
		this.quantum = quantum;
		queue = new ArrayList<Process>();
	}

	@Override
	public void scheduleProcess(Process process) {
		queue.add(process);
	}

	@Override
	public Process peek() {
		if (queue.size() > 0) {
			return queue.get(0);
		}
		return null;
	}

	@Override
	public int pop() {
		if (queue.size() > 0) {
			Process object = queue.get(0);
			object.setQuantum(object.getQuantum() + quantum);
			if (object.getQuantum() >= object.getBurst_time()) {
				return queue.remove(0).getBurst_time();
			}
			//loop it to the back
			queue.add(queue.remove(0));
			return quantum;
		}
		return 0;
	}
	
	public int getQuantum() {
		return quantum;
	}

	@Override
	public void addProcesses(List<Process> process) {
		queue = process;
	}

	@Override
	public int size() {
		return queue.size();
	}
}
