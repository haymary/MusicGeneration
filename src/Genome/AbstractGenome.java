package Genome;

import java.util.ArrayList;
import java.util.Comparator;

import Gene.Chord;

public abstract class AbstractGenome{
	
	protected ArrayList<Chord> notes;
	protected String instrument_type;
	protected double fitness = -1;

	public AbstractGenome() {
		setNotes(new ArrayList<>());
		//TODO
		//Should somehow have an interval in which to generate notes
	}
	
	public abstract void mutate();
	
	public double getFitness(){
		count_fitness();
		return fitness;
	}

	public Comparator<AbstractGenome> getComparator(){
		return new Comparator<AbstractGenome>() {

			@Override
			public int compare(final AbstractGenome o1, final AbstractGenome o2) {
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

	public abstract AbstractGenome reproduce(final AbstractGenome parent2);

	public abstract AbstractGenome generateIndividual();

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
			sb.append(chord.getValue() + " ");
		}
		return sb.toString();
	}
	
	

}
