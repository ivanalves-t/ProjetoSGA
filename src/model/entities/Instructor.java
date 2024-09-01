package model.entities;

import java.util.ArrayList;

public class Instructor extends Employee{

	private double wage;
	private String password;
	private ArrayList<GymMember> listMembers = new ArrayList<>();
	

	public Instructor(String name, String cpf, String gymName, double wage, ArrayList<GymMember> listMembers) {
		super(name, cpf);
		this.listMembers = listMembers;
		this.wage = wage;
		
	}
	
	public Instructor (String name,String cpf, String password, double wage) {
		super(name, cpf);
		this.password = password;
		this.wage = wage;
		this.listMembers = new ArrayList<GymMember>();
	}
	
	@Override
	public double payment() {
		return wage;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<GymMember> getListMembers() {
		return listMembers;
	}
	
	
}
