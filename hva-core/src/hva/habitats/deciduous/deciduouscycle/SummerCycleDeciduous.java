package hva.habitats.deciduous.deciduouscycle;

import hva.habitats.deciduous.Deciduous;
import java.io.Serializable;

public class SummerCycleDeciduous implements SeasonalCycleDeciduous,
Serializable{

    private double _age = 0;

    public SummerCycleDeciduous(double age){
        _age = age;
    }

    @Override
    public void advanceCycle(Deciduous tree){
        tree.setSeasonalCycle(new AutumnCycleDeciduous(_age + 0.25));
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
        return "COMFOLHAS";
    }
}