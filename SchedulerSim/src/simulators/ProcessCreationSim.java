package simulators;

import Process.Process;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessCreationSim implements Simulation {
	private Scanner in;
	private boolean verbose;
	private ArrayList<Process> processes;
	
	public ProcessCreationSim(File file) throws FileNotFoundException {
		in = new Scanner(file);
		processes = new ArrayList<Process>();
		verbose = false;
	}

	@Override
	public void run() {
		while (in.hasNextInt()) {
			Process adding = new Process(in.nextInt(), in.nextInt(), in.nextInt());
			processes.add(adding);
			if (verbose) {
				System.out.println("Created Process w/ PID: " + adding.getPid() + ",BurstTime: " + adding.getBurst_time() + ",Priority: " + adding.getPriority());
			}
		}
	}

	public List<Process> getList() {
		return processes;
	}
	
	@Override
	public void reset() {
		in.reset();
	}

	@Override
	public void verbose(boolean val) {
		verbose = val;
	}
	
	
}
