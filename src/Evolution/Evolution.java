package Evolution;
import static Evolution.Constants.POP_SIZE;

import java.util.ArrayList;
import java.util.Random;

import Genome.AbstractGenome;
import Parser.GenomeParser;
import Parser.ParserFactory;

public class Evolution {
	private final AbstractGenome COMMON_PROGENITOR;
	
	private ArrayList<AbstractGenome> pop;
	private ArrayList<String> phenotype;
//	private int number_of_generations = 0;
	
	public Evolution(final AbstractGenome parent) {
		COMMON_PROGENITOR = parent;
		setPop(createPopulation(POP_SIZE));
	}
	
	private ArrayList<AbstractGenome> createPopulation(final int pop_size2) {
		ArrayList<AbstractGenome> newPop = new ArrayList<>();
		for (int i = 0; i < POP_SIZE; i++) {
			newPop.add(COMMON_PROGENITOR.generateIndividual());
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
		ArrayList<AbstractGenome> newPop = new ArrayList<>();
		newPop.addAll(pop.subList(0, POP_SIZE));
		setPop(newPop);
	}


	public void produceNextGeneration() {
		ArrayList<AbstractGenome> newGeneration = new ArrayList<>();
		
		for (AbstractGenome parent1 : getPop()) {
			for (AbstractGenome parent2 : getPop()) {
				AbstractGenome child = parent1.reproduce(parent2);
				mutate(child);
				newGeneration.add(child);
			}
		}
		setPop(newGeneration);
	}

	private void mutate(final AbstractGenome child) {
		Random rand = new Random();
		if(rand.nextBoolean()){
			child.mutate();
		}
	}

	public ArrayList<AbstractGenome> getPop() {
		return pop;
	}

	private void setPop(final ArrayList<AbstractGenome> pop) {
		this.pop = pop;
	}

//	int getNumber_of_generations() {
//		return number_of_generations;
//	}

	public void popToPhenotype() {
		phenotype = new ArrayList<>();
		ParserFactory parser_factory = new ParserFactory();
		GenomeParser parser = parser_factory.getParser(COMMON_PROGENITOR.getInstrumentType());
		for (AbstractGenome individual : pop) {
			phenotype.add(parser.translateToPhenotype(individual));
		}
	}

	public ArrayList<String> getPhenotype() {
		return phenotype;
	}

	public AbstractGenome getGenomeByIndex(final int i) {
		return pop.get(i);
	}

	public String getInstrumentType(){
		return COMMON_PROGENITOR.getInstrumentType();
	}
}
