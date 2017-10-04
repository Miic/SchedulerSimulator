package schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Process.Process;

public class FCFS implements Scheduler {
	private Queue<Process> queue;
	
	@SuppressWarnings("unchecked")
	public FCFS() {
		queue = (Queue<Process>) new ArrayList<Process>();
	}

	@Override
	public void scheduleProcess(Process process) {
		queue.add(process);
	}

	@Override
	public Process peek() {
		return queue.peek();
	}

	@Override
	public Process pop() {
		return queue.poll();
	}

	@Override
	public void addProcesses(List<Process> process) {
		for(Process i : process) {
			queue.add(i);
		}
	}
		
}
