package Gene;

import java.util.Random;

public class DrumChord extends Chord{

	/* Drums instruments are trigger-type
		and can be expressed as a binary signal string.
		1 - trigger note
		0 - rest, generated as Chord
	 */

	public DrumChord(boolean pulse){
		if(pulse) generateChord();
		else generateNote();
	}

	//We use random in generation over standard pulse mask in order to make initial individuals differ from each over

	@Override
	protected void generateChord() {
		setValue(1);
	}

	@Override
	protected void generateNote() {
		setValue(0);
	}
}
