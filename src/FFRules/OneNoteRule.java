package FFRules;

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
	}
	
	@Override
	public void run() {
		int note_count = 0;
		for (Chord chord : notes) {
			if(note == chord.getValue()){
				note_count++;
			}
		}
		
		if(is_beneficial){
			count =  note_count * cost;
		}else{
			count  = - note_count * cost;
		}
	} 

}
