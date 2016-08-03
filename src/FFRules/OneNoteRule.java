package FFRules;

import static Evolution.Constants.MELODY_LENGTH;

import java.util.ArrayList;

import Gene.Chord;

public class OneNoteRule extends ARule {

	private int  	note;
	private boolean is_beneficial;
	private int cost;
	
	public OneNoteRule(final ArrayList<Chord> notes, final int note, final boolean is_beneficial, final int cost) {
		this.notes 			= notes;
		this.note 			= note;
		this.is_beneficial 	= is_beneficial;
		this.cost = cost;
		this.type = 1;
	}
	
	@Override
	public void run() {
		int note_count = 0;
		for (Chord chord : notes) {
			if(note == chord.getValue()){
				note_count++;
			}
		}
		
		count = (note_count / MELODY_LENGTH) * cost;
		if(!is_beneficial){
			count *= (-1);
		}
	}
}
