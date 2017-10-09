import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import schedulers.*;
import simulators.ProcessCreationSim;
import simulators.SchedulerSim;

public class SchedulerProj {
	private static SchedulerSim sim;
	private static Scheduler scheduler;
	
	public static void main(String[] args) {
		ProcessCreationSim in;
		int input = -1;
		Scanner kb = new Scanner(System.in);
		
		do {
			System.out.println("==== [ Scheduler Simulator ] ====\n\n Step 1> Have an input.txt in the proper process format in the local directory\n Step 2> Select a Scheduler to begin Simulation\n");
			System.out.println("  1) FCFS\n  2) SJF\n  3) RR,Q=25\n  4) RR,Q=50\n  5) L,Q=60");
			System.out.print("\nEnter option: ");
			
			try {
				input = Integer.parseInt(kb.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input! Try again!");
				continue;
			}
				
			switch (input) {
			case 1:
				scheduler = new FCFS();
				break;
			case 2:
				scheduler = new SJF();
				break;
			case 3:
				scheduler = new RoundRobin(25);
				break;
			case 4:
				scheduler = new RoundRobin(50);
				break;
			case 5:
				scheduler = new Lottery(60);
				break;
			default:
				System.out.println("Invalid Option! Try again!");
				continue;
			}
		
		} while (input > 5 || input < 1);
		
		kb.close();
		
		try {
			sim = new SchedulerSim(scheduler, 3);
			sim.verbose(true);
			in = new ProcessCreationSim(new File("input.txt"));
			in.verbose(true);
			System.out.println();
			in.run();
			System.out.println();
			scheduler.addProcesses(in.getList());
			sim.run();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
