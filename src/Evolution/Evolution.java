package Evolution;
import static Service.Constants.POP_SIZE;

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
			newPop.add(COMMON_PROGENITOR.generateIndividual(COMMON_PROGENITOR.getInstrumentType()));
		}
		return newPop;
	}

	public void selection() {
		java.util.Collections.sort(pop);
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

	public Evolution setPop(final ArrayList<AbstractGenome> pop) {
		this.pop = pop;
		return this;
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
