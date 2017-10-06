package schedulers;

import java.util.List;
import java.util.PriorityQueue;

import Process.Process;
import comparator.BurstTimeComparator;

public class SJF implements Scheduler {
	private PriorityQueue<Process> queue;
	
	public SJF() {
		queue = new PriorityQueue<Process>(10, new BurstTimeComparator());
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

	@Override
	public int size() {
		return queue.size();
	}
}
