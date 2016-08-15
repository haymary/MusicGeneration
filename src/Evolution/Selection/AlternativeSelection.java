package Evolution.Selection;

import java.util.ArrayList;

import Evolution.Evolution;
import FF.MultiFF.AlternativeMultiInsrumentFF;
import Genome.AbstractGenome;

public class AlternativeSelection extends AbstractSelection{

	private AbstractGenome 	original_genome;
	
	public AlternativeSelection(final ArrayList<Evolution> instrumentsEvolution, 
			final AbstractGenome genome) {
		this.instrumentsEvolution = instrumentsEvolution;
		setOriginalGenome(genome);
		multiFF = new AlternativeMultiInsrumentFF();
		multiinstrumentSelection();
	}

	public void setOriginalGenome(final AbstractGenome genome) {
		original_genome = genome;
	}

	@Override
	protected ArrayList<AbstractGenome> getInstruments(final int index_of_genome) {
		ArrayList<AbstractGenome> instruments = new ArrayList<>();
		for (Evolution evolution : instrumentsEvolution) {
			instruments.add(evolution.getGenomeByIndex(index_of_genome));
		}
		instruments.add(original_genome);
		return instruments;
	}

}
