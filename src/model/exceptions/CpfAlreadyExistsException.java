package model.exceptions;

public class CpfAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CpfAlreadyExistsException(String message) {
		super(message);
	}

	 
}
