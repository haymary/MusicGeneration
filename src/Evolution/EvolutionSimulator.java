package Evolution;

import Instruments.DrumsGenome;
import Instruments.PianoGenome;

public class EvolutionSimulator {

	Evolution pianoEvolution;
	Evolution drumsEvolution;
	private static final int MAX_NUMBER_GENERATIONS = 50;

	public EvolutionSimulator() {
		pianoEvolution = new Evolution(new PianoGenome());
		drumsEvolution = new Evolution(new DrumsGenome());
	}
	
	public void startSimulation(){
		while (!isAbsolutelyFitting() || pianoEvolution.getNumber_of_generations() < MAX_NUMBER_GENERATIONS) {
			pianoEvolution.produceNextGeneration();
			drumsEvolution.produceNextGeneration();
			//TODO
			//Method to check how they corelate with each other
		}
		GenomeParser p = new GenomeParser();
		p.translateToPhenotype(pianoEvolution.getPop());
	}

	private boolean isAbsolutelyFitting() {
		if(pianoEvolution.is_minimized()) {
			return true;
		}
		return false;
	}
	
	
}
