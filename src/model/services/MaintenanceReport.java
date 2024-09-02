package model.services;

import model.entities.MaintenanceEmployee;

public class MaintenanceReport {

	private MaintenanceEmployee me;
	private String message;
	
	public MaintenanceReport(String message, MaintenanceEmployee me) {
		this.me = me;
		this.message = message;
	}

	public MaintenanceEmployee getMe() {
		return me;
	}

	public String getMessage() {
		return message;
	}
	
	
}
