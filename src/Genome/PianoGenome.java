package Genome;

import java.util.Random;

import Gene.GeneralChord;

public class PianoGenome extends AbstractInstrument       {
	static int NUM_NOTES_IN_OCTAVE = 12;
	static int PIANO_START_OCTAVE = 3;
	static int NUM_PIANO_OCTAVES = 4;
	
	static int NUM_OCTAVES = 2;
	static int START_OCTAVE;
	
	public PianoGenome() {
		instrument_type = "Piano";
		Random rand = new Random();
		START_OCTAVE = rand.nextInt(4);
	}

	@Override
	public void count_fitness() {
		//C should be the most common key
		
		//I, IV, V are most popular chords
		
		//If there many jumps between the notes from 
		//one octave to another and back
		
		//If there too many notes that are too short
		
		//IF it not have leaps between notes bigger 
		//than a fifth
		
		//If it contain at least a minimum amount of
		//of a second (50% in the current implementation)
		
		//In major keys, diminished/augmented melodic intervals are NOT recommended.
		//Augmented 2nds and 4ths are NOT allowed.
		//6ths should be avoided.
		//Leading notes in dominant chords ALWAYS resolve onto the tonic of a tonic chord. (Bach didn't always do this, though!) 
		//In a cadential 6-4, the 4 resolves to 3 and the 6 resolves to 5.
		//Always choose a semitone step if one is available.
		//Try to make the spacing of each chord as even as possible
		//Never double the leading note of the scale.
	}

	@Override
	public AbstractInstrument reproduce(final AbstractInstrument parent2) {
		PianoGenome child = new PianoGenome();
		child.notes.addAll(this.notes.subList(0, melody_length / 2));
		child.notes.addAll(parent2.notes.subList(melody_length / 2, melody_length));
		return child;
	}

	@Override
	public AbstractInstrument generateIndividual() {
		PianoGenome child = new PianoGenome();
		child.generateGenome();
		return new PianoGenome();
	}

	private void generateGenome() {
		for (int i = 0; i < melody_length; i++) {
			this.notes.add(new GeneralChord());
		}
		
	}

}
