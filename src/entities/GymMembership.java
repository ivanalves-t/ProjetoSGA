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
	
	
}
