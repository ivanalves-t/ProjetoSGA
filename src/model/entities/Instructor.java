package model.entities;

import java.util.ArrayList;

public class Instructor extends Employee{

	private double wage;
	private ArrayList<GymMembership> listMember = new ArrayList<>();
	
	public Instructor(String name, String cPF, String gymName, double wage, ArrayList<GymMembership> listMember) {
		super(name, cPF, gymName);
		this.listMember = listMember;
		this.wage = wage;
		
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public ArrayList<GymMembership> getListMember() {
		return listMember;
	}

	public void setListMember(ArrayList<GymMembership> listMember) {
		this.listMember = listMember;
	}
	
}
