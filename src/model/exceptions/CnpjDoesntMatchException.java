package model.exceptions;

public class CnpjDoesntMatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CnpjDoesntMatchException(String message) {
		super(message);
	}
}
