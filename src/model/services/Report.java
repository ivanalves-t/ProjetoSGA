package model.services;

public abstract class Report {
	
	protected String message;
	
	public Report(String message) {
		this.message = message;
	}
	
	public abstract String generateReport();
	public abstract String mergeReport(String meta);
}
