package hva.habitats.evergreen.evergreencycle;

import hva.habitats.evergreen.Evergreen;

public interface SeasonalCycleEvergreen {

    void advanceCycle(Evergreen tree);

    int getSeasonalEffort();

    int age();


}
