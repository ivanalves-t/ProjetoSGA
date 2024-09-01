package model.exceptions;

public class CnpjAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CnpjAlreadyExistsException(String message) {
		super(message);
	}

}
