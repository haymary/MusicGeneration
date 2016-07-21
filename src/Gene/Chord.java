package Gene;

public abstract class Chord{
	
	public Chord() {
	}

	public void mutate() {
		generateChord();
	}

	protected abstract void generateChord() ;
	
	protected abstract void generateNote();

	public abstract String getChord();
}
