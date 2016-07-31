package FFRules;

import java.util.List;

import Gene.Chord;

public abstract class ARule implements Runnable{

	protected int 			type;
	protected List<Chord>   notes;
	protected int           count;
	protected boolean       isActivated = false;
    
	public boolean isActivated() {
        return isActivated;
    }

	public int getPoints() {
        return count;
    }

	public int getType() {
		return type;
	}
}
