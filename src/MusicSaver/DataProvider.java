package MusicSaver;

import java.util.LinkedList;

import Genome.DrumsGenome;
import Parser.DrumsParser;
import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;

import java.util.Map;


/**
 * Created by pisatel on 26.07.16.
 */
public class DataProvider {


    //BEGIN PRIVATE FIELDS
    private LinkedList<String> listOfDrums = new LinkedList<>();
    private LinkedList<String> listOfNotes = new LinkedList<>();
    private Pattern musicPattern = new Pattern();
    private Rhythm rhythm = new Rhythm(DrumsParser.rhythmKit);
    //END PRIVATE FIELDS



    //BEGIN #MAIN# PUBLIC METHODS
    public DataProvider(final LinkedList<String> listOfNotes, LinkedList<String> instrumentTypes) {
        for (int i = 0; i < listOfNotes.size(); i++) {
            if(DrumsGenome.drumType.containsValue(instrumentTypes.get(i))){
                listOfDrums.add(listOfNotes.get(i));
            } else this.listOfNotes.add(listOfNotes.get(i));
        }
        makePattern();
    }

    private void addRhythmToPattern() {
        for (String drum : listOfDrums) {
            rhythm.addLayer(drum);
        }
        Pattern rhythmPattern = new Pattern(rhythm);
        musicPattern.add(rhythmPattern);
    }

    public Pattern getMusicPattern() {
        return this.musicPattern;
    }

    //END #MAIN# PUBLIC METHODS



    //BEGIN #MAKE PATTERN FROM LIST# PRIVATE METHODS
    private void makePattern() {
    	int voice = 0;

        for (String singleInstrumentNotes : listOfNotes) {
            this.musicPattern.add("V" + voice + " " + singleInstrumentNotes);
            voice++;
        }
        addRhythmToPattern();
    }
    //END #MAKE PATTERN FROM LIST# PRIVATE METHODS

}
