package Instruments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public abstract class AbstractInstrument{
	protected int NUM_OF_NOTES_IN_BEAT= 16;
	protected int NUM_OF_BEATS= 5;
	protected int melody_length = NUM_OF_NOTES_IN_BEAT * NUM_OF_BEATS;
	
	protected ArrayList<Chord> notes;
	protected String instrument_type;
	protected double fitness = -1;

	public AbstractInstrument() {
		notes = new ArrayList<>();
	}
	
	public void mutate(){
		Random number_of_mutations = new Random();
		for (int i = 0; i < number_of_mutations.nextInt(melody_length/3); i++) {
			Random element_to_mutate = new Random();
			notes.get(element_to_mutate.nextInt(melody_length)).mutate(); 
		}
	}
	
	public double getFitness(){
		if(fitness == -1){
			count_fitness();
		}
		return fitness;
	}

	public boolean fitsAbsolutely(){
		if (fitness == 1){
			return true;
		}
		return false;
	}
	
	public Comparator<AbstractInstrument> getComporator(){
		return new Comparator<AbstractInstrument>() {

			@Override
			public int compare(final AbstractInstrument o1, final AbstractInstrument o2) {
				if(o1.fitness > o2.fitness){
					return 1;
				}else if(o1.fitness == o2.fitness) {
					return 0;
				}
				return -1;
			}
		};
	}

	public String getInstumentType() {
		return instrument_type;
	}

	protected abstract void count_fitness();

	public abstract AbstractInstrument reproduce(final AbstractInstrument parent2);

	public abstract AbstractInstrument generateIndividual();

}
