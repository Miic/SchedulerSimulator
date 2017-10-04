package comparator;

import java.util.Comparator;
import Process.Process;

public class BurstTimeComparator implements Comparator<Process> {

	@Override
	public int compare(Process o1, Process o2) {
		if (o1.getBurst_time() < o2.getBurst_time()) {
			return -1;
		} else if (o1.getBurst_time() > o2.getBurst_time()) {
			return 1;
		}
		return 0;
	}

}
