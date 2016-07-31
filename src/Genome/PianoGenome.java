package Genome;

public class PianoGenome extends GeneralGenome       {
	
	public PianoGenome() {
		instrument_type = "Piano";
	}

	@Override
	protected GeneralGenome generateChild() {
		
		return new PianoGenome();
	}


}
