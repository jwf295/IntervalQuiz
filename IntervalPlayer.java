package Components;

import javax.sound.midi.*;

public class IntervalPlayer {
	
	private Synthesizer synth;
	private Instrument[] instrumentArray;
	private MidiChannel[] midiChannelArray;
	private int channelNum;
	private int instrumentNum;
	
	public IntervalPlayer() {
		try {
			this.synth = MidiSystem.getSynthesizer();
			openSynth();
			this.instrumentArray = synth.getDefaultSoundbank().getInstruments();
			this.midiChannelArray = synth.getChannels();
			this.channelNum = 0;
			this.instrumentNum = 0;
			loadInstrument(this.instrumentNum);
		}
		catch (MidiUnavailableException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	public int getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(int channelNum) {
		this.channelNum = channelNum;
	}

	public int getInstrumentNum() {
		return instrumentNum;
	}

	public void setInstrumentNum(int instrumentNum) {
		this.instrumentNum = instrumentNum;
	}

	public void openSynth() throws MidiUnavailableException {
		synth.open();
	}
	
	public void closeSynth() {
		synth.close();
	}
	
	public void loadInstrument(int instrumentNum) throws IllegalArgumentException {
		if (!synth.loadInstrument(instrumentArray[instrumentNum])) {
			throw new IllegalArgumentException("Synthesizer does not support specified instrument's soundbank.");
		}
	}
	
	public void playInterval(Interval interval) {
		midiChannelArray[channelNum].noteOn(interval.getFirstPitch(), 120);
		threadSleep();
		midiChannelArray[channelNum].noteOff(interval.getFirstPitch());
		
		midiChannelArray[channelNum].noteOn(interval.getSecondPitch(), 120);
		threadSleep();
		midiChannelArray[channelNum].noteOff(interval.getSecondPitch());
	}
	
	public void threadSleep() {
		try {
			Thread.sleep(750);
		}
		catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
