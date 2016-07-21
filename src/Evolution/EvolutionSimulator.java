package Evolution;

import java.util.ArrayList;

import Genome.DrumsGenome;
import Genome.PianoGenome;

public class EvolutionSimulator {
	private static final int MAX_NUMBER_GENERATIONS = 50;

	ArrayList<Evolution> instrumentsEvolution;

	public EvolutionSimulator() {
		instrumentsEvolution = new ArrayList<>();
		instrumentsEvolution.add(new Evolution(new PianoGenome()));
		instrumentsEvolution.add(new Evolution(new DrumsGenome()));
	}
	
	public void startSimulation(){
		while (!isAbsolutelyFitting() 
				|| instrumentsEvolution.get(0).getNumber_of_generations() 
				< MAX_NUMBER_GENERATIONS) {
			for (Evolution evolution : instrumentsEvolution) {
				evolution.produceNextGeneration();
			}
			//TODO
			//Method to check how they corelate with each other
		}
		
		for (Evolution evolution : instrumentsEvolution) {
			evolution.popToPhenotype();
		}
		
		//TODO
		//Save samples somehow
		
	}

	private boolean isAbsolutelyFitting() {
		for (Evolution evolution : instrumentsEvolution) {
			if (evolution.is_minimized()){
				return true;
			}
		}
		return false;
	}
	
	
}
