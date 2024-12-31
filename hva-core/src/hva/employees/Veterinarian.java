package hva.employees;


import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;

import hva.animals.Species;
import hva.satisfaction.Satisfaction;
import hva.satisfaction.VeterinarianSatisfaction;

public class Veterinarian extends Employee{
    private Comparator<String> _caseInsensitiveComparator 
        = String.CASE_INSENSITIVE_ORDER;
    private Map<String, Species> _species 
        = new TreeMap<String, Species>(_caseInsensitiveComparator);
    
    public Veterinarian(String employeeKey, String name){
        super(employeeKey, name);
    }

    public void addResponsability(Species species) {
        _species.put(species.getKey(), species);
        species.addVeterinarian(this);
    }

    @Override
    public void removeResponsability(String speciesKey){
        _species.get(speciesKey).removeVeterinarian(getKey());
        _species.remove(speciesKey);
    }

    @Override
    public boolean containsResponsability(String speciesKey){
        return _species.containsKey(speciesKey);
    }

    public ArrayList<Species> getAllSpecies() {
        return new ArrayList<>(_species.values());
    }

    @Override
    public double calculateSatisfaction() {
        Satisfaction satisfaction = 
            new VeterinarianSatisfaction(getAllSpecies());
        return satisfaction.calculateSatisfaction();
    }

    @Override
    public String toString() {
        StringBuilder speciesKeys = new StringBuilder();
        if (!_species.isEmpty()) {
            speciesKeys.append("|");
            for (String key : _species.keySet()) {
                speciesKeys.append(key).append(",");
            }
            speciesKeys.setLength(speciesKeys.length() - 1);
        }
        return "VET" + super.toString() + speciesKeys.toString();
    }
}
