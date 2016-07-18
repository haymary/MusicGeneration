package Instruments;

import Evolution.Evolutable;

public abstract class AbstractInstrument implements Evolutable{
	protected int number_of_notes_in_beat;
	protected int number_of_beats;
	protected int temp;
	protected int[] melody;
	
	protected void setNotesInterval(final int fromNote, final int toNote){
		//TODO
	}
}
