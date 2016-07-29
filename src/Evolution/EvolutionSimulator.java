package Evolution;

import static Evolution.Constants.MAX_NUMBER_GENERATIONS;
import static Evolution.Constants.POP_SIZE;

import java.util.ArrayList;

import FF.MultiInstrumentFF;
import Genome.AbstractInstrument;
import Genome.DrumsGenome;
import Genome.PianoGenome;
import Genome.ViolinGenome;

import javax.sound.midi.Soundbank;

public class EvolutionSimulator {
	MultiInstrumentFF multiFF;
	
	private ArrayList<Evolution> instrumentsEvolution;
	
	public EvolutionSimulator() {
		instrumentsEvolution = new ArrayList<>();
		//instrumentsEvolution.add(new Evolution(new PianoGenome()));
		//instrumentsEvolution.add(new Evolution(new ViolinGenome()));
		instrumentsEvolution.add(new Evolution(new DrumsGenome("Kick")));
		//instrumentsEvolution.add(new Evolution(new DrumsGenome("Snare")));
	}
	
	public void startSimulation(){
		for (int i = 0; i < MAX_NUMBER_GENERATIONS; i++) {
			nextGeneration();
			multiinstrumentSelection();
			//CHeck
			{
				System.out.println(i);
				for (Evolution evolution : instrumentsEvolution) {
					System.out.print(evolution.getInstrumentType() + ": ");
					for(int j = 0; j < evolution.getPop().size(); j++){
						System.out.println(evolution.getGenomeByIndex(j).toString());
					}

				}
	//			for (Evolution evolution : instrumentsEvolution) {
	//				evolution.popToPhenotype();
	//			}
	//			
	//			System.out.println(instrumentsEvolution.get(0).getPhenotype().get(0));
				
			}
			//TODO:
			//Save samples somehow
			
		}
		
	}

	private void multiinstrumentSelection(){
		for (int i = 0; i < POP_SIZE; i++) {
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
		
		double total_fit = sumInstrumentFitness(index_of_genome) * WEIGHT_OF_OWN_FITNESS
				+  count_instrument_interaction(index_of_genome) * WEIGHT_OF_INTERACTION;
				
		return total_fit;
	}

	/**
	 * @param index
	 * @return
	 */
	private double sumInstrumentFitness(final int index) {
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
