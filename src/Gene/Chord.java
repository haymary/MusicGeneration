package Gene;

public abstract class Chord{
	
	protected boolean 	continues_last = false;
	protected int 		octave_num;
	protected int 		value;
	
	public Chord() {}
	
	protected abstract void newChord();
	protected abstract void setChord() ;
	protected abstract void setNote();
	
	public void mutate() {
		newChord();
	}
	
	public boolean isContinuesLast() {
		return continues_last;
	}
	
	public void setContinuesLast(final boolean is_continues_last) {
		this.continues_last = is_continues_last;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(final int value) {
		this.value = value;
	}

	public int getOctave_num() {
		return octave_num;
	}


	
	
}
