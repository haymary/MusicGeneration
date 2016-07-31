package FF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import FFRules.ARule;
import FFRules.OneNoteRule;
import FFRules.RandomRule;
import FFRules.RuleOfFollowing;
import Gene.CHORDS;
import Gene.Chord;
import Gene.NOTES;

/**
 * Created by pisatel on 28.07.16.
 */
public class GeneralFF {

    private ArrayList<Chord> notes;
    private LinkedList<ARule> rules;

    private final int ENTRY_BONUS            = 1;
    private final int ONE_RULE_HOLDS_BONUS   = 10;
    private final int HALF_RULES_HOLDS_BONUS = 30;
    private final int ALL_RULES_HOLDS_BONUS  = 100;

    public GeneralFF(final ArrayList<Chord> notes) {
        this.notes = notes;
    }

    public int calculateScore() {
        this.initRulesArray();

        this.parallelizeProcess();

        int score = this.calculateTotalBonus();

        return score;

    }

    //1) If the rule is holds                    (+10)
    //2) The number of times that the rule holds (+1/per time)
    //3) If more than half rules holds           (+30)
    //4) If all rules holds                      (+100)
    //-The system is funded
    private int calculateTotalBonus() {
        int score = 0;
        int count_activated_rules = 0;
        for (ARule rule : this.rules) {
        	if(rule.getType() == 0){
        		if (rule.isActivated()) {
        			score += ONE_RULE_HOLDS_BONUS;
        			count_activated_rules++;
        			score += rule.getPoints() * ENTRY_BONUS;
        		}
        	}else{
        		score += rule.getPoints();
        	}
        }
        if (count_activated_rules > this.rules.size()/2) {
            score += HALF_RULES_HOLDS_BONUS;
        }
        if (count_activated_rules == this.rules.size()) {
            score += ALL_RULES_HOLDS_BONUS;
        }

        return score;
    }



    //BEGIN #HELPER# PRIVATE METHODS
    private void parallelizeProcess() {
        LinkedList<Thread> threads = new LinkedList<>();
        boolean isAlive;
        try {
            for (ARule rule : this.rules) {
                threads.add((new Thread(rule)));
            }
            for (Thread thread : threads) {
                thread.start();
            }

            //Wait until all the processes run
            while(true) {
                isAlive = false;
                for (Thread thread : threads) {
                    if (thread.isAlive()) {
                        isAlive = true;
                        break;
                    }
                }
                if (!isAlive) {
                    break;
                }
            }
        }
        catch (Exception ex){
            System.out.println("PARALLELIZE ERROR!");
        }
    }

    private void initRulesArray() {
    	rules = new LinkedList<>();
        this.rules.add(this.rule1());
        this.rules.add(this.rule2());
        this.rules.add(this.rule3());
        this.rules.add(this.rule4());
        this.rules.add(this.rule5());
        this.rules.add(this.rule6());
        this.rules.add(this.rule7());
        this.rules.add(this.rule8());
        this.rules.add(this.rule9());
        this.rules.add(this.rule10());
        this.rules.add(this.rule11());
        this.rules.add(this.rule12());
        this.rules.add(this.rule13());
        this.rules.add(this.rule14());
    }

    //END #HELPER# PRIVATE METHODS


    //BEGIN #RULES# PRIVATE METHODS

    /*
	 maj chords (I)
	 min (i)
	 aug (+)
	 dim (o)
	 */



