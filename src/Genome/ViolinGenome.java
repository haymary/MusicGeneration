package Genome;

public class ViolinGenome extends GeneralGenome{

	public ViolinGenome() {
		instrument_type = "Violin";
	}

	@Override
	protected GeneralGenome generateChild() {
		return new ViolinGenome();
	}
	
	
}
