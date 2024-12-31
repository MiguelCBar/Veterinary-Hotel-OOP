package hva.habitats;

public enum Season {
    SPRING(0),
    SUMMER(1),
    AUTUMN(2),
    WINTER(3);

    private final int _seasonNumber;

    Season(int seasonNumber) {
        _seasonNumber = seasonNumber;
    }

    public int getSeasonNumber() {
        return _seasonNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(_seasonNumber);
    }
}