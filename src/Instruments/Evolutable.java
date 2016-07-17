package Instruments;
import java.util.ArrayList;
import java.util.Comparator;

public interface Evolutable{
	
	public ArrayList<Evolutable> createPopulation(int size);
	
	public void mutate();
	
	public Evolutable reproduce(Evolutable parent2);
	
	public boolean fitsAbsolutely();
	
	public Comparator<Evolutable> getComporator();

	public void count_fitness();

	public double get_fitness();

		
}
