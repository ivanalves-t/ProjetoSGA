package entities;

public class Instructor extends Employee{

	public double wage;
	public GymMembership[] students;
	
	public Instructor(String name, String cPF, String gymName, double wage, GymMembership[] students) {
		super(name, cPF, gymName);
		this.students = students;
		this.wage = wage;
		
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}
	
}
