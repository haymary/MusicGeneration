package Genome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import Gene.Chord;

public abstract class AbstractInstrument{
	protected int NUM_OF_NOTES_IN_BAR = 16;
	protected int NUM_OF_BARS = 1;
	protected int melody_length = NUM_OF_NOTES_IN_BAR * NUM_OF_BARS;

	
	protected ArrayList<Chord> notes;
	protected String instrument_type;
	protected double fitness = -1;

	public AbstractInstrument() {
		setNotes(new ArrayList<>());
		//TODO
		//Should somehow have an interval in which to generate notes
	}
	
	public void mutate(){
		Random random = new Random();
		int num_of_mutations = random.nextInt(melody_length/3);
		for (int i = 0; i < num_of_mutations; i++) {
			Random element_to_mutate = new Random();
			getNotes().get(element_to_mutate.nextInt(melody_length)).mutate(); 
		}
	}
	
	public double getFitness(){
		count_fitness();
		return fitness;
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

	public abstract AbstractInstrument generateIndividual(String instrument_type);

	public ArrayList<Chord> getNotes() {
		return notes;
	}

	public void setNotes(final ArrayList<Chord> notes) {
		this.notes = notes;
	}

	public void setFitness(final double total_fit) {
		fitness = total_fit;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Chord chord : notes) {
			sb.append(chord.getValue());
		}
		return sb.toString();
	}
	
	

}
