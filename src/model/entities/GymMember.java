package model.entities;

import model.services.MembershipPlan;

public class GymMember {

	private String name;
	private String cpf;
	private String instructorName;
	private String[][] trainList = {
		    {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"},
		    null
		};
	private MembershipPlan membershipPlan;
	private String password;
	private boolean checkIn;


	public GymMember(String name, String cpf, MembershipPlan membershipPlan, String password) {
		this.name = name;
		this.cpf = cpf;
		this.membershipPlan = membershipPlan;
		this.password = password;
		this.checkIn = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String[][] getTrainList() {
		return trainList;
	}

	public void setTrainList(String[][] trainList) {
		this.trainList = trainList;
	}

	public MembershipPlan getPlan() {
		return membershipPlan;
	}


	public String getPassword() {
		return password;
	}
	
	public void setCheckIn() {
		this.checkIn = true;
	}

	public MembershipPlan getMembershipPlan() {
		return membershipPlan;
	}

	public boolean getCheckIn() {
		return checkIn;
	}
	
    
    
}
