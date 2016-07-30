package Evolution;

import static Evolution.Constants.MAX_NUMBER_GENERATIONS;
import static Evolution.Constants.POP_SIZE;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import FF.MultiInstrumentFF;
import Genome.AbstractGenome;
import Genome.PianoGenome;
import Genome.ViolinGenome;
import MusicSaver.DataProvider;
import MusicSaver.MusicSaver;

public class EvolutionSimulator {
	MultiInstrumentFF multiFF;
	String rootFolder;
	
	private ArrayList<Evolution> instrumentsEvolution;
	
	public EvolutionSimulator() {
		instrumentsEvolution = new ArrayList<>();
		instrumentsEvolution.add(new Evolution(new PianoGenome()));
		instrumentsEvolution.add(new Evolution(new ViolinGenome()));
		
		rootFolder = "Music";
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
					System.out.println(evolution.getGenomeByIndex(0).toString() + "*");
				}
				
				for (Evolution evolution : instrumentsEvolution) {
					evolution.popToPhenotype();
				}
	     		saveGenerationSamples(i);
			}
		}
		
	}

	private void saveGenerationSamples(final int i) {
		for (int j = 0; j < POP_SIZE;j++) {
			LinkedList<String> song = new LinkedList<>();
			for (Evolution evolution : instrumentsEvolution) {
				song.add(evolution.getPhenotype().get(j));
			}
			DataProvider provider = new DataProvider(song);
		    MusicSaver saver = new MusicSaver();
		    saver.saveToMidi(provider, getFileName(i, j));
		}
	}

	private String getFileName(final int i, final int j) {
		String pathname = rootFolder + File.separator + i;  
        File newDir = new File(pathname);
        newDir.mkdirs();
        return pathname + File.separator + j + ".midi";
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
		ArrayList<AbstractGenome> instruments = new ArrayList<>();
		for (Evolution evolution : instrumentsEvolution) {
			instruments.add(evolution.getGenomeByIndex(index_of_genome));
		}
		multiFF = new MultiInstrumentFF(instruments);
		return multiFF.count_ff();
	}
}
