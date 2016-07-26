package Genome;

import java.util.LinkedList;
import java.util.Random;

import Gene.Chord;
import Gene.GeneralChord;

public class PianoGenome extends AbstractInstrument       {
	static int NUM_NOTES_IN_OCTAVE = 12;
	static int PIANO_START_OCTAVE = 3;
	static int NUM_PIANO_OCTAVES = 4;
	
	static int NUM_OF_OCTAVES = 2;
	static int START_OCTAVE;
	
	public PianoGenome() {
		instrument_type = "Piano";
		Random rand = new Random();
		START_OCTAVE = rand.nextInt(4);
	}

	@Override
	public void count_fitness() {
		int fit = 1;
		
		/*Most common chords
		 * (Root note is C)
		 * I - V - vi - IV
		 * I - V - vi - iii
		 * I - vi - IV - V
		 * I - IV - vi - V 
		 * vi - V - IV -V
		 * i - V - IV - V
		 */
		//Karp
		{
			int num_of_right_progressions = 0;
			
			int[][] target_num = {{1, 5, 6, 4},
					{1, 5, 6,3},
					{1,6,4,5},
					{1, 4, 6, 5},
					{6,5,4,5},
					{1, 5,4,5}
					};
			
			//Target hash 
			LinkedList<Integer> target_hash = new LinkedList<>();
			
			for (int i = 0; i < target_num.length; i++) {
				int hash = 0;
					for (int j = 0; j < target_num[i].length; j++) {
						hash += target_num[i][j] * Math.pow(10, j + 1);
					}
				target_hash.add(hash);
			}			
			
			int num_elements = 1;
			int hash = 0;
			for(int i = 0; i < getNotes().size(); i++){
				num_elements = 1;
				hash = 0;
				hash += getNotes().get(i).getValue() * Math.pow(10, num_elements);
				
				for (int j = i + 1; j < getNotes().size(); j++) {
					if(num_elements == 4){
						break;
					}
					if(!getNotes().get(j).equals(getNotes().get(j - 1))){
						num_elements++;
						hash += getNotes().get(j).getValue() * Math.pow(10, num_elements);
					}
				}
				for (Integer integer : target_hash) {
					if(integer.equals(hash)){
						num_of_right_progressions++;
					}
				}
			}
			fit += num_of_right_progressions * 2;
		}
		
		
		//I, IV, V are most popular chords
		{
			int i = 0, iv = 0, v = 0;
			int num_chords = 0;
			for (Chord chord : getNotes()) {
				num_chords++;
				if(chord.getValue() == -1){
					i++;
				}else if(chord.getValue() == -4){
					iv++;
				}else if(chord.getValue() == -5){
					v++;
				}
			}
			if(i + iv + v >= num_chords/2){
				fit += 5;
			}
		}
		//A song usually ends on the I	
		if(getNotes().get(melody_length - 1).getValue() == -1){
			fit += 1;
		}
		
		//If there many jumps between the notes from 
		//one octave to another and back
		
		//If there too many notes that are too short
		
		//IF it not have leaps between notes bigger 
		//than a fifth
		
		//If it contain at least a minimum amount of
		//of a second (50% in the current implementation)
		
		//In major keys, diminished/augmented melodic intervals are NOT recommended.
		//Augmented 2nds and 4ths are NOT allowed.
		//6ths should be avoided.
		//Leading notes in dominant chords ALWAYS resolve onto the tonic of a tonic chord. (Bach didn't always do this, though!) 
		//In a cadential 6-4, the 4 resolves to 3 and the 6 resolves to 5.
		//Always choose a semitone step if one is available.
		//Try to make the spacing of each chord as even as possible
		//Never double the leading note of the scale.
		
		fitness = fit / 100;
	}

	@Override
	public AbstractInstrument reproduce(final AbstractInstrument parent2) {
		Random rand = new Random();
		int point_of_division = rand.nextInt(melody_length);
		
		PianoGenome child = new PianoGenome();
		child.getNotes().addAll(parent2.getNotes().subList(point_of_division, melody_length));
		child.getNotes().addAll(this.getNotes().subList(0, point_of_division));
		return child;
	}

	@Override
	public AbstractInstrument generateIndividual() {
		PianoGenome child = new PianoGenome();
		child.generateGenome();
		return child;
	}

	private void generateGenome() {
		for (int i = 0; i < melody_length; i++) {
			this.notes.add(new GeneralChord(NUM_OF_OCTAVES, START_OCTAVE));
		}
		
	}

}
