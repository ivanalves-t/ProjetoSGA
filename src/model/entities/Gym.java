package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String ownerCNPJ;
    private String gymName;
    private ArrayList<GymMember> members;
    private ArrayList<Employee> employees;
    private List<Device> devices;
	private double monthlyValue;
    
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
            System.out.println("A academia já foi criada.");
        }
        return instance;
    }

    // Método para obter a instância única da academia
    public static Gym getInstance() {
        if (instance == null) {
            System.out.println("A academia ainda não foi criada.");
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
    
    public void addMaintenanceWorker(MaintenanceEmployee mE) {
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
    
    public void addDevice(Device d) {
    	devices.add(d);
    }
    
    public void removeDevice(Device d) {
    	devices.remove(d);
    }

    public List<Device> getDevices() {
    	return devices;
    }
    
}
