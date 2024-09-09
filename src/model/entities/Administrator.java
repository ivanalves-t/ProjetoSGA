package model.entities;

import java.util.List;

import util.DocumentsRepository;

public class Administrator implements DocumentsRepository {

	private String name;
	private String cpf;
	private String password;
	private static Gym gym;
	public Administrator() {

	}

	public Administrator(String name, String cpf, Gym gym) {
		this.gym = gym;
		this.name = name;
		this.password = cpf;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	    sb.append("\n======= ADMIN DATA =======\n")
	      .append("Admin Name: ").append(name).append("\n")
	      .append("Admin CPF: ").append(cpf).append("\n")
	      .append("Admin password: ").append(password).append("\n\n");

	    List<Employee> employees = gym.getEmployees();
	    List<GymMember> members = gym.getMembers();
	    sb.append("\n==================== MEMBERS DATA =====================\n");
	    if (members.isEmpty()) {
	        sb.append("No members data\n");
	    } else {
	        sb.append(String.format("%-15s%-15s%-15s%-15s%-15s\n", "Name", "CPF", "Type", "Monthly", "Password"));
	        for (GymMember gm : members) {
	            sb.append(String.format("%-15s%-15s%-15s%-15.2f%-15s\n",
	                    gm.getName(), gm.getCpf(), gm.getMembershipPlan().getKind(), gm.getMembershipPlan().getMonthly(), gm.getPassword()));
	        }
	    }

	    sb.append("\n======= EMPLOYEES DATA =======\n");
	    if (employees.isEmpty()) {
	        sb.append("No employees data\n");
	    } else {
	        sb.append(String.format("%-15s%-15s%-15s%-15s%-15s\n", "Name", "CPF", "Type", "Payment", "Password"));
	        for (Employee e : employees) {
	            sb.append(String.format("%-15s%-15s%-15s%-15.2f%-15s\n",
	                    e.getName(), e.getCpf(), e.getClass().getSimpleName(), e.payment(), e.getPassword()));
	        }
	    }

	    return sb.toString();
	}


	
}
