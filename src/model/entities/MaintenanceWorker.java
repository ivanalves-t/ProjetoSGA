package model.entities;

public class MaintenanceWorker extends Employee{

	private double dailyPayment;

	public MaintenanceWorker(String name, String cPF, String gymName) {
		super(name, cPF, gymName);
		this.dailyPayment = dailyPayment;
	}

	public double getDailyPayment() {
		return dailyPayment;
	}

	public void setDailyPayment(double dailyPayment) {
		this.dailyPayment = dailyPayment;
	}
	
	
	
	
}
