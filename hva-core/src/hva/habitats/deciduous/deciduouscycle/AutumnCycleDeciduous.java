package hva.habitats.deciduous.deciduouscycle;

import hva.habitats.deciduous.Deciduous;
import java.io.Serializable;

public class AutumnCycleDeciduous implements SeasonalCycleDeciduous, 
Serializable{

    private double _age = 0;

    public AutumnCycleDeciduous(double age){
        _age = age;
    }

    @Override
    public void advanceCycle(Deciduous tree){
        tree.setSeasonalCycle(new WinterCycleDeciduous(_age + 0.25));
    }

    @Override
    public int getSeasonalEffort() {
        return 5;
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