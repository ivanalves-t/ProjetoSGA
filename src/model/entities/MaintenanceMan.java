package model.entities;

public class MaintenanceMan extends Employee{
	private double dailyPayment;
	
	public MaintenanceMan(String name, String cpf, double dailyPayment) {
		super(name, cpf);
		this.dailyPayment = dailyPayment;
	}
	
	@Override
	public double payment() {
		return dailyPayment;
	}
	
	@Override
	public String toString() {
		return "Maintenance employee\n" + "Name: " + getName() + "\nCPF: " + getCpf() + "\nPassword: " + getPassword() + "\nWage: " + payment();
	}
	
}
