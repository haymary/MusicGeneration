package FF;

import java.util.ArrayList;

import Gene.Chord;
import Genome.AbstractGenome;

public class MultiInstrumentFF {

	private ArrayList<AbstractGenome> instruments;
	private double fit = 0;
	
	public MultiInstrumentFF(final ArrayList<AbstractGenome> instruments) {
		this.instruments = instruments;
	}

	public double count_ff() {
		
		// Bass tends to play root note of the chord
		
		differentDirectionsRule();
		//Use as a small move as possible. 
		//Be super lazy! The best is to keep the alto and tenor part very «boring».
		return 0;
	}

	/*
	 * Rule:
	 * Let the four parts move in different directions if possible, 
	 * even if only one does it’s OK.
	 */
	private void differentDirectionsRule() {
		int WINDOW_SIZE = 3;
		
		ArrayList<Chord> leadNotes = instruments.get(0).getNotes();
		ArrayList<Chord> backNotes = instruments.get(1).getNotes();
		
		for (int i = 0; i < leadNotes.size() - WINDOW_SIZE; i++) {
			if(isIncreasing(leadNotes, i, WINDOW_SIZE)){
				if(isIncreasing(leadNotes, i, WINDOW_SIZE)){
					fit -= 1;
				}
			}else if(isDecreasing(leadNotes, i, WINDOW_SIZE)) {
				if(isDecreasing(backNotes, i, WINDOW_SIZE)){
					fit -= 1;
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
