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
		lotteryTickets += process.getPriority();
		if (queue.size() == 0) {
			queue.add(process.clone());
		} else {
			for (int i = 0; i < queue.size(); i++) {
				if (process.getPriority() < queue.get(i).getPriority()) {
					queue.add(i, process.clone());
					break;
				}
			}
		}
	}

	@Override
	public Process peek() {
		Random r = new Random();
		while (queue.size() > 0) {
			for(int i = 0; i < queue.size(); i++) {
				if (queue.get(i).getPriority() >= (r.nextInt(lotteryTickets) + 1)) {
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
				Process temp = peekedEntry;
				peekedEntry = null;
				return temp.getBurst_time() - pastQuantum;
			}
			Process temp = peekedEntry;
			peekedEntry = null;
			return temp.getQuantum() - pastQuantum;
		}
		return 0;
	}
	
	@Override
	public void addProcesses(List<Process> process) {
		queue = new ArrayList<Process>();
		lotteryTickets = 0;
		Process holder = null;
		for(int i = 0; i < process.size(); i++) {
			holder = process.get(i).clone();
			lotteryTickets += process.get(i).getPriority();
			if (queue.size() > 0) {
				for(int j = 0; j < queue.size(); j++) {
					if (holder.getPriority() < queue.get(j).getPriority()) {
						queue.add(j, holder);
						break;
					}
				}
			} else {
				queue.add(holder);
			}
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
