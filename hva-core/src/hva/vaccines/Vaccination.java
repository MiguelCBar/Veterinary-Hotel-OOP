package hva.vaccines;

import hva.animals.Animal;
import hva.employees.Veterinarian;
import java.io.Serializable;

public class Vaccination implements Serializable{
    private Veterinarian _veterinarian;
    private Vaccine _vaccine;
    private Animal _animal;
    private Damage _damage;

    public Vaccination(Veterinarian veterinarian, Vaccine vaccine, 
                Animal animal, Damage damage){
        _veterinarian = veterinarian;
        _vaccine = vaccine;
        _animal = animal;
        _damage = damage;
    }

    public Damage getDamage() {
        return _damage;
    }

    public Animal getAnimal(){
        return _animal;
    }

    public Veterinarian getVeterinarian(){
        return _veterinarian;
    }

    @Override
    public String toString() {
        return "REGISTO-VACINA|" + _vaccine.getVaccineKey() + "|"
        + _veterinarian.getKey() + "|" + _animal.getSpecies().getKey();
    }
}
