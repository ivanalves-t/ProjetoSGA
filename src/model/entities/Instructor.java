package model.entities;

import java.util.ArrayList;

public class Instructor extends Employee{

	private double wage;
	private ArrayList<GymMember> listMembers = new ArrayList<>();
	
	
	public Instructor (String name,String cpf, String password, double wage) {
		super(name, cpf, password);
		this.wage = wage;
		this.listMembers = new ArrayList<GymMember>();
	}
	
	@Override
	public double payment() {
		return wage;
	}

	public ArrayList<GymMember> getListMembers() {
		return listMembers;
	}
	
	@Override
	public String toString() {
		return "Instructor\n" + "Name: " + getName() + "\nCPF: " + getCpf() + "\nPassword: " + getPassword() + "\nWage: " + payment();
	}
	
}
