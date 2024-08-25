package model.entities;

import java.util.ArrayList;

public class Instructor extends Employee{

	private double wage;
	private ArrayList<GymMember> listMembers = new ArrayList<>();
	

	public Instructor(String name, String cpf, String gymName, double wage, ArrayList<GymMember> listMembers) {
		super(name, cpf, gymName);
		this.listMembers = listMembers;
		this.wage = wage;
		
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public ArrayList<GymMember> getListMember() {
		return listMembers;
	}

	public void setListMember(ArrayList<GymMember> listMembers) {
		this.listMembers = listMembers;
	}

	@Override
	public double payment() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
