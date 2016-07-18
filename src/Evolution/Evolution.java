package Evolution;
import java.util.ArrayList;
import java.util.Random;

import Parser.GenomeParser;
import Parser.ParserFactory;

public class Evolution {
	private static final double MIN_FITNESS = 0.6;
	private int MAX_STAGNATION_DEPTH = 4;
	
	private boolean is_minimized = false;
	private Evolutable commonProgenetor;
	private ArrayList<Double> fitness_factor;
	private ArrayList<Evolutable> pop;
	private ArrayList<String> phenotype;
	private int number_of_generations = 0;
	private int pop_size = 100;
	private int number_of_servivors = (int) (0.7*pop_size);
	
	public Evolution(final Evolutable parent) {
		commonProgenetor = parent;
		setPop(createPopulation(pop_size));
	}
	
	public Evolution(final Evolutable parent, final int size, final int num_generations, final double lucky_part) {
		this.pop_size = size;
		setPop(createPopulation(pop_size));
		number_of_servivors = (int) ((1-lucky_part)*size);
	}
	
	private ArrayList<Evolutable> createPopulation(final int pop_size2) {
		ArrayList<Evolutable> newPop = new ArrayList<>();
		for (int i = 0; i < pop_size; i++) {
			newPop.add(commonProgenetor.generateIndividual());
		}
		return newPop;
	}

	void produceNextGeneration() {
		ArrayList<Evolutable> newGeneration;
		newGeneration = reproduce();
		increaseNumber_of_generations();
		selection(newGeneration);
		if(is_minimized) {
			return;
		}
		if(isInStagnation()){
			shake();
			setNumber_of_generations(0);
		}
		
	}
	
	private void shake() {
		double deth_rate = 0.7;
		int servivour_num = (int) (pop_size * deth_rate);
		ArrayList<Evolutable> newPop = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < servivour_num; i++) {
			newPop.add(getPop().get(random.nextInt(pop_size - 1)));
		}
		newPop.addAll(createPopulation(pop_size - servivour_num));
		setPop(newPop);
	}

	private boolean isInStagnation() {
		Double current_fitness = countGenerationFitness();
		fitness_factor.add(current_fitness);
		if(current_fitness.equals(fitness_factor.get(
				getNumber_of_generations() - MAX_STAGNATION_DEPTH))){
			if (current_fitness.equals(fitness_factor.get(
					getNumber_of_generations()) - 1)) {
				return true;
			}
		}
		return false;
	}

	private Double countGenerationFitness() {
		double generation_fitness = 0;
		for (Evolutable individual : getPop()) {
			generation_fitness += individual.get_fitness();
		}
		return generation_fitness / pop_size;
	}

	private void selection(final ArrayList<Evolutable> newGeneration) {
		newGeneration.sort(commonProgenetor.getComporator());
		if(newGeneration.get(0).fitsAbsolutely()){
			set_minimized();
			return;
		}
		setPop(new ArrayList<>());
		pop.addAll(newGeneration.subList(0, number_of_servivors));
		pop.addAll(newGeneration.subList(newGeneration.size() - 
				(pop_size - number_of_servivors), newGeneration.size() - 1));
	}


	private ArrayList<Evolutable> reproduce() {
		ArrayList<Evolutable> newGeneration = new ArrayList<>();
		for (Evolutable parent1 : getPop()) {
			for (Evolutable parent2 : getPop()) {
				if(!parent1.equals(parent2)) {
					Evolutable child = parent1.reproduce(parent2);
					child.count_fitness();
					newGeneration.add(child);
				}
			}
		}
		return newGeneration;
	}

	public boolean is_minimized() {
		return is_minimized;
	}

	public void set_minimized() {
		this.is_minimized = true;
	}

	public ArrayList<Evolutable> getPop() {
		return pop;
	}

	private void setPop(final ArrayList<Evolutable> pop) {
		this.pop = pop;
	}

	int getNumber_of_generations() {
		return number_of_generations;
	}

	private void setNumber_of_generations(final int number_of_generations) {
		this.number_of_generations = number_of_generations;
	}
	
	private void increaseNumber_of_generations(){
		this.number_of_generations++;
	}

	public ArrayList<Evolutable> getSuccessors() {
		ArrayList<Evolutable> sucessors = new ArrayList<>();
		pop.sort(commonProgenetor.getComporator());
		
		for (Evolutable individual : pop) {
			if(individual.get_fitness() > MIN_FITNESS) {
				sucessors.add(individual);
			}
		}
		return sucessors;
	}

	public void popToPhenotype() {
		phenotype = new ArrayList<>();
		ParserFactory parser_factory = new ParserFactory();
		GenomeParser parser = parser_factory.getParser(commonProgenetor.getType());
		for (Evolutable individual : getSuccessors()) {
			phenotype.add(parser.translateToPhenotype(individual));
		}
	}

}
