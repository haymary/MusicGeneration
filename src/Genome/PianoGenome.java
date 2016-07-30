package Genome;

import static Evolution.Constants.MELODY_LENGTH;

import java.util.Random;

import FF.GeneralFF;
import Gene.GeneralChord;

public class PianoGenome extends AbstractGenome       {
	
	public PianoGenome() {
		instrument_type = "Piano";
	}

	@Override
	public AbstractGenome reproduce(final AbstractGenome parent2) {
		Random rand = new Random();
		int point_of_division = rand.nextInt(MELODY_LENGTH);
		
		PianoGenome child = new PianoGenome();
		child.getNotes().addAll(parent2.getNotes().subList(point_of_division, MELODY_LENGTH));
		child.getNotes().addAll(this.getNotes().subList(0, point_of_division));
		return child;
	}

	@Override
	public AbstractGenome generateIndividual() {
		PianoGenome child = new PianoGenome();
		child.generateGenome();
		return child;
	}

	private void generateGenome() {
		for (int i = 0; i < MELODY_LENGTH; i++) {
			this.notes.add(new GeneralChord());
		}
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

}
