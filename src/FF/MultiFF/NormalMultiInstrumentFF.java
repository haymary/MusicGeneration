package FF.MultiFF;

import java.util.ArrayList;

import Gene.Chord;

public class NormalMultiInstrumentFF extends MultiInstrumentFF{

	public NormalMultiInstrumentFF() {
	}

	@Override
	public double count_ff() {
		ArrayList<Chord> leadNotes = instruments.get(0).getNotes();
		ArrayList<Chord> backNotes = instruments.get(1).getNotes();
		differentDirectionsRule(leadNotes, backNotes);
		return fit;
	}

	
}
