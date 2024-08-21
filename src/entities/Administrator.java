package entities;

public class Administrator {
	private String name;
	private int CPF;
	private Gym[] gyms;
	private final int password;

	public Administrator(String name,int CPF, int password) {
		this.name = name;
		this.CPF = CPF;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCPF() {
		return CPF;
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
