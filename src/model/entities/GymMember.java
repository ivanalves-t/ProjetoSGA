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


	public GymMember(String name, String cpf, MembershipPlan membershipPlan) {
		this.name = name;
		this.cpf = cpf;
		this.membershipPlan = membershipPlan;
		this.password = cpf;
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
	
	public void setTrain(Train train) {
		this.train = train;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nCPF: " + cpf + "\nPassword: " + password
				+ "\nCheckIn : " + checkIn + "\nMembership kind: " + membershipPlan.getKind() + "\nPrice: R$ " + membershipPlan.getMonthly();
	}
}
