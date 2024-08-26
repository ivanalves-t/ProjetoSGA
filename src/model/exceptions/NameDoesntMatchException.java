package model.exceptions;

public class NameDoesntMatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NameDoesntMatchException(String message) {
		super(message);
	}

	
}
