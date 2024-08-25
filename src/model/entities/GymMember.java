package model.entities;

public class GymMember {

	private String name;
	private String cpf;
	private String instructorName;
	private String[][] trainList;
	private String gymName;
	private String plain;
	private String password;


	public GymMember(String name, String cpf, String gymName, String plain, String password) {
		this.name = name;
		this.cpf = cpf;
		this.plain = plain;
		this.gymName = gymName;
		this.password = password;
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

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}


	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getPassword() {
		return password;
	}
	
	
	
}
