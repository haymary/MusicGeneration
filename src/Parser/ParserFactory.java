package Parser;

public class ParserFactory {
	public GenomeParser getParser(final String type) {
		if (type.equals("Drums")){
			return new DrumsParser();
		}
		//return new ChordParser();
		return new AlternativeParser();
	}

}
