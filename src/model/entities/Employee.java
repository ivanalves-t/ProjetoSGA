package model.entities;

public abstract class Employee {

	private String name;
	private String cpf;
	private String gymName;

	public Employee(String name, String cpf, String gymName) {
		this.name = name;
		this.gymName = gymName;
		this.cpf = cpf;
		
	}

	public abstract double payment();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getcpf() {
		return cpf;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

}
