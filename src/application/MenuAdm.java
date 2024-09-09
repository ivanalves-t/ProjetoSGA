package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.entities.Administrator;
import model.entities.Employee;
import model.entities.Gym;
import model.entities.GymMember;
import model.entities.Instructor;
import model.entities.MaintenanceEmployee;
import model.exceptions.CpfDoesntMatchException;
import model.services.CashReport;
import model.services.FrequencyReport;
import model.services.MembershipPlan;
import model.services.Report;
import util.ValidDocumentsScan;
// done
public class MenuAdm {

	// Singleton instances. Unique multi instances

	private static MenuAdm instance;
	private static Scanner sc = new Scanner(System.in);
	private static Gym gym;
	private static Administrator adm;

	private MenuAdm() {

	}

	// Background colors
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

	public static MenuAdm getInstance() {
		if (instance == null) {
			instance = new MenuAdm();
		}
		return instance;
	}

	// Adm main menu

	public void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== ADM MENU ===============");
			System.out.println("|  1 - Create an admin account         |");
			System.out.println("|  2 - Sign in the admin account       |");
			System.out.println("|  0 - Back to previous menu           |");
			System.out.println("========================================");
			System.out.println(ANSI_RESET);
			System.out.println("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();
				switch (opt) {
				case 1:
					if (adm != null) {
						throw new IllegalArgumentException("Admin account was already registered!");
					}
					running = false;
					createAccountAdm();
					break;
				case 2:
					if (adm == null) {
						throw new NullPointerException("Admin account not registered yet!");
					}
					running = false;
					accessAccountAdm();
					break;
				case 0:
					running = false;
					System.out.println("Going back to main menu...\n\n");
					Program.main(null);
					break;
				default:
					System.out.println("\u001B[41m");
					System.out.println("Error: Invalid value! Type a number between the valid options!");
					System.out.println("\u001B[0m");
					break;
				}

			} catch (NullPointerException | IllegalArgumentException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: " + e.getMessage());
				System.out.println(ANSI_RESET);
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number inside the range of options!");
				System.out.println(ANSI_RESET);
			}
		}
	}

	// create admin account
	private void createAccountAdm() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("========== CREATE ACCOUNT ADM =========");
		System.out.println(ANSI_RESET);
		System.out.print("Enter your CPF: ");
		String cpf = ValidDocumentsScan.readNewCpf();
		System.out.print("Type your name: ");
		String name = ValidDocumentsScan.readName();
		System.out.print("Create an password: ");
		String password = ValidDocumentsScan.readPassword();

		adm = new Administrator(name, cpf, password);
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("Admin account created successfully!");
		System.out.println(ANSI_RESET);

		System.out.println("Now register your Gym");

		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== NEW GYM ===============");
		System.out.println(ANSI_RESET);

		System.out.print("Enter gym CNPJ: ");
		String ownerCnpj = ValidDocumentsScan.readNewCnpj();
		System.out.print("Enter the name of your gym: ");
		String gymName = ValidDocumentsScan.readName();
		System.out.print("Enter the monthly value: ");
		Double monthly = null;

		boolean valid = false;
		while (!valid) {
			try {
				monthly = sc.nextDouble();
				if (monthly < 110.50) {
					throw new IllegalArgumentException(
							"Please enter a bigger value, we suggest you put at least R$ 110.50 for monthly value.");
				}
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a numerical value!");
				System.out.println(ANSI_RESET);
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println(ANSI_YELLOW_BACKGROUND);
				System.out.println("Error: " + e.getMessage());
				System.out.println(ANSI_RESET);
				sc.nextLine();
			}
		}
		gym = Gym.createGym(gymName, ownerCnpj, monthly);
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.printf("%s's Gym was registered successfully!\n", gymName);
		System.out.println(ANSI_RESET);
		displayMenu();
	}

	// access adm account logic
	private void accessAccountAdm() {
		try {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== SIGN IN ===============");
			System.out.println(ANSI_RESET);
			System.out.print("Type your CPF: ");
			String cpf = ValidDocumentsScan.readCpfVal();
			if (cpf == null) {
				System.out.println("Try again later.");
				displayMenu();
				return;
			}

			System.out.print("Type your password: ");
			String password = ValidDocumentsScan.readPassword();

			if (!adm.getCpf().equals(cpf)) {
				throw new CpfDoesntMatchException(" Wrong cpf!");
			}
			if (!adm.getPassword().equals(password)) {
				throw new IllegalArgumentException(" Wrong Password!");
			}
			menuAdm2();
		} catch (CpfDoesntMatchException | IllegalArgumentException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: " + e.getMessage());
			System.out.println(ANSI_RESET);
			displayMenu();
			return;
		}
	}

	// second adm menu
	private void menuAdm2() {
		boolean running = true;

		while (running) {

			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== ADM ACCOUNT ===============");
			System.out.println("|  1 - Manager member                     |");
			System.out.println("|  2 - Manage employees                   |");
			System.out.println("|  3 - Manage Reports                     |");
			System.out.println("|  4 - Display adm data                   |");
			System.out.println("|  0 - Previous menu                      |");
			System.out.println("===========================================");
			System.out.println(ANSI_RESET);

			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();

				switch (opt) {
				case 1:
					running = false;
					memberManagement();
					break;
				case 2:
					running = false;
					employeeManagement();
					break;
				case 3:
					running = false;
					reportManagement();
					break;
				case 4:
					System.out.println(adm);
					break;
				case 0:
					System.out.println("Going back to previous menu...");
					running = false;
					displayMenu();
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println(ANSI_RESET);
			}
		}
	}

	// MEMBER MANAGEMENT LOGIC

	private void memberManagement() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== MEMBER MANAGEMENT ===============");
			System.out.println("|  1 - Create new member                        |");
			System.out.println("|  2 - Remove a member                          |");
			System.out.println("|  3 - List all members                         |");
			System.out.println("|  0 - Previous menu                            |");
			System.out.println("=================================================");
			System.out.println(ANSI_RESET);

			System.out.println("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();

				switch (opt) {
				case 1:
					running = false;
					addMember();
					break;
				case 2:
					running = false;
					removeMember();
					break;
				case 3:
					running = false;
					listAllMembers();
					break;
				case 0:
					running = false;
					menuAdm2();
					System.out.println("Going back to previous menu...");
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println(ANSI_RESET);
			}
		}

	}

	private void addMember() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== NEW MEMBER ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Enter member name: ");
		String name = ValidDocumentsScan.readName();

		System.out.print("Enter member CPF: ");
		String cpf = ValidDocumentsScan.readNewCpfOpt();
		if (cpf == null) {
			System.out.println("Try again later.");
			memberManagement();
			return;
		}

		System.out.print("Enter gymMembership: 'M' to monthly, 'Q' to quarterly and 'A' to annual: ");
		String plan = ValidDocumentsScan.readPlan();

		MembershipPlan mp;
		if (plan.equals("m")) {
			mp = new MembershipPlan("Monthly, no one discount", gym.generatePlan()[0]);
		} else if (plan.equals("t")) {
			mp = new MembershipPlan("Quarterly, 10% off", gym.generatePlan()[1]);
		} else {
			mp = new MembershipPlan("Annual, 30% off", gym.generatePlan()[2]);
		}

		System.out.print("Create an password to the new member: ");
		String password = ValidDocumentsScan.readPassword();

		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.printf("Gym member %s was added successfully!\n", name);
		System.out.println(ANSI_RESET);
		gym.addMember(new GymMember(name, cpf, mp, password));
		memberManagement();
	}

	private void removeMember() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== REMOVE MEMBER ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Enter member CPF: ");
		String cpf = ValidDocumentsScan.readCpfVal();
		if (cpf == null) {
			System.out.println("Try again later.");
			memberManagement();
			return;
		}
		List<GymMember> gMs = gym.getMembers();
		for (GymMember gm : gMs) {
			if (gm.getCpf().equals(cpf)) {
				gym.removeGymMember(gm);
				System.out.println(ANSI_GREEN_BACKGROUND);
				System.out.printf("Member %s was removed successfully!\n", gm.getName());
				System.out.println(ANSI_RESET);
				cpf = ValidDocumentsScan.deleteCpf();
				memberManagement();
				return;
			}
		}
		System.out.println(ANSI_RED_BACKGROUND);
		System.out.println("Member not found! ");
		System.out.println(ANSI_RESET);
		memberManagement();
	}

	private void listAllMembers() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== LIST MEMBERS ===============");
		System.out.println(ANSI_RESET);
		StringBuilder sb = new StringBuilder();
		try {
			List<GymMember> members = gym.getMembers();
			if (members.isEmpty()) {
				throw new NullPointerException("Register some members");
			}
			for (GymMember gm : members) {
				sb.append(gm + "\n");
			}
			System.out.println("Gym Member List:\n" + sb);
			memberManagement();
		} catch (NullPointerException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: No one members registered yet. " + e.getMessage());
			System.out.println(ANSI_RESET);
			memberManagement();
			return;
		}
	}
	// EMPLOYEE MANAGEMENT LOGIC
	private void employeeManagement() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== EMPLOYEE MANAGEMENT ===============");
			System.out.println("|  1 - Register new instructor                    |");
			System.out.println("|  2 - Remove an instructor                       |");
			System.out.println("|  3 - Register new maintenance employee          |");
			System.out.println("|  4 - Remove a maintenance employee              |");
			System.out.println("|  5 - List all employees                         |");
			System.out.println("|  0 - Previous menu                              |");
			System.out.println("===================================================");
			System.out.println(ANSI_RESET);

			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();

				switch (opt) {
				case 1:
					running = false;
					addInstructor();
					break;
				case 2:
					running = false;
					removeInstructor();
					break;
				case 3:
					running = false;
					addMaintenanceEmployee();
					break;
				case 4:
					running = false;
					removeMaintenanceEmployee();
					break;
				case 5:
					running = false;
					listAllEmployees();
				case 0:
					running = false;
					System.out.println("Going back to previous menu...");
					menuAdm2();
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("\u001B[41m");
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println("\u001B[0m");
			}

		}
	}

	private void addInstructor() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== ADD INSTRUCTOR ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Enter instructor name: ");
		String name = ValidDocumentsScan.readName();
		System.out.print("Enter instructor CPF: ");
		String cpf = ValidDocumentsScan.readNewCpfOpt();
		if (cpf == null) {
			System.out.println("Try again later.");
			employeeManagement();
			return;
		}
		System.out.print("Create a passwod to the new instructor");
		String password = ValidDocumentsScan.readPassword();
		System.out.print("Enter instructor's wage");
		Double wage = null;
		boolean valid = false;
		while (!valid) {
			try {
				wage = sc.nextDouble();
				if (wage < 0) {
					throw new IllegalArgumentException("Please enter a bigger value than 0!");
				}
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println("\u001B[43mError: Please, enter a numerical value!\u001B[0m");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println("\u001B[43mError: " + e.getMessage() + "\u001B[0m");
				sc.nextLine();
			}
		}
		gym.addInstructor(new Instructor(name, cpf, password, wage));

		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.printf("Instructor %s was added successfully!\n", name);
		System.out.println(ANSI_RESET);
		employeeManagement();
	}

	private void addMaintenanceEmployee() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== ADD MAINTENANCE WORKER ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Enter name of maintenance employee");
		String name = ValidDocumentsScan.readName();
		System.out.print("Enter CPF of maintenance employee");
		String cpf = ValidDocumentsScan.readNewCpfOpt();
		if (cpf == null) {
			System.out.println("Try again later.");
			employeeManagement();
			return;
		}
		System.out.print("Enter password of maintenance employee");
		String password = ValidDocumentsScan.readPassword();
		System.out.print("Enter daily payment of the maintenance employee");
		Double wage = null;
		boolean valid = false;
		while (!valid) {
			try {
				wage = sc.nextDouble();
				if (wage < 0) {
					throw new IllegalArgumentException("Please enter a bigger value than 0!");
				}
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println("\u001B[43mError: Please, enter a numerical value!\u001B[0m");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println("\u001B[43mError: " + e.getMessage() + "\u001B[0m");
				sc.nextLine();
			}
		}
		gym.addMaintenanceEmployee(new MaintenanceEmployee(name, cpf, password, wage));
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.printf("Maintenance Employee %s was added successfully!\n", name);
		System.out.println(ANSI_RESET);
		employeeManagement();
	}

	private void listAllEmployees() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== LIST EMPLOYEES ===============");
		System.out.println(ANSI_RESET);
		StringBuilder sb = new StringBuilder();
		try {
			List<Employee> employees = gym.getEmployees();
			for (Employee e : employees) {
				sb.append(e + "\n");
			}
			System.out.println("List of employees\n" + sb);
			employeeManagement();
		} catch (NullPointerException e) {
			System.out.println("Error: register some employees.");
			employeeManagement();
			return;
		}
	}

	private void removeInstructor() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== REMOVE INSTRUCTOR ===============");
		System.out.println(ANSI_RESET);

		System.out.print("Enter instructor's CPF");
		String cpf = ValidDocumentsScan.readCpfVal();

		if (cpf == null) {
			System.out.println("Try again later.");
			employeeManagement();
			return;
		}
		List<Employee> employees = gym.getEmployees();
		for (Employee e : employees) {
			if (e instanceof Instructor && e.getCpf().equals(cpf)) {
				gym.removeEmployee(e);
				System.out.println(ANSI_GREEN_BACKGROUND);
				System.out.printf("Instructor %s was removed successfully!\n", e.getName());
				System.out.println(ANSI_RESET);
				cpf = ValidDocumentsScan.deleteCpf();
				employeeManagement();
				return;
			}
		}
		System.out.println("Employee doesn't exists on system.");
		employeeManagement();
	}

	private void removeMaintenanceEmployee() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== REMOVE MAINTENANCE EMPLOYEE ===============");
		System.out.println(ANSI_RESET);

		System.out.print("Enter maintenance employee's CPF");
		String cpf = ValidDocumentsScan.readCpfVal();

		if (cpf == null) {
			System.out.println("Try again later.");
			employeeManagement();
			return;
		}
		List<Employee> employees = gym.getEmployees();
		for (Employee e : employees) {
			if (e instanceof MaintenanceEmployee && e.getCpf().equals(cpf)) {
				gym.removeEmployee(e);
				System.out.println(ANSI_GREEN_BACKGROUND);
				System.out.printf("Maintenance employee %s was removed successfully!\n", e.getName());
				System.out.println(ANSI_RESET);
				cpf = ValidDocumentsScan.deleteCpf();
				employeeManagement();
				return;
			}
		}
		System.out.println("Employee doesn't exists on system.");
		employeeManagement();
	}
	
	// REPORT MANAGEMENT LOGIC
	private void reportManagement() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== REPORT MANAGEMENT ===============");
			System.out.println("|  1 - Create a CashReport                      |");
			System.out.println("|  2 - Create a frequency report                |");
			System.out.println("|  3 - Show all reports                         |");
			System.out.println("|  4 - Delete all reports                       |");
			System.out.println("|  0 - Previous menu                            |");
			System.out.println("=================================================");
			System.out.println(ANSI_RESET);
			System.out.print("Escolha uma opção: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();

				switch (opt) {
				case 1:
					running = false;
					createCashReport();
					break;
				case 2:
					running = false;
					createFrequencyReport();
					break;
				case 3:
					running = false;
					showAllReports();
					break;
				case 0:
					running = false;
					System.out.println("Going back to previous menu...");
					menuAdm2();
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number!");
				System.out.println(ANSI_RESET);
			}
		}
	}

	private void createCashReport() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== GENERATE NEW CASH REPORT ===============");
		System.out.println(ANSI_RESET);
		try {
			CashReport cashReport = new CashReport();
			System.out.println(ANSI_GREEN_BACKGROUND);
			System.out.println(cashReport.getMessage());
			System.out.println(ANSI_RESET);
			gym.addReport(new CashReport());
			reportManagement();
		} catch (NullPointerException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: register more members and employees!");
			System.out.println(ANSI_RESET);
			reportManagement();
			return;
		}
	}

	private void createFrequencyReport() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== GENERATE NEW FREQUENCY REPORT ===============");
		System.out.println(ANSI_RESET);
		try {
			FrequencyReport frequencyReport = new FrequencyReport();
			System.out.println(ANSI_GREEN_BACKGROUND);
			System.out.println(frequencyReport.getMessage());
			System.out.println(ANSI_RESET);
			gym.addReport(new CashReport());
			reportManagement();
		} catch (NullPointerException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: register more members");
			System.out.println(ANSI_RESET);
			reportManagement();
			return;
		}
	}

	private void showAllReports() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== SHOW ALL REPORTS ===============");
		System.out.println(ANSI_RESET);
		try {
			List<Report> reports = gym.getReports();
			for (Report report : reports) {
				System.out.println(ANSI_GREEN_BACKGROUND);
				System.out.println(report);
				System.out.println(ANSI_RESET);
			}
			reportManagement();
		} catch (NullPointerException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: No one report generated yet");
			System.out.println(ANSI_RESET);
			reportManagement();
			return;
		}
	}
}
