package application;

import java.util.Scanner;

import model.entities.Administrator;
import model.entities.Employee;
import model.entities.Gym;
import model.entities.GymMember;
import model.entities.Instructor;
import model.entities.MaintenanceEmployee;
import model.services.MaintenanceReport;
import model.services.MembershipPlan;
import util.ScanUtil;
import util.ValidDocumentsScan;

public class MenuAdm {
    private static MenuAdm instance;
	private static Scanner sc = new Scanner(System.in);
	private static Gym gym;
	private static Administrator adm;
	
	private MenuAdm() {
		
	}
	
    public static MenuAdm getInstance() {
        if (instance == null) {
            instance = new MenuAdm();
        }
        return instance;
    }
    
	public void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa:");
			System.out.println("1 - Criar conta");
			System.out.println("2 - Acessar conta");
			System.out.println("0 - Sair\n=-=-=-=-=-=-=-=-=-=-");

			System.out.print("Escolha uma opção: ");
			byte choice = (byte) ScanUtil.readOpt();

			switch (choice) {
			case 1:
				if (adm.getCpf() == null) {
					createAccountAdm();
					break;
				}
				System.out.println("Conta já criada! ");
				break;
			case 2:
				if (adm.getCpf() == null) {
					System.out.println("Erro: crie uma conta antes de acessa-la!");
					break;
				}
				accessAccountAdm();
				break;
			case 0:
				running = false;
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Erro: Digite um número dentre as opções listadas!");
			}
		}
	}

	private void createAccountAdm() {
		System.out.print("Digite o seu CPF: ");
		String cpf = ValidDocumentsScan.readNewCpf();
		if (cpf == null) {
			System.out.println("Voltando ao menu anterior");
			return;
		}

		System.out.print("Escreva o seu nome: ");
		String name = sc.nextLine();

		System.out.print("Crie uma senha: ");
		String password = sc.nextLine();

		adm = new Administrator(name, cpf, password);
		System.out.println("Conta criada com sucesso!");
	}

	private void accessAccountAdm() {
		System.out.print("Digite o seu CPF: ");
		String cpf = sc.nextLine();
		if (cpf == null) {
			System.out.println("Voltando ao menu anterior");
			return;
		}

		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine();

		boolean found = false;

		if (adm.getCpf().equals(cpf) && adm.getPassword().equals(password)) {
			System.out.println("Entrando na conta...");
			found = true;
			menuAdm2();
		}

		if (!found) {
			System.out.println("CPF ou senha incorretos.");
		}
	}

	private void menuAdm2() {
		boolean running = true;

		while (running) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa:");
			System.out.println("1 - Criar academia");
			System.out.println("2 - Cadastrar novo aluno");
			System.out.println("3 - Cadastrar novo instrutor");
			System.out.println("4 - Gerar relatórios de finanças");
			System.out.println("5 - Exibir todos os alunos");
			System.out.println("6 - Exibir todos os funcionarios");
			System.out.println("7 - Registrar um novo aparelho");
			System.out.println("8 - Remover um membro");
			System.out.println("9 - Remover um instrtor");
			System.out.println("10 - Registrar uma manutencao");
			System.out.println("11 - Deletar tudo");
			System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

			System.out.print("Escolha uma opção: ");
			byte choice = (byte) ScanUtil.readOpt();

			switch (choice) {
			case 1:
				System.out.println("-=-=-=-=-=-=-=-=-=-=\nCadastrar nova academia");
				System.out.print("Nome da Academia: ");
				String gymName = sc.nextLine();

				System.out.print("Informe o CNPJ da academia: ");
				String ownerCnpj = ValidDocumentsScan.readNewCnpj();
				if (ownerCnpj == null) {
					System.out.println("Voltando ao menu anterior");
					break;
				}
				System.out.print("Informe o valor mensal: ");
				double monthly = ScanUtil.readDouble();

				gym = Gym.createGym(gymName, ownerCnpj, monthly);
				System.out.println("Academia cadastrada com sucesso!");
				break;

			case 2:
				if (gym == null) {
					System.out.println("Erro: Nenhuma academia cadastrada. Por favor, cadastre uma academia primeiro.");
					break;
				}
				System.out.println("-=-=-=-=-=-=-=-=-=-=\nCadastrar novo aluno");

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
				break;

			case 3:
				if (gym == null) {
					System.out.println("Erro: Nenhuma academia cadastrada. Por favor, cadastre uma academia primeiro.");
					break;
				}
				System.out.println("-=-=-=-=-=-=-=-=-=-=\nCadastrar novo Instrutor");

				System.out.print("Informe o nome do Instrutor");
				name = sc.nextLine();
				System.out.print("Informe o cpf do Instrutor");
				cpf = ValidDocumentsScan.readNewCpf();
				if (cpf == null) {
					System.out.println("Voltando ao menu anterior");
					return;
				}
				System.out.print("Informe a senha do Instrutor");
				password = sc.nextLine();
				System.out.print("Informe o salário do Instrutor");
				double wage = ScanUtil.readDouble();

				gym.addInstructor(new Instructor(name, cpf, password, wage));
				break;

			case 4:
				if (gym == null) {
					System.out.println("Erro: Nenhuma academia cadastrada. Por favor, cadastre uma academia primeiro.");
					break;
				}
				System.out.println("-=-=-=-=-=-=-=-=-=-=\\nRelatório de financas");
				double sum = 0;
				try {
					for (Employee e : gym.getEmployees()) {
						System.out.println("Name: " + e.getName() + "\nAmmount: R$ " + e.payment() + "\nEmployee type: "
								+ e.getClass().getSimpleName());
						sum += e.payment();
					}
					System.out.println("\nTotal: " + sum);
					break;
				}catch (NullPointerException e) {
					System.out.println("Error: cadastre alguns funcionarios antes");
					break;
				}
			case 5:

				try {
					for (GymMember gm : gym.getMembers()) {
						System.out.println(gm);
						break;
					}
				} catch (NullPointerException e) {
					System.out.println("Erro: cadastre alguns membros antes");
				}
				break;
			case 6:
				if (gym == null) {
					System.out.println("Erro: Nenhuma academia cadastrada. Por favor, cadastre uma academia primeiro.");
					break;
				}
				try {
					for (Employee e : gym.getEmployees()) {
						System.out.println(e);
					}
					break;
				}catch (NullPointerException e) {
					System.out.println("Error: cadastre alguns empregados antes");
					break;
				}
			case 7:
				if (gym == null) {
					System.out.println("Erro: Nenhuma academia cadastrada. Por favor, cadastre uma academia primeiro.");
					break;
				}
				System.out.println("-=-=-=-=-=-=-=-=-=-=\\nRegistrar um novo aparelho");
				System.out.print("Informe o nome do aparelho");
				name = sc.nextLine();
				System.out.print("Informe o status do aparelho");
				String status = sc.nextLine();

				break;
			case 8:
				if (gym == null) {
					System.out.println("Erro: Nenhuma academia cadastrada. Por favor, cadastre uma academia primeiro.");
					break;
				}
				System.out.println("-=-=-=-=-=-=-=-=-=-=\\nRemover um membro da academia");
				System.out.print("Informe o cpf do membro");
				cpf = ValidDocumentsScan.deleteCpf();
				if (cpf == null) {
					System.out.println("Voltando ao menu anterior");
					break;
				}
				for (GymMember gm : gym.getMembers()) {
					if (gm.getCpf().equals(cpf)) {
						gym.removeGymMember(gm);
						break;
					}
				}
				System.out.println("Aluno não encontrado! ");
				break;
			case 9:
				if (gym == null) {
					System.out.println("Erro: Nenhuma academia cadastrada. Por favor, cadastre uma academia primeiro.");
					break;
				}
				System.out.println("-=-=-=-=-=-=-=-=-=-=\\nRemover um instrutor da academia");
				System.out.print("Informe o cpf do instrutor");
				cpf = ValidDocumentsScan.readCpfVal();
				if (cpf == null) {
					System.out.println("Voltando ao menu anterior");
					break;
				}
				for (Employee gm : gym.getEmployees()) {
					if (gm.getCpf().equals(cpf) && gm instanceof Instructor) {
						gym.removeEmployee(gm);
						break;
					}
				}
				System.out.println("Instrutor não encontrado! ");
				break;
			case 10:
				if (gym == null) {
					System.out.println("Erro: Nenhuma academia cadastrada. Por favor, cadastre uma academia primeiro.");
					break;
				}
				System.out.println("-=-=-=-=-=-=-=-=-=-=\\nRegistro de manutenção");
				System.out.print("Informe o nome do empregado de manutenção");
				name = sc.nextLine();
				System.out.print("Informe o cpf do empregado de manutenção");
				cpf = ValidDocumentsScan.readNewCpf();
				if (cpf == null) {
					System.out.println("Voltando ao menu anterior");
					break;
				}
				System.out.print("Informe a diária do empregado de manutenção");
				double daily = ScanUtil.readDouble();
				System.out.print("Informe a mensagem para o relatorio, o que foi limpo ou alterado");
				String message = sc.nextLine();

				MaintenanceReport mr = new MaintenanceReport(message, new MaintenanceEmployee(name, cpf, daily));
				System.out.println("Finalizado!");
				break;
			case 11:
				adm = null;
				gym = null;
				running = false;
				break;
			case 0:
				running = false;
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Erro: Digite um número dentre as opções listadas!");
			}
		}
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
				System.out.println("Erro: Digite um número dentre as opções listadas!");
			}
		}
	}

}
