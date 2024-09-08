package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.entities.Employee;
import model.entities.Gym;
import model.entities.GymMember;
import model.entities.Instructor;
import model.exceptions.CpfDoesntMatchException;
import util.ValidDocumentsScan;

public class MenuInstructor {
	private static Gym gym;
	private List<Employee> employees = gym.getEmployees();
	private Instructor currentlyInstructor;
	private String currentlyCpf;
	private static MenuInstructor instance;
	private static Scanner sc = new Scanner(System.in);
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

	private MenuInstructor() {

	}

	public static MenuInstructor getInstance() {
		if (instance == null) {
			instance = new MenuInstructor();
		}
		return instance;
	}

	public void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_PURPLE_BACKGROUND);
			System.out.println("=============== INSTRUCTOR MENU ===============");
			System.out.println("|  1 - Sign in instructor account             |");
			System.out.println("|  2 - Show current members frequency         |");
			System.out.println("|  0 - Back to previous menu                  |");
			System.out.println("===============================================");
			System.out.println(ANSI_RESET);

			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();

				switch (opt) {
				case 1:
					accessValidateInstructorAccount();
					break;
				case 2:
					showCurrentlyMembers();
					break;
				case 0:
					running = false;
					System.out.println("Going back to previous menu...");
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Invalid value! Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number inside the range of options!");
				System.out.println(ANSI_RESET);
			}
		}
	}
	
	private void showCurrentlyMembers() {
		System.out.println(ANSI_PURPLE_BACKGROUND);
		System.out.println("=============== CURRENTLY MEMBERS ===============");
		System.out.println(ANSI_RESET);
		int sum = 0;
		List<GymMember> members = gym.getMembers();
		for (GymMember gm : members) {
			if (gm.getCheckIn() == true) {
				sum++;
			}
		}
		System.out.println("Total currently members on gym: " + sum);
	}
	
	private void accessValidateInstructorAccount() {
		System.out.print("Type your CPF: ");
		String cpf = ValidDocumentsScan.readCpfVal();
		if (cpf == null) {
			System.out.println("Error: Employee doesn't registered on system!");
			return;
		}
		System.out.print("Type your password: ");
		String password = sc.nextLine();
		try {
			for (Employee employee : employees) {
				if (!employee.getCpf().equals(cpf) && !(employee instanceof Instructor)) {
					throw new CpfDoesntMatchException(" Wrong cpf!");
				}
				if (!employee.getPassword().equals(password)) {
					throw new IllegalArgumentException(" Wrong Password!");
				}
				currentlyCpf = employee.getCpf();
				currentlyInstructor = (Instructor) employee;
				accessAccountInstructor();
			}
			
		}catch(CpfDoesntMatchException | IllegalArgumentException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: " + e.getMessage());
			System.out.println(ANSI_RESET);
		}
	}
	
	private void accessAccountInstructor() {

		boolean running = true;

		while (running == true) {
			System.out.println(ANSI_PURPLE_BACKGROUND);
			System.out.println("=============== MENU MEMBER ===============");
			System.out.println("|  1 - Show train list                    |");
			System.out.println("|  2 - Show linked instructor's name      |");
			System.out.println("|  3 - Show currently membership plan     |");
			System.out.println("|  0 - Back to previous menu              |");
			System.out.println("===========================================");
			System.out.println(ANSI_RESET);
			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();
				switch (opt) {
				
				case 1:
					showTrain();
					break;
				case 2:
					showInstructorName();
					break;
				case 3:
					showCurrentlyMembershipPlan();
				case 0:
					running = false;
					currentlyCpf = null;
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Invalid value! Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}
				
			}catch(InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println(ANSI_RESET);
			}

		}
	}
}
