package Gene;

import java.util.Random;

public class GeneralChord extends Chord{
	/*
	 * (0 - 12) - standard notes in octave
	 * 13 - nothing
	 */
	static int NUM_OF_NOTES = 13;
	static int NUM_OF_CHORDS = 31;

	int note;
	int chord_num;
	private boolean continues_last = false;
	
	public GeneralChord() {
		Random random = new Random();
		
		//Does it continues previouse note?
		if(random.nextBoolean()){
			set_continues_last(true);
			return;
		}
		generateNote();
		if(random.nextBoolean()){
			generateChord();
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
}
