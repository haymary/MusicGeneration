package Parser;

import Genome.AbstractGenome;
import Genome.DrumsGenome;

import java.util.HashMap;
import java.util.Map;

public class DrumsParser extends GenomeParser{

	//DO NOT CHANGE ORDER OF ENTRIES IN MAP. Key values are calculated almost manually
	public static final Map<Character, String> rhythmKit = new HashMap() {
		{
			this.put(Character.valueOf('.'), "Rs");
			this.put(Character.valueOf('O'), "[BASS_DRUM]s");
			this.put(Character.valueOf('>'), "[CLOSED_HI_HAT]s");
			this.put(Character.valueOf('^'), "[OPEN_HI_HAT]s");
			this.put(Character.valueOf('S'), "[ACOUSTIC_SNARE]s");
			this.put(Character.valueOf('C'), "[HAND_CLAP]s");
			this.put(Character.valueOf('*'), "[CRASH_CYMBAL_1]s");
			this.put(Character.valueOf('t'), "[HI_MID_TOM]s");
		}
	};

	private static final HashMap<Integer, Character> rhythmPhenotype = new HashMap() {
		{
			this.put(0, '.');
			this.put(1, 'O');
			this.put(2, '>');
			this.put(3, '^');
			this.put(4, 'S');
			this.put(5, 'C');
			this.put(6, '*');
			this.put(7, 't');
		}
	};

	@Override
	public
	String translateToPhenotype(final AbstractGenome individual) {
		String phenotype = "";
		String instrumentType = individual.getInstrumentType();
		int key = drumKeyForKitMap(instrumentType);
		for(int i = 0; i < individual.getNotes().size(); i++){
			if(individual.getNotes().get(i).getValue() == 1){
				phenotype+=rhythmPhenotype.get(key);
			} else phenotype+=rhythmPhenotype.get(0);
		}
		return phenotype;
	}

	private int drumKeyForKitMap(String instrumentType){
		int key = 0;
		switch (instrumentType){
			case "Kick":
				key = 1;
				break;
			case "Closed Hi-Hat":
				key = 2;
				break;
			case "Open Hi-Hat":
				key = 3;
				break;
			case "Snare":
				key = 4;
				break;
			case "Clap":
				key = 5;
				break;
			case "Crash":
				key = 6;
				break;
			case "Tom1":
				key = 7;
				break;
		}
		return key;
	}

}
