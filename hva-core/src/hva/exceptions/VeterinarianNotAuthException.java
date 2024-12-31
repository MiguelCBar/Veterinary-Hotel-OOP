package hva.exceptions;

import java.io.Serial;

public class VeterinarianNotAuthException extends Exception{

    @Serial
    private static final long serialVersionUID = 202407081733L;
    
    private String _veterinarianKey;
    private String _speciesKey;

    public VeterinarianNotAuthException(String veterinarianKey, 
    String speciesKey){
        _veterinarianKey = veterinarianKey;
        _speciesKey = speciesKey;

    }

    public String getSpeciesKey() {
        return _speciesKey;
    }

    public String getVeterinarianKey() {
        return _veterinarianKey;
    }
}
