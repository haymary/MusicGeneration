package FF;

import java.util.ArrayList;

import Genome.AbstractInstrument;

public class MultiInstrumentFF {

	public MultiInstrumentFF(final ArrayList<AbstractInstrument> instruments) {
		// TODO Auto-generated constructor stub
	}

	public double count_ff() {
		double fit = 0;
		
		// Bass tends to play root note of the chord
		
		//Let the four parts move in different directions if possible, even if only one does it’s OK.
		fit += differentDirectionsRule();
		//Use as a small move as possible. 
		//Be super lazy! The best is to keep the alto and tenor part very «boring».
		return 0;
	}

	private double differentDirectionsRule() {
		// TODO Auto-generated method stub
		return 0;
	}
}
