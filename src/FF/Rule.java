package FF;

import Gene.Chord;

import java.util.List;

/**
 * Created by pisatel on 29.07.16.
 */
public class Rule implements Runnable{
    private int           count;
    private List<Integer> lead;
    private List<Integer> next;
    private List<Chord>   notes;
    private boolean       isActivated = false;

    public Rule(List<Chord> notes, List<Integer> lead, List<Integer> next) {
        this.notes       = notes;
        this.lead        = lead;
        this.next        = next;
        this.count       = 0;
        this.isActivated = false;
    }


    public void run() {
        for (int i = 0; i < this.notes.size()-1; i++) {
            for (Integer lead_chord : this.lead) {
                if (this.notes.get(i).getValue() != lead_chord) {
                    continue;
                }
                for (Integer next_chord: this.next) {
                    if (this.notes.get(i+1).getValue() == next_chord) {
                        this.count++;
                    }
                }
            }
        }
        if (count > 0) {
            isActivated = true;
        }
    }

    public boolean isActivated() {
        return isActivated;
    }

    public int getPoints() {
        return count;
    }

}
