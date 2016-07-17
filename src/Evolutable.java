import java.util.ArrayList;

public interface Evolutable{
	
	public ArrayList<Evolutable> createPopulation(int size);
	
	public void mutate();
	
	public Evolutable reproduce(Evolutable parent2);
	
	public int ff();

		
}
