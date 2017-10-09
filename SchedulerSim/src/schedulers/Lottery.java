package schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Process.Process;

public class Lottery implements Scheduler {
	
	private List<Process> queue;
	private int lotteryTickets;
	private int quantum;
	private Process peekedEntry;
	
	public Lottery(int quantum) {
		queue = new ArrayList<Process>();
		lotteryTickets = 0;
		this.quantum = quantum;
		peekedEntry = null;
	}
	
	@Override
	public void scheduleProcess(Process process) {
		queue.add(process);
		lotteryTickets += process.getPriority();
	}

	@Override
	public Process peek() {
		Random r = new Random();
		while (queue.size() > 0) {
			for(int i = 0; i < queue.size(); i++) {
				if (queue.get(i).getPriority() >= (r.nextInt(lotteryTickets-1) + 1)) {
					peekedEntry = queue.get(i);
					return peekedEntry;
				}
			}
		}
		return null;
	}

	@Override
	public int pop() {
		if (queue.size() > 0) {
			if (peekedEntry == null) {
				peek();
			}
			int pastQuantum = peekedEntry.getQuantum();
			peekedEntry.setQuantum(peekedEntry.getQuantum() + quantum);
			if (peekedEntry.getQuantum() >= peekedEntry.getBurst_time()) {
				lotteryTickets -= peekedEntry.getPriority();
				queue.remove(peekedEntry);
				return peekedEntry.getBurst_time() - pastQuantum;
			}
			Process temp = peekedEntry;
			peekedEntry = null;
			return temp.getQuantum() - pastQuantum;
		}
		return 0;
	}
	
	@Override
	public void addProcesses(List<Process> process) {
		queue = process;
		lotteryTickets = 0;
		for(int i = 0; i < process.size(); i++) {
			lotteryTickets += process.get(i).getPriority(); 
		}
	}
	
	public int getQuantum() {
		return quantum;
	}
	
	public int getTotalLotteryTickets() {
		return lotteryTickets;
	}

	@Override
	public int size() {
		return queue.size();
	}

}
