package model.exceptions;

public class CpfDoesntMatchException extends Exception{

	private static final long serialVersionUID = 1L;

	public CpfDoesntMatchException(String message) {
		super(message);
	}
}
