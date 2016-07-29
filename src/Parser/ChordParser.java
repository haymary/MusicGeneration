package Parser;

import java.util.ArrayList;

import Gene.Chord;
import Genome.AbstractInstrument;

public class ChordParser extends GenomeParser {

    public ChordParser() {
    }


	/*
	 * 0 - R (rest)
	 * (1 - 8) - standard notes in octave (only C, D, E, F, G, A, B)
	 * (1 - 8) + (-1) * 8 - maj chords
	 * (1 - 8) + (-2) * 8 - min
	 * (1 - 8) + (-3) * 8 - aug
	 * (1 - 8) + (-4) * 8 - dim
	 */
    
    @Override
    public String translateToPhenotype(final AbstractInstrument individual) {
        ArrayList<Chord> notes = individual.getNotes();
        StringBuilder phenotype = new StringBuilder();
        for (int i = 0; i < notes.size(); i++) {
			
		}
    }

    // Method for translation number to note(when number is positive)
    private String NumberToNote(final int number, final int oct) {

        String[] oc = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] not = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        String result = null, resOct = null, resNote = null;
        resOct = oc[oct];
        resNote = not[number - 1];
        if (resNote != null && resOct != null) {
			result = " " + resNote + resOct;
		}
        return result;
    }

    // Method for translation number to chord(when number is negative)
    private String NumberToChord(final int number, final int octave) {
        String[] not = {"C", "D", "E", "F", "G", "A", "B"};
        String[] chord = {"maj", "min", "min", "maj", "maj", "min", "dim"};
        return not[number - 1] + Integer.toString(octave) + chord[number - 1];
    }

    private String NumberToDuration(final int n) {
        String[] duration = {"s", "i", "i.", "q", "", "", "", "h", "", "", "", "h.", "", "", "", "w"};

        return null;

    }


}
