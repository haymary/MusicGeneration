package Parser;

import java.util.ArrayList;
import java.util.Random;

import Gene.Chord;
import Genome.AbstractInstrument;
import Genome.PianoGenome;


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

    public String translateToPhenotype(final AbstractInstrument individual) {
        ArrayList<Chord> notes = individual.getNotes();
        int duration = 0;
        String result = new String();
        result = CheckType(individual);
        int k = 0;
        for (int i = 0; i < notes.size(); i++) {
            System.out.println(notes.get(i).getValue());
            System.out.println(notes.get(i).is_continues_last());

            if (notes.get(i).is_continues_last()) {
                duration++;
            } else {
                result += " " + NumberToNoteOrChord(notes.get(k).getValue(), duration, notes.get(k).getOctave_num());
               if(k==i) k=i+1;
                else k=i;
                duration = 0;
            }
            System.out.println(result);

        }
        result += " " + NumberToNoteOrChord(notes.get(k).getValue(), duration, notes.get(k).getOctave_num());

        return result;
    }

    // Method for translation number to note or chord
    private String NumberToNoteOrChord(int number, int duration, int octave) {
        String[] not = {"C", "D", "E", "F", "G", "A", "B"};
        String[] chord = {"maj", "min", "aug", "dim"};
        String result = new String();
        if (number > 0 && number < 8) {
            result = not[number - 1] + Integer.toString(octave) + NumberToDuration(duration);
        } else if (number == 0) {
            result = "R" + NumberToDuration(duration);
        } else if (number < 0 && number > -29) {
            int c=number%7==0?6:(-1*number)%7-1;
            result = not[c] + Integer.toString(octave) + chord[(-1 * number - 1) / 7] + NumberToDuration(duration).toUpperCase();
        }
        return result;

    }

    //Method for translating numbers of sixteenths to duration
    private String NumberToDuration(final int n) {
        String result = new String();
        if (n >= 16)
            for (int i = 0; i < n / 16; i++)
                result += "w";
        String[] duration = {"s", "i", "i.", "q", "qs", "qi", "qi.", "h", "hs", "hi", "hi.", "hq", "hqs", "hqi", "hqi.", "w"};
        if (n % 16 != 0 || n == 0)
            result += duration[n % 16];
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

    public static void main(String[] args) {
        PianoGenome p = new PianoGenome();
        ChordParser ch = new ChordParser();

        System.out.println(ch.translateToPhenotype(p.generateIndividual()));
    }

}

