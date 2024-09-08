package model.entities;

import model.services.MembershipPlan;
import model.services.Train;

public class GymMember {

	private String name;
	private String cpf;
	private Train train;
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

	public Train getTrain() {
		return train;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	@Override
	public String toString() {
		return "Member\n" + "Name: " + name + "\nCPF: " + cpf + "\nPassword: " + password + "\nCheckIn : " + checkIn;
	}
}
