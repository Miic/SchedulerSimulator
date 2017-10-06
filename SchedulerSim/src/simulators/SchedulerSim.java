package simulators;

import schedulers.*;

import java.util.ArrayList;

import Process.Process;

public class SchedulerSim implements Simulation {

	private Scheduler scheduler;
	private boolean verbose; 
	
	public SchedulerSim(Scheduler scheduler) {
		this.scheduler = scheduler;
		verbose = false;
	}
	
	@Override
	public void run() {
		if (verbose) {
			System.out.println("Beginning Simulation");
		}
		while (scheduler.size() > 0) {
			Process process = scheduler.pop();
			if (verbose) {
				System.out.println(process.getPid() + "");
			}
		}
		if (verbose) {
			System.out.println("Ended Simulation");
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
