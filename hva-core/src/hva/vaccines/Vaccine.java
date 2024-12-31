package hva.vaccines;


import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import hva.animals.Species;

public class Vaccine implements Serializable{

    private final String _VACCINE_KEY;
    private String _vaccineName;
    private Comparator<String> _caseInsensitiveComparator 
    = String.CASE_INSENSITIVE_ORDER;
    private Map <String, Species> _adequateSpecies = 
        new TreeMap<String, Species>(_caseInsensitiveComparator);
    private List<Vaccination> _vaccinations = 
        new ArrayList<Vaccination>();

    public Vaccine(String vaccineKey, String vaccineName){
        _VACCINE_KEY = vaccineKey;
        _vaccineName = vaccineName;
    }

    public String getVaccineKey() {return _VACCINE_KEY;}

    public String getVaccineName() {return _vaccineName;}

    public void addSpecies(Species species) {
        _adequateSpecies.put(species.getKey(), species);
    }

    public boolean checkSpeciesAdequacy(String speciesKey){
        return _adequateSpecies.containsKey(speciesKey);
    }

    public void addVacination(Vaccination vaccination){
        _vaccinations.add(vaccination);
    }

    public Damage calculateDamage(String speciesName){
        int maxDamage = 0;

        for (Species species : _adequateSpecies.values()) {
            String adequateSpeciesName = species.getName();
            
            int damage = Math.max(speciesName.length(), 
                        adequateSpeciesName.length()) 
                         - countCommonCharacters(speciesName, 
                         adequateSpeciesName);
            
            maxDamage = Math.max(maxDamage, damage);
        }

        return Damage.NORMAL.getDamage(maxDamage);
    }

    private int countCommonCharacters(String str1, String str2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : str1.toCharArray()) {
            set1.add(c);
        }
        for (char c : str2.toCharArray()) {
            set2.add(c);
        }

        set1.retainAll(set2);
        return set1.size();
    }

    @Override
    public String toString() {
        StringBuilder speciesKeys = new StringBuilder();
        if (!_adequateSpecies.isEmpty()) {
            speciesKeys.append("|");
            for (String key : _adequateSpecies.keySet()) {
                speciesKeys.append(key).append(",");
            }
            speciesKeys.setLength(speciesKeys.length() - 1);
        }
        return "VACINA|" + _VACCINE_KEY + "|" + _vaccineName + "|"
        + _vaccinations.size() + speciesKeys.toString();
    }
}
