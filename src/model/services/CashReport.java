package model.services;

public class CashReport extends Report{

	public CashReport(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String generateReport() {
		return message;
	}

	@Override
	public String mergeReport(String meta) {
		return message + meta;
	}
}
