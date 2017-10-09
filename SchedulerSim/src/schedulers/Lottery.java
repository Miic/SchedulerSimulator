package schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Process.Process;

public class Lottery implements Scheduler {
	
	private List<Process> queue;
	private int lotteryTickets;
	private int quantum;
	
	public Lottery(int quantum) {
		queue = new ArrayList<Process>();
		lotteryTickets = 0;
		this.quantum = quantum;
	}
	
	@Override
	public void scheduleProcess(Process process) {
		queue.add(process);
		lotteryTickets += process.getTickets();
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
			Random r = new Random();
			boolean flag = false;
			do {
				for(int i = 0; i < queue.size(); i++) {
					if (queue.get(i).getTickets() < (r.nextInt(lotteryTickets-1) + 1)) {
						queue.get(i).setQuantum(queue.get(i).getQuantum() + quantum);
						if (queue.get(i).getQuantum() >= queue.get(i).getBurst_time()) {
							lotteryTickets -= queue.get(i).getTickets();
							return queue.remove(i).getBurst_time();
						}
						flag = true;
						break;
					}
				}
			} while (flag == false);
		}
		return 0;
	}
	
	@Override
	public void addProcesses(List<Process> process) {
		queue = process;
		
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
