package Parser;

import static Evolution.Constants.GENERAL_NUM_NOTES;
import static Evolution.Constants.MELODY_LENGTH;

import java.util.ArrayList;
import java.util.Random;

import Gene.Chord;
import Genome.AbstractGenome;

public class ChordParser extends GenomeParser {


	public ChordParser() {}
	
    /*
     * 0 - R (rest)
	 * (1 - 7) - standard notes in octave (only C, D, E, F, G, A, B)
	 * -(1 - 7) + (0) * 7 - maj chords
	 * -(1 - 7) + (-1) * 7 - min
	 * -(1 - 7) + (-2) * 7 - aug
	 * -(1 - 7) + (-3) * 7 - dim
	 */

    @Override
	public String translateToPhenotype(final AbstractGenome individual) {
    	ArrayList<Chord> 	notes = individual.getNotes();
		StringBuilder 		phenotype = new StringBuilder();
        
		phenotype.append(CheckType(individual));
        
        Chord current_note = null;
        int duration = 0;
        
        for (int note_num = 0; note_num < MELODY_LENGTH; note_num++) {
			current_note = notes.get(note_num);
			for (int i = note_num + 1; i < MELODY_LENGTH; i++) {
					if(!notes.get(i).isContinuesLast()){
					note_num = i - 1;
					break;
				}
				duration++;	
			}
			phenotype.append(" ");
			phenotype.append(NumberToNoteOrChord(current_note.getValue(), 
													duration, 
													current_note.getOctave_num()));
		}

        return phenotype.toString();
    }

    // Method for translation number to note or chord
    private String NumberToNoteOrChord(int chord_value, final int duration, final int octave) {
        String[] notes = {"R", "C", "D", "E", "F", "G", "A", "B"};
        String[] chords = {"maj", "min", "aug", "dim"};
        StringBuilder result = new StringBuilder();
        if(chord_value >= 0){
        	result.append(notes[chord_value]);
        	if(chord_value != 0){
        		result.append(octave);
        	}
        }else{
        	chord_value = Math.abs(chord_value);
        	int note_num = chord_value % GENERAL_NUM_NOTES;
        	if(note_num == 0){
        		note_num = GENERAL_NUM_NOTES;
        	}
        	int chord_num = (chord_value - note_num) / GENERAL_NUM_NOTES;
        	result.append(notes[note_num]);
        	result.append(octave);
        	result.append(chords[chord_num]);
        }
        result.append(NumberToDuration(duration));
        
        return result.toString();

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
        if (n % 16 != 0 || n == 0) {
			result += duration[n % 16];
		}
        return result;

    }

    private String CheckType(final AbstractGenome individual) {
        String result = new String();
        Random rand = new Random(); //Random choose of instrument in JFugue
        if (individual.getInstrumentType() == "Piano") {
            result = "I" + 0;//Integer.toString(rand.nextInt(7));
        } else if (individual.getInstrumentType() == "Violin") {
            result = "I" + 40;//Integer.toString(rand.nextInt(7) + 40);
        }
        return result;
    }


}

