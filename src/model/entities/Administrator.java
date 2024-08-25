package model.entities;

public class Administrator {
	private String name;
	private int CPF;
	private final int password;
	private String nameGym;
	private int CNPJ;
	
	public Administrator(String name,int CPF, int password, String nameGym, int CNPJ) {
		this.name = name;
		this.CPF = CPF;
		this.password = password;
		this.nameGym = nameGym;
		this.CNPJ = CNPJ;
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

	public String getNameGym() {
		return nameGym;
	}
	
	public int getPassword() {
		return password;
	}

	public void setNameGym(String nameGym) {
		this.nameGym = nameGym;
	}

	public int getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(int cNPJ) {
		CNPJ = cNPJ;
	}
}
