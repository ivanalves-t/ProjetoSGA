package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.entities.Administrator;
import model.entities.Employee;
import model.entities.Gym;
import model.entities.GymMember;
import model.entities.Instructor;
import model.entities.MaintenanceMan;
import model.exceptions.CpfDoesntMatchException;
import model.exceptions.MinimumWageException;
import model.services.CashReport;
import model.services.FrequencyReport;
import model.services.MembershipPlan;
import model.services.Report;
import util.ValidDocumentsScan;

// done
public class AdmMenu {

	private static Scanner sc = new Scanner(System.in);
	private Gym gym;
	private static Administrator adm;

	public AdmMenu() {
	}

	// Background colors
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

	// Adm main menu

	public void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("================ ADM MENU ================");
			System.out.println("|   1 - Create an admin account          |");
			System.out.println("|   2 - Sign in the admin account        |");
			System.out.println("|   0 - Back to previous menu            |");
			System.out.println("==========================================");
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
					MainMenu.main(null);
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Invalid value! Type a number between the valid options!");
					System.out.println(ANSI_RESET);
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
				sc.nextLine();
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
				if (monthly < 50.00) {
					throw new IllegalArgumentException(
							"Please enter a bigger value, we suggest you put at least R$ 50.00 for monthly value.");
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
		adm = new Administrator(name, cpf, gym);
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
				System.out.println("Error: Try again later.");
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
			System.out.println("Tip: first acces? you password could be your CPF");
			displayMenu();
			return;
		}
	}

	// second adm menu
	private void menuAdm2() {
		boolean running = true;
//		gym = Gym.getInstance();

		while (running) {

			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== ADM ACCOUNT ===============");
			System.out.println("|  1 - Manager member                     |");
			System.out.println("|  2 - Manage employees                   |");
			System.out.println("|  3 - Manage Reports                     |");
			System.out.println("|  4 - Display adm data                   |");
			System.out.println("|  5 - Modify password                    |");
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
				case 5:
					running = false;
					alterPassword();
					break;
				case 0:
					running = false;
					System.out.println("Going back to previous menu...");
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
				sc.nextLine();
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
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println(ANSI_RESET);
				sc.nextLine();
			}
		}

	}

	private void alterPassword() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== SET NEW PASSWORD ===============");
		System.out.println(ANSI_RESET);

		System.out.print("Please, enter your new password: ");
		String password = ValidDocumentsScan.readPassword();
		adm.setPassword(password);
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("New password modified successfully!");
		System.out.println(ANSI_RESET);
		System.out.println("Please sign in again to confirm your new password.");
		displayMenu();
	}

	private void addMember() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== NEW MEMBER ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Enter member name: ");
		String name = ValidDocumentsScan.readNameOpt();

		System.out.print("Enter member CPF: ");
		String cpf = ValidDocumentsScan.readNewCpfOpt();
		if (cpf == null) {
			System.out.println("Try again later.");
			memberManagement();
			return;
		}

		System.out.print("Enter gymMembership: 'M' to monthly, 'Q' to quarterly and 'A' to annual: ");
		String plan = ValidDocumentsScan.readPlan();

		MembershipPlan mp = null;
		double values[] = new double[3];
		values = gym.generatePlan();
		if (plan.equals("m")) {
			mp = new MembershipPlan("monthly", values[0]);
		} else if (plan.equals("q")) {
			mp = new MembershipPlan("quarterly", values[1]);
		} else {
			mp = new MembershipPlan("annual", values[2]);
		}

		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.printf("Gym member %s was added successfully!\n", name);
		System.out.println(ANSI_RESET);
		gym.addMember(new GymMember(name, cpf, mp));
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
				System.out.printf(ANSI_GREEN_BACKGROUND + "Member %s was removed successfully!\n",
						gm.getName() + ANSI_RESET);
				ValidDocumentsScan.deleteCpf(cpf);
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
		StringBuilder sb = new StringBuilder();
		try {
			List<GymMember> members = gym.getMembers();
			sb.append("\n==================== MEMBERS DATA =====================\n");

			if (members.isEmpty()) {
				throw new NullPointerException("Register some members");
			} else {
				sb.append("Name\tCPF\tMonthlyType\tMonthlyKind\tPassword\tFreq\n");
				for (GymMember gm : members) {
					sb.append(gm.getName()).append("\t").append(gm.getCpf()).append("\t").append(gm.getMembershipPlan().getMonthly())
							.append("\t").append(gm.getMembershipPlan().getKind()).append("\t").append(gm.getPassword()).append("\t").append(gm.getCheckIn()).append("\n");
				}
			}
		} catch (NullPointerException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: " + e.getMessage());
			System.out.println(ANSI_RESET);
			memberManagement();
		}

		String table = sb.toString();
		String[] rows = table.split("\n");
		StringBuilder result = new StringBuilder();

		for (String row : rows) {
			String[] columns = row.split("\t");
			for (String column : columns) {
				result.append(String.format("%-15s", column));
			}
			result.append("\n");
		}

		System.out.println(result);
		memberManagement();
	}

	// EMPLOYEE MANAGEMENT LOGIC
	private void employeeManagement() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== EMPLOYEE MANAGEMENT ===============");
			System.out.println("|  1 - Register new instructor                    |");
			System.out.println("|  2 - Register new maintenance employee          |");
			System.out.println("|  3 - Remove an employee                         |");
			System.out.println("|  4 - List all employees                         |");
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
					addMaintenanceEmployee();
					break;
				case 3:
					running = false;
					removeAnEmployee();
					break;
				case 4:
					running = false;
					listAllEmployees();
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
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println(ANSI_RESET);
				sc.nextLine();
			}

		}
	}

	private void addInstructor() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== ADD INSTRUCTOR ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Enter instructor name: ");
		String name = ValidDocumentsScan.readNameOpt();
		System.out.print("Enter instructor CPF: ");
		String cpf = ValidDocumentsScan.readNewCpfOpt();
		if (cpf == null) {
			System.out.println("Try again later.");
			employeeManagement();
			return;
		}
		System.out.print("Enter instructor's wage: ");
		Double wage = null;
		boolean valid = false;
		while (!valid) {
			try {
				wage = sc.nextDouble();
				if (wage < 0 || wage > 1412.10) {
					throw new MinimumWageException("Please enter a bigger value than 0 and lower than R$ 1412,10");
				}
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println("\u001B[43mError: Please, enter a numerical value!\u001B[0m");
				sc.nextLine();
			} catch (MinimumWageException e) {
				System.out.println("\u001B[43mError: " + e.getMessage() + ANSI_RESET);
				sc.nextLine();
			}
		}
		gym.addInstructor(new Instructor(name, cpf, wage));

		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.printf("Instructor %s was added successfully!\n", name);
		System.out.println(ANSI_RESET);
		employeeManagement();
	}

	private void addMaintenanceEmployee() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== ADD MAINTENANCE WORKER ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Enter name of maintenance employee: ");
		String name = ValidDocumentsScan.readNameOpt();
		System.out.print("Enter CPF of maintenance employee: ");
		String cpf = ValidDocumentsScan.readNewCpfOpt();
		if (cpf == null) {
			System.out.println("Try again later.");
			employeeManagement();
			return;
		}
		System.out.print("Enter daily payment of the maintenance employee: ");
		Double wage = null;
		boolean valid = false;
		while (!valid) {
			try {
				wage = sc.nextDouble();
				if (wage < 0 || wage > 300) {
					throw new IllegalArgumentException("Please enter a bigger value than 0 and lower than R$ 301,00");
				}
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println("\u001B[43mError: Please, enter a numerical value!\u001B[0m");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println("\u001B[43mError: " + e.getMessage() + ANSI_RESET);
				sc.nextLine();
			}
		}
		gym.addMaintenanceEmployee(new MaintenanceMan(name, cpf, wage));
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.printf("Maintenance Employee %s was added successfully!\n", name);
		System.out.println(ANSI_RESET);
		employeeManagement();
	}

	private void listAllEmployees() {
	    StringBuilder sb = new StringBuilder();
	    try {
	    	
	    	List<Employee> employees = gym.getEmployees();
	    	sb.append("\n======= EMPLOYEES DATA =======\n");
	    	if (employees.isEmpty()) {
	    		sb.append("No employees data\n");
	    		throw new NullPointerException("Please register some employees before.");
	    	} else {
	    		sb.append(String.format("%-15s%-15s%-15s%-15s%-15s\n", "Name", "CPF", "Type", "Payment", "Password"));
	    		for (Employee e : employees) {
	    			sb.append(String.format("%-15s%-15s%-15s%-15.2f%-15s\n",
	    					e.getName(), e.getCpf(), e.getClass().getSimpleName(), e.payment(), e.getPassword()));
	    		}
	    	}
	    }catch (NullPointerException e) {
	    	System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: " + e.getMessage());
			System.err.println(ANSI_RESET);
			employeeManagement();
		}

	    System.out.println(sb);
	    employeeManagement();
	}

	private void removeAnEmployee() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== REMOVE EMPLOYEE ===============");
		System.out.println(ANSI_RESET);

		System.out.print("Enter employee's CPF: ");
		String cpf = ValidDocumentsScan.readCpfVal();

		if (cpf == null) {
			System.out.println("Try again later.");
			employeeManagement();
			return;
		}
		List<Employee> employees = gym.getEmployees();
		for (Employee e : employees) {
			if (e.getCpf().equals(cpf)) {
				gym.removeEmployee(e);
				System.out.println(ANSI_GREEN_BACKGROUND);
				System.out.printf("Employee %s was removed successfully!\n", e.getName());
				System.out.println(ANSI_RESET);
				ValidDocumentsScan.deleteCpf(cpf);
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
				case 4:
					running = false;
					removeAllReports();
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
				System.out.println("Error: Please, enter a number inside the range of options!");
				System.out.println(ANSI_RESET);
				sc.nextLine();
			}
		}
	}

	private void createCashReport() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== GENERATE NEW CASH REPORT ===============");
		System.out.println(ANSI_RESET);
		try {
			CashReport cashReport = new CashReport();
			System.out.println(cashReport.getMessage());
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
			System.out.println(frequencyReport.getMessage());
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
				System.out.println(report);
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

	private void removeAllReports() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== REMOVE ALL REPORTS ===============");
		System.out.println(ANSI_RESET);
		gym.removeReports();
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("All reports deleted!");
		System.out.println(ANSI_RESET);
		reportManagement();
	}
}
