package model.services;

import model.entities.GymMember;

public class Train {

	private GymMember gymMember;
	private String[][] trainList = {
		    {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"},
		    null
		};
	public Train(GymMember gymMember, String[][] trainList) {
		super();
		this.gymMember = gymMember;
		this.trainList = trainList;
	}
	public GymMember getGymMember() {
		return gymMember;
	}
	public void setGymMember(GymMember gymMember) {
		this.gymMember = gymMember;
	}
	public String[][] getTrainList() {
		return trainList;
	}
	public void setTrainList(String[][] trainList) {
		this.trainList = trainList;
	}
	
	
}
