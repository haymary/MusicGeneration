package Genome;

import Gene.DrumChord;

import java.util.HashMap;
import java.util.Random;

public class DrumsGenome extends AbstractInstrument {

	public static final HashMap<Integer,String> drumType = new HashMap(){
		{
			this.put(0,"Kick");
			this.put(1,"Closed Hi-Hat");
			this.put(2,"Open Hi-Hat");
			this.put(3,"Snare");
			this.put(4,"Tom1");
			this.put(5,"Crash");
			this.put(6,"Clap");
		}
	};

	private PulseMask mask;

	public DrumsGenome(){
		Random random = new Random();
		instrument_type = drumType.get(random.nextInt(7));
		int pulseNum = NUM_OF_BARS+random.nextInt(NUM_OF_BARS*2);
		mask = new PulseMask(pulseNum,melody_length);
		generateInstPulseMask();


	}
	public DrumsGenome(String instrument_type){
		Random random = new Random();
		this.instrument_type = instrument_type;
		int pulseNum = NUM_OF_BARS+random.nextInt(NUM_OF_BARS*2);
		mask = new PulseMask(pulseNum,melody_length);
		generateInstPulseMask();
	}

	
	@Override
	public void count_fitness(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractInstrument reproduce(final AbstractInstrument parent2) {
		DrumsGenome child = new DrumsGenome(this.getInstrumentType());
		child.getNotes().addAll(this.getNotes().subList(0, melody_length / 2));
		child.getNotes().addAll(parent2.getNotes().subList(melody_length / 2, melody_length));
		return child;
	}

	@Override
	public AbstractInstrument generateIndividual() {
		// TODO Auto-generated method stub
		return null;
	}

	private void generateGenome(){
		for (int i = 0; i < melody_length; i++) {
			if(mask.pulseMask[i] == 1)	this.getNotes().add(new DrumChord(true));
			else this.getNotes().add(new DrumChord(false));
		}
	}

	private void generateInstPulseMask(){
		Random random = new Random();
		switch (this.instrument_type){
			case "Kick":
				break; //standard constructor mask is optimal for init individual
			case "Snare":
				mask.createOffsetMask(4 + NUM_OF_NOTES_IN_BAR * random.nextInt(2)/4);
				break;
			case "Clap":
				mask.createOffsetMask(4 + NUM_OF_NOTES_IN_BAR * random.nextInt(2)/4);
				break;
			case "Closed Hi-Hat":
				mask.createInvertMask();
				break;
			case "Open Hi-Hat":
				mask.createInvertMask();
				break;
			case "Tom1":
				mask.createFillingMask();
				break;
			case "Crash":
				mask.createFillingMask();
				break;
		}
	}
}
