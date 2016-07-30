package Parser;

import java.util.ArrayList;
import java.util.Random;

import Gene.Chord;
import Genome.AbstractInstrument;

public class ChordParser extends GenomeParser {

    public ChordParser() {
    }
    /*
     * 0 - R (rest)
	 * (1 - 7) - standard notes in octave (only C, D, E, F, G, A, B)
	 * -(1 - 7) + (0) * 7 - maj chords
	 * -(1 - 7) + (-1) * 7 - min
	 * -(1 - 7) + (-2) * 7 - aug
	 * -(1 - 7) + (-3) * 7 - dim
	 */

    @Override
	public String translateToPhenotype(final AbstractInstrument individual) {
        ArrayList<Chord> notes = individual.getNotes();
        int duration = 0;
        String result = new String();
        result = CheckType(individual);
        for (int i = 1; i < notes.size(); i++) {
            if (notes.get(i).is_continues_last()) {
                duration++;
            } else {
                result += " " + NumberToNoteOrChord(notes.get(i - 1).getValue(), duration, notes.get(i - 1).getOctave_num());
                duration = 0;
            }

        }

        return result;
    }

    // Method for translation number to note or chord
    private String NumberToNoteOrChord(final int number, final int duration, final int octave) {
        String[] not = {"C", "D", "E", "F", "G", "A", "B"};
        String[] chord = {"maj", "min", "aug", "dim"};
        String result = new String();
        if (number > 0 && number < 8) {
            result = not[number - 1] + Integer.toString(octave) + NumberToDuration(duration);
        } else if (number == 0) {
            result = "R" + NumberToDuration(duration);
        } else if (number < 0 && number > -29) {
            result = not[(-1 * number - 1) % 7]+ Integer.toString(octave) + chord[(-1 * number - 1) / 7] + NumberToDuration(duration).toUpperCase();
        }
        return result;

    }
    //Method for translating numbers of sixteenths to duration
    private String NumberToDuration(final int n) {
        String result = new String();
        if (n >= 16) {
			for (int i = 0; i < n / 16; i++) {
				result += "w";
			}
		}

        String[] duration = {"s", "i", "i.", "q", "qs", "qi", "qi.", "h", "hs", "hi", "hi.", "hq", "hqs", "hqi", "hqi.", "w"};
        if (n % 16 != 0) {
			result += duration[n % 16 - 1];
		}

        return result;

    }

    private String CheckType(final AbstractInstrument individual) {
        String result = new String();

        Random rand = new Random(); //Random choose of instrument in JFugue
        if (individual.getInstrumentType() == "Piano") {

            result = "I" + Integer.toString(rand.nextInt(7));
        } else if (individual.getInstrumentType() == "Violin") {

            result = "I" + Integer.toString(rand.nextInt(7) + 40);
        }
        return result;
    }

}