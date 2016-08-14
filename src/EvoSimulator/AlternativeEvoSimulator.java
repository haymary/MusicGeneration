package EvoSimulator;

import java.util.ArrayList;

import Evolution.Evolution;
import Evolution.Selection.AlternativeSelection;
import Genome.AbstractGenome;
import Genome.PianoGenome;

public class AlternativeEvoSimulator extends AbstractEvoSimulator{
	private final int SECOND_INSTR = 30;
	private final int THIRD_INSTR = 62;

	ArrayList<AbstractGenome> original_pop;

	public AlternativeEvoSimulator(final ArrayList<AbstractGenome> pop) {
		original_pop = getThreeBest(pop);
		instrumentsEvolution = new ArrayList<>();
		instrumentsEvolution.add(new Evolution(new PianoGenome()));
		instrumentsEvolution.add(new Evolution(new PianoGenome()));
	}

	private ArrayList<AbstractGenome> getThreeBest(final ArrayList<AbstractGenome> pop) {
		ArrayList<AbstractGenome> three_best = new ArrayList<>();
		three_best.addAll(pop.subList(0, 3));
		return three_best;
	}

	public void evoluteOtherInstruments() {
		final int MAX_NUMBER_GENERATIONS_FOR_OTHER_GENOMES = 20;
		
		for (AbstractGenome genome : original_pop) {
			for (int generation_num = 0; generation_num 
					< MAX_NUMBER_GENERATIONS_FOR_OTHER_GENOMES; generation_num++) {
				nextGeneration();
				AlternativeSelection selection = new AlternativeSelection(instrumentsEvolution);
				selection.setOriginalGenome(genome);
				selection.multiinstrumentSelection();
			}
		}
	}

	private void deleteOriginalPop() {
		instrumentsEvolution.remove(instrumentsEvolution.size() - 1);
	}

	private void addOriginalPop() {
		instrumentsEvolution.add((new Evolution(new PianoGenome())).setPop(original_pop));
	}


	public ArrayList<Evolution>  getInstrumentsEvolution() {
		addOriginalPop();
		return instrumentsEvolution;
	}
}
