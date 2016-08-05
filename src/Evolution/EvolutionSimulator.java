package Evolution;

import static Evolution.Constants.MAX_NUMBER_GENERATIONS;
import static Evolution.Constants.POP_SIZE;
import static Evolution.Constants.ROOT_FOLDER;
import static Evolution.Constants.WEIGHT_OF_INSTRUMENTS_FITNESS;
import static Evolution.Constants.WEIGHT_OF_INTERACTION;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import FF.MultiInstrumentFF;
import Genome.*;
import MusicSaver.DataProvider;
import MusicSaver.MusicSaver;

public class EvolutionSimulator {
	MultiInstrumentFF multiFF;
	
	private ArrayList<Evolution> instrumentsEvolution;
	
	public EvolutionSimulator() {
		instrumentsEvolution = new ArrayList<>();
		//instrumentsEvolution.add(new Evolution(new PianoGenome()));
		//instrumentsEvolution.add(new Evolution(new ViolinGenome()));
		instrumentsEvolution.add(new Evolution(new DrumInstrument()));
	}
	
	public void startSimulation(){
		for (int generation_num = 0; generation_num < MAX_NUMBER_GENERATIONS; generation_num++) {
			nextGeneration();
			multiinstrumentSelection();
			translateGenerationToPhenotype();
			saveThreeBestofGeneration(generation_num);
			//saveAllofGeneration(generation_num);
			
			System.out.println(generation_num);
			//printFF();
			//printGenomeLength();
			printRowGenomeToConsole();
			printTranslatedGenomeToConsole();
		}
		
	}


	private void printGenomeLength() {
		for (Evolution evolution : instrumentsEvolution) {
			System.out.println("Genotype:");
			for (AbstractGenome genome : evolution.getPop()) {
				System.out.print(genome.getNotes().size());
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}

	private void printFF() {
		for (Evolution evolution : instrumentsEvolution) {
			System.out.println(evolution.getGenomeByIndex(0).getFitness());
		}
	}

	private void printTranslatedGenomeToConsole() {
		for (Evolution evolution : instrumentsEvolution) {
			System.out.println(evolution.getPhenotype().get(1));
		}
	}

	private void translateGenerationToPhenotype() {
		for (Evolution evolution : instrumentsEvolution) {
			evolution.popToPhenotype();
		}
	}

	private void printRowGenomeToConsole() {
		for (Evolution evolution : instrumentsEvolution) {
			System.out.print(evolution.getInstrumentType() + ": ");
			System.out.println(evolution.getGenomeByIndex(1).toString());
		}
	}

	private void saveThreeBestofGeneration(final int generation_num) {
		saveGenerationSamples(generation_num, 3);
	}
	
	private void saveAllofGeneration(final int generation_num) {
		saveGenerationSamples(generation_num, POP_SIZE);
	}

	private void saveGenerationSamples(final int generation_num, final int num_to_save) {
		for (int individual_num = 0; individual_num < num_to_save; individual_num++) {
			LinkedList<String> song = getInstrumentsPhenotypes(individual_num);
			LinkedList<String> instrumentTypes = getInstrumentTypes(individual_num);
			DataProvider provider = new DataProvider(song, instrumentTypes);
		    MusicSaver saver = new MusicSaver();
		    saver.saveToMidi(provider, getFileName(generation_num, individual_num));
		}
	}

	private LinkedList<String> getInstrumentTypes(int individual_num){
		LinkedList<String> instrumentTypes = new LinkedList<>();
		for (Evolution evolution : instrumentsEvolution) {
			instrumentTypes.add(evolution.getPop().get(individual_num).getInstrumentType());
		}
		return instrumentTypes;
	}

	private LinkedList<String> getInstrumentsPhenotypes(final int individual_num) {
		LinkedList<String> song = new LinkedList<>();
		for (Evolution evolution : instrumentsEvolution) {
			song.add(evolution.getPhenotype().get(individual_num));
		}
		return song;
	}

	private String getFileName(final int generation_num, final int individual_num) {
		String pathname = getDirectoryForGeneration(generation_num);
        return pathname + File.separator + individual_num + ".mid";
	}

	private String getDirectoryForGeneration(final int generation_num) {
		String pathname = ROOT_FOLDER + File.separator + generation_num;  
        File newDir = new File(pathname);
        newDir.mkdirs();
		return pathname;
	}

	private void multiinstrumentSelection(){
		countFitnessForEachSong();
		
		for (Evolution evolution : instrumentsEvolution) {
			evolution.selection();
		}
	}

	private void countFitnessForEachSong() {
		for (int index_in_genome = 0; index_in_genome < POP_SIZE * POP_SIZE; index_in_genome++) {
			double song_fitness = countSongFitness(index_in_genome);
			setSongFitness(index_in_genome, song_fitness);
		}
	}

	private void setSongFitness(final int index_in_genome, final double song_fitness) {
		for (Evolution evolution : instrumentsEvolution) {
			evolution.getGenomeByIndex(index_in_genome).setFitness(song_fitness);
		}
	}
	
	private double countSongFitness(final int index_of_genome) {
		if(instrumentsEvolution.size() == 1) {
			return instrumentsEvolution.get(0).getGenomeByIndex(index_of_genome).getFitness();
		} 
		return sumInstrunmentFitness(index_of_genome) * WEIGHT_OF_INSTRUMENTS_FITNESS 
				+  count_instrument_interaction(index_of_genome) * WEIGHT_OF_INTERACTION;
	}


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

	private void nextGeneration() {
		for (Evolution evolution : instrumentsEvolution) {
			evolution.produceNextGeneration();
		}
	}
}
