package Parser;

import static Evolution.Constants.MELODY_LENGTH;

import java.util.ArrayList;
import java.util.Random;

import Evolution.Constants;
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
        boolean is_end = false;
        for (int note_num = 0; note_num < MELODY_LENGTH; note_num++) {
        	Chord current_note = notes.get(note_num);
			int duration = 1;
			for (int i = note_num + 1; i < MELODY_LENGTH; i++) {
				if(notes.get(i).isContinuesLast()){
					duration++;	
					if(i == MELODY_LENGTH - 1){
						is_end = true;
					}
				}else{
					note_num = i - 1;
					break;
				}
			}
			phenotype.append(" ");
			phenotype.append(NumberToNoteOrChord(current_note, 
													duration));
			if(is_end){
				break;
			}
		}

        return phenotype.toString();
    }

    // Method for translation number to note or chord
    private String NumberToNoteOrChord(final Chord chord, final int duration) {
        String[] notes = {"R", "C", "D", "E", "F", "G", "A", "B"};
        String[] chords = {"maj", "min", "aug", "dim"};
        StringBuilder result = new StringBuilder();
        
        result.append(notes[chord.getRootNote()]);
        if(chord.isNotRest()){
        	result.append(chord.getOctave_num());
        	if(chord.isChord()){
        		result.append(chords[chord.getChord_type()]);
        	}
        }
        result.append(NumberToDuration(duration));
        
        return result.toString();

    }

    //Method for translating numbers of sixteenths to duration
    private String NumberToDuration(final int n) {
    	StringBuilder result = new StringBuilder();
//    	for (int i = 0; i < n; i++) {
//			result.append("s");
//		}
    	double duration = Constants.MIN_NOTE_DURATION * n;
    	result.append("/");
    	result.append(duration);
        return result.toString();

    }

    private String CheckType(final AbstractGenome individual) {
        String result = new String();
        Random rand = new Random(); //Random choose of instrument in JFugue
        if (individual.getInstrumentType() == "Piano") {
            result = "I" + 0;
        } else if (individual.getInstrumentType() == "Violin") {
            result = "I" + 40;
        }
        return result;
    }


}

