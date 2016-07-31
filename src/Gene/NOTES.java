package Gene;

public enum NOTES {
	REST(0), 
	C(1), D(2), E(3), F(4), G(5), H(6), I(7),
	CONTINUES(9);

	private int value;
	
	private NOTES(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
