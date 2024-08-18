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
	
	
}