    //ii chords lead to I, V, or vii° chords
    private RuleOfFollowing rule1() {
        List<Integer> lead = Arrays.asList(CHORDS.ii.getValue());

        List<Integer> next = Arrays.asList(CHORDS.I.getValue(),
                                            CHORDS.V.getValue(),
                                            CHORDS.viiD.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //iii chords lead to I, ii, IV, or vi chords
    private RuleOfFollowing rule2() {
        List<Integer> lead = Arrays.asList(CHORDS.iii.getValue());

        List<Integer> next = Arrays.asList(CHORDS.I.getValue(),
                                            CHORDS.ii.getValue(),
                                            CHORDS.IV.getValue(),
                                            CHORDS.vi.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //IV chords lead to I, ii, iii, V, or vii° chords
    private RuleOfFollowing rule3() {
        List<Integer> lead = Arrays.asList(CHORDS.IV.getValue());

        List<Integer> next = Arrays.asList(CHORDS.I.getValue(),
                                            CHORDS.ii.getValue(),
                                            CHORDS.iii.getValue(),
                                            CHORDS.V.getValue(),
                                            CHORDS.viiD.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //V chords lead to I or vi chords
    private RuleOfFollowing rule4() {
        List<Integer> lead = Arrays.asList(CHORDS.V.getValue());

        List<Integer> next = Arrays.asList(CHORDS.I.getValue(),
                                            CHORDS.vi.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //vi chords lead to I, ii, iii, IV, or V chords
    private RuleOfFollowing rule5() {
        List<Integer> lead = Arrays.asList(CHORDS.vi.getValue());

        List<Integer> next = Arrays.asList(CHORDS.I.getValue(),
                                            CHORDS.ii.getValue(),
                                            CHORDS.iii.getValue(),
                                            CHORDS.IV.getValue(),
                                            CHORDS.V.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //vii° chords lead to I or iii chords
    private RuleOfFollowing rule6() {
        List<Integer> lead = Arrays.asList(CHORDS.viiD.getValue());

        List<Integer> next = Arrays.asList(CHORDS.I.getValue(),
                                            CHORDS.iii.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //ii° or ii chords lead to i, iii, V, v, vii°, or VII chords
    private RuleOfFollowing rule7() {
        List<Integer> lead = Arrays.asList(CHORDS.viiD.getValue(),
                                            CHORDS.ii.getValue());

        List<Integer> next = Arrays.asList(CHORDS.i.getValue(),
                                            CHORDS.iii.getValue(),
                                            CHORDS.V.getValue(),
                                            CHORDS.v.getValue(),
                                            CHORDS.viiD.getValue(),
                                            CHORDS.VII.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //III or III+ chords lead to i, iv, IV, VI, vii°, or VI chords
    private RuleOfFollowing rule8() {
        List<Integer> lead = Arrays.asList(CHORDS.III.getValue(),
                                            CHORDS.IIIA.getValue());

        List<Integer> next = Arrays.asList(CHORDS.i.getValue(),
                                            CHORDS.iv.getValue(),
                                            CHORDS.IV.getValue(),
                                            CHORDS.VI.getValue(),
                                            CHORDS.viiD.getValue(),
                                            CHORDS.VI.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //iv or IV chords lead to i, V, v, vii°, or VII chords
    private RuleOfFollowing rule9() {
        List<Integer> lead = Arrays.asList(CHORDS.iv.getValue(),
                                            CHORDS.IV.getValue());

        List<Integer> next = Arrays.asList(CHORDS.i.getValue(),
                                            CHORDS.V.getValue(),
                                            CHORDS.v.getValue(),
                                            CHORDS.viiD.getValue(),
                                            CHORDS.VII.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //V or v chords lead to i, VI chords
    private RuleOfFollowing rule10() {
        List<Integer> lead = Arrays.asList(CHORDS.V.getValue(),
                                            CHORDS.v.getValue());

        List<Integer> next = Arrays.asList(CHORDS.i.getValue(),
                                            CHORDS.VI.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //VI or #vi° chords lead to i, III, III+, iv, IV, V, v, vii°, or VII chords
    private RuleOfFollowing rule11() {
        List<Integer> lead = Arrays.asList(CHORDS.VI.getValue());

        List<Integer> next = Arrays.asList(CHORDS.i.getValue(),
                                            CHORDS.III.getValue(),
                                            CHORDS.IIIA.getValue(),
                                            CHORDS.iv.getValue(),
                                            CHORDS.IV.getValue(),
                                            CHORDS.V.getValue(),
                                            CHORDS.v.getValue(),
                                            CHORDS.viiD.getValue(),
                                            CHORDS.VII.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }

    //vii° or VII chords lead to i chord
    private RuleOfFollowing rule12() {
        List<Integer> lead = Arrays.asList(CHORDS.viiD.getValue(),
                                            CHORDS.VII.getValue());

        List<Integer> next = Arrays.asList(CHORDS.i.getValue());
        return new RuleOfFollowing(this.notes, lead, next);
    }
    
    //not too much rest
    private OneNoteRule rule13(){
    	int note = NOTES.REST.getValue();
    	int cost = 1;
		return new OneNoteRule(this.notes, note, false, cost);
    }
    
    //smooth moves
    //unusual note length
    private RandomRule rule14() {
		return new RandomRule(notes);
	}

    //END #RULES# PUBLIC METHODS


}


