package Service;


public final class Constants {

	public static final int 	POP_SIZE 					= 20;
	public static final int 	MAX_NUMBER_GENERATIONS 		= 200;
	
	public static final double 	MIN_NOTE_DURATION 			= 0.0625;
	public static final int 	NUM_OF_NOTES_IN_BAR 		= 16;
	public static final int 	NUM_OF_BARS 				= 4;
	public static final int 	NUM_VALUES_CHORDS 			= 8 * 5 + 1;
	
	public static final int 	MELODY_LENGTH = NUM_OF_NOTES_IN_BAR * NUM_OF_BARS;
	
	public static final int 	GENERAL_START_OCTAVE 		= 3;
	public static final int 	GENERAL_NUM_OCTAVES 		= 2;
	public static final int 	GENERAL_NUM_NOTES 			= 7;
	public static final int 	GENERAL_NUM_TYPES_CHORDS 	= 4;
	
	public static final int 	STANDARD_LENGTH_OF_NOTE_START = 2;
	public static final int 	STANDARD_LENGTH_OF_NOTE_END = 4;
	
	public static final double 	WEIGHT_OF_INSTRUMENTS_FITNESS 	= 1;
	public static final double 	WEIGHT_OF_INTERACTION 			= 1;
	
	//FINES
	public static final int 	JUMPS_BETWEEN_NOTES_FINE 		= 10;
	public static final int 	REST_FINE						= 20;
	public static final int		ONE_NOTE_REPETITION_FINE		= 20;
	public static final int 	SHORT_END_FINE 					= 20;
	public static final int 	UNUSUAL_LENGTH_FINE   			= 5;
	
	//BONUSES
	public static final int 	ENTRY_BONUS            	= 10;
	public static final int 	ONE_RULE_HOLDS_BONUS   	= 20;
	public static final int 	HALF_RULES_HOLDS_BONUS 	= 30;
	public static final int 	ALL_RULES_HOLDS_BONUS  	= 100;
	public static final int 	LONG_END_NOTE_BONUS 	= 50;
	public static final int 	BAR_BEGINNING_REPETITION_BONUS 			= 10;
	public static final int 	SMALL_VARIETY_IN_SECONDARY_VOICE_BONUS 	= 10;
	
	public static final boolean IS_FOUR_PART_HARMONY = false;
	public static final boolean IS_ALTERNATIVE_EVOLUTION = true;

//	public static final String 	ROOT_FOLDER = "C:\\1 ---- MINE-----\\GeneratedMusic\\NormalEvo";
//	public static final String 	ROOT_FOLDER = "C:\\1 ---- MINE-----\\GeneratedMusic\\FourPartEvo";
	public static final String 	ROOT_FOLDER = "C:\\1 ---- MINE-----\\GeneratedMusic\\AlternativeEvo";
	
}
	