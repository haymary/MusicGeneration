package FFRules;

import java.util.List;

import Gene.Chord;

/**
 * Created by pisatel on 29.07.16.
 */
public class RuleOfFollowing extends ARule{
    private List<Integer> lead;
    private List<Integer> next;
    

    public RuleOfFollowing(final List<Chord> notes, final List<Integer> lead, final List<Integer> next) {
        this.notes       = notes;
        this.lead        = lead;
        this.next        = next;
        this.count       = 0;
        this.isActivated = false;
        this.type 		 = 0;
    }


    @Override
	public void run() {
        for (int i = 0; i < this.notes.size()-1; i++) {
            for (Integer lead_chord : this.lead) {
                if (this.notes.get(i).getValue() != lead_chord) {
                    continue;
                }
                for (Integer next_chord: this.next) {
                    if (this.notes.get(i+1).getValue() == next_chord) {
                        this.count++;
                        break;
                    }
                }
            }
        }
        if (count > 0) {
            isActivated = true;
        }
    }
}
