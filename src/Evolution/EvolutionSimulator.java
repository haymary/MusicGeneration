package Evolution;

import java.util.ArrayList;

import FF.MultiInstrumentFF;
import Gene.Chord;
import Genome.AbstractInstrument;
import Genome.PianoGenome;

public class EvolutionSimulator {
	final int MAX_NUMBER_GENERATIONS;
	final int POP_SIZE;
	MultiInstrumentFF multiFF;
	
	private ArrayList<Evolution> instrumentsEvolution;
	
	public EvolutionSimulator() {
		MAX_NUMBER_GENERATIONS = Constants.MAX_NUMBER_GENERATIONS;
		POP_SIZE = Constants.POP_SIZE;
		
		instrumentsEvolution = new ArrayList<>();
		instrumentsEvolution.add(new Evolution(new PianoGenome()));
		//instrumentsEvolution.add(new Evolution(new DrumsGenome()));
	}
	
	public void startSimulation(){
		for (int i = 0; i < MAX_NUMBER_GENERATIONS; i++) {
			nextGeneration();
			multiinstrumentSelection();
			
			//TODO:
			//Save samples somehow
			
		}
		
		//CHeck
		{
			for (Chord chord : instrumentsEvolution.get(0).getPop().get(0).getNotes()) {
				System.out.print(chord.getValue() + " ");
			}
			
			System.out.println();
			for (Evolution evolution : instrumentsEvolution) {
				evolution.popToPhenotype();
			}
			
			System.out.println(instrumentsEvolution.get(0).getPhenotype().get(0));
			
		}
	}

	private void multiinstrumentSelection(){
		for (int i = 0; i < POP_SIZE * POP_SIZE; i++) {
			double total_fit = get_total_fitness(i);
			for (Evolution evolution : instrumentsEvolution) {
				evolution.getGenomeByIndex(i).setFitness(total_fit);
			}
		}
		for (Evolution evolution : instrumentsEvolution) {
			evolution.selection();
		}
	}
	
	private void nextGeneration() {
		for (Evolution evolution : instrumentsEvolution) {
			evolution.produceNextGeneration();
		}
	}

	private double get_total_fitness(final int index_of_genome) {
		double WEIGHT_OF_OWN_FITNESS = 1;
		double WEIGHT_OF_INTERACTION = 1;

		if(instrumentsEvolution.size() == 1) {
			return instrumentsEvolution.get(0).getGenomeByIndex(index_of_genome).getFitness();
		} 
		
		double total_fit = sumInstrunmentFitness(index_of_genome) * WEIGHT_OF_OWN_FITNESS 
				+  count_instrument_interaction(index_of_genome) * WEIGHT_OF_INTERACTION;
				
		return total_fit;
	}

	/**
	 * @param index
	 * @return
	 */
	private double sumInstrunmentFitness(final int index) {
		double total_fit = 0;
		for (Evolution evolution : instrumentsEvolution) {
			total_fit += evolution.getGenomeByIndex(index).getFitness();
		}
		return total_fit;
	}

	private double count_instrument_interaction(final int index_of_genome) {
		ArrayList<AbstractInstrument> instruments = new ArrayList<>();
		for (Evolution evolution : instrumentsEvolution) {
			instruments.add(evolution.getGenomeByIndex(index_of_genome));
		}
		multiFF = new MultiInstrumentFF(instruments);
		return multiFF.count_ff();
	}

//	private class SelectionNode implements Comparable<SelectionNode>{
//		private int fitness;
//		private int index;
//		
//		public SelectionNode(final int fitness, final int index) {
//			this.fitness = fitness;
//			this.index = index;
//		}
//		
//		@Override
//		public int compareTo(final SelectionNode o) {
//			if (o.getFitness() > this.fitness) {
//				return -1;
//			}
//			if (o.getFitness() == this.fitness) {
//				return 0;
//			}
//			return 1;
//		}
//
//		public int getFitness() {
//			return fitness;
//		}
//
//		public void setFitness(final int fitness) {
//			this.fitness = fitness;
//		}
//
//		public int getIndex() {
//			return index;
//		}
//
//		public void setIndex(final int index) {
//			this.index = index;
//		}
//		
//	}
}
