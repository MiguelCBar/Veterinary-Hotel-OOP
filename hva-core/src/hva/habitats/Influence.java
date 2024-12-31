package hva.habitats;

public enum Influence{
    POS(20),
    NEG(-20),
    NEU(0);

    private int _impact;

    private Influence(int impact) {
        _impact = impact;
    }

    public int impact() {
        return _impact;
    }


    public Influence formatString(String influence) {
        switch (influence) {
            case "POS" -> {
                return POS;
            }
            case "NEG" -> {
                return NEG;
            }
            default -> {
                return NEU;
            }
        }
    }
}
