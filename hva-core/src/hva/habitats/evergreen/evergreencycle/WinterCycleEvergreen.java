package hva.habitats.evergreen.evergreencycle;

import hva.habitats.evergreen.Evergreen;
import java.io.Serializable;

public class WinterCycleEvergreen implements SeasonalCycleEvergreen,
Serializable{
    
    private double _age = 0;

    public WinterCycleEvergreen(double age){
        _age = age;
    }

    @Override
    public void advanceCycle(Evergreen tree){
        tree.setSeasonalCycle(new SpringCycleEvergreen(_age + 0.25));
    }

    @Override
    public int getSeasonalEffort() {
        return 2;
    }

    @Override
    public int age() {
        return (int) Math.floor(_age);
    }

    @Override
    public String toString() {
        return "LARGARFOLHAS";
    }
}
