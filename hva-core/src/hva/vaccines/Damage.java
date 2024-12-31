package hva.vaccines;

public enum Damage{
    NORMAL(0),
    CONFUSÃO(0, 0),
    ACIDENTE(1, 4),
    ERRO(5);

    private final int min;
    private final int max;

    Damage(int min, int max) {
        this.min = min;
        this.max = max;
    }

    Damage(int min) {
        this(min, Integer.MAX_VALUE);
    }

    public Damage getDamage(int value) {
        for (Damage damage : values()) {
            if (damage == NORMAL) {
                continue;
            }
            if (value >= damage.min && value <= damage.max) {
                return damage;
            }
        }
        return Damage.NORMAL;
    }

    @Override
    public String toString() {
        return this.name();
    }
}

