package model.entities;

public class Administrator {
	private String name;
	private int cpf;
	private final int password;
	private String nameGym;
	private int cnpj;
	
	public Administrator(String name,int cpf, int password, String nameGym, int cnpj) {

		this.name = name;
		this.cpf = cpf;
		this.password = password;
		this.nameGym = nameGym;
		this.cnpj = cnpj;
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

	public String getNameGym() {
		return nameGym;
	}
	
	public int getPassword() {
		return password;
	}

	public void setNameGym(String nameGym) {
		this.nameGym = nameGym;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}
}
