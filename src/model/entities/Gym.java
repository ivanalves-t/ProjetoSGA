package model.entities;

import java.util.ArrayList;

public class Gym {

	private String OwnerCNPJ;
	private String GymName;
	private ArrayList<Employee> employees;
	private ArrayList<GymMembership> memberships;
	private double[] planValues;
	
	public Gym(String GymName, String ownerCNPJ, ArrayList<Employee> employees, ArrayList<GymMembership> memberships,
			double[] planValues) {
		this.GymName = GymName;
		this.OwnerCNPJ = ownerCNPJ;
		this.employees = employees;
		this.memberships = memberships;
		this.planValues = planValues;
	}

	public String getOwnerCNPJ() {
		return OwnerCNPJ;
	}

	public void setOwnerCNPJ(String ownerCNPJ) {
		OwnerCNPJ = ownerCNPJ;
	}

	public String getGymName() {
		return GymName;
	}

	public void setGymName(String gymName) {
		GymName = gymName;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public ArrayList<GymMembership> getMemberships() {
		return memberships;
	}
	
	public void addMemberships(GymMembership gm) {
		memberships.add(gm);
	}

	public double[] getPlanValues() {
		return planValues;
	}
}
