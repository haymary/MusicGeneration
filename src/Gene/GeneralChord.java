package Gene;

import static Evolution.Constants.GENERAL_NUM_NOTES;
import static Evolution.Constants.GENERAL_NUM_OCTAVES;
import static Evolution.Constants.GENERAL_NUM_TYPES_CHORDS;
import static Evolution.Constants.GENERAL_START_OCTAVE;

import java.util.Random;

public class GeneralChord extends Chord{
	
	/*
	 * 0 - R (rest)
	 * (1 - 7) - standard notes in octave (only C, D, E, F, G, A, B)
	 * (1 - 7) - (-1) * 7 - maj chords
	 * (1 - 7) - (-2) * 7 - min
	 * (1 - 7) - (-3) * 7 - aug
	 * (1 - 7) -  (-4) * 7 - dim
	 */
	
	
	private Chord previouse_chord;

	public GeneralChord(final Chord previouse_chord) {
		this.previouse_chord = previouse_chord;
		newChord();
	}

	@Override
	public void newChord() {
		if (isFirstChord()){
			generareIndependentIndividual();
		}else{
			generateGeneralIndividual();
		}
	}

	private void generateGeneralIndividual() {
		if(randomWithTwoThirdProbability()){
			setContinuesLast(true);
			value = previouse_chord.getValue();
			octave_num = previouse_chord.getOctave_num();
			root_note = previouse_chord.getRoot_note();
			chord_type = previouse_chord.getChord_type();
		}else{
			generareIndependentIndividual();
		}
	}

	private void generareIndependentIndividual() {
		setOctave();
		if(randomWithEqualProbability()){
			setChord();
		}else{
			setNote();
		}
	}

	private boolean isFirstChord() {
		return previouse_chord == null;
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
		octave_num = GENERAL_START_OCTAVE + (random.nextInt(GENERAL_NUM_OCTAVES) + 1);
	}

	@Override
	protected void setChord() {
		setNote();
		Random random = new Random();
		chord_type = random.nextInt(GENERAL_NUM_TYPES_CHORDS);
		setValue( - (value + GENERAL_NUM_NOTES * chord_type));
	}

	@Override
	protected void setNote() {
		Random random = new Random();
		root_note = random.nextInt(GENERAL_NUM_NOTES + 1);
		setValue(root_note);
	}

}
