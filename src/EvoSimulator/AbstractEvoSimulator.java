package EvoSimulator;

import java.util.ArrayList;

import Evolution.Evolution;

public class AbstractEvoSimulator {

	protected ArrayList<Evolution> instrumentsEvolution;
	
	protected void nextGeneration() {
		for (Evolution evolution : instrumentsEvolution) {
			evolution.produceNextGeneration();
		}
	}
	
}
