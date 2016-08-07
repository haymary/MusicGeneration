package Parser;

import Genome.DrumsGenome;

public class ParserFactory {
	public GenomeParser getParser(final String type) {
		if (DrumsGenome.drumType.containsValue(type)){
			return new DrumsParser();
		}
		//return new ChordParser();
		return new AlternativeParser();
	}

}
