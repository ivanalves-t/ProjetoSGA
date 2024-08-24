package model.entities;

public class Instructor extends Employee{

	public double wage;
	public GymMembership[] students;
	
	public Instructor(String name, String cpf, String gymName, double wage, GymMembership[] students) {
		super(name, cpf, gymName);
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
