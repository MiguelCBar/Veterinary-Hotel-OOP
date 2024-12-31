package hva.habitats.deciduous;

import hva.habitats.Season;
import hva.habitats.Tree;
import hva.habitats.deciduous.deciduouscycle.AutumnCycleDeciduous;
import hva.habitats.deciduous.deciduouscycle.SeasonalCycleDeciduous;
import hva.habitats.deciduous.deciduouscycle.SpringCycleDeciduous;
import hva.habitats.deciduous.deciduouscycle.SummerCycleDeciduous;
import hva.habitats.deciduous.deciduouscycle.WinterCycleDeciduous;

public class Deciduous extends Tree {

    private SeasonalCycleDeciduous _seasonalCycle;

    public Deciduous(String treeKey, String treeName, int baseCleaningDiff){
        super(treeKey, treeName, baseCleaningDiff);
    }

    @Override
    public void initiateSeasonalCycle(Season currentSeason, int age){
        switch(currentSeason) {
            case Season.SPRING -> { 
                setSeasonalCycle(new SpringCycleDeciduous(age));}
            case Season.SUMMER -> {
                setSeasonalCycle(new SummerCycleDeciduous(age));}
            case Season.AUTUMN -> {
                setSeasonalCycle(new AutumnCycleDeciduous(age));}
            case Season.WINTER -> {
                setSeasonalCycle(new WinterCycleDeciduous(age));}
        }
    }

    public void setSeasonalCycle(SeasonalCycleDeciduous seasonalCycle){
        _seasonalCycle = seasonalCycle;
    }

    public SeasonalCycleDeciduous getCycle(){ return _seasonalCycle;}

    @Override
    public double cleaningEffort() {
        return getBaseCleaningDif() * 
            _seasonalCycle.getSeasonalEffort() * 
            Math.log(_seasonalCycle.age() + 1);
    }

    @Override
    public void updateCycle() {
        _seasonalCycle.advanceCycle(this);
    }

    @Override
    public String toString() {
        return super.toString() + _seasonalCycle.age() + "|" 
        + getBaseCleaningDif() + "|CADUCA" + "|" + _seasonalCycle.toString();
    }
}
