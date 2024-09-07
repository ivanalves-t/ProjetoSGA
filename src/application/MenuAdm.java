package application;

import java.util.Scanner;

import model.entities.Administrator;
import model.entities.Employee;
import model.entities.Gym;
import model.entities.GymMember;
import model.entities.Instructor;
import model.entities.MaintenanceEmployee;

import model.exceptions.CpfDoesntMatchException;
import model.services.MaintenanceReport;
import model.services.MembershipPlan;
import util.DocumentsRepository;

import util.ScanUtil;
import util.ValidDocumentsScan;

public class MenuAdm {

	// Singleton instances. Unique multi instances


	private static MenuAdm instance;
	private static Scanner sc = new Scanner(System.in);
	private static Gym gym;
	private static Administrator adm;


	private MenuAdm() {

	}

	// Background colors
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
			System.out.println("=============== MENU ADM ===============");
			System.out.println("|  1 - Create an admin account         |");
			System.out.println("|  2 - Sign in the admin account       |");
			System.out.println("|  0 - Back to previous menu           |");
			System.out.println("========================================");
			System.out.println(ANSI_RESET);
			byte choice = (byte) ScanUtil.readOpt();
			try {
				switch (choice) {
				case 1:
					if (adm != null) {
						throw new IllegalArgumentException("Admin account was already registered!");
					}
					createAccountAdm();
					break;
				case 2:
					if (adm == null) {
						throw new NullPointerException("Admin account not registered yet!");
					}
					accessAccountAdm();
					break;
				case 0:
					running = false;
					System.out.println("Going to previous menu...");
					break;
				default:
					System.out.println("Error: Type a number betwen the valid options!");
				}

			} catch (NullPointerException | IllegalArgumentException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: " + e.getMessage());
				System.out.println(ANSI_RESET);
			}
		}
	}

	// create admin account
	private void createAccountAdm() {
		System.out.print("Type your CPF: ");
		String cpf = ValidDocumentsScan.readNewCpf();
		if (cpf == null) {
			System.out.println("Please try again later.");
			return;
		}

		System.out.print("Type your name: ");
		String name = sc.nextLine();

		System.out.print("Create an password: ");
		String password = sc.nextLine();

		adm = new Administrator(name, cpf, password);
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("Admin account created successfully!");
		System.out.println(ANSI_RESET);

		System.out.println("Now register your Gym");

		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== NEW GYM ===============");
		System.out.println(ANSI_RESET);
		System.out.print("Enter the name of your gym: ");
		String gymName = sc.nextLine();

		System.out.print("Enter the CNPJ of gym: ");
		String ownerCnpj = ValidDocumentsScan.readNewCnpj();
		System.out.print("Enter the monthly value: ");
		double monthly = ScanUtil.readDouble();
		gym = Gym.createGym(gymName, ownerCnpj, monthly);
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("Gym registered successfully!");
		System.out.println(ANSI_RESET);
	}

	// access adm account logic
	private void accessAccountAdm() {
		try {

			System.out.print("Type your CPF: ");
			String cpf = ValidDocumentsScan.readCpfVal();

			if (cpf == null)
				return;

			System.out.print("Type your password: ");
			String password = sc.nextLine();

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
			return;
		}

	}

	// second adm menu
	private void menuAdm2() {
		boolean running = true;

		while (running) {

			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== ADM ACCOUNT ===============");
			System.out.println("|  0 - Previous menu                      |");
			System.out.println("|  1 - Manager member                     |");
			System.out.println("|  2 - Manage employees                   |");
			System.out.println("|  3 - Manage Reports                     |");
			System.out.println("|  4 - Exibe dados do adm                 |");
			System.out.println("===========================================");
			System.out.println(ANSI_RESET);


			System.out.print("Escolha uma opção: ");
			byte choice = (byte) ScanUtil.readOpt();

			switch (choice) {
			case 1:
				memberManagement();
				break;
			case 2:
				employeeManagement();
				break;
			case 3:
				reportManagement();
				break;
			case 0:
				running = false;
				System.out.println("Going to previous menu...");
				break;
			default:
				System.out.println("Error: Type a listed option!");
			}

		}
	}

	// MEMBER MANAGEMENT

	private void memberManagement() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== MEMBER MANAGEMENT ===============");
			System.out.println("|  0 - Previous menu                            |");
			System.out.println("|  1 - Create new member                        |");
			System.out.println("|  2 - Remove a member                          |");
			System.out.println("|  3 - List all members                         |");
			System.out.println("|  4 - Alter a member                           |");
			System.out.println("==================================================");
			System.out.println(ANSI_RESET);

			System.out.print("Escolha uma opção: ");
			byte choice = (byte) ScanUtil.readOpt();

			switch (choice) {
			case 1:
				addMember();
				break;
			case 2:
				removeMember();
				break;
			case 3:
				listAllMembers();
			case 4:
				alterMember();
				break;
			case 0:
				running = false;
				System.out.println("Going to previous menu...");
				break;
			default:
				System.out.println("Error: Type a listed option!");
			}

		}

	}
	
	private void addMember() {
		System.out.println("=============== NEW MEMBER ===============");

		System.out.print("Informe o nome do aluno: ");
		String name = sc.nextLine();

		System.out.print("Informe o CPF do aluno: ");
		String cpf = ValidDocumentsScan.readNewCpf();
		if (cpf == null) {
			System.out.println("Voltando ao menu anterior");
			return;
		}
		sc.nextLine(); // Limpa o buffer após a leitura do CPF

		System.out.print("Informe o plano do aluno: 'M' para mensal, 'T' para trimestral, e 'A' para anual.");
		String plan = ValidDocumentsScan.readPlan();

		MembershipPlan mp;
		if (plan.equals("m")) {
			mp = new MembershipPlan("mensal, sem desconto", gym.generatePlan()[0]);
		} else if (plan.equals("t")) {
			mp = new MembershipPlan("trimestral, com desconto de 10%", gym.generatePlan()[1]);
		} else {
			mp = new MembershipPlan("anual, com desconto de 30%", gym.generatePlan()[2]);
		}

		System.out.print("Digite uma senha para o aluno:");
		String password = sc.nextLine();

		System.out.println("Aluno cadastrado com sucesso!");
		gym.addMember(new GymMember(name, cpf, mp, password));
	}
	
	private void removeMember() {
		System.out.println("=============== REMOVE MEMBER ===============");
		System.out.print("Informe o cpf do membro");
		String cpf = ValidDocumentsScan.deleteCpf();
		if (cpf == null) {
			System.out.println("Going to previous menu...");
			return;
		}
		for (GymMember gm : gym.getMembers()) {
			if (gm.getCpf().equals(cpf)) {
				gym.removeGymMember(gm);
				return;
			}
		}
		System.out.println(ANSI_RED_BACKGROUND);
		System.out.println("Member not found! ");
		System.out.println(ANSI_RESET);
	}
	
	private void listAllMembers() {
		StringBuilder sb = new StringBuilder();
		try {
			for (GymMember gm : gym.getMembers()) {
				sb.append(gm);
			}
		} catch (NullPointerException e) {
			System.out.println("Erro: cadastre alguns membros antes");
			return;
		}
	}
	
	private void alterMember() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== REPORT MANAGEMENT ===============");
		System.out.println("|  0 - Previous menu                            |");
		System.out.println("|  1 - Modify member name                       |");
		System.out.println("|  2 - Modify membership plan                   |");
		System.out.println("|  3 - Manage Reports                           |");
		System.out.println("|  7 - Deletar tudo                             |");
		System.out.println("|  8 - Exibe dados do adm                       |");
		System.out.println("=================================================");
		System.out.println(ANSI_RESET);
		System.out.print("Escolha uma opção: ");
	}


	private void reportManagement() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_CYAN_BACKGROUND);
			System.out.println("=============== REPORT MANAGEMENT ===============");
			System.out.println("|  0 - Menu Anterior                            |");
			System.out.println("|  1 - Manager member                           |");
			System.out.println("|  2 - Manage employees                         |");
			System.out.println("|  3 - Manage Reports                           |");
			System.out.println("|  7 - Deletar tudo                             |");
			System.out.println("|  8 - Exibe dados do adm                       |");
			System.out.println("=================================================");
			System.out.println(ANSI_RESET);
			System.out.print("Escolha uma opção: ");
			byte choice = (byte) ScanUtil.readOpt();

			switch (choice) {
			case 1:
				memberManagement();
				break;
			case 2:
				employeeManagement();
				break;
			case 3:
				reportManagement();
				break;
			case 0:
				running = false;
				System.out.println("Going to previous menu...");
				break;
			default:
				System.out.println("Error: Type a listed option!");
			}

		}
	}

	private void employeeManagement() {
		System.out.println(ANSI_CYAN_BACKGROUND);
		System.out.println("=============== EMPLOYEE MANAGEMENT ===============");
		System.out.println("|  0 - Previous menu                              |");
		System.out.println("|  1 - Register new instructor                    |");
		System.out.println("|  2 - Register new maintenance employee          |");
		System.out.println("|  3 - Remove a maintenance employee              |");
		System.out.println("|  4 - Remove an instructor                       |");
		System.out.println("|  5 - List all employees                         |");
		System.out.println("|  6 - Alter an employee                          |");
		System.out.println("==================================================");
		System.out.println(ANSI_RESET);

		System.out.print("Escolha uma opção: ");
		byte choice = (byte) ScanUtil.readOpt();
	}


	private void addEmployee() {
		System.out.println("=============== NEW EMPLOYEE ===============");
		int tries = 4;
		System.out.println("Enter the type of the new employee, instructor or maintenance worker(i/m)");
		String type = sc.nextLine();

		while (tries > 0) {
			try {
				switch (type) {
				case "i":
					addInstructor();
					return;
				case "m":
					addMaintenanceWorker();
					return;
				default:
					throw new IllegalArgumentException("Type only 'i' or 'm' character!");
				}

			} catch (IllegalArgumentException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.printf("Error: %s You got %d yet.", e.getMessage(), (tries - 1));
				System.out.println(ANSI_RESET);
				tries--;
			}

		}

	}

	// ADD METHODS

	private void addInstructor() {

		System.out.print("Informe o nome do Instrutor");
		String name = sc.nextLine();
		System.out.print("Informe o cpf do Instrutor");
		String cpf = ValidDocumentsScan.readNewCpf();
		if (cpf == null) {
			System.out.println("Voltando ao menu anterior");
			return;
		}
		System.out.print("Informe a senha do Instrutor");
		String password = sc.nextLine();
		System.out.print("Informe o salário do Instrutor");
		double wage = ScanUtil.readDouble();


		gym.addInstructor(new Instructor(name, cpf, password, wage));
	}

	private void addMaintenanceWorker() {

		System.out.print("Informe o nome do Instrutor");
		String name = sc.nextLine();
		System.out.print("Informe o cpf do Instrutor");
		String cpf = ValidDocumentsScan.readNewCpf();
		if (cpf == null) {
			System.out.println("Voltando ao menu anterior");
			return;
		}
		System.out.print("Informe a senha do Instrutor");
		String password = sc.nextLine();
		System.out.print("Informe o salário do Instrutor");
		double wage = ScanUtil.readDouble();

		gym.addInstructor(new Instructor(name, cpf, password, wage));
	}

	private void listAllEmployees() {
		StringBuilder sb = new StringBuilder();
		try {
			for (Employee e : gym.getEmployees()) {
				sb.append(e);
			}
		} catch (NullPointerException e) {
			System.out.println("Error: cadastre alguns empregados antes");
			return;
		}
	}

	private void removeEmployee() {
		System.out.println("=============================== REMOVE EMPLOYEE ===============================");
		System.out.println("Enter the type of the employee to remove, instructor or maintenance worker(i/m)");
		int tries = 4;
		String type = sc.nextLine();

		while (tries > 0) {
			try {
				switch (type) {
				case "i":
					addInstructor();
					return;
				case "m":
					addMaintenanceWorker();
					return;
				default:
					throw new IllegalArgumentException("Type only 'i' or 'm' character!");

				}


			} catch (IllegalArgumentException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.printf("Error: %s You got %d yet.", e.getMessage(), (tries - 1));
				System.out.println(ANSI_RESET);
				tries--;
			}
		}
	}

	private void removeInstructor() {

	}

	private void removeMaintenanceWorker() {

	}

	private void generateReports() {
		boolean running = true;

		while (running) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa/ Relatorios:");
			System.out.println("1 - Mostrar dados do administrador");
			System.out.println("2 - Relatórios de pagamentos");
			System.out.println("3 - Relatório de frequência atual");
			System.out.println("4 - Mostrar todos os alunos");
			System.out.println("5 - Mostrar todos os funcionários");
			System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

			System.out.print("Escolha uma opção: ");
			byte choice = (byte) ScanUtil.readOpt();

			switch (choice) {
			case 1:
				System.out.println(adm);
				break;

			case 2:
				System.out.println("-=-=-=-=-=-=-=-=-=-=\nCadastrar novo aluno");
//
//				System.out.println("Informe o nome do aluno: ");
//				String name = sc.nextLine();
//
//				System.out.println("Informe o seu CPF do aluno: ");
//				String cpf = ValidDocumentsScan.readCpf();
//
//				sc.nextLine(); // Limpa o buffer após a leitura do CPF
//
//				System.out.println("Informe o plano do aluno: ");
//				String plan = sc.nextLine();
//
//				System.out.println("Digite uma senha para o aluno:");
//				String password = sc.nextLine();
//
//				System.out.println("Aluno cadastrado com sucesso!");
//				Program.gym.addMembers(new GymMember(name, cpf, plan, password));
				break;

			case 3:
				System.out.println("Cadastrar novo funcionário");
				break;
			case 4:
				System.out.println("Gerar relatórios");
				break;
			case 5:
				System.out.println("Funcionalidade de excluir conta");
				break;
			case 0:
				running = false;
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Error: Type a listed option!");
			}
		}
	}


}
