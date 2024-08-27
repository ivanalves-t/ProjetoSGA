package model.exceptions;

public class CnpjRangeException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public CnpjRangeException(String message) {
		super(message);
	}
}
