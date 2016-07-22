package Gene;

public abstract class Chord{
	int NUM_OCTAVES;
	int START_OCTAVE;
	
	int octave_num;
	private boolean continues_last = false;
	private int value;
	
	public Chord() {
	}

	public void mutate() {
		generateChord();
	}

	public boolean is_continues_last() {
		return continues_last;
	}
	
	public void set_continues_last(final boolean is_continues_last) {
		this.continues_last = is_continues_last;
	}
	
	protected abstract void generateChord() ;
	
	protected abstract void generateNote();

	public int getValue() {
		return value;
	}

	public void setValue(final int value) {
		this.value = value;
	}
}
