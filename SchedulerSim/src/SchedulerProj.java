import java.io.File;
import java.io.FileNotFoundException;

import schedulers.*;
import simulators.ProcessCreationSim;
import simulators.SchedulerSim;

public class SchedulerProj {
	private static SchedulerSim sim;
	private static Scheduler scheduler;
	
	public static void main(String[] args) {
		ProcessCreationSim in;
		try {
			scheduler = new FCFS();
			sim = new SchedulerSim(scheduler, 3);
			sim.verbose(true);
			in = new ProcessCreationSim(new File("input.txt"));
			in.run();
			scheduler.addProcesses(in.getList());
			sim.run();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
