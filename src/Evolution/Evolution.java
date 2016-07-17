package Evolution;
import java.util.ArrayList;
import java.util.Random;

import Instruments.Evolutable;

public class Evolution {
	static int number_of_generations = 0;
	ArrayList<Double> fitness_factor;
	Evolutable commonProgenetor;
	ArrayList<Evolutable> pop;
	ArrayList<Evolutable> newGeneration;
	int pop_size = 100;
	int max_num_generations = 1000;
	int number_of_servivors = (int) (0.7*pop_size);
	
	public Evolution(final Evolutable parent) {
		commonProgenetor = parent;
		pop = parent.createPopulation(pop_size);
	}
	
	public Evolution(final Evolutable parent, final int size, final int num_generations, final double lucky_part) {
		this.pop_size = size;
		this.max_num_generations = num_generations;
		pop = parent.createPopulation(size);
		number_of_servivors = (int) ((1-lucky_part)*size);
	}
	
	private void evolute() {
		for (int i = 0; i < max_num_generations; i++) {
			reproduce();
			number_of_generations++;
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
		double deth_rate = 0.7;
		int servivour_num = (int) (pop_size * deth_rate);
		ArrayList<Evolutable> newPop = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < servivour_num; i++) {
			newPop.add(pop.get(random.nextInt(pop_size - 1)));
		}
		newPop.addAll(commonProgenetor.createPopulation(1 - servivour_num));
	}

	private boolean isInStagnation() {
		int MAX_STAGNATION_DEPTH = 4;
		Double current_fitness = count_generation_fitness();
		fitness_factor.add(current_fitness);
		if(current_fitness.equals(fitness_factor.get(
				number_of_generations - MAX_STAGNATION_DEPTH))){
			if (current_fitness.equals(fitness_factor.get(
					number_of_generations) - 1)) {
				return true;
			}
		}
		return false;
	}

	private Double count_generation_fitness() {
		double generation_fitness = 0;
		for (Evolutable individual : pop) {
			generation_fitness += individual.get_fitness();
		}
		return generation_fitness / pop_size;
	}

	private boolean selection() {
		newGeneration.sort(commonProgenetor.getComporator());
		if(newGeneration.get(0).fitsAbsolutely()){
			return true;
		}
		pop = new ArrayList<>();
		pop.addAll(newGeneration.subList(0, number_of_servivors));
		pop.addAll(newGeneration.subList(newGeneration.size() - 
				(pop_size - number_of_servivors), newGeneration.size() - 1));
		return false;
		
	}

	private void reproduce() {
		newGeneration = new ArrayList<>();
		for (Evolutable parent1 : pop) {
			for (Evolutable parent2 : pop) {
				if(!parent1.equals(parent2)) {
					Evolutable child = parent1.reproduce(parent2);
					child.count_fitness();
					newGeneration.add(child);
				}
			}
		}
	}
}
