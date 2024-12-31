package hva.exceptions;

import java.io.Serial;

/** Thrown when an responsability doesn't exist */
public class NoResponsabilityException extends Exception {

	@Serial
	private static final long serialVersionUID = 202407081733L;

	private String _responsability;


	public NoResponsabilityException(String responsability) {
		_responsability = responsability;
	}

	public String getResponsability() {
		return _responsability;
	}

}