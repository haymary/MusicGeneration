package Instruments;

public class PianoGenome extends AbstractInstrument       {
	//static Map<Integer, ArrayList<Integer>> chordMap;

	public PianoGenome() {
		instrument_type = "Piano";
		//fillChordMap();
	}

//	private void fillChordMap() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void count_fitness() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractInstrument reproduce(final AbstractInstrument parent2) {
		PianoGenome child = new PianoGenome();
		child.notes.addAll(this.notes.subList(0, melody_length / 2));
		child.notes.addAll(parent2.notes.subList(melody_length / 2, melody_length));
		return child;
	}

	@Override
	public AbstractInstrument generateIndividual() {
		// TODO Auto-generated method stub
		return null;
	}

	class PianoChord extends Chord{
		
		@Override
		protected void generate_chord() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
