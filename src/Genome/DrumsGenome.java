package Genome;


import static Service.Constants.MELODY_LENGTH;
import static Service.Constants.NUM_OF_NOTES_IN_BAR;

import java.util.HashMap;
import java.util.Random;

import DrumsEvo.PulseMask;
import Gene.DrumChord;
public abstract class DrumsGenome extends AbstractGenome {

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

	protected PulseMask mask;
	protected int pulseNum;


	protected abstract DrumsGenome generateChild(String instrument_type1);
	
	@Override
	public void count_fitness(){
		fitness	= 1;
		switch (this.instrument_type){
			case "Kick":
				if(this.notes.get(0).getValue() == 0) {
					fitness /= 100;
				}
				int kickNum = 0;
				for(int i = 0; i < this.notes.size(); i++){
					if(this.notes.get(i).getValue() == 1){
						kickNum++;
						if(calculateDistanceFromMask(i, mask.getPulseMask()) > 1) {
							fitness /= 2;
						}
					}
				}
				if(kickNum > pulseNum*2) {
					fitness /= 100;
				}
				break;
			case "Snare":
				kickNum = 0;
				for(int i = 0; i < this.notes.size(); i++){
					if(this.notes.get(i).getValue() == 1){
						kickNum++;
						if(calculateDistanceFromMask(i, mask.getPulseMask()) > 1) {
							fitness /= 2;
						}
					}
				}
				if(kickNum > pulseNum*2) {
					fitness /= 100;
				}
				break;
			case "Clap":
				kickNum = 0;
				for(int i = 0; i < this.notes.size(); i++){
					if(this.notes.get(i).getValue() == 1){
						kickNum++;
						if(calculateDistanceFromMask(i, mask.getPulseMask()) > 1) {
							fitness /= 2;
						}
					}
				}
				if(kickNum > pulseNum*2) {
					fitness /= 100;
				}
				break;

			case "Closed Hi-Hat":
				int hitNum = 0;
				for(int i = 0; i < this.notes.size(); i++) {
					if (this.notes.get(i).getValue() == 1) {
						hitNum++;
					}
				}
				if(hitNum == MELODY_LENGTH | hitNum == MELODY_LENGTH / 2 | hitNum == MELODY_LENGTH / 4){
					int[] full = mask.generateCommonMask(hitNum, MELODY_LENGTH);
					for(int i = 0; i < this.notes.size(); i++) {
						if (calculateDistanceFromMask(i, full) > MELODY_LENGTH / hitNum) {
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
			case "Open Hi-Hat":
				hitNum = 0;
				for(int i = 0; i < this.notes.size(); i++) {
					if (this.notes.get(i).getValue() == 1) {
						hitNum++;
					}
				}
				if(hitNum == MELODY_LENGTH | hitNum == MELODY_LENGTH / 2 | hitNum == MELODY_LENGTH / 4){
					int[] full = mask.generateCommonMask(hitNum, MELODY_LENGTH);
					for(int i = 0; i < this.notes.size(); i++) {
						if (calculateDistanceFromMask(i, full) > MELODY_LENGTH / hitNum) {
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
	public AbstractGenome reproduce(final AbstractGenome parent2) {
		DrumsGenome child = generateChild(instrument_type);
		Random rand = new Random();
		int point_of_division = MELODY_LENGTH - (MELODY_LENGTH/4) * (rand.nextInt(3) + 1);
		child.getNotes().clear();
		child.getNotes().addAll(parent2.getNotes().subList(point_of_division, MELODY_LENGTH));
		child.getNotes().addAll(this.getNotes().subList(0, point_of_division));
		return child;
	}

	@Override
	public void mutate() {
		int MAX_NUM_MUTATIONS = pulseNum/2;

		Random number_of_mutations = new Random();
		for (int i = 0; i < number_of_mutations.nextInt(MAX_NUM_MUTATIONS); i++) {
			Random element_to_mutate = new Random();
			getNotes().get(element_to_mutate.nextInt(MELODY_LENGTH)).mutate();
		}
	}

	@Override
	public AbstractGenome generateIndividual(final String instrument_type1) {
		DrumsGenome child = generateChild(instrument_type1);
		child.generateGenome();
		return child;
	}

	@Override
	public int compareTo(final AbstractGenome other_genome) {
		if(this.fitness > other_genome.fitness){
			return -1;
		}else if(this.fitness == other_genome.fitness) {
			return 0;
		}
		return 1;
	}



	protected void generateGenome(){
		int[] tempMask = mask.getPulseMask();
		for (int i = 0; i < MELODY_LENGTH; i++) {
			if(tempMask[i] == 1) {
				this.notes.add(new DrumChord(true));
			} else {
				this.notes.add(new DrumChord(false));
			}
		}
	}

	protected void generateInstPulseMask(){
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
