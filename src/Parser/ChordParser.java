package Parser;

import Gene.Chord;
import Genome.AbstractInstrument;
import Genome.PianoGenome;

import java.util.ArrayList;
import java.util.Random;

public class ChordParser extends GenomeParser {

    public ChordParser() {
    }


    @Override
    public String translateToPhenotype(final AbstractInstrument individual) {
        ArrayList<Chord> input = individual.getNotes();
        String result = "";

        //Loop for 1/16 duration
        int duration = 0;

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).is_continues_last()) {
                duration++;
            } else {
                if (duration > 4) {
                    if (duration > 8) {
                        if (duration > 12) {
                            if (input.get(i - 1).getValue() > 0) {
                                result += NumberToNote(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(12) + "-" + NumberToNote(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(duration - 12);
                                duration = 0;
                            } else if (input.get(i - 1).getValue() < 0) {
                                result += NumberToChord(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(12) + "-" + NumberToChord(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(duration - 12);
                                duration = 0;
                            } else {
                                result += "R" + NumberToDuration(12)+" R"+NumberToDuration(duration-12);
                                duration = 0;
                            }
                        } else {
                            if (input.get(i - 1).getValue() > 0) {
                                result += NumberToNote(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(8) + "-" + NumberToNote(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(duration - 8);
                                duration = 0;
                            } else if (input.get(i - 1).getValue() < 0) {
                                result += NumberToChord(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(8) + "-" + NumberToChord(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(duration - 8);
                                duration = 0;
                            } else {
                                result += "R" + NumberToDuration(8)+" R"+NumberToDuration(duration-8);
                                duration = 0;
                            }
                        }
                    }
                } else if (input.get(i - 1).getValue() > 0) {
                    result += NumberToNote(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(duration);
                    duration = 0;
                } else if (input.get(i - 1).getValue() < 0) {
                    result += NumberToChord(input.get(i - 1).getValue(), input.get(i - 1).getOctave_num()) + NumberToDuration(duration);
                    duration = 0;
                } else {
                    result += "R" + NumberToDuration(duration);
                    duration = 0;
                }
            }
        }
        int n=0;
        if (individual.getInstumentType()=="Piano") {
            Random rand =new Random();
             n=rand.nextInt(7);//Random choose of piano

        }
        result="I"+Integer.toString(n)+result;
        return result;
    }

    // Method for translation number to note(when number is positive)
    private String NumberToNote(int number, int oct) {

        String[] oc = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] not = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        String result = null, resOct = null, resNote = null;
        resOct = oc[oct];
        resNote = not[number - 1];
        if (resNote != null && resOct != null)
            result = " " + resNote + resOct;
        return result;
    }

    // Method for translation number to chord(when number is negative)
    private String NumberToChord(int number, int octave) {
        String[] not = {"C", "D", "E", "F", "G", "A", "B"};
        String[] chord = {"maj", "min", "min", "maj", "maj", "min", "dim"};
        return not[number - 1] + Integer.toString(octave) + chord[number - 1];
    }

    private String NumberToDuration(int n) {
        String[] duration = {"s", "i", "i.", "q", "", "", "", "h", "", "", "", "h.", "", "", "", "w"};

        return null;

    }


}
