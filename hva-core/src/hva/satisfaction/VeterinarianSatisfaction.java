package hva.satisfaction;

import hva.animals.Species;
import java.io.Serializable;
import java.util.ArrayList;


public class VeterinarianSatisfaction implements Satisfaction, Serializable{

    private ArrayList<Species> _species;

    public VeterinarianSatisfaction(ArrayList<Species> species) {
        _species = species;
    }

    @Override
    public double calculateSatisfaction() {
        double satisfaction = 0;
        for(Species species: _species)
            satisfaction += (species.countAnimals()/
            species.countVeterinarians());

        satisfaction = 20 - satisfaction;

        return satisfaction;
    }
}


