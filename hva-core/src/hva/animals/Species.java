package hva.animals;

import hva.employees.Veterinarian;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class Species implements Serializable{
    private final String _SPECIES_KEY;
    private String _speciesName;
    private Map<String, Animal> _animals = new HashMap<String, Animal>();
    private Map<String, Veterinarian> _veterinarians = 
        new HashMap<String, Veterinarian>();

    public Species(String key, String name) {
        _SPECIES_KEY = key;
        _speciesName = name;
    }

    public void addVeterinarian(Veterinarian veterinarian) {
        _veterinarians.put(veterinarian.getKey(), veterinarian);
    }

    public void removeVeterinarian(String veterinarianKey) {
        _veterinarians.remove(veterinarianKey);
    }

    public Veterinarian getVeterinarian(String veterinarianKey) {
        return _veterinarians.get(veterinarianKey);
    }

    public int countVeterinarians() {return _veterinarians.size();}

    public String getKey() {return _SPECIES_KEY;}

    public String getName() {return _speciesName;}

    public void addAnimalResgistry(Animal animal) 
        {_animals.put(animal.getKey(), animal);
    }

    public Animal getAnimal(String animalKey) {
        return _animals.get(animalKey);
    }

    public int countAnimals() {return _animals.size();}

    @Override
    public String toString() {
        return "" + _SPECIES_KEY;
    }
}
