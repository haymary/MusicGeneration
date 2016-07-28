package Gene;

import java.util.Random;

public class GeneralChord extends Chord{
	
	static int NUM_OF_NOTES = 9;
	static int NUM_OF_CHORDS = 4;
	
	/*
	 * 0 - R (rest)
	 * (1 - 8) - standard notes in octave (only C, D, E, F, G, A, B)
	 * (1 - 8) + (-1) * 8 - maj chords
	 * (1 - 8) + (-2) * 8 - min
	 * (1 - 8) + (-3) * 8 - aug
	 * (1 - 8) + (-4) * 8 - dim
	 */
	
	public GeneralChord(final int NUM_OCTAVES, final int START_OCTAVE) {
		this.NUM_OCTAVES = NUM_OCTAVES;
		this.START_OCTAVE = START_OCTAVE;
		Random random = new Random();
		
		//Does it continues previous note?
		if(random.nextBoolean()){
			set_continues_last(true);
		}else{
			//Number of octave
			octave_num = random.nextInt(NUM_OCTAVES) + 1;
			generateNote();
			//Generate chord or note
			if(random.nextBoolean()){
				generateChord();
			}
		}
	}

	@Override
	protected void generateChord() {
		Random random = new Random();
		setValue( - (value + NUM_OF_NOTES * random.nextInt(NUM_OF_CHORDS)));
	}

	@Override
	protected void generateNote() {
		Random random = new Random();
		setValue(random.nextInt(NUM_OF_NOTES));
	}

}
