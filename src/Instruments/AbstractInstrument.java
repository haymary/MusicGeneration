package Instruments;

import java.util.Comparator;

import Evolution.Evolutable;

public abstract class AbstractInstrument implements Evolutable{
	protected int number_of_notes_in_beat;
	protected int number_of_beats;
	protected int temp;
	protected int[] melody;
	
	protected void setNotesInterval(final int fromNote, final int toNote){
		//TODO
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
	public Comparator<Evolutable> getComporator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double get_fitness() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean fitsAbsolutely() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Evolutable generateIndividual() {
		// TODO Auto-generated method stub
		return null;
	}
}
