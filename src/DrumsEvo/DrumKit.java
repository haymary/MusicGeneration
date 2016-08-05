package DrumsEvo;

import Genome.DrumsGenome;

import java.util.HashSet;

/**
 * Created by DmitryRukavchuk on 26.07.2016.
 */


/* Probably will be necessary for group evaluation */
    
public class DrumKit {
    //Drum kit have only single occurrence of particular drum (we exclude exotic x2 Kick Drum kits)
    private HashSet<DrumsGenome> kit = new HashSet<>();

    public DrumKit(HashSet<DrumsGenome> kit) {
        this.kit = kit;
    }

    public float evaluateKit(){
        float fitness = 1;



        return fitness;
    }

    public HashSet<DrumsGenome> getKit() {
        return kit;
    }

}
