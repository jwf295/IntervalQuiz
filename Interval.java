package Components;

public class Interval {
	
	private int firstPitch;
	private int secondPitch;
	private int distanceAsInt;
	private String intervalName;
	
	public Interval() {
		this.firstPitch = getRandomPitch();
		this.secondPitch = getRandomPitch();
		this.distanceAsInt = Math.abs(this.firstPitch - this.secondPitch);
		this.intervalName = toString();
	}

	public int getFirstPitch() {
		return firstPitch;
	}

	public int getSecondPitch() {
		return secondPitch;
	}

	public int getDistanceAsInt() {
		return distanceAsInt;
	}

	public String getIntervalName() {
		return intervalName;
	}

	public int getRandomPitch() {
		return (int)(60 + Math.random() * 13);
	}

	@Override
	public String toString() {
		String intervalString = "";
		switch (distanceAsInt) {
			case 0: 
				intervalString = "P1";
				break;
			case 1:
				intervalString = "m2";
				break;
			case 2:
				intervalString = "M2";
				break;
			case 3:
				intervalString = "m3";
				break;
			case 4:
				intervalString = "M3";
				break;
			case 5:
				intervalString = "P4";
				break;
			case 6:
				intervalString = "d5";
				break;
			case 7:
				intervalString = "P5";
				break;
			case 8:
				intervalString = "m6";
				break;
			case 9:
				intervalString = "M6";
				break;
			case 10:
				intervalString = "m7";
				break;
			case 11:
				intervalString = "M7";
				break;
			case 12:
				intervalString = "P8";
				break;
		}
		return intervalString;
	}
}
