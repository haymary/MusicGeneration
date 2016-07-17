package Evolution;

import java.util.Comparator;

import Instruments.AbstractInstrument;
import Instruments.Evolutable;

public class PianoGenome extends AbstractInstrument{

	public PianoGenome() {
		size  = 10;
		melody = new int[number_of_beats * number_of_notes_in_beat];
		
	}
	
	
	@Override
	public void mutate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Evolutable reproduce(final Evolutable parent2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean fitsAbsolutely() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comparator<Evolutable> getComporator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void count_fitness() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double get_fitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Evolutable generateIndividual() {
		// TODO Auto-generated method stub
		return null;
	}

}
