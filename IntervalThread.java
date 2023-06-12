package Components;

public class IntervalThread implements Runnable {
	
	private Interval interval;
	
	public IntervalThread(Interval interval) {
		this.interval = interval;
	}
	
	@Override
	public void run() {
		IntervalPlayer intervalPlayer = new IntervalPlayer();
		intervalPlayer.playInterval(interval);
		intervalPlayer.closeSynth();
	}
}
