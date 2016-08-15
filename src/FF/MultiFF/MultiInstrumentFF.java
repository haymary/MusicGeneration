package FF.MultiFF;

import java.util.ArrayList;

import Gene.Chord;
import Genome.AbstractGenome;

public abstract class MultiInstrumentFF {

	protected ArrayList<AbstractGenome> instruments;
	
	protected double fit = 0;
	
	public void setInstruments(final ArrayList<AbstractGenome> instruments){
		this.instruments = instruments;
	}
	
	public abstract double count_ff();
	
	/*
	 * Rule:
	 * Let the four parts move in different directions if possible, 
	 * even if only one does it’s OK.
	 */
	protected void differentDirectionsRule(final ArrayList<Chord> leadNotes, final ArrayList<Chord> backNotes) {
		int WINDOW_SIZE = 3;
		int FINE_FOR_GOING_IN_SAME_DIRECTIONS = 2;
		
		for (int i = 0; i < leadNotes.size() - WINDOW_SIZE; i++) {
			if(isIncreasing(leadNotes, i, WINDOW_SIZE)){
				if(isIncreasing(leadNotes, i, WINDOW_SIZE)){
					fit -= FINE_FOR_GOING_IN_SAME_DIRECTIONS;
				}
			}else if(isDecreasing(leadNotes, i, WINDOW_SIZE)) {
				if(isDecreasing(backNotes, i, WINDOW_SIZE)){
					fit -= FINE_FOR_GOING_IN_SAME_DIRECTIONS;
				}
			}
		}
	}

	private boolean isDecreasing(final ArrayList<Chord> leadNotes, final int index, final int window_size) {
		for (int j = 0; j < window_size - 1; j++) {
			if(!(Math.abs(leadNotes.get(index + j).getValue()) 
					>= Math.abs(leadNotes.get(index + j + 1).getValue()))){
				return false;
			}
		}
		return true;
	}

	private boolean isIncreasing(final ArrayList<Chord> leadNotes, final int index, final int window_size) {
		for (int j = 0; j < window_size - 1; j++) {
			if(!(Math.abs(leadNotes.get(index + j).getValue()) 
					<= Math.abs(leadNotes.get(index + j + 1).getValue()))){
				return false;
			}
		}
		return true;
	}
}
