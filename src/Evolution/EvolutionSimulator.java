package Evolution;

import java.util.ArrayList;

import Gene.Chord;
import Genome.PianoGenome;

public class EvolutionSimulator {
	final int MAX_NUMBER_GENERATIONS;
	
	ArrayList<Evolution> instrumentsEvolution;
	
	private int nun_generations = 0;

	public EvolutionSimulator() {
		MAX_NUMBER_GENERATIONS = Constants.MAX_NUMBER_GENERATIONS;
		instrumentsEvolution = new ArrayList<>();
		instrumentsEvolution.add(new Evolution(new PianoGenome()));
		//instrumentsEvolution.add(new Evolution(new DrumsGenome()));
	}
	
	public void startSimulation(){
		while ( nun_generations < MAX_NUMBER_GENERATIONS) {
			nun_generations++;
			//Produce next gen for each instrumrnt
			for (Evolution evolution : instrumentsEvolution) {
				evolution.produceNextGeneration();
				
			}
			//Selecting
			
		}
		
		for (Chord chord : instrumentsEvolution.get(0).getPop().get(0).getNotes()) {
			System.out.print(chord.getValue() + " ");
		}
		
		System.out.println();
		for (Evolution evolution : instrumentsEvolution) {
			evolution.popToPhenotype();
		}
		
		System.out.println(instrumentsEvolution.get(0).getPhenotype().get(0));
		//TODO
		//Save samples somehow
		
	}

}
