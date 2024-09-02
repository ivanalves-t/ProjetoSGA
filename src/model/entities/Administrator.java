package model.entities;

import util.DocumentsRepository;

public class Administrator implements DocumentsRepository {

	private String name;
	private String cpf;
	private String password;

	public Administrator() {

	}

	public Administrator(String name, String cpf, String password) {
		this.name = name;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Dados administrativos:\n" + "Nome: " + getName() + "\nCPF: " + getCpf();

	}
}
