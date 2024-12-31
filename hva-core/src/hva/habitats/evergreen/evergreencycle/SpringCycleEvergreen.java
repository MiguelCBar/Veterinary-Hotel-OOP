package hva.habitats.evergreen.evergreencycle;

import hva.habitats.evergreen.Evergreen;
import java.io.Serializable;

public class SpringCycleEvergreen implements SeasonalCycleEvergreen,
Serializable{

    private double _age = 0;

    public SpringCycleEvergreen(double age){
        _age = age;
    }

    @Override
    public void advanceCycle(Evergreen tree){
        tree.setSeasonalCycle(new SummerCycleEvergreen(_age + 0.25));
    }

    @Override
    public int getSeasonalEffort() {
        return 1;
    }

    @Override
    public int age() {
        return (int) Math.floor(_age);
    }

    @Override
    public String toString() {
        return "GERARFOLHAS";
    }
}
