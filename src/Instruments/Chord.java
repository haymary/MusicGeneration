package Instruments;

import java.util.ArrayList;
import java.util.Random;

abstract class Chord{
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
	
	protected abstract void generate_chord() ;

	private void generate_note() {
		chord.add(random.nextInt(244) - 127);
	}

	public void mutate() {
		// TODO Auto-generated method stub
		
	}
}
