package Jfugue;

/**
 * Created by DmitryRukavchuk on 17.07.2016.
 */

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;

public class TranslatorDemo {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {

        /*Key-type midi pattern. V: 0 - 15 midi channel; I[Instrument] according to GeneralMidi standard
        E4q - 4 octave E note quarter length; | bar splitter;
        ...", 2) - number of repetitions of pattern in midi (2 bars x 2 = 4 bars in file)
        Rw - rest full bar length
         */
        Pattern p1 = new Pattern()
                .add("V0 I[Piano] E4q  C4h. | E4q C4h.", 1) //2 bars pattern; 4 total
                .add("V1 I[Flute] Rw     | G4majQQQ  C5majQ", 1); //G4majQQQ - maj accord base note G4; 3/4 bar length

        //2 bars sequence * 4 beats; . - rest is 1/16 AND 1/8 length (O..oO...); "O" is 1/8 while "o" is 1/16 rest + 1/16 kick; symbol map is below
        Rhythm rhythm2bars = new Rhythm()
                .addLayer("O..oO...O..oOO..")
                .addLayer("..S...S...S...S.")
                .addLayer("````````````````")
                .addLayer("...............+")
                .setLength(1); //sets number of repetitions in midi-file and playback

        p1.add(rhythm2bars);
        p1.setTempo(110); //sets tempo for all instruments in pattern (sets tempo for midi-file)

        /* Plays pattern with soundcard synthesiser in realtime
        Player player = new Player();
        player.play(p1);
        */

        /* Generates midi-file with given filename in relative root directory  .mid is a must!
         */
        MidiFileManager.savePatternToMidi(p1, new File("Piano+Flute+Drums Score.mid"));

        // loads pattern from midi-file in relative root directory
        //Pattern loadedPattern = MidiFileManager.loadPatternFromMidi(new File("PUT YOUR MIDI FILENAME HERE"));
        p1.getPattern();

    }
}

/* DrumKit HashMap; Note duration: s - 1/16; i - 1/8; "O..oO..." 1 bar KickDrum pattern.


{
        this.put(Character.valueOf('.'), "Ri");
        this.put(Character.valueOf('O'), "[BASS_DRUM]i");
        this.put(Character.valueOf('o'), "Rs [BASS_DRUM]s");
        this.put(Character.valueOf('S'), "[ACOUSTIC_SNARE]i");
        this.put(Character.valueOf('s'), "Rs [ACOUSTIC_SNARE]s");
        this.put(Character.valueOf('^'), "[PEDAL_HI_HAT]i");
        this.put(Character.valueOf('`'), "[PEDAL_HI_HAT]s Rs");
        this.put(Character.valueOf('*'), "[CRASH_CYMBAL_1]i");
        this.put(Character.valueOf('+'), "[CRASH_CYMBAL_1]s Rs");
        this.put(Character.valueOf('X'), "[HAND_CLAP]i");
        this.put(Character.valueOf('x'), "Rs [HAND_CLAP]s");
}
*/