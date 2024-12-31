package hva.habitats;


import java.util.TreeMap;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.Comparator;

import hva.searchpredicate.SearchPredicate;
import hva.animals.Animal;
import hva.animals.Species;
import hva.employees.Keeper;

import java.util.List;
import java.util.ArrayList;

import java.util.Collections;

import java.io.Serializable;


public class Habitat implements Serializable{
    private final String _HABITAT_KEY;
    private String _habitatName;
    private int _area;

    private Comparator<String> _caseInsensitiveComparator 
    = String.CASE_INSENSITIVE_ORDER;
    private Map<String, Animal> _animals = 
        new TreeMap<String, Animal>(_caseInsensitiveComparator);
    private Map<String, Tree> _trees = 
        new TreeMap<String, Tree>(_caseInsensitiveComparator);

    private Map<String, Influence> _influences = 
        new HashMap<String, Influence>();

    private Map<String, Keeper> _keepers = 
        new HashMap<String, Keeper>();


    public Habitat(String habitatKey, String habitatName, int area) {
        _HABITAT_KEY = habitatKey;
        _habitatName = habitatName;
        _area = area;
    }

    public String getKey() {return _HABITAT_KEY;}

    public Tree getTree(String treeKey) {return _trees.get(treeKey);}

    public Collection<Tree> getTrees() {return _trees.values();}

    public Collection<Animal> getAnimals() {return _animals.values();}

    public void addKeeper(Keeper keeper) {
        _keepers.put(keeper.getKey(), keeper);

    }

    public void removeKeeper(String keeperKey) {
        _keepers.remove(keeperKey);
    }

    public int countKeepers() {return _keepers.size();}

    public int getTreeCount(){return _trees.size();}

    public void plantTree(Tree tree) {
        _trees.put(tree.getKey(), tree);
    }

    public double getTotalCleaningEffort() {
        double totalEffort = 0;
        for(Tree tree: _trees.values()) {
            totalEffort += tree.cleaningEffort();
        }
        return totalEffort;
    }

    public Animal getAnimal(String animalKey) {
        return _animals.get(animalKey);
    }

    public void addAnimal(Animal animal){
        _animals.put(animal.getKey(),animal);
    }

    public void removeAnimal(String animalKey) {_animals.remove(animalKey);}

    public void setArea(int newArea) {_area = newArea;}

    public int getArea() {return _area;}

    public void changeSpeciesInfluence(Species species, String influence){
        Influence speciesInfluence = Influence.POS.formatString(influence);
        if(speciesInfluence != Influence.NEU) {
            _influences.put(species.getKey(), speciesInfluence);
        }
        else {
            if(_influences.containsKey(species.getKey()))
                _influences.remove(species.getKey());
        }
    }

    public int countSameSpeciesAnimals(String animalKey) {
        Animal queryAnimal = _animals.get(animalKey);
        int counter = 0;
        for(Animal animal: _animals.values()) {
                if(queryAnimal.getSpecies().getKey().equals(
                    animal.getSpecies().getKey()))
                    counter++;
        }
        return counter - 1;
    }

    public int countDifferentSpeciesAnimals(String animalKey) {
        int sameSpecies = countSameSpeciesAnimals(animalKey);
        return _animals.size() - sameSpecies - 1;
    }

    public int getPopulation() {
        return _animals.size();
    }

    public int getAnimalAdequacy(String animalKey) {
        Influence influence = _influences.get(
                _animals.get(animalKey).getSpecies().getKey());
        if(influence == null)
            return Influence.NEU.impact();
        return influence.impact();
    }

    public Collection<Animal> findAnimalsHabitat(SearchPredicate<Animal> 
    predicate){
        List<Animal> result = new ArrayList<>();
        for (Animal animal : _animals.values()) {
            if (predicate.test(animal)) {
                result.add(animal);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public Collection<Tree> findTrees(SearchPredicate<Tree> predicate){
        List<Tree> result = new ArrayList<>();
        for (Tree tree : _trees.values()) {
            if (predicate.test(tree)) {
                result.add(tree);
            }
        }
        return Collections.unmodifiableList(result);
    }

    @Override
    public String toString() {
        StringBuilder habitatTrees = new StringBuilder();
        habitatTrees.append("\n");

        for(Tree treeObject: _trees.values())
            habitatTrees.append(treeObject.toString()).append("\n");
        if(habitatTrees.length() > 0) {
            habitatTrees.setLength(habitatTrees.length() - 1);
        }
        return "HABITAT|" + _HABITAT_KEY + "|" + _habitatName + "|" 
        + _area + "|" + getTreeCount() + habitatTrees;
    }
}
