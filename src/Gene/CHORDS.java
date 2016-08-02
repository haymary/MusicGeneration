package Gene;

public enum CHORDS {
    i(setMinor(1)), ii(setMinor(2)), iii(setMinor(3)), iv(setMinor(4)),
    v(setMinor(5)), vi(setMinor(6)), vii(setMinor(7)),

    I(setMajor(1)), II(setMajor(2)), III(setMajor(3)), IV(setMajor(4)),
    V(setMajor(5)), VI(setMajor(6)), VII(setMajor(7)),

    IA(setAug(1)), IIA(setAug(2)), IIIA(setAug(3)), IVA(setAug(4)),
    VA(setAug(5)), VIA(setAug(6)), VIIA(setAug(7)),

    iD(setDim(1)), iiD(setDim(2)), iiiD(setDim(3)), ivD(setDim(4)),
    vD(setDim(5)), viD(setDim(6)), viiD(setDim(7)),

    ;

    private int value;

    private CHORDS(final int value) {
        this.value = value;
    }

    //BEGIN PUBLIC METHODS
    public int getValue() {
        return value;
    }
    //END PUBLIC METHODS

    //BEGIN #FORMULA# PRIVATE METHODS
    private static int setMajor(final int note) {
        return formula(0, note);
    }

    private static int setMinor(final int note) {
        return formula(1, note);
    }

    private static int setAug(final int note) {
        return formula(2, note);
    }
    private static int setDim(final int note) {
        return formula(3, note);
    }

    private static int formula(final int type, final int note) {
        return - (note + 7 * type);
    }
    //END #FORMULA# PRIVATE METHODS


}
