package hva.employees;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;

import hva.habitats.Habitat;
import hva.satisfaction.Satisfaction;
import hva.satisfaction.KeeperSatisfaction;

public class Keeper extends Employee{
    private Comparator<String> _caseInsensitiveComparator 
        = String.CASE_INSENSITIVE_ORDER;
    private Map<String, Habitat> _habitats 
        = new TreeMap<String, Habitat>(_caseInsensitiveComparator);

    public Keeper(String employeeKey, String name){
        super(employeeKey, name);
    }

    public void addResponsability(Habitat habitat) {
        _habitats.put(habitat.getKey(), habitat);
        habitat.addKeeper(this);
    }

    public void removeResponsability(String habitatKey){
        _habitats.get(habitatKey).removeKeeper(getKey());
        _habitats.remove(habitatKey);
    }

    public boolean containsResponsability(String habitatKey){
        return _habitats.containsKey(habitatKey);
    }

    public ArrayList<Habitat> getAllHabitats() {
        return new ArrayList<>(_habitats.values());
    }

    
    @Override
    public double calculateSatisfaction() {
        Satisfaction satisfaction = 
            new KeeperSatisfaction(getAllHabitats());
        return satisfaction.calculateSatisfaction();
    }


    @Override
    public String toString() {
        StringBuilder habitatKeys = new StringBuilder();

        if (!_habitats.isEmpty()) {
            habitatKeys.append("|");
            for (String key : _habitats.keySet()) {
                habitatKeys.append(key).append(",");
            }
            habitatKeys.setLength(habitatKeys.length() - 1);
        }
        return "TRT" + super.toString() + habitatKeys.toString();
    }
}
