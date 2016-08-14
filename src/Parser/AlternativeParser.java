package Parser;

import static Service.Constants.MELODY_LENGTH;

import java.util.ArrayList;
import java.util.HashMap;

import Gene.Chord;
import Genome.AbstractGenome;
import Service.Constants;
import Service.NOTES;

public class AlternativeParser extends GenomeParser{
	private final int FIRST_INSTRUMENT 	= 1;
	private final int SECOND_INSTRUMENT = 30;
	private final int THIRD_INSTRUMENT 	= 62;
	
	private ArrayList<Chord> notes;
	private ArrayList<StringBuilder> instruments;
	private HashMap<Integer, int[]> intervals;

	public AlternativeParser() {
		intervals = new HashMap<>();
		int[] maj = {0, 4, 7};
		int[] min = {0, 3, 7};
		int[] aug = {0, 4, 8};
		int[] dim = {0, 3, 6};
		intervals.put(0,maj);
		intervals.put(1,min);
		intervals.put(2,aug);
		intervals.put(3,dim);
	}

	@Override
	public String translateToPhenotype(final AbstractGenome genome) {
		this.notes = genome.getNotes();
		
		newStringBuilderForEachInstrument();
		assignInstruments();
		splitChordsForInstrumrnts();
		
		return mergeInstruments();
	}

	private String mergeInstruments() {
		StringBuilder result = new StringBuilder();
		for (StringBuilder instrument : instruments) {
			result.append(instrument.toString());
			if(instruments.indexOf(instrument) != instrument.length() -1){
				result.append(";");
			}
		}
		return result.toString();
	}

	/**
	 * 
	 */
	private void newStringBuilderForEachInstrument() {
		instruments = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			instruments.add(new StringBuilder());
		}
	}

	
	private void splitChordsForInstrumrnts() {
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
			
			splitChord(current_note);
			addOctave(current_note);
			addDuration(duration);
			
			if(is_end){
				break;
			}
		}
		
	}

	private void addOctave(final Chord current_note) {
		for (StringBuilder instrument : instruments) {
			instrument.append(current_note.getOctave_num());
		}
	}

	private void addDuration(final int n) {
		for (StringBuilder instrument : instruments) {
			double duration = Constants.MIN_NOTE_DURATION * n;
			instrument.append("/");
			instrument.append(duration);
		}
	}


	private void splitChord(final Chord chord) {
		addSpace();
		
		if(chord.isChord()){
			instruments.get(0).append(getNoteName(chord.getRootNote()));
			instruments.get(1).append(getNoteOfChord(1, chord));
			instruments.get(2).append(getNoteOfChord(2, chord));
		}else{
			String note = getNoteName(chord.getRootNote());
			for (StringBuilder instrument : instruments) {
				instrument.append(note);
			}
		}
	}

	private void addSpace() {
		for (StringBuilder instrument : instruments) {
			instrument.append(" ");
		}
	}

	private String getNoteOfChord(final int note_number_in_chord, final Chord chord) {
		int note = chord.getRootNote() 
				+ getIntervalForNoteNumber(note_number_in_chord, chord);
		
		if(note != NOTES.REST.getValue()){
			if(note != Constants.GENERAL_NUM_NOTES){
				note = note % Constants.GENERAL_NUM_NOTES;
			}
		}
		return getNoteName(note);
	}

	private String getNoteName(final int note) {
		String[] string_notes = {"R", "C", "D", "E", "F", "G", "A", "B"};
		return string_notes[note];
	}

	private int getIntervalForNoteNumber(final int i, final Chord chord) {
		return getIntervalsOfChord(chord.getChord_type())[i];
	}

	private int[] getIntervalsOfChord(final int chord_type) {
		return intervals.get(chord_type);
	}

	private void assignInstruments() {
//		for (int i = 0; i < 3; i++) {
//			instruments.get(i).append("V" + i + " ");
//		}
		instruments.get(0).append("I" + FIRST_INSTRUMENT);
		instruments.get(1).append("I" + SECOND_INSTRUMENT);
		instruments.get(2).append("I" + THIRD_INSTRUMENT);
	}
}
