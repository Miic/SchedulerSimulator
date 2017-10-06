package schedulers;
import java.util.List;

import Process.Process;

public interface Scheduler {
	public void scheduleProcess(Process process);
	public Process peek();
	public Process pop();
	public void addProcesses(List<Process> process);
	public int size();
}