package model.entities;

public class MaintenanceWorker extends Employee{

	private double dailyPayment;

	public MaintenanceWorker(String name, String cpf, String gymName) {
		super(name, cpf, gymName);
		this.dailyPayment = dailyPayment;
	}

	public double getDailyPayment() {
		return dailyPayment;
	}

	public void setDailyPayment(double dailyPayment) {
		this.dailyPayment = dailyPayment;
	}
	
	
	
	
}
