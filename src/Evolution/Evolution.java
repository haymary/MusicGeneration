package Evolution;
import java.util.ArrayList;
import java.util.Random;

import Genome.AbstractInstrument;
import Parser.GenomeParser;
import Parser.ParserFactory;

public class Evolution {
	private static final double MIN_FITNESS = 0.6;
	private static int MAX_STAGNATION_DEPTH = 4;
	private static int POP_SIZE = 10;
	private int NUB_OF_SURVIVORS;
	
	private boolean is_minimized = false;
	private AbstractInstrument commonProgenitor;
	private ArrayList<Double> fitness_factor = new ArrayList<>();
	private ArrayList<AbstractInstrument> pop;
	private ArrayList<String> phenotype;
	private int number_of_generations = 0;
	
	public Evolution(final AbstractInstrument parent) {
		commonProgenitor = parent;
		setPop(createPopulation(POP_SIZE));
		NUB_OF_SURVIVORS = (int) (0.7*POP_SIZE);
	}
	
//	public Evolution(final AbstractInstrument parent, final int size, 
//			final int num_generations, final double lucky_part) {
//		this.POP_SIZE = size;
//		setPop(createPopulation(POP_SIZE));
//		NUB_OF_SURVIVORS = (int) ((1-lucky_part)*size);
//	}
	
	private ArrayList<AbstractInstrument> createPopulation(final int pop_size2) {
		ArrayList<AbstractInstrument> newPop = new ArrayList<>();
		for (int i = 0; i < POP_SIZE; i++) {
			newPop.add(commonProgenitor.generateIndividual());
		}
		return newPop;
	}

	void produceNextGeneration() {
		ArrayList<AbstractInstrument> newGeneration;
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
		double depth_rate = 0.7;
		int survivor_num = (int) (POP_SIZE * depth_rate);
		ArrayList<AbstractInstrument> newPop = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < survivor_num; i++) {
			newPop.add(getPop().get(random.nextInt(POP_SIZE - 1)));
		}
		newPop.addAll(createPopulation(POP_SIZE - survivor_num));
		setPop(newPop);
	}

	private boolean isInStagnation() {
		Double current_fitness = countGenerationFitness();
		fitness_factor.add(current_fitness);
		if(number_of_generations < 10){
			return false;
		}
		if(current_fitness.equals(fitness_factor.get(
				getNumber_of_generations() - MAX_STAGNATION_DEPTH))){
			if (current_fitness.equals(fitness_factor.get(
					getNumber_of_generations() - 1 ))) {
				return true;
			}
		}
		return false;
	}

	private Double countGenerationFitness() {
		double generation_fitness = 0;
		for (AbstractInstrument individual : getPop()) {
			generation_fitness += individual.getFitness();
		}
		return generation_fitness / POP_SIZE;
	}

	private void selection(final ArrayList<AbstractInstrument> newGeneration) {
		newGeneration.sort(commonProgenitor.getComparator());
		if(newGeneration.get(0).fitsAbsolutely()){
			set_minimized();
			return;
		}
		setPop(new ArrayList<>());
		pop.addAll(newGeneration.subList(0, NUB_OF_SURVIVORS));
		pop.addAll(newGeneration.subList(newGeneration.size() - 
				(POP_SIZE - NUB_OF_SURVIVORS), newGeneration.size() - 1));
	}


	private ArrayList<AbstractInstrument> reproduce() {
		ArrayList<AbstractInstrument> newGeneration = new ArrayList<>();
		for (AbstractInstrument parent1 : getPop()) {
			for (AbstractInstrument parent2 : getPop()) {
				if(!parent1.equals(parent2) & (parent1.getInstrumentType().compareTo(parent2.getInstrumentType()) == 0)) {
					AbstractInstrument child = parent1.reproduce(parent2);
					child.getFitness();
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

	public ArrayList<AbstractInstrument> getPop() {
		return pop;
	}

	private void setPop(final ArrayList<AbstractInstrument> pop) {
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

	public ArrayList<AbstractInstrument> getSuccessors() {
		ArrayList<AbstractInstrument> sucessors = new ArrayList<>();
		pop.sort(commonProgenitor.getComparator());
		
		for (AbstractInstrument individual : pop) {
			if(individual.getFitness() > MIN_FITNESS) {
				sucessors.add(individual);
			}
		}
		return sucessors;
	}

	public void popToPhenotype() {
		phenotype = new ArrayList<>();
		ParserFactory parser_factory = new ParserFactory();
		GenomeParser parser = parser_factory.getParser(commonProgenitor.getInstrumentType());
		for (AbstractInstrument individual : getSuccessors()) {
			phenotype.add(parser.translateToPhenotype(individual));
		}
	}

}
