package Gene;

import static Service.Constants.GENERAL_NUM_NOTES;

import java.util.Random;

import Service.Constants;
public class GeneralChord extends Chord{
	
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
		setOctave();
		if(Constants.IS_FOUR_PART_HARMONY){
			setChord();
		}else{
			setChordOrNote();
		}
		if(randomWithTwoThirdProbability()){
			setContinuesLast(true);
		}else{
			setContinuesLast(false);
		}
	}

	private void setChordOrNote() {
		if(randomWithEqualProbability()){
			setChord();
		}else{
			setNote();
		}
	}

	private boolean randomWithEqualProbability() {
		Random random = new Random();
		return random.nextBoolean();
	}

	private boolean randomWithTwoThirdProbability() {
		Random random = new Random();
		return random.nextInt(3) == 2;
	}

	private void setOctave() {
		Random random = new Random();
		octave_num = Constants.GENERAL_START_OCTAVE + (random.nextInt(Constants.GENERAL_NUM_OCTAVES) + 1);
	}

	@Override
	protected void setChord() {
		setNote();
		Random random = new Random();
		chord_type = random.nextInt(Constants.GENERAL_NUM_TYPES_CHORDS);
		setValue( - (value + GENERAL_NUM_NOTES * chord_type));
	}

	@Override
	protected void setNote() {
		Random random = new Random();
		root_note = random.nextInt(GENERAL_NUM_NOTES + 1);
		setValue(root_note);
	}

}
