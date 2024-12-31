package hva.animals;

import hva.habitats.Habitat;
import hva.satisfaction.AnimalSatisfaction;
import hva.satisfaction.Satisfaction;
import hva.vaccines.Vaccination;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Animal implements Serializable{
    private final String _ANIMAL_KEY;
    private String _animalName;
    private Species _species;
    private Habitat _habitat;
    private List<Vaccination> _vaccinations = new ArrayList<Vaccination>();
    
    public Animal(String key, String name, Species species, Habitat habitat){
        _ANIMAL_KEY = key;
        _animalName = name;
        _species = species;
        _habitat = habitat;
    }

    public String getKey() {return _ANIMAL_KEY;}

    public String getName() {return this._animalName;}

    public Species getSpecies() {return this._species;}

    public Habitat getHabitat() {return this._habitat;}

    public void setHabitat(Habitat habitat) {_habitat = habitat;}

    public void addVaccination(Vaccination vaccination) {
        _vaccinations.add(vaccination);
    }

    public void vaccinateAnimal(Vaccination vaccination){
        _vaccinations.add(vaccination);
    }

    public Collection<Vaccination> getVaccinations() {
        return Collections.unmodifiableList(_vaccinations);
    }

    public double calculateSatisfaction() {
        Satisfaction satisfaction =
            new AnimalSatisfaction(getHabitat(), getKey());
        return satisfaction.calculateSatisfaction();
    }

    @Override
    public String toString() {
        String healthHistory;
        if (_vaccinations.isEmpty()) {
            healthHistory = "VOID";
        } else {
            StringBuilder healthHistoryBuilder = new StringBuilder();
            for (Vaccination vaccination : _vaccinations) {
                healthHistoryBuilder.append(vaccination.getDamage()
                .toString()).append(",");
            }
            healthHistoryBuilder.setLength(healthHistoryBuilder.length() - 1);
            healthHistory = healthHistoryBuilder.toString();
        }
        return "ANIMAL|" + _ANIMAL_KEY + "|" + _animalName + "|" 
            + _species.getKey() + "|" + healthHistory + "|" 
            + _habitat.getKey();
    }
}
