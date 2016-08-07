package Genome;

import DrumsEvo.PulseMask;

import java.util.Random;

import static Evolution.Constants.MELODY_LENGTH;
import static Evolution.Constants.NUM_OF_BARS;
import static Evolution.Constants.NUM_OF_NOTES_IN_BAR;

/**
 * Created by DmitryRukavchuk on 05.08.2016.
 */
public class DrumInstrument extends DrumsGenome {

    public DrumInstrument(){
        Random random = new Random();
        instrument_type = drumType.get(random.nextInt(5));
        pulseNum = NUM_OF_BARS * (random.nextInt(NUM_OF_NOTES_IN_BAR / 4) + 1);
        mask = new PulseMask(pulseNum, MELODY_LENGTH);
        generateInstPulseMask();
        generateGenome();
    }

    public DrumInstrument(final String instrument_type){
        Random random = new Random();
        this.instrument_type = instrument_type;
        pulseNum = NUM_OF_BARS * (random.nextInt(NUM_OF_NOTES_IN_BAR / 4) + 1);
        mask = new PulseMask(pulseNum, MELODY_LENGTH);
        generateInstPulseMask();
        generateGenome();
    }

    @Override
    protected DrumsGenome generateChild(String instrument_type) {
        return new DrumInstrument(instrument_type);
    }
}
