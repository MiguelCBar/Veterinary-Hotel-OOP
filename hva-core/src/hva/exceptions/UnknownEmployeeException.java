package hva.exceptions;

import java.io.Serial;

public class UnknownEmployeeException extends Exception{
    
    @Serial
	private static final long serialVersionUID = 202407081733L;

    private String _key;

    public UnknownEmployeeException(String key) {
        _key = key;
    }

    public String getKey() {
        return _key;
    }
}
