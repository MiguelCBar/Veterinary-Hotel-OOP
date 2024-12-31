package hva.habitats;

import java.io.Serializable;

public abstract class Tree implements Serializable{
    private final String _TREE_KEY;
    private String _treeName;
    private int _baseCleaningDif;

    public Tree(String treeKey, String treeName, int baseCleaningDif){
        _TREE_KEY = treeKey;
        _treeName = treeName;
        _baseCleaningDif = baseCleaningDif;
    }

    public String getKey() {return _TREE_KEY;}

    public int getBaseCleaningDif() {return _baseCleaningDif;}

    public abstract void initiateSeasonalCycle(Season currentSeason, int age);

    public abstract double cleaningEffort();

    public abstract void updateCycle();

    @Override
    public String toString() {
        return "ÁRVORE|" + _TREE_KEY + "|" + _treeName + "|";
    }
}
