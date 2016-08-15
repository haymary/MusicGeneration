package Genome;

import java.util.ArrayList;

import Gene.Chord;

public abstract class AbstractGenome implements Comparable<AbstractGenome>{
	
	protected ArrayList<Chord> 	notes;
	protected String 			instrument_type;
	protected double 			fitness = -1;
	protected int 				instrument_type_num;
	
	public AbstractGenome() {
		notes = new ArrayList<>();
	}
	
	public abstract void mutate();
	protected abstract void count_fitness();
	public abstract AbstractGenome reproduce(final AbstractGenome parent2);
	public abstract AbstractGenome generateIndividual(String instrument_type1);
	
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
				sb.append("+ ");
			}else{
				sb.append(chord.getValue() + " ");
			}
		}
		return sb.toString();
	}

	public Chord getFirstNote() {
		return notes.get(0);
	}

	public int getInstrument_type_num() {
		return instrument_type_num;
	}

	protected void setInstrument_type_num(final int instrument_type_num) {
		this.instrument_type_num = instrument_type_num;
	}
	
	

}
