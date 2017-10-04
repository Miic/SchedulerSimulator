package comparator;

import java.util.Comparator;
import Process.Process;

public class PriorityComparator implements Comparator<Process> {

	@Override
	public int compare(Process o1, Process o2) {
		if (o1.getPriority() < o2.getPriority()) {
			return -1;
		} else if (o1.getPriority() > o2.getPriority()) {
			return 1;
		}
		return 0;
	}

}
