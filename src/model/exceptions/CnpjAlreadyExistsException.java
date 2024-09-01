package model.exceptions;

public class CpfAlreadyExcistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfAlreadyExcistsException(String message) {
		super(message);
	}

}
