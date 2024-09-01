package model.entities;

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

}
