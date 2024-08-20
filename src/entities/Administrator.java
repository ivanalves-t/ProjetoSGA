package entities;

public class Administrator {
	private String name;
	private String CNPJ;
	private Gym[] gyms;

	public Administrator(String name, String cNPJ, Gym[] gyms) {
		this.name = name;
		this.CNPJ = cNPJ;
		this.gyms = gyms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public Gym[] getGyms() {
		return gyms;
	}

}
