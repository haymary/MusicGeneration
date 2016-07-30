package Gene;

public abstract class Chord{
	protected int NUM_OCTAVES;
	protected int START_OCTAVE;
	
	protected boolean continues_last = false;
	protected int octave_num;
	protected int value;
	
	public Chord() {
	}

	public void mutate() {
		newChord();
	}

	public boolean is_continues_last() {
		return continues_last;
	}
	
	public void set_continues_last(final boolean is_continues_last) {
		this.continues_last = is_continues_last;
	}
	
	protected abstract void newChord();
	protected abstract void generateChord() ;
	
	protected abstract void generateNote();

	public int getValue() {
		return value;
	}

	public void setValue(final int value) {
		this.value = value;
	}

	public int getOctave_num() {
		return octave_num + START_OCTAVE;
	}
}
