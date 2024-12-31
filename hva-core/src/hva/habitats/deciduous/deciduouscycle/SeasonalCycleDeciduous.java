package hva.habitats.deciduous.deciduouscycle;

import hva.habitats.deciduous.Deciduous;

public interface SeasonalCycleDeciduous {

    void advanceCycle(Deciduous tree);

    int getSeasonalEffort();

    int age();
}
