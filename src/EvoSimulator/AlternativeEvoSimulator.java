package EvoSimulator;

import java.util.ArrayList;

import Evolution.Evolution;
import Evolution.Selection.AlternativeSelection;
import Genome.AbstractGenome;
import Genome.PianoGenome;

public class AlternativeEvoSimulator extends AbstractEvoSimulator{
	private final int MAX_NUMBER_GENERATIONS_FOR_OTHER_GENOMES = 20;
	private final int SECOND_INSTR 	= 30;
	private final int THIRD_INSTR 	= 62;

	ArrayList<Evolution> result;
	ArrayList<AbstractGenome> original_pop;
	ArrayList<AbstractGenome> second_voice;
	ArrayList<AbstractGenome> third_voice;
	

	public AlternativeEvoSimulator(final ArrayList<AbstractGenome> pop) {
		original_pop = getThreeBest(pop);
		second_voice = new ArrayList<>();
		third_voice = new ArrayList<>();
		result = new ArrayList<>();
		result.add(new Evolution(new PianoGenome()));
		result.add(new Evolution(new PianoGenome(SECOND_INSTR)));
		result.add(new Evolution(new PianoGenome(THIRD_INSTR)));
	}

	private ArrayList<AbstractGenome> getThreeBest(final ArrayList<AbstractGenome> pop) {
		ArrayList<AbstractGenome> three_best = new ArrayList<>();
		three_best.addAll(pop.subList(0, 3));
		return three_best;
	}

	public void evoluteOtherInstruments() {
		for (AbstractGenome genome : original_pop) {
			startNewEvolution();
			for (int generation_num = 0; generation_num 
					< MAX_NUMBER_GENERATIONS_FOR_OTHER_GENOMES; generation_num++) {
				nextGeneration();
				selection(genome);
			}
			saveBestBackVoices();
		}
	}

	private void saveBestBackVoices() {
		second_voice.add(getFirstGenomeFromInstrument(0));
		third_voice.add(getFirstGenomeFromInstrument(1));
	}

	private AbstractGenome getFirstGenomeFromInstrument(final int instr_num) {
		return instrumentsEvolution.
				get(instr_num).
				getPop().
				get(0);
	}

	private void selection(final AbstractGenome genome) {
		AlternativeSelection selection = new AlternativeSelection(
				instrumentsEvolution, genome);
		selection.multiinstrumentSelection();
	}

	private void startNewEvolution() {
		instrumentsEvolution = new ArrayList<>();
		instrumentsEvolution.add(new Evolution(new PianoGenome(SECOND_INSTR)));
		instrumentsEvolution.add(new Evolution(new PianoGenome(THIRD_INSTR)));
	}

	public ArrayList<Evolution>  getInstrumentsEvolution() {
		constructEvolutions();
		return result;
	}

	private void constructEvolutions() {
		result.get(0).setPop(original_pop);
		result.get(1).setPop(second_voice);
		result.get(2).setPop(third_voice);
	}
}
