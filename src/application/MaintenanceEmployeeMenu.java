package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.entities.Device;
import model.entities.Employee;
import model.entities.Gym;
import model.entities.MaintenanceMan;
import model.exceptions.CpfDoesntMatchException;
import model.exceptions.NameException;
import model.services.MaintenanceReport;
import util.ValidDocumentsScan;

public class MaintenanceEmployeeMenu {

	private Gym gym;
	private MaintenanceMan currentlyMm;
	private static Scanner sc = new Scanner(System.in);

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_MAGENTA_BACKGROUND = "\u001B[45m";

	public MaintenanceEmployeeMenu() {
		gym = Gym.getInstance();
	}

	public void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_MAGENTA_BACKGROUND);
			System.out.println("=============== MAINTENANCE MAN MENU ===============");
			System.out.println("|  1 - Sign in MaintenanceMan menu                 |");
			System.out.println("|  0 - Back to previous menu                       |");
			System.out.println("====================================================");
			System.out.println(ANSI_RESET);

			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();

				switch (opt) {
				case 1:
					running = false;
					accessValidateMaintenanceManMenu();
					break;
				case 0:
					running = false;
					System.out.println("Going back to main menu...\n\n");
					currentlyMm = null;
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
			}
		}
	}

	private void accessValidateMaintenanceManMenu() {
		System.out.print("Type your CPF: ");
		String cpf = ValidDocumentsScan.readCpfVal();
		if (cpf == null) {
			System.out.println("Error: Ask to your admin to register you!");
			displayMenu();
			return;
		}
		System.out.print("Type your password: ");
		String password = sc.nextLine();
		try {
			List<Employee> employees = gym.getEmployees();
			Employee foundEmployee = null;
			for (Employee employee : employees) {
				if (employee.getCpf().equals(cpf) && employee instanceof MaintenanceMan) {
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
			currentlyMm = (MaintenanceMan) foundEmployee;
			accessAccountMaintenanceMan();

		} catch (CpfDoesntMatchException | IllegalArgumentException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: " + e.getMessage());
			System.out.println(ANSI_RESET);
			System.out.println("Tip: first access? your password could be your CPF");
			displayMenu();
		}
	}

	private void accessAccountMaintenanceMan() {

		boolean running = true;

		while (running == true) {
			System.out.println(ANSI_MAGENTA_BACKGROUND);
			System.out.println("============= MAINTENANCE MENU ============");
			System.out.println("|  1 - Register new device                |");
			System.out.println("|  2 - Delete a device                    |");
			System.out.println("|  3 - Generate a maintenance report      |");
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
					createDevice();
					break;
				case 2:
					running = false;
					deleteDevice();
					break;
				case 3:
					running = false;
					generateMaintenanceReport();
					break;
				case 4:
					running = false;
					modifyPassword();
					break;
				case 0:
					running = false;
					currentlyMm = null;
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
				sc.nextLine();
			}

		}
	}
	
	private void modifyPassword() {
		System.out.println(ANSI_MAGENTA_BACKGROUND);
		System.out.println("=============== SET NEW PASSWORD ===============");
		System.out.println(ANSI_RESET);
		
		System.out.print("Please, enter your new password: ");
		String password = ValidDocumentsScan.readPassword();
		for (Employee employee : gym.getEmployees()) {
			if (employee.getCpf().equals(currentlyMm.getCpf())){
				employee.setPassword(password);
			}
		}
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("Password modified successfully!");
		System.out.println(ANSI_RESET);
		System.out.println("Please sign in again.");
		displayMenu();
	}

	private void createDevice() {
		System.out.println(ANSI_MAGENTA_BACKGROUND);
		System.out.println("============= REGISTER NEW DEVICE =============");
		System.out.println(ANSI_RESET);
		System.out.println("Please, enter the name of the device to register:");
		boolean valid = false;
		String name = null;
		while (!valid) {
			try {
				name = sc.nextLine();
				if (name.trim().length() < 10) {
					throw new NameException("Error: Please insert a device name bigger than 10 caracters.");
				}
				valid = true;
			} catch (NameException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: " + e.getMessage());
				System.out.println(ANSI_RESET);
			}
		}
		valid = false;
		System.out.println("Please, insert the machine status:");
		String status = null;
		while (!valid) {
			try {
				status = sc.nextLine().trim();
				if (!status.equals("new") && !status.equals("medium") && !status.equals("need_to_change")) {
					throw new IllegalArgumentException(
							"Error: Insert only \"new\", \"medium\", or \"need_to_change\" options.");
				}
				valid = true;
			} catch (IllegalArgumentException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: " + e.getMessage());
				System.out.println(ANSI_RESET);
			}
		}
		gym.addDevice(new Device(name, status));
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("New device created successfully!");
		System.out.println(ANSI_RESET);
		accessAccountMaintenanceMan();
	}

	private void deleteDevice() {
		System.out.println(ANSI_MAGENTA_BACKGROUND);
		System.out.println("============= DELETE A DEVICE =============");
		System.out.println(ANSI_RESET);
		System.out.println("Please, enter the name of the device to delete:");

		String name = sc.nextLine();
		for (Device d : gym.getDevices()) {
			if (d.getDeviceName().equals(name)) {
				gym.removeDevice(d);
				System.out.println(ANSI_GREEN_BACKGROUND);
				System.out.println("New device created successfully!");
				System.out.println(ANSI_RESET);
				accessAccountMaintenanceMan();
			}
			System.out.println("Device not found on system!");
			accessAccountMaintenanceMan();

		}
	}
	
	private void generateMaintenanceReport() {
		System.out.println(ANSI_MAGENTA_BACKGROUND);
		System.out.println("============= GENERATE A REPORT =============");
		System.out.println(ANSI_RESET);
		gym.addReport(new MaintenanceReport(currentlyMm.getName()));
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("Report created successfully!");
		System.out.println(ANSI_RESET);
		accessAccountMaintenanceMan();
	}
}
