package FF.MultiFF;

import Genome.AbstractGenome;

public class AlternativeMultiInsrumentFF extends MultiInstrumentFF{

	AbstractGenome original_genome;
	
	public AlternativeMultiInsrumentFF() {
		int last_index = instruments.size() - 1;
		original_genome = instruments.get(last_index);
		instruments.remove(last_index);
	}
	
	@Override
	public double count_ff() {
		for (AbstractGenome abstractGenome : instruments) {
			differentDirectionsRule(original_genome.getNotes(), abstractGenome.getNotes());
		}
		
		//TODO:
		//Add rules
		return fit;
	}

}
