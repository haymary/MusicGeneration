package Gene;

import java.util.Random;

public class DrumChord extends Chord{

	/* Drums instruments are trigger-type
		and can be expressed as a binary signal string.
		1 - trigger note
		0 - rest, generated as Chord
	 */

	public DrumChord(boolean pulse){
		if(pulse) generateNote();
		else generateChord();
	}

	//We use random in generation over standard pulse mask in order to make initial individuals differ from each over

	@Override
	protected void generateChord() {
		Random random = new Random();
		if (random.nextInt(4) > 0) setValue(0);
		else setValue(1);
	}

	@Override
	protected void generateNote() {
		Random random = new Random();
		if (random.nextInt(3) > 0) setValue(1);
		else setValue(0);
	}
}
