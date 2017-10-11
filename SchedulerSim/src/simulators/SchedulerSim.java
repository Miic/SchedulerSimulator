package simulators;

import schedulers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Process.Process;

public class SchedulerSim implements Simulation {

	private Scheduler scheduler;
	private boolean verbose;
	private int switchOutTime;
	private boolean print;
	
	public SchedulerSim(Scheduler scheduler, int switchOutTime) {
		this.scheduler = scheduler;
		verbose = false;
		this.switchOutTime = switchOutTime;
	}
	
	@Override
	public void run() {
		int timeTaken = 0;
		int timeAdded = 0;
		float total = scheduler.size();
		Process process = null;
		StringBuilder builder = null;
		
		if (print) {
			builder = new StringBuilder();
		}
		
		if (verbose) {
			System.out.println("<+> Beginning Simulation\n");
		}
		while (scheduler.size() > 0) {
			if (process != null && scheduler.peek() != null && process.getPid() != scheduler.peek().getPid()) {
				timeTaken += switchOutTime;
				if (print) {
					builder.append(timeTaken + "," + "Switching Process," + switchOutTime + "," + timeTaken + "\n");
				}
				if (verbose) {
					System.out.println("  Cycle " + timeTaken + ": [Switching Processes] : +" + switchOutTime);
				}
			}
			process = scheduler.peek();
			timeAdded = scheduler.pop();
			timeTaken += timeAdded;
			if (verbose) {
				System.out.println("  Cycle " + timeTaken + ": [PID " + process.getPid() + "] : +" + timeAdded);
			}
			if (print) {
				builder.append(timeTaken + "," + process.getPid() + "," + timeAdded + "," + timeTaken + "\n");
			}
		}
		if (verbose) {
			System.out.println("\n<-> Ended Simulation\n\n    Total Time Quanta Taken: " + timeTaken);
			System.out.println("    Average Time Quanta Taken: " + ((float)timeTaken/total) + "\n");
		}
		
		if (print) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new File("output.csv"));
				pw.append("Time,PID,Time Added,CurrentTotal\n");
				pw.append(builder.toString() + "\n");
				pw.append("\nTotal Time Quanta,Average Time Quanta\n");
				pw.append(timeTaken + "," + ((float)timeTaken/total));
				pw.close();
				if (verbose) {
					System.out.println("Scheduler Simulation Log Printed to output.csv\n\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void verbose(boolean val) {
		verbose = val;
	}
	
	public void printable(boolean val) {
		print = val;
	}

	@Override
	public void reset() {
		scheduler.addProcesses(new ArrayList<Process>());
	}
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
