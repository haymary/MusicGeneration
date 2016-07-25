package Gene;

import java.util.Random;

public class GeneralChord extends Chord{
	
	static int NUM_OF_NOTES = 13;
	static int NUM_OF_CHORDS = 7;
	
	/*
	 * (-8, -1) - chords
	 * (0 - 12) - standard notes in octave
	 * 13 - nothing
	 */
	
	public GeneralChord(final int NUM_OCTAVES, final int START_OCTAVE) {
		this.NUM_OCTAVES = NUM_OCTAVES;
		this.START_OCTAVE = START_OCTAVE;
		Random random = new Random();
		
		//Does it continues previous note?
		if(random.nextBoolean()){
			set_continues_last(true);
			return;
		}
		//Number of octave
		octave_num = random.nextInt(NUM_OCTAVES);
		
		//Generate chord or note
		if(random.nextBoolean()){
			generateChord();
		}else{
			generateNote();
		}
	}

	@Override
	protected void generateChord() {
		Random random = new Random();
		setValue( - (random.nextInt(NUM_OF_CHORDS) + 1));
	}

	@Override
	protected void generateNote() {
		Random random = new Random();
		setValue(random.nextInt(NUM_OF_NOTES));
	}

}
