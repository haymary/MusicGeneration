package Instruments;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import Evolution.Evolutable;

public class PianoGenome extends AbstractInstrument{
	static Map<Integer, ArrayList<Integer>> chordMap;

	public PianoGenome() {
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

	@Override
	public Evolutable generateIndividual() {
		// TODO Auto-generated method stub
		return null;
	}

	class Chord{
		ArrayList<Integer> chord;
		Random random;
		
		public Chord() {
			random = new Random();
			chord = new ArrayList<>();
			if(random.nextInt(2) == 0){
				generate_chord();
				return;
			}
			generate_note();
			return;
		}
		
		private void generate_chord() {
			chord.addAll(chordMap.get(random.nextInt(chordMap.size())));
		}

		private void generate_note() {
			chord.add(random.nextInt(244) - 127);
		}

		public void mutate() {
			// TODO Auto-generated method stub
			
		}
	}
}
