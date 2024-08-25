package model.exceptions;

public class CpfRangeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CpfRangeException (String message) {
		super(message);
	}
}
