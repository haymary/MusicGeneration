package EvoSimulator;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import Evolution.Evolution;
import Evolution.Selection.Selection;
import Genome.AbstractGenome;
import Genome.PianoGenome;
import MusicSaver.DataProvider;
import MusicSaver.MusicSaver;
import Service.Constants;

public class EvolutionSimulator extends AbstractEvoSimulator{
	
	private ArrayList<Evolution> instrumentsEvolution;
	private ArrayList<Evolution> instrumentsEvolutionForSave;
	
	public EvolutionSimulator() {
		instrumentsEvolution = new ArrayList<>();
		instrumentsEvolution.add(new Evolution(new PianoGenome()));
		//instrumentsEvolution.add(new Evolution(new ViolinGenome()));
	}
	
	public void startSimulation(){
		for (int generation_num = 0; generation_num 
				< Constants.MAX_NUMBER_GENERATIONS; generation_num++) {
			nextGeneration();
			Selection selection = new Selection(instrumentsEvolution, "Normal");
			selection.multiinstrumentSelection();
			
			if(Constants.IS_ALTERNATIVE_EVOLUTION){
				AlternativeEvoSimulator a_evo 
				= new AlternativeEvoSimulator(instrumentsEvolution.get(0).getPop());
				a_evo.evoluteOtherInstruments();
				instrumentsEvolutionForSave.addAll(a_evo.getInstrumentsEvolution());
			}else{
				instrumentsEvolutionForSave.addAll(instrumentsEvolution);
			}
			
			translateGenerationToPhenotype();
			saveThreeBestofGeneration(generation_num);
			
			System.out.println(generation_num);
			//printFF();
			printRowGenomeToConsole();
			printTranslatedGenomeToConsole();
		}
		
	}


	private void printGenomeLength() {
		for (Evolution evolution : instrumentsEvolutionForSave) {
			System.out.println("Genotype:");
			for (AbstractGenome genome : evolution.getPop()) {
				System.out.print(genome.getNotes().size());
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}

	private void printFF() {
		for (Evolution evolution : instrumentsEvolutionForSave) {
			System.out.println(evolution.getGenomeByIndex(0).getFitness());
		}
	}

	private void printTranslatedGenomeToConsole() {
		for (Evolution evolution : instrumentsEvolutionForSave) {
			System.out.println(evolution.getPhenotype().get(1));
		}
	}

	private void printRowGenomeToConsole() {
		for (Evolution evolution : instrumentsEvolutionForSave) {
			System.out.print(evolution.getInstrumentType() + ": ");
			System.out.println(evolution.getGenomeByIndex(1).toString());
		}
	}

	private void saveThreeBestofGeneration(final int generation_num) {
		saveGenerationSamples(generation_num, 3);
	}
	
	private void saveAllofGeneration(final int generation_num) {
		saveGenerationSamples(generation_num, Constants.POP_SIZE);
	}

	private void saveGenerationSamples(final int generation_num, final int num_to_save) {
		for (int individual_num = 0; individual_num < num_to_save;individual_num++) {
			LinkedList<String> song = getInstrumentsPhenotypes(individual_num);

			if(Constants.IS_FOUR_PART_HARMONY){
				song = makeItFourPartHarmony(song);
			}
			
			DataProvider provider = new DataProvider(song);
		    MusicSaver saver = new MusicSaver();
		    saver.saveToMidi(provider, getFileName(generation_num, individual_num));
		}
	}

	private LinkedList<String> makeItFourPartHarmony(final LinkedList<String> input_song) {
		String[] instruments = input_song.get(0).split(";");
		LinkedList<String> song = new LinkedList<>();
		for (String string : instruments) {
			song.add(string);
			System.out.println(string);
		}
		return song;
	}

	protected void translateGenerationToPhenotype() {
		for (Evolution evolution : instrumentsEvolutionForSave) {
			evolution.popToPhenotype();
		}
	}
	
	protected LinkedList<String> getInstrumentsPhenotypes(final int individual_num) {
		LinkedList<String> song = new LinkedList<>();
		for (Evolution evolution : instrumentsEvolutionForSave) {
			song.add(evolution.getPhenotype().get(individual_num));
		}
		return song;
	}
	
	private String getFileName(final int generation_num, final int individual_num) {
		String pathname = getDirectoryForGeneration(generation_num);
        return pathname + File.separator + individual_num + ".midi";
	}

	private String getDirectoryForGeneration(final int generation_num) {
		String pathname = Constants.ROOT_FOLDER + File.separator + generation_num;  
        File newDir = new File(pathname);
        newDir.mkdirs();
		return pathname;
	}

}
