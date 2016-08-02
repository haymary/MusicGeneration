package FFRules;

import static Evolution.Constants.GENERAL_NUM_NOTES;
import static Evolution.Constants.MELODY_LENGTH;

import java.util.ArrayList;

import Evolution.Constants;
import Gene.Chord;
import Gene.NOTES;


public class RandomRule extends ARule {
	static final int FINE_FORE_JUMPS = 10;

	public RandomRule(final ArrayList<Chord> notes) {
		this.notes = notes;
	}
	
	@Override
	public void run() {
		smoothMoveRule();
		averageLengthOfNotesRule();
	}
	
	private void smoothMoveRule(){
		int jumps_count = 0;
		
		for (int i = 0; i < MELODY_LENGTH - 1; i++) {
			if(Math.abs(
					getDifferebceBetweenNotes(notes.get(i), notes.get(i + 1))) 
					> GENERAL_NUM_NOTES){
				jumps_count++;
			}
		}
		count += - (jumps_count / MELODY_LENGTH) * FINE_FORE_JUMPS;
	}

	private int getDifferebceBetweenNotes(final Chord chord1, final Chord chord2) {
		int note1 = countNoteValue(chord1);
		int note2 = countNoteValue(chord2);
		if(note1 == NOTES.REST.getValue() 
				|| note2 == NOTES.REST.getValue()){
			return 0;
		}else{
			return Math.abs(note1 - note2);
		}
	}

	private int countNoteValue(final Chord chord1) {
		return getOctaveNumberFromStartOctave(chord1) * getNoteNumber(chord1);
	}

	private int getNoteNumber(final Chord chord) {
		int note_num;
		if (isNote(chord)){
			note_num = chord.getValue();
		}else{
			note_num = - (chord.getValue() % GENERAL_NUM_NOTES);
			if(note_num == 0){
				note_num = GENERAL_NUM_NOTES;
			}
		}
		return note_num;
	}

	private boolean isNote(final Chord chord) {
		return chord.getValue() >= 0;
	}

	private int getOctaveNumberFromStartOctave(final Chord chord) {
		return chord.getOctave_num() - Constants.GENERAL_START_OCTAVE;
	}
	
	

	private void averageLengthOfNotesRule(){
		int UNUSUAL_LENGTH_FINE = 2;
		int STANDART_LENGTH 	= 3;

		double 	punushment 	= 0;
		int 	note_length = 0;
		
		for (Chord chord : notes) {
			if(!chord.isContinuesLast()){
				punushment += UNUSUAL_LENGTH_FINE * 
						(Math.abs(STANDART_LENGTH - note_length));
				note_length = 0;
			}
			note_length++;
		}
		
		count -= (int) punushment;
	}
	
}
