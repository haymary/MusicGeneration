package Evolution;
import static Evolution.Constants.POP_SIZE;

import java.util.ArrayList;
import java.util.Random;

import Genome.AbstractInstrument;
import Parser.GenomeParser;
import Parser.ParserFactory;

public class Evolution {
	private final AbstractInstrument COMMON_PROGENITOR;
	
	private ArrayList<AbstractInstrument> pop;
	private ArrayList<String> phenotype;
//	private int number_of_generations = 0;
	
	public Evolution(final AbstractInstrument parent) {
		COMMON_PROGENITOR = parent;
		setPop(createPopulation(POP_SIZE));
	}
	
	private ArrayList<AbstractInstrument> createPopulation(final int pop_size2) {
		ArrayList<AbstractInstrument> newPop = new ArrayList<>();
		for (int i = 0; i < POP_SIZE; i++) {
			newPop.add(COMMON_PROGENITOR.generateIndividual(COMMON_PROGENITOR.getInstrumentType()));
		}
		return newPop;
	}

//	void produceNextGeneration() {
//		ArrayList<AbstractInstrument> newGeneration;
//		pop = reproduce();
//
//		//		increaseNumber_of_generations();
////		selection(newGeneration);
////		if(is_minimized) {
////			return;
////		}
////		if(isInStagnation()){
////			shake();
////			setNumber_of_generations(0);
////		}
//		
//	}
	
//	private void shake() {
//		double depth_rate = 0.7;
//		int survivor_num = (int) (POP_SIZE * depth_rate);
//		ArrayList<AbstractInstrument> newPop = new ArrayList<>();
//		Random random = new Random();
//		for (int i = 0; i < survivor_num; i++) {
//			newPop.add(getPop().get(random.nextInt(POP_SIZE - 1)));
//		}
//		newPop.addAll(createPopulation(POP_SIZE - survivor_num));
//		setPop(newPop);
//	}
//
//	private boolean isInStagnation() {
//		Double current_fitness = countGenerationFitness();
//		fitness_factor.add(current_fitness);
//		if(number_of_generations < 10){
//			return false;
//		}
//		if(current_fitness.equals(fitness_factor.get(
//				getNumber_of_generations() - MAX_STAGNATION_DEPTH))){
//			if (current_fitness.equals(fitness_factor.get(
//					getNumber_of_generations() - 1 ))) {
//				return true;
//			}
//		}
//		return false;
//	}

//	private Double countGenerationFitness() {
//		double generation_fitness = 0;
//		for (AbstractInstrument individual : getPop()) {
//			generation_fitness += individual.getFitness();
//		}
//		return generation_fitness / POP_SIZE;
//	}

	public void selection() {
		ArrayList<AbstractInstrument> newPop = new ArrayList<>();
		newPop.addAll(pop.subList(0, POP_SIZE));
		setPop(newPop);
	}


	public void produceNextGeneration() {
		ArrayList<AbstractInstrument> newGeneration = new ArrayList<>();
		
		for (AbstractInstrument parent1 : getPop()) {
			for (AbstractInstrument parent2 : getPop()) {
				AbstractInstrument child = parent1.reproduce(parent2);
				mutate(child);
				newGeneration.add(child);
			}
		}
		setPop(newGeneration);
	}

	private void mutate(final AbstractInstrument child) {
		Random chance = new Random();

		if(chance.nextInt(11) > 9){
			child.mutate();
		}
	}

	public ArrayList<AbstractInstrument> getPop() {
		return pop;
	}

	private void setPop(final ArrayList<AbstractInstrument> pop) {
		this.pop = pop;
	}

//	int getNumber_of_generations() {
//		return number_of_generations;
//	}

	public void popToPhenotype() {
		phenotype = new ArrayList<>();
		ParserFactory parser_factory = new ParserFactory();
		GenomeParser parser = parser_factory.getParser(COMMON_PROGENITOR.getInstrumentType());
		for (AbstractInstrument individual : pop) {
			phenotype.add(parser.translateToPhenotype(individual));
		}
	}

	public ArrayList<String> getPhenotype() {
		return phenotype;
	}

	public AbstractInstrument getGenomeByIndex(final int i) {
		return pop.get(i);
	}

	public String getInstrumentType(){
		return COMMON_PROGENITOR.getInstrumentType();
	}
}
