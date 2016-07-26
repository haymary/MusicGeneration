package Genome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import Gene.Chord;

public abstract class AbstractInstrument{
	protected int NUM_OF_NOTES_IN_BAR = 16;
	protected int NUM_OF_BARS = 4;
	protected int melody_length = NUM_OF_NOTES_IN_BAR * NUM_OF_BARS;
	protected int FROM_NOTE, TO_NOTE;
	
	
	protected ArrayList<Chord> notes;
	protected String instrument_type;
	protected double fitness = -1;

	public AbstractInstrument() {
		setNotes(new ArrayList<>());
		//TODO
		//Should somehow have an interval in which to generate notes
	}
	
	public void mutate(){
		Random number_of_mutations = new Random();
		for (int i = 0; i < number_of_mutations.nextInt(melody_length/3); i++) {
			Random element_to_mutate = new Random();
			getNotes().get(element_to_mutate.nextInt(melody_length)).mutate(); 
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
	
	public Comparator<AbstractInstrument> getComparator(){
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

	public String getInstrumentType() {
		return instrument_type;
	}

	protected abstract void count_fitness();

	public abstract AbstractInstrument reproduce(final AbstractInstrument parent2);

	public abstract AbstractInstrument generateIndividual();

	public ArrayList<Chord> getNotes() {
		return notes;
	}

	public void setNotes(final ArrayList<Chord> notes) {
		this.notes = notes;
	}

}
