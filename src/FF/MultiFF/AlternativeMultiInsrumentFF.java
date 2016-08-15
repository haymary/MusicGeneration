package FF.MultiFF;

import static Service.Constants.MELODY_LENGTH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import Gene.Chord;
import Genome.AbstractGenome;
import Service.Constants;

public class AlternativeMultiInsrumentFF extends MultiInstrumentFF{

	AbstractGenome original_genome;
	
	public AlternativeMultiInsrumentFF() {
	}
	
	@Override
	public void setInstruments(final ArrayList<AbstractGenome> instruments){
		this.instruments = instruments;
		int last_index = instruments.size() - 1;
		original_genome = instruments.get(last_index);
		instruments.remove(last_index);
	}
	
	@Override
	public double count_ff() {
		//First should be interesting 
		//and go with base in different directions
		differentDirectionsRule(original_genome.getNotes(), 
				instruments.get(0).getNotes());
		
		//Second is boring with lots of long notes
		boringRule(instruments.get(1).getNotes());

		return fit;
	}

	private void boringRule(final ArrayList<Chord> notes) {
		/*
		 * Boring:
		 * 1) No big intervals
		 * 2) A lot of repetition
		 * 3) Long durations
		 * 4) Not a lot of different notes
		 */
		//smallIntervalsRule(voice);
		repetitionRule(notes);
		bigDurationsRule(notes);
	}

	private void smallVarietyRule(final HashMap<Integer,Integer> notesMap) {
		fit += Constants.NUM_VALUES_CHORDS / notesMap.size() 
				* Constants.SMALL_VARIETY_IN_SECONDARY_VOICE_BONUS;
	}

	private void bigDurationsRule(final ArrayList<Chord> notes) {
		int 	note_length = 1;
		
		for (Chord chord : notes) {
			if(!chord.isContinuesLast()){
				fit += Constants.UNUSUAL_LENGTH_FINE *  note_length;
				note_length = 0;
			}
			note_length++;
		}
	}

	private void repetitionRule(final ArrayList<Chord> notes) {
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
				fit += num_repetitions / MELODY_LENGTH * Constants.ONE_NOTE_REPETITION_FINE;
			}
		}
		smallVarietyRule(notesMap);
	}

	private void smallIntervalsRule(final ArrayList<Chord> notes) {
	}

}
