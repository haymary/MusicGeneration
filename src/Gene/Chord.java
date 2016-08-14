package Gene;

import Service.NOTES;

public abstract class Chord{
	
	protected int 		root_note;
	protected int 		chord_type;
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

	public int getRootNote() {
		return root_note;
	}

	public void setRoot_note(final int root_note) {
		this.root_note = root_note;
	}

	public int getChord_type() {
		return chord_type;
	}

	public void setChord_type(final int chord_type) {
		this.chord_type = chord_type;
	}

	public boolean isNotRest() {
		return (root_note != NOTES.REST.getValue());
	}

	public boolean isChord() {
		return value < 0;
	}


	
	
}
