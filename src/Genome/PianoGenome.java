package Genome;

import java.util.Random;

import FF.GeneralFF;
import Gene.GeneralChord;

public class PianoGenome extends AbstractInstrument       {
	static int NUM_NOTES_IN_OCTAVE = 12;
	static int PIANO_START_OCTAVE = 3;
	static int NUM_PIANO_OCTAVES = 4;
	
	static int NUM_OF_OCTAVES = 2;
	static int START_OCTAVE;
	
	public PianoGenome() {
		instrument_type = "Piano";
		Random rand = new Random();
		START_OCTAVE = rand.nextInt(4);
	}

	@Override
	public AbstractInstrument reproduce(final AbstractInstrument parent2) {
		Random rand = new Random();
		int point_of_division = rand.nextInt(melody_length);
		
		PianoGenome child = new PianoGenome();
		child.getNotes().addAll(parent2.getNotes().subList(point_of_division, melody_length));
		child.getNotes().addAll(this.getNotes().subList(0, point_of_division));
		return child;
	}

	@Override
	public AbstractInstrument generateIndividual() {
		PianoGenome child = new PianoGenome();
		child.generateGenome();
		return child;
	}

	private void generateGenome() {
		for (int i = 0; i < melody_length; i++) {
			this.notes.add(new GeneralChord(NUM_OF_OCTAVES, START_OCTAVE));
		}
		
	}

	@Override
	protected void count_fitness() {
		GeneralFF ff = new GeneralFF(notes);
		fitness = ff.calculateScore();
	}

}
