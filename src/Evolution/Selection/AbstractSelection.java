package Evolution.Selection;

import java.util.ArrayList;

import Evolution.Evolution;
import FF.MultiFF.MultiInstrumentFF;
import Genome.AbstractGenome;
import Service.Constants;

public abstract class AbstractSelection {

	protected ArrayList<Evolution> 	instrumentsEvolution;
	protected MultiInstrumentFF 	multiFF;
	
	public void multiinstrumentSelection(){
		countFitnessForEachSong();
		
		for (Evolution evolution : instrumentsEvolution) {
			evolution.selection();
		}
	}

	private void countFitnessForEachSong() {
		for (int index_in_genome = 0; index_in_genome < 
				instrumentsEvolution.get(0).getPop().size() - 1; index_in_genome++) {
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
			return instrumentsEvolution.get(0).getGenomeByIndex(
					index_of_genome).getFitness();
		} 
		return sumInstrunmentFitness(index_of_genome) 
				* Constants.WEIGHT_OF_INSTRUMENTS_FITNESS 
				+ count_instrument_interaction(index_of_genome) 
				* Constants.WEIGHT_OF_INTERACTION;
	}



	private double sumInstrunmentFitness(final int index) {
		double total_fit = 0;
		for (Evolution evolution : instrumentsEvolution) {
			total_fit += evolution.getGenomeByIndex(index).getFitness();
		}
		return total_fit;
	}

	protected double count_instrument_interaction(final int index_of_genome) {
		multiFF.setInstruments(getInstruments(index_of_genome));
		return multiFF.count_ff();
	}

	protected abstract ArrayList<AbstractGenome> getInstruments(final int index_of_genome);

}
