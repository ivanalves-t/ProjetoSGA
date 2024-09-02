package model.services;

import java.time.LocalDate;

public interface Report {
	

	public static final LocalDate date = LocalDate.now();
	
	public abstract String generateReport();
}
