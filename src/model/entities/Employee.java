package model.entities;

public abstract class Employee {

	private String name;
	private String cpf;

	public Employee(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
		
	}

	public abstract double payment();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

}
