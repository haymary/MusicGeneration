package Gene;

import java.util.Random;

public class DrumChord extends Chord{

	/* Drums instruments are trigger-type
		and can be expressed as a binary signal string.
		1 - trigger note
		0 - rest, generated as Chord
	 */

	public DrumChord(boolean pulse){
		if(pulse) setNote();
		else setChord();
	}

	//We use random in generation over standard pulse mask in order to make initial individuals differ from each over

	@Override
	protected void newChord() {
		Random random = new Random();
		if(value == 1) setValue(0);
		else setValue(1);
	}

	@Override
	protected void setChord() {
		setValue(0);
	}

	@Override
	protected void setNote() {
		setValue(1);
	}
}
