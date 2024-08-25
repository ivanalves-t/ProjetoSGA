package model.entities;

public class MaintenanceWorker extends Employee{

	private double dailyPayment;

	public MaintenanceWorker(String name, String cpf, String gymName, double dailyPayment) {
		super(name, cpf, gymName);
		this.dailyPayment = dailyPayment;
	}

	public double getDailyPayment() {
		return dailyPayment;
	}

	public void setDailyPayment(double dailyPayment) {
		this.dailyPayment = dailyPayment;
	}

	@Override
	public double payment() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
