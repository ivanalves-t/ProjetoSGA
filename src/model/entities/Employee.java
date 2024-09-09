package model.entities;

public abstract class Employee {

	private String name;
	private String cpf;
	private String password;

	public Employee(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
		this.password = cpf;
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

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
