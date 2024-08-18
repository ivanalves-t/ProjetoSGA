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
	
	

}
