package hva.habitats.deciduous.deciduouscycle;

import hva.habitats.deciduous.Deciduous;
import java.io.Serializable;

public class SpringCycleDeciduous implements SeasonalCycleDeciduous,
Serializable{

    private double _age = 0;

    public SpringCycleDeciduous(double age){
        _age = age;
    }

    @Override
    public void advanceCycle(Deciduous tree){
        tree.setSeasonalCycle(new SummerCycleDeciduous(_age + 0.25));
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