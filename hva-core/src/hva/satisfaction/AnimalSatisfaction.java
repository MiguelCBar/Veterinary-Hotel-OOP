package hva.satisfaction;

import hva.habitats.Habitat;
import java.io.Serializable;

public class AnimalSatisfaction implements Satisfaction, Serializable {
    private Habitat _habitat;
    private String _animalKey;

    public AnimalSatisfaction(Habitat habitat, String animalKey) {
        this._habitat = habitat;
        this._animalKey = animalKey;
    }

    public void setHabitat(Habitat habitat) {
        this._habitat = habitat;
    }

    @Override
    public double calculateSatisfaction() {
        int equals = _habitat.countSameSpeciesAnimals(_animalKey);
        int different = _habitat.countDifferentSpeciesAnimals(_animalKey);
        int area = _habitat.getArea();
        int population = _habitat.getPopulation();
        int adequacy = _habitat.getAnimalAdequacy(_animalKey);

        double satisfaction = 20 + 3*equals - 2*different + area/population
                        + adequacy;

        return satisfaction;
    }
}