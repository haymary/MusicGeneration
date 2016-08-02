package Genome;

import java.util.ArrayList;
import java.util.Comparator;

import Gene.Chord;

public abstract class AbstractGenome{
	
	protected ArrayList<Chord> notes;
	protected String instrument_type;
	protected double fitness = -1;

	public AbstractGenome() {
		notes = new ArrayList<>();
	}
	
	public abstract void mutate();
	protected abstract void count_fitness();
	public abstract AbstractGenome reproduce(final AbstractGenome parent2);
	public abstract AbstractGenome generateIndividual();
	
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

	public ArrayList<Chord> getNotes() {
		return notes;
	}

	public double getFitness(){
		count_fitness();
		return fitness;
	}

	public void setFitness(final double total_fit) {
		fitness = total_fit;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Chord chord : notes) {
			if(chord.isContinuesLast()){
				sb.append("- ");
			}else{
				sb.append(chord.getValue() + " ");
			}
		}
		return sb.toString();
	}

	public Chord getFirstNote() {
		return notes.get(0);
	}
	
	

}
