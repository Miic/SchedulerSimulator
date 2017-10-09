package schedulers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Process.Process;

public class FCFS implements Scheduler {
	private Queue<Process> queue;
	
	public FCFS() {
		queue = new LinkedList<Process>();
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
	public int pop() {
		return queue.poll().getBurst_time();
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
