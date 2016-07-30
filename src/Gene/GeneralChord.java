package Gene;

import static Evolution.Constants.GENERAL_NUM_NOTES;
import static Evolution.Constants.GENERAL_NUM_OCTAVES;
import static Evolution.Constants.GENERAL_NUM_TYPES_CHORDS;
import static Evolution.Constants.GENERAL_START_OCTAVE;

import java.util.Random;

public class GeneralChord extends Chord{
	private int start_octave;
	
	/*
	 * 0 - R (rest)
	 * (1 - 7) - standard notes in octave (only C, D, E, F, G, A, B)
	 * (1 - 7) - (-1) * 7 - maj chords
	 * (1 - 7) - (-2) * 7 - min
	 * (1 - 7) - (-3) * 7 - aug
	 * (1 - 7) -  (-4) * 7 - dim
	 */
	
	public GeneralChord() {
		newChord();
	}

	@Override
	public void newChord() {
		Random random = new Random();
		
		if(random.nextBoolean()){
			setContinuesLast(true);
		}else{
			setOctave();
			if(random.nextBoolean()){
				setChord();
			}else{
				setNote();
			}
		}
	}

	private void setOctave() {
		Random random = new Random();
		octave_num = GENERAL_START_OCTAVE + (random.nextInt(GENERAL_NUM_OCTAVES) + 1);
	}

	@Override
	protected void setChord() {
		setNote();
		Random random = new Random();
		setValue( - (value + GENERAL_NUM_NOTES * random.nextInt(GENERAL_NUM_TYPES_CHORDS)));
	}

	@Override
	protected void setNote() {
		Random random = new Random();
		setValue(random.nextInt(GENERAL_NUM_NOTES + 1));
	}

}
