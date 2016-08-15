package Genome;

public class PianoGenome extends GeneralGenome{
	
	public PianoGenome() {
		instrument_type = "Piano";
	}

	public PianoGenome(final int instrument_type_num2) {
		instrument_type_num = instrument_type_num2;
		instrument_type = "Piano";
	}
	
	@Override
	protected GeneralGenome generateChild() {
		return new PianoGenome();
	}

}
