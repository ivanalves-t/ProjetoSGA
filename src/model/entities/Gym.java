package model.entities;

import java.util.ArrayList;

public class Gym{

	private String OwnerCNPJ;
	private String GymName;
	private ArrayList<Employee> employees;
	private ArrayList<GymMember> members;
	private double[] planValues;
	static int currentlyMembers = 0;
	static int currentlyInstructors = 0;
	
	public Gym() {
		this.members = new ArrayList<>();
		this.employees = new ArrayList<>();
	}
	
	public Gym(String GymName, String ownerCNPJ,  double[] planValues) {
		this.GymName = GymName;
		this.OwnerCNPJ = ownerCNPJ;
		this.planValues = planValues;
		this.employees = new ArrayList<>();
		this.members = new ArrayList<>();
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

	public ArrayList<GymMember> getMembers() {
		return members;
	}
	
	public void addMembers(GymMember gm) {
		members.add(gm);
	}
	
	public void deleteMembers(GymMember gm) {
		members.remove(gm);
	}

	public double[] getPlanValues() {
		return planValues;
	}

	public void setPlanValues(double[] planValues) {
		this.planValues = planValues;
	}
	
	public double[] generatePlan(double monthlyValue) {
		double[] plan = new double[3];
		plan[0] = monthlyValue;
		plan[1] = (monthlyValue * 3) - (monthlyValue * 3 * 0.1);
		plan[1] = (monthlyValue * 12) - (monthlyValue * 12 * 0.3);
		return plan;
	}
	
	

}
