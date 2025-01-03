package hva.exceptions;

import java.io.Serial;

public class UnknownVaccineException extends Exception{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private String _key;

    public UnknownVaccineException(String key) {
        _key = key;
    }

    public String getKey() {
        return _key;
    }
}
