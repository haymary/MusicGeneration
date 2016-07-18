package Instruments;

import java.util.ArrayList;
import java.util.Map;

public class PianoGenome extends AbstractInstrument{
	static Map<Integer, ArrayList<Integer>> chordMap;

	public PianoGenome() {
		type = "Piano";
		fillChordMap();
		number_of_beats = 10;
		number_of_notes_in_beat = 16;
		melody = new int[number_of_beats * number_of_notes_in_beat];
	}

	private void fillChordMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void count_fitness() {
		// TODO Auto-generated method stub
		
	}

	class PianoChord extends Chord{

		@Override
		protected void generate_chord() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
