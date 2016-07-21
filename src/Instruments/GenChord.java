package Instruments;

import java.util.ArrayList;
import java.util.Random;

public class GenChord extends Chord{
	ArrayList<Integer> chord;
	
	public GenChord() {
		
	}

	private void generate_chord() {
		Random random = new Random();
		chord = new ArrayList<>();
		if(random.nextInt(2) == 0){
			//generate_chord();
			return;
		}
		chord.add(generateNote());
		return;
	}

	@Override
	protected void generate_individual() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int generateNote() {
		// TODO Auto-generated method stub
		return 0;
	}
}
