package Genome;

import java.util.HashMap;
import java.util.Random;

import Gene.DrumChord;

public class DrumsGenome extends AbstractInstrument {

	public static final HashMap<Integer,String> drumType = new HashMap(){
		{
			this.put(0,"Kick");
			this.put(1,"Closed Hi-Hat");
			this.put(2,"Open Hi-Hat");
			this.put(3,"Snare");
			this.put(4,"Clap");
			this.put(5,"Crash");
			this.put(6,"Tom1");
		}
	};

	private PulseMask mask;

	public DrumsGenome(){
		Random random = new Random();
		//Use drums 0 - 4, because Crash and Tom1 generation is not done yet
		instrument_type = drumType.get(random.nextInt(5));
		int pulseNum = NUM_OF_BARS * random.nextInt(NUM_OF_NOTES_IN_BAR / 4 + 1);
		mask = new PulseMask(pulseNum,melody_length);
		generateInstPulseMask();


	}
	public DrumsGenome(final String instrument_type){
		Random random = new Random();
		this.instrument_type = instrument_type;
		int pulseNum = NUM_OF_BARS * random.nextInt(NUM_OF_NOTES_IN_BAR / 4 + 1);
		mask = new PulseMask(pulseNum, melody_length);
		generateInstPulseMask();
	}

	
	@Override
	public void count_fitness(){
		fitness	= 1;
		switch (this.instrument_type){
			case "Kick":
				if(this.notes.get(0).getValue() == 0) {
					fitness /= 100;
				}
				for(int i = 0; i < this.notes.size(); i++){
					if(this.notes.get(i).getValue() == 1){
						if(calculateDistanceFromMask(i, mask.getPulseMask()) > 1) {
							fitness /= 2;
						}
					}
				}
				break;
			case "Snare":
			case "Clap":
				for(int i = 0; i < this.notes.size(); i++){
					if(this.notes.get(i).getValue() == 1){
						if(calculateDistanceFromMask(i, mask.getPulseMask()) > 2) {
							fitness /= 2;
						}
					}
				}
				break;

			case "Closed Hi-Hat":
			case "Open Hi-Hat":
				int hitNum = 0;
				for(int i = 0; i < this.notes.size(); i++) {
					if (this.notes.get(i).getValue() == 1) {
						hitNum++;
					}
				}
				if(hitNum == melody_length | hitNum == melody_length / 2 | hitNum == melody_length / 4){
					int[] full = mask.generateCommonMask(hitNum, melody_length);
					for(int i = 0; i < this.notes.size(); i++) {
						if (calculateDistanceFromMask(i, full) > melody_length / hitNum) {
							fitness /= 2;
						}
					}
				} else {
					for (int i = 0; i < this.notes.size(); i++) {
						if (this.notes.get(i).getValue() == 1) {
							if (calculateDistanceFromMask(i, mask.getPulseMask()) > 1) {
								fitness /= 2;
							}
						}
					}
				}
				break;
			// TODO Solve Tom1 and Crash composition principles
			/*
			case "Tom1":

				break;
			case "Crash":

				break;
			*/
		}
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
		DrumsGenome child = new DrumsGenome();
		child.generateGenome();
		return child;
	}

	private void generateGenome(){
		int[] tempMask = mask.getPulseMask();
		for (int i = 0; i < melody_length; i++) {
			if(tempMask[i] == 1) {
				this.notes.add(new DrumChord(true));
			} else {
				this.notes.add(new DrumChord(false));
			}
		}
	}

	private void generateInstPulseMask(){
		Random random = new Random();
		switch (this.instrument_type){
			case "Kick":
				break; //standard constructor mask is optimal for init individual
			case "Snare":
				mask.createOffsetMask(NUM_OF_NOTES_IN_BAR/8 + random.nextInt(NUM_OF_NOTES_IN_BAR / 8 + 1));
				break;
			case "Clap":
				mask.createOffsetMask(NUM_OF_NOTES_IN_BAR/8 + random.nextInt(NUM_OF_NOTES_IN_BAR / 8 + 1));
				break;
			case "Closed Hi-Hat":
				mask.createInvertMask();
				break;
			case "Open Hi-Hat":
				mask.createInvertMask();
				break;
			// TODO Solve Tom1 and Crash composition principles
			/*
			case "Tom1":
				mask.createFillingMask();
				break;
			case "Crash":
				mask.createFillingMask();
				break;
			*/
		}
	}



	public int calculateDistanceFromMask(final int currTrig, final int[] mask1){

		int rightDistance = 0;
		int leftDistance = 0;

		for(int i = currTrig; i < mask1.length; i++){
			if(mask1[i] == 0) {
				rightDistance++;
			} else {
				break;
			}
		}

		for(int i = currTrig; i > 0; i--){
			if(mask1[i] == 0) {
				leftDistance++;
			} else {
				break;
			}
		}

		if(rightDistance<=leftDistance) {
			return rightDistance;
		} else {
			return leftDistance;
		}
	}
}
