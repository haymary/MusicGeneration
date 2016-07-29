package Test;

import Genome.DrumsGenome;

/**
 * Created by DmitryRukavchuk on 29.07.2016.
 */
public class DrumIndvEvo {
    public static void main(String[] args) {
        DrumsGenome s1 = new DrumsGenome("Snare");
        DrumsGenome s2 = new DrumsGenome("Snare");
        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }
}
