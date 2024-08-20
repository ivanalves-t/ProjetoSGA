package entities;

public class GymMembership {

	private String name;
	private String CPF;
	private String instructorName;
	private String[][] trainList;
	private String gymName;

	public GymMembership(String name, String cPF, String instructorName, String[][] trainList, String gymName) {
		this.name = name;
		CPF = cPF;
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

}
