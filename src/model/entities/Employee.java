package model.entities;

public abstract class Employee {

	private String name;
	private String cpf;
	private String password;

	public Employee(String name, String cpf, String password) {
		this.name = name;
		this.cpf = cpf;
		this.password = password;
	}

	public abstract double payment();
	
	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPassword() {
		return password;
	}

	
}
