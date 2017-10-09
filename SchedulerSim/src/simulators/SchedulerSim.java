package simulators;

import schedulers.*;

import java.util.ArrayList;

import Process.Process;

public class SchedulerSim implements Simulation {

	private Scheduler scheduler;
	private boolean verbose;
	private int switchOutTime;
	
	public SchedulerSim(Scheduler scheduler, int switchOutTime) {
		this.scheduler = scheduler;
		verbose = false;
		this.switchOutTime = switchOutTime;
	}
	
	@Override
	public void run() {
		int timeTaken = 0;
		int timeAdded = 0;
		Process process = null;
		if (verbose) {
			System.out.println("<+> Beginning Simulation");
		}
		while (scheduler.size() > 0) {
			if (process != null && process.getPid() != scheduler.peek().getPid()) {
				timeTaken += switchOutTime;
				if (verbose) {
					System.out.println("  Switching Process: +" + switchOutTime);
				}
			}
			process = scheduler.peek();
			timeAdded = scheduler.pop();
			timeTaken += timeAdded;
			if (verbose) {
				System.out.println("  PID >" + process.getPid() + " : +" + timeAdded);
			}
		}
		if (verbose) {
			System.out.println("<-> Ended Simulation\n    Total Time Quanta Taken: " + timeTaken);
		}
	}

	@Override
	public void verbose(boolean val) {
		verbose = val;
	}

	@Override
	public void reset() {
		scheduler.addProcesses(new ArrayList<Process>());
	}
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
