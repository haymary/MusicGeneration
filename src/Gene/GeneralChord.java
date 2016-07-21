package Gene;

import java.util.Random;

public class GeneralChord extends Chord{
	/*
	 * (0 - 12) - standard notes in octave
	 * 13 - nothing
	 */
	
	static int NUM_OF_NOTES = 13;
	static int NUM_OF_CHORDS = 31;
	
	int NUM_OCTAVES;
	int START_OCTAVE;

	int note;
	int chord_num;
	int octave_num;
	
	private boolean continues_last = false;
	
	public GeneralChord(final int NUM_OCTAVES, final int START_OCTAVE) {
		this.NUM_OCTAVES = NUM_OCTAVES;
		this.START_OCTAVE = START_OCTAVE;
		Random random = new Random();
		
		//Does it continues previouse note?
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
		chord_num = random.nextInt(NUM_OF_CHORDS);
	}

	@Override
	protected void generateNote() {
		Random random = new Random();
		chord_num = random.nextInt(NUM_OF_NOTES);
	}

	public boolean is_continues_last() {
		return continues_last;
	}

	public void set_continues_last(final boolean is_continues_last) {
		this.continues_last = is_continues_last;
	}

	@Override
	public String getChord() {
		// TODO Auto-generated method stub
		return null;
	}
}
