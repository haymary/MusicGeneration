package MusicSaver;

import java.util.LinkedList;

import org.jfugue.pattern.Pattern;


/**
 * Created by pisatel on 26.07.16.
 */
public class DataProvider {

    //BEGIN PRIVATE FIELDS
    private LinkedList<String> listOfNotes = new LinkedList<>();
    private Pattern musicPattern= new Pattern();
    //END PRIVATE FIELDS



    //BEGIN #MAIN# PUBLIC METHODS
    public DataProvider(final LinkedList<String> listOfNotes) {
        setListOfNotes(listOfNotes);
    }
    public Pattern getMusicPattern() {
        return this.musicPattern;
    }

    public void setListOfNotes(final LinkedList<String> listOfNotes) {
        this.listOfNotes = listOfNotes;
        makePatternFromNotesList();
    }
    //END #MAIN# PUBLIC METHODS



    //BEGIN #MAKE PATTERN FROM LIST# PRIVATE METHODS
    private void makePatternFromNotesList() {
        for (String singleInstrumentNotes : this.listOfNotes) {
            this.musicPattern.add(singleInstrumentNotes);
        }
    }
    //END #MAKE PATTRN FROM LIST# PRIVATE METHODS

}
