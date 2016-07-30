package MusicSaver;

import java.io.File;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

/**
 * Created by pisatel on 26.07.16.
 */

public class MusicSaver {
    public void saveToMidi(final DataProvider dataProvider, final String filename) {

        MidiFileManager saveManager = new MidiFileManager();
        Pattern music = dataProvider.getMusicPattern();

        try {
            saveManager.savePatternToMidi(music, new File(filename));
        }
        catch (Exception ex) {
            System.out.print(ex);
        }

    }


}
