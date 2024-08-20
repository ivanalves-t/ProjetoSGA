package entities;

public abstract class Employee {

	private String name;
	private String CPF;
	private String gymName;

	public Employee(String name, String cPF, String gymName) {
		this.name = name;
		this.CPF = cPF;
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

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

}
