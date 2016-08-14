package Evolution.Selection;

import java.util.ArrayList;

import Evolution.Evolution;
import FF.MultiFF.FFFactory;
import Genome.AbstractGenome;


public class Selection extends AbstractSelection{

	public Selection(final ArrayList<Evolution> instrumentsEvolution, final String ff_type) {
		this.instrumentsEvolution = instrumentsEvolution;
		FFFactory ff_factory = new FFFactory();
		multiFF = ff_factory.getFFMethod(ff_type);
	}
	

	@Override
	protected double count_instrument_interaction(final int index_of_genome) {
		ArrayList<AbstractGenome> instruments = new ArrayList<>();
		for (Evolution evolution : instrumentsEvolution) {
			instruments.add(evolution.getGenomeByIndex(index_of_genome));
		}
		multiFF.setInstruments(instruments);
		return multiFF.count_ff();
	}
	
	@Override
	protected ArrayList<AbstractGenome> getInstruments(final int index_of_genome) {
		ArrayList<AbstractGenome> instruments = new ArrayList<>();
		for (Evolution evolution : instrumentsEvolution) {
			instruments.add(evolution.getGenomeByIndex(index_of_genome));
		}
		return instruments;
	}
}
