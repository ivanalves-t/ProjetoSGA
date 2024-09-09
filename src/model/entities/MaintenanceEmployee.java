package model.entities;

import model.services.MaintenanceReport;

public class MaintenanceEmployee extends Employee{
	private double dailyPayment;
	
	public MaintenanceEmployee(String name, String cpf, double dailyPayment) {
		super(name, cpf);
		this.dailyPayment = dailyPayment;
	}
	
	@Override
	public double payment() {
		return dailyPayment;
	}
	
	public MaintenanceReport generateMaintenanceReport(String message) {
		return new MaintenanceReport(message);
	}
	
	@Override
	public String toString() {
		return "Maintenance employee\n" + "Name: " + getName() + "\nCPF: " + getCpf() + "\nPassword: " + getPassword() + "\nWage: " + payment();
	}
	
}
