package FF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import Gene.Chord;

public class GeneralFF {

	public double countFF(final ArrayList<Chord> notes) {
		int melody_length = notes.size();
		int fit = 1;
		/*
		 * Penalize for not smooth steps
		 * */
		/*		
		 * I chords can appear anywhere in a progression
		 * ii chords lead to I, V, or vii° chords
		 * iii chords lead to I, ii, IV, or vi chords
		 * IV chords lead to I, ii, iii, V, or vii° chords
		 * V chords lead to I or vi chords
		 * vi chords lead to I, ii, iii, IV, or V chords
		 * vii° chords lead to I or iii chords
		 * i chords can appear anywhere in a progression
		 * ii° or ii chords lead to i, iii, V, v, vii°, or VII chords
		 * III or III+ chords lead to i, iv, IV, VI, #vi°, vii°, or VI chords
		 * iv or IV chords lead to i, V, v, vii°, or VII chords
		 * V or v chords lead to i, VI or #vi° chords
		 * VI or #vi° chords lead to i, III, III+, iv, IV, V, v, vii°, or VII chords
		 * vii° or VII chords lead to i chord
 		*/
		
		/*Most common chords
		 * (Key note is C)
		 * I - V - vi - IV
		 * I - V - vi - iii
		 * I - vi - IV - V
		 * I - IV - vi - V 
		 * vi - V - IV -V
		 * i - V - IV - V
		 */
		{
			int num_of_right_progressions = 0;

			LinkedList<Integer> commonProgressions = read_progressions();
			
			int num_elements = 1;
			int hash = 0;
			for(int i = 0; i < notes.size(); i++){
				num_elements = 1;
				hash = 0;
				hash += notes.get(i).getValue() * Math.pow(10, num_elements);
				
				for (int j = i + 1; j < notes.size(); j++) {
					if(num_elements == 4){
						break;
					}
					if(!notes.get(j).equals(notes.get(j - 1))){
						num_elements++;
						hash += notes.get(j).getValue() * Math.pow(10, num_elements);
					}
				}
				for (Integer integer : commonProgressions) {
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
			for (Chord chord : notes) {
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
		if(notes.get(melody_length - 1).getValue() == -1){
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
		
		return fit ;
	}

	/**
	 * @return
	 */
	private LinkedList<Integer> read_progressions() {
		LinkedList<Integer> target_hash = new LinkedList<>();
		File progressions = new File("Common Chord Progressions");
		Scanner sc = null;
		try {
			sc = new Scanner(progressions);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] line;
		while (sc.hasNextLine()) {
			line = sc.nextLine().split(" ");
			int hash = 0;
			int counter = 1;
			for (String string : line) {
				hash += Integer.parseInt(string) * Math.pow(10, counter);
			}
			target_hash.add(hash);
			
			
		}
		return target_hash;
	}
}
