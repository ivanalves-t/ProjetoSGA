package model.entities;

public class Administrator {
	private String name;
	private int cpf;
	private Gym[] gyms;
	private final int password;

	public Administrator(String name,int cpf, int password) {
		this.name = name;
		this.cpf = cpf;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCpf() {
		return cpf;
	}

	public Gym[] getGyms() {
		return gyms;
	}

	public void setGyms(Gym[] gyms) {
		this.gyms = gyms;
	}

	public int getPassword() {
		return password;
	}
	
}
