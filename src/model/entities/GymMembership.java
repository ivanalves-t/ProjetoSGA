package model.entities;

public class GymMembership {

	private String name;
	private String CPF;
	private String instructorName;
	private String[][] trainList;
	private String gymName;
	private String plane;

	public GymMembership(String name, String CPF, String instructorName, String[][] trainList, String gymName, String plane) {
		this.name = name;
		this.CPF = CPF;
		this.instructorName = instructorName;
		this.trainList = trainList;
		this.gymName = gymName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCPF() {
		return CPF;
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

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public String getPlane() {
		return plane;
	}

	public void setPlane(String plane) {
		this.plane = plane;
	}
	
}
