package model.entities;

public class Administrator {
	
	private String name;
	private String cpf;
	private String password;
	private String nameGym;
	private String cnpj;
	
	public Administrator() {
		
	}
	
	public Administrator(String name, String cpf, String password, String nameGym, String cnpj) {
	    this.name = name;
	    this.password = password;
	    this.nameGym = nameGym;
	    this.cnpj = cnpj;
	    this.cpf = cpf;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNameGym() {
		return nameGym;
	}
	
	public String getPassword() {
		return password;
	}

	public void setNameGym(String nameGym) {
		this.nameGym = nameGym;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
