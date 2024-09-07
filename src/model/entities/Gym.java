package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.services.Report;

public class Gym {
    private static Gym instance;
    private String ownerCNPJ;
    private String gymName;
    private ArrayList<GymMember> members;
    private ArrayList<Employee> employees;
	private double monthlyValue;
    private ArrayList<Report> reports;
    
    // Construtor privado
    private Gym(String gymName, String ownerCNPJ) {
        this.gymName = gymName;
        this.ownerCNPJ = ownerCNPJ;
        this.members = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    // Método para criar a academia
    public static Gym createGym(String gymName, String ownerCNPJ, double monthlyValue) {
        if (instance == null) {
            instance = new Gym(gymName, ownerCNPJ);
        } else {
            System.out.println("Gym already registered!");
        }
        return instance;
    }

    // Método para obter a instância única da academia
    public static Gym getInstance() {
        if (instance == null) {
            System.out.println("Gym was not registered yet.");
        }
        return instance;
    }

    // Métodos de acesso
    public String getGymName() {
        return gymName;
    }

    public String getOwnerCNPJ() {
        return ownerCNPJ;
    }

    public ArrayList<GymMember> getMembers() {
        return new ArrayList<>(members);
    }

    public void addMember(GymMember member) {
        members.add(member);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addInstructor(Instructor instructor) {
        employees.add(instructor);
    }
    
    public void addMaintenanceEmployee(MaintenanceEmployee mE) {
    	employees.add(mE);
    }
    
	public double[] generatePlan() {
		double[] plan = new double[3];
		plan[0] = this.monthlyValue;
		plan[1] = (this.monthlyValue * 3) - (this.monthlyValue * 3 * 0.1);
		plan[2] = (this.monthlyValue * 12) - (this.monthlyValue * 12 * 0.3);
		return plan;
	}
	
	public double getMonthlyValue() {
		return monthlyValue;
	}
	
	public void removeEmployee(Employee e) {
		employees.remove(e);
	}
    
    public void removeGymMember(GymMember gm) {
    	members.remove(gm);
    }
    
    public void addReport(Report report) {
    	reports.add(report);
    }
    
    public void removeReport(Report report) {
    	reports.remove(report);
    }
    
    public List<Report> getReports(){
    	return reports;
    }
    
}
