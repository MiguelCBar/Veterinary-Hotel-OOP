package hva.exceptions;

import java.io.Serial;

public class VaccineNotAdequateException extends Exception{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private String _vaccineKey;

    private String _animalKey;

    public VaccineNotAdequateException(String vaccineKey, String animalKey){
        _vaccineKey = vaccineKey;
        _animalKey = animalKey;
    }

    public String getVaccineKey() {
        return _vaccineKey;
    }

    public String getAnimalKey() {
        return _animalKey;
    }
}
