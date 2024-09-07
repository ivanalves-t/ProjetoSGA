package model.entities;

import java.util.List;

import model.services.Report;
import util.DocumentsRepository;

public class Administrator implements DocumentsRepository {

	private String name;
	private String cpf;
	private String password;
	private List<Report> reports;
	
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
	
	public void addReport(Report report) {
		reports.add(report);
	}
}
