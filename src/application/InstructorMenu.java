package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.entities.Employee;
import model.entities.Gym;
import model.entities.GymMember;
import model.entities.Instructor;
import model.exceptions.CpfDoesntMatchException;
import model.services.Train;
import util.ValidDocumentsScan;

public class InstructorMenu {
	private Gym gym;
	private Instructor currentlyInstructor;

	private static Scanner sc = new Scanner(System.in);
	private GymMember gmTrainee;
	public static final String ANSI_RESET = "\u001B[0m";
	
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
//done
	public InstructorMenu() {
		gym = Gym.getInstance();
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
				sc.nextLine();

				switch (opt) {
				case 1:
					running = false;
					accessValidateInstructorAccount();
					break;
				case 2:
					running = false;
					showCurrentlyMembers();
					break;
				case 0:
					running = false;
					System.out.println("Going back to main menu...\n\n");
					currentlyInstructor = null;
					gmTrainee = null;
					MainMenu.main(null);
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
				sc.nextLine();
			}
		}
	}

	private void showCurrentlyMembers() {
	    System.out.println(ANSI_PURPLE_BACKGROUND);
	    System.out.println("=============== CURRENTLY MEMBERS ===============");
	    System.out.println(ANSI_RESET);
	    
	    StringBuilder sb = new StringBuilder();
	    try {
	    	List<GymMember> members = gym.getMembers();
	    	if (members.isEmpty()) {
	    		sb.append("No members data\n");
	    	} else {
	    		// Corrigi o cabeçalho para incluir "NeedTrain"
	    		sb.append(String.format("%-20s%-20s%-20s%-20s\n", "Name", "CPF", "Freq", "NeedTrain"));
	    		for (GymMember gm : members) {
	    			// Ajustei o formato para não incluir o ".2f" na coluna "NeedTrain"
	    			sb.append(String.format("%-20s%-20s%-20.2f%-20s\n",
	    					gm.getName(), gm.getCpf(), gm.getCheckIn(), gm.getTrain() == null ? "yes" : "no"));
	    		}
	    	}
	    }catch (NullPointerException e) {
	    	System.out.println("Error: " + "No one member registered yet.");
	    	displayMenu();
	    }
	    
	    System.out.print(sb.toString()); // Exibe a construção finalizada
	    displayMenu();
	}



	private void accessValidateInstructorAccount() {
	    System.out.print("Type your CPF: ");
	    String cpf = ValidDocumentsScan.readCpfVal();
	    if (cpf == null) {
	        System.out.println("Error: Ask to your admin to register you!");
	        displayMenu();
	        return;
	    }
	    System.out.print("Type your password: ");
	    String password = ValidDocumentsScan.readPassword();
	    try {
	        List<Employee> employees = gym.getEmployees();
	        Employee foundEmployee = null;
	        for (Employee employee : employees) {
	            if (employee.getCpf().equals(cpf) && employee instanceof Instructor) {
	                foundEmployee = employee;
	                break;
	            }
	        }
	        if (foundEmployee == null) {
	            throw new CpfDoesntMatchException("Wrong CPF!");
	        }
	        if (!foundEmployee.getPassword().equals(password)) {
	            throw new IllegalArgumentException("Wrong Password!");
	        }
	        currentlyInstructor = (Instructor) foundEmployee;
	        accessAccountInstructor();
	        
	    } catch (CpfDoesntMatchException | IllegalArgumentException e) {
	        System.out.println(ANSI_RED_BACKGROUND);
	        System.out.println("Error: " + e.getMessage());
	        System.out.println(ANSI_RESET);
	        System.out.println("Tip: first access? your password could be your CPF");
	        displayMenu();
	    }
	}

	private void accessAccountInstructor() {

		boolean running = true;

		while (running == true) {
			System.out.println(ANSI_PURPLE_BACKGROUND);
			System.out.println("============= INSTRUCTOR MENU =============");
			System.out.println("|  1 - Create a train                     |");
			System.out.println("|  2 - Delete a train                     |");
			System.out.println("|  3 - Modify a train                     |");
			System.out.println("|  4 - Modify Password                    |");
			System.out.println("|  0 - Back to previous menu              |");
			System.out.println("===========================================");
			System.out.println(ANSI_RESET);
			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();
				switch (opt) {

				case 1:
					running = false;
					validateCreateTrain();
					break;
				case 2:
					running = false;
					deleteTrain();
					break;
				case 3:
					running = false;
					validateAlterTrain();
					break;
				case 4:
					running = false;
					modifyPassword();
					break;
				case 0:
					running = false;
					currentlyInstructor = null;
					gmTrainee = null;
					displayMenu();
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Invalid value! Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println(ANSI_RESET);
				sc.nextLine(); // limpar o buffer
			}

		}
	}
	private void modifyPassword() {
		System.out.println(ANSI_PURPLE_BACKGROUND);
		System.out.println("=============== SET NEW PASSWORD ===============");
		System.out.println(ANSI_RESET);
		
		System.out.print("Please, enter your new password: ");
		String password = ValidDocumentsScan.readPassword();
		for (Employee employee : gym.getEmployees()) {
			if (employee.getCpf().equals(currentlyInstructor.getCpf())){
				employee.setPassword(password);
			}
		}
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("Password modified successfully!");
		System.out.println(ANSI_RESET);
		System.out.println("Please sign in again.");
		displayMenu();
	}

	private void validateCreateTrain() {
		System.out.print("Type the CPF of your trainee member: ");
		String cpfTrainee = ValidDocumentsScan.readCpfVal();
		if (cpfTrainee == null) {
			System.out.println("Try again later.");
			accessAccountInstructor();
			return;
		}
		boolean found = false;
		List<GymMember> members = gym.getMembers();
		for (GymMember gymMember : members) {
			if (gymMember.getCpf().equals(cpfTrainee)) {
				gmTrainee = gymMember;
				found = true;
				createTrain();
			}
		}
		if (!found) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: Member doesn't registered on system.");
			System.out.println(ANSI_RESET);
			accessAccountInstructor();
			return;
		}
	}
	
	private void validateAlterTrain() {
		System.out.print("Type the CPF of your trainee member: ");
		String cpfTrainee = ValidDocumentsScan.readCpfVal();
		if (cpfTrainee == null) {
			System.out.println("Try again later.");
			accessAccountInstructor();
			return;
		}
		boolean found = false;
		List<GymMember> members = gym.getMembers();
		for (GymMember gymMember : members) {
			if (gymMember.getCpf().equals(cpfTrainee)) {
				gmTrainee = gymMember;
				found = true;
				alterTrain();
			}
		}
		if (!found) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: Member doesn't registered on system.");
			System.out.println(ANSI_RESET);
			accessAccountInstructor();
			return;
		}
	}
	
	private void createTrain() {
		System.out.println(ANSI_PURPLE_BACKGROUND);
		System.out.println("=============== CREATE TRAIN ===============");
		System.out.println(ANSI_RESET);
		String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		String[] exercises = new String[7];
		
		System.out.println("Please, enter a train for each day on week:");
		for (int i = 0; i < daysOfWeek.length; i++) {
			System.out.print(daysOfWeek[i] + ": ");
			exercises[i] = ValidDocumentsScan.readTrain();
		}
		String[][] trainList = {daysOfWeek, exercises};
		Train train = new Train(currentlyInstructor.getName(), gmTrainee.getName(), trainList);
		for (GymMember gymMember : gym.getMembers()) {
			if (gymMember.getCpf().equals(gmTrainee.getCpf())) {
				gymMember.setTrain(train);				
			}
		}
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("The train was created successfully!");
		System.out.println(ANSI_RESET);
		accessAccountInstructor();
	}
	
	private void deleteTrain() {
		System.out.println(ANSI_PURPLE_BACKGROUND);
		System.out.println("=============== DELETE TRAIN ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Type the CPF of your trainee member: ");
		String cpfTrainee = ValidDocumentsScan.readCpfVal();
		if (cpfTrainee == null) {
			System.out.println("Try again later.");
			accessAccountInstructor();
			return;
		}
		boolean found = false;
		for (GymMember gymMember : gym.getMembers()) {
			if (gymMember.getCpf().equals(cpfTrainee)) {
				gmTrainee = gymMember;
				found = true;
				gymMember.setTrain(null);
				System.out.println(ANSI_GREEN_BACKGROUND);
				System.out.println("The train was deleted successfully!");
				System.out.println(ANSI_RESET);
				accessAccountInstructor();
			}
		}
		if (!found) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: Member doesn't registered on system.");
			System.out.println(ANSI_RESET);
			accessAccountInstructor();
			return;
		}
	}
	
	
	private void alterTrain() {
		System.out.println(ANSI_PURPLE_BACKGROUND);
		System.out.println("=============== ALTER TRAIN ===============");
		System.out.println(ANSI_RESET);
		String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		String[] exercises = new String[7];
		
		System.out.println("Please, enter a train for each day on week:");
		for (int i = 0; i < daysOfWeek.length; i++) {
			System.out.print(daysOfWeek[i] + ": ");
			exercises[i] = ValidDocumentsScan.readTrain();
		}
		String[][] trainList = {daysOfWeek, exercises};
		Train train = new Train(currentlyInstructor.getName(), gmTrainee.getName(), trainList);
		for (GymMember gymMember : gym.getMembers()) {
			if (gymMember.getCpf().equals(gmTrainee.getCpf())) {
				gymMember.setTrain(train);				
			}
		}
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("The train was deleted successfully!");
		System.out.println(ANSI_RESET);
		accessAccountInstructor();
	}
}
