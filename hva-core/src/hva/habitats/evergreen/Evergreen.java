package hva.habitats.evergreen;

import hva.habitats.Season;
import hva.habitats.Tree;
import hva.habitats.evergreen.evergreencycle.AutumnCycleEvergreen;
import hva.habitats.evergreen.evergreencycle.SeasonalCycleEvergreen;
import hva.habitats.evergreen.evergreencycle.SpringCycleEvergreen;
import hva.habitats.evergreen.evergreencycle.SummerCycleEvergreen;
import hva.habitats.evergreen.evergreencycle.WinterCycleEvergreen;

public class Evergreen extends Tree {

    private SeasonalCycleEvergreen _seasonalCycle;

    public Evergreen(String treeKey, String treeName, int baseCleaningDiff){
        super(treeKey, treeName, baseCleaningDiff);
    }

    @Override
    public void initiateSeasonalCycle(Season currentSeason, int age){
        switch(currentSeason) {
            case Season.SPRING -> {
                setSeasonalCycle(new SpringCycleEvergreen(age));
                break;}
            case Season.SUMMER -> {
                setSeasonalCycle(new SummerCycleEvergreen(age));
                break;}
            case Season.AUTUMN -> {
                setSeasonalCycle(new AutumnCycleEvergreen(age));
                break;
            }
            case Season.WINTER -> {
                setSeasonalCycle(new WinterCycleEvergreen(age));
                break;
            }
        }
    }

    public void setSeasonalCycle(SeasonalCycleEvergreen seasonalCycle){
        _seasonalCycle = seasonalCycle;
    }

    public SeasonalCycleEvergreen getCycle(){ return _seasonalCycle;}

    @Override
    public void updateCycle() {
        _seasonalCycle.advanceCycle(this);
    }
    
    @Override
    public double cleaningEffort() {
        return getBaseCleaningDif() * 
            _seasonalCycle.getSeasonalEffort() * 
            Math.log(_seasonalCycle.age() + 1);
    }


    @Override
    public String toString() {
        return super.toString() + _seasonalCycle.age() + "|" 
        + getBaseCleaningDif() + "|PERENE" + "|" + _seasonalCycle.toString();
    }
}