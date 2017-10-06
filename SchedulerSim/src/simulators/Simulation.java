package simulators;

public interface Simulation {
	public void run();
	public void reset();
	public void verbose(boolean val);
}
