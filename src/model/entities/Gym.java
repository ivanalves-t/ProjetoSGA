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
    
    // Construtor privado para garantir que não pode ser chamado externamente
    private Gym(String gymName, String ownerCNPJ, double monthlyValue) {
        this.gymName = gymName;
        this.ownerCNPJ = ownerCNPJ;
        this.monthlyValue = monthlyValue;
        this.members = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    // Método para criar a academia ou retornar a instância existente
    public static Gym createGym(String gymName, String ownerCNPJ, double monthlyValue) {
        if (instance == null) {
            instance = new Gym(gymName, ownerCNPJ, monthlyValue);
        }
        return instance;
    }
    
    private Gym() {
        this.members = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    // Método para obter a instância única da academia
    public static Gym getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Gym ainda não foi criada. Utilize createGym() primeiro.");
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
        return new ArrayList<>(employees);
    }

    public void addInstructor(Instructor instructor) {
        employees.add(instructor);
    }
    
    public void addMaintenanceEmployee(MaintenanceMan mE) {
        employees.add(mE);
    }

    public double[] generatePlan() {
        double[] plan = new double[3];
        plan[0] = this.monthlyValue;
        plan[1] = ((this.monthlyValue * 3) - (this.monthlyValue * 3 * 0.1)) / 3;
        plan[2] = ((this.monthlyValue * 12) - (this.monthlyValue * 12 * 0.3)) / 12;
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

    public List<Report> getReports() {
        return reports;
    }
    
    public void removeReports() {
    	this.reports = new ArrayList<>();
    }
}
