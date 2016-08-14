package FF.MultiFF;

public class FFFactory {

	public MultiInstrumentFF getFFMethod(final String ff_type) {
		switch (ff_type) {
		case "Normal":
			return new NormalMultiInstrumentFF();
		case "Alt":
			return new AlternativeMultiInsrumentFF();

		default:
			break;
		}
		return null;
	}

}
