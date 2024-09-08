package model.entities;

import java.util.List;

import model.services.Report;
import util.DocumentsRepository;

public class Administrator implements DocumentsRepository {

	private String name;
	private String cpf;
	private String password;
	private List<Report> reports;
	private static Gym gym;
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
		StringBuilder sb = new StringBuilder();
		sb.append("Administrator data: \n" + "Admin Name: " + name + "\nAdmin CPF: " + cpf + "\nAdmin password: " + password);
		
		List<Employee> employees = gym.getEmployees();
		List<GymMember> members = gym.getMembers();
		sb.append("\n======= MEMBERS DATA =======\n");
		if (gym.getMembers().isEmpty()) {
			sb.append("No members data");
		}else {
			for (GymMember gm : members) {
				sb.append(gm + "\n");
			}	
		}
		sb.append("\n======= EMPLOYEES DATA =======\n");
		if (gym.getEmployees().isEmpty()) {
			sb.append("No employees data");
		}else {
			for (Employee e : employees) {
				sb.append(e + "\n");
			}	
		}
		
		return sb.toString();
	}
	
	public void addReport(Report report) {
		reports.add(report);
	}
}
