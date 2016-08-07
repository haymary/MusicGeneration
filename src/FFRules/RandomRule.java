package FFRules;

import static Evolution.Constants.GENERAL_NUM_NOTES;
import static Evolution.Constants.JUMPS_BETWEEN_NOTES_FINE;
import static Evolution.Constants.MELODY_LENGTH;
import static Evolution.Constants.NUM_OF_NOTES_IN_BAR;
import static Evolution.Constants.ONE_NOTE_REPETITION_FINE;
import static Evolution.Constants.STANDARD_LENGTH_OF_NOTE_END;
import static Evolution.Constants.STANDARD_LENGTH_OF_NOTE_START;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import Evolution.Constants;
import Gene.Chord;
import Gene.NOTES;

public class RandomRule extends ARule {

	public RandomRule(final ArrayList<Chord> notes) {
		this.notes = notes;
		this.type = 1;
	}
	
	@Override
	public void run() {
		smoothMoveRule();
		averageLengthOfNotesRule();
		noRepetitionOfOneNoteRule();
		longNoteInTheEndRule();
		barStartRepetitionRule();
		oneNoteRepetitionRule();
	}
	
	private void oneNoteRepetitionRule() {
		HashMap<Integer, Integer> notesMap = new HashMap<>();
		for (Chord chord : notes) {
			int key = chord.getValue();
			if(notesMap.containsKey(key)){
				notesMap.put(key, notesMap.get(key) + 1);
			}else{
				notesMap.put(key, 1);
			}
		}
		LinkedList<Integer> repetitions = new LinkedList<>();
		repetitions.addAll(notesMap.values());
		
		for (Integer num_repetitions : repetitions) {
			if(num_repetitions > MELODY_LENGTH / 3){
				count -= num_repetitions / MELODY_LENGTH * Constants.ONE_NOTE_REPETITION_FINE;
			}
		}
	}

	private void barStartRepetitionRule() {
		LinkedList<Integer> start_notes = new LinkedList<>();
		int iterator = 0;
		int num_repetitions = 0;
		while(iterator < MELODY_LENGTH){
			if(start_notes.contains(notes.get(iterator).getValue())){
				num_repetitions++;
			}else{
				start_notes.add(notes.get(iterator).getValue());
			}
			iterator += NUM_OF_NOTES_IN_BAR;
		}
		count += (num_repetitions / Constants.NUM_OF_BARS) 
				* Constants.BAR_BEGINNING_REPETITION_BONUS;
	}

	private void longNoteInTheEndRule() {
		int duration = getLastNoteDuration();
		if(duration > STANDARD_LENGTH_OF_NOTE_END){
			count += Constants.LONG_END_NOTE_BONUS;
		}
		if(duration == -1){
			count -= Constants.SHORT_END_FINE;
		}
	}

	private int getLastNoteDuration() {
		int last_note_index = getLastNoteIndex();
		int iterator = last_note_index - 1;
		int duration = 1;
		
		if(last_note_index == -1){
			return -1;
		}
		if(!notes.get(last_note_index).isContinuesLast()){
			return -1;
		}
		
		while(iterator > 0 && 
				notes.get(iterator).isContinuesLast()){
			duration++;
			iterator--;
		}
		return duration;
	}

	private int getLastNoteIndex() {
		int iterator = MELODY_LENGTH - 1;
		while(notes.get(iterator).getValue() == NOTES.REST.getValue()){
			iterator--;
			if(iterator < 1){
				return -1;
			}
		}
		return iterator;
	}

	private void noRepetitionOfOneNoteRule() {
		int current_chord = Integer.MAX_VALUE;
		int duration = 0;
		for (Chord chord : notes) {
			if(current_chord == chord.getValue()){
				duration++;
			}else{
				if(duration > NUM_OF_NOTES_IN_BAR / 2){
					count -= duration * ONE_NOTE_REPETITION_FINE;
				}
				current_chord = chord.getValue();
				duration = 1;
			}
		}
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
		count -=  (jumps_count / MELODY_LENGTH) * JUMPS_BETWEEN_NOTES_FINE;
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
		double 	punushment 	= 0;
		int 	note_length = 1;
		
		for (Chord chord : notes) {
			if(!chord.isContinuesLast()){
				punushment += Constants.UNUSUAL_LENGTH_FINE *  fineForBeingNotInStandartInterval(note_length);
				note_length = 0;
			}
			note_length++;
		}
		
		count -= (int) punushment;
	}

	private int fineForBeingNotInStandartInterval(final int note_length) {
		int diff = 0;
		if(note_length < STANDARD_LENGTH_OF_NOTE_START){
			diff = difference(STANDARD_LENGTH_OF_NOTE_START, note_length);
			
		}else if(note_length > STANDARD_LENGTH_OF_NOTE_END){
			diff = difference(STANDARD_LENGTH_OF_NOTE_END, note_length);
		}
		return diff;
	}

	private int difference(final int first, final int second) {
		return Math.abs(first - second);
	}
	
}
