package Evolution;
import java.util.ArrayList;
import java.util.Comparator;

import Instruments.Evolutable;

public class Evolution {

	Comparator<Evolutable> comparator;
	ArrayList<Evolutable> pop;
	ArrayList<Evolutable> newGeneration;
	int size = 100;
	int max_num_generations = 1000;
	int number_of_servivors;
	
	public Evolution(final Evolutable parent, final Comparator<Evolutable> comparator) {
		this.comparator = comparator;
		pop = parent.createPopulation(size);
		number_of_servivors = (int) (0.7*size);
	}
	
	public Evolution(final Evolutable parent, final Comparator<Evolutable> comparator, final int size, final int num_generations, final double lucky_part) {
		this.comparator = comparator;
		this.size = size;
		this.max_num_generations = num_generations;
		pop = parent.createPopulation(size);
		number_of_servivors = (int) ((1-lucky_part)*size);
	}
	
	private void evolute() {
		for (int i = 0; i < max_num_generations; i++) {
			reproduce();
			boolean isFinished = selection();
			if(isFinished) {
				break;
			}
			if(isInStagnation()){
				shake();
			}
			
		}
		
		
	}
	
	private void shake() {
		// TODO Auto-generated method stub
		
	}

	private boolean isInStagnation() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean selection() {
		newGeneration.sort(comparator);
		if(newGeneration.get(0).ff() == 1){
			return true;
		}
		pop = new ArrayList<>();
		pop.addAll(newGeneration.subList(0, number_of_servivors));
		pop.addAll(newGeneration.subList(newGeneration.size() - 
				(size - number_of_servivors - 1), newGeneration.size() - 1));
		return false;
		
	}

	private void reproduce() {
		newGeneration = new ArrayList<>();
		for (Evolutable parent1 : pop) {
			for (Evolutable parent2 : pop) {
				if(!parent1.equals(parent2)) {
					newGeneration.add(parent1.reproduce(parent2));
				}
			}
		}
	}
}
