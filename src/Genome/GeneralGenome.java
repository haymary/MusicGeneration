package Genome;

import static Evolution.Constants.MELODY_LENGTH;

import java.util.Random;

import FF.GeneralFF;
import Gene.Chord;
import Gene.GeneralChord;

public abstract class GeneralGenome extends AbstractGenome{
	
	protected abstract GeneralGenome generateChild();
	
	@Override
	public AbstractGenome reproduce(final AbstractGenome parent2) {
		Random rand = new Random();
		int point_of_division = rand.nextInt(MELODY_LENGTH);
		
		GeneralGenome child = generateChild();
		child.getNotes().addAll(parent2.getNotes().subList(point_of_division, MELODY_LENGTH));
		child.getNotes().addAll(this.getNotes().subList(0, point_of_division));
		setFirstElementIndependent(child);
		return child;
	}

	
	@Override
	public AbstractGenome generateIndividual() {
		GeneralGenome child = generateChild();
		child.generateGenome();
		setFirstElementIndependent(child);
		return child;
	}

	//First element should not continue previous note
	private void setFirstElementIndependent(final AbstractGenome child) {
		child.getFirstNote().setContinuesLast(false);
	}

	private void generateGenome() {
		for (int i = 0; i < MELODY_LENGTH; i++) {
			this.notes.add(new GeneralChord(getPreviousNote()));
		}
	}

	private Chord getPreviousNote() {
		if(notes.isEmpty()) {
			return null;
		}
		return notes.get(notes.size() - 1);
		
	}

	@Override
	protected void count_fitness() {
		GeneralFF ff = new GeneralFF(notes);
		fitness = ff.calculateScore();
	}

	@Override
	public void mutate() {
		int MAX_NUM_MUTATIONS = MELODY_LENGTH/3;
		
		Random number_of_mutations = new Random();
		for (int i = 0; i < number_of_mutations.nextInt(MAX_NUM_MUTATIONS); i++) {
			Random element_to_mutate = new Random();
			getNotes().get(element_to_mutate.nextInt(MELODY_LENGTH)).mutate(); 
		}
		
	}

	@Override
	public int compareTo(final AbstractGenome other_genome) {
		if(this.fitness > other_genome.fitness){
			return -1;
		}else if(this.fitness == other_genome.fitness) {
			return 0;
		}
		return 1;
	}
}
