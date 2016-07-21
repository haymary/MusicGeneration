package Instruments;

import java.util.ArrayList;
import java.util.Random;

abstract class Chord{
	ArrayList<Integer> chord;
	int min_note;
	int max_note;
	
	public Chord() {
		generate_individual();
	}

	public void mutate() {
		Random rand = new Random();
		if(rand.nextInt(2) == 0){
			generate_individual();
		}else{
			Random place_in_chord = new Random();
			chord.set(place_in_chord.nextInt(chord.size()), generateNote());
		}
	}

	protected abstract void generate_individual() ;
	
	protected abstract int generateNote();
}
