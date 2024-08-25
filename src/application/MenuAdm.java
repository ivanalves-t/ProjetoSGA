package application;

import java.util.Scanner;

import model.entities.Administrator;
import model.entities.GymMember;
import util.ScanUtil;

public class MenuAdm{

	private static Scanner sc = new Scanner(System.in);

	public static void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa:");
			System.out.println("1 - Criar conta");
			System.out.println("2 - Acessar conta");
			System.out.println("0 - Sair\n=-=-=-=-=-=-=-=-=-=-");

			System.out.print("Escolha uma opção: ");
			int choice = ScanUtil.readOpt(); // Leitura direta com Scanner

			switch (choice) {
			case 1:
				createAccountAdm();
				break;
			case 2:
				accessAccountAdm();
				break;
			case 0:
				running = false;
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

	private static void createAccountAdm() {
		System.out.print("Digite o seu CPF: ");
		String cpf = ScanUtil.readCpf();

		System.out.print("Escreva o seu nome: ");
		String name = sc.next();

		System.out.print("Crie uma senha: ");
		String password = sc.next();

		System.out.print("Digite o nome da academia: ");
		String nameGym = sc.next();
		sc.nextLine();

		System.out.print("Digite o CNPJ: ");
		String CNPJ = sc.nextLine();

		Program.listAdm.add(new Administrator(name, cpf, password, nameGym, CNPJ));
		System.out.println("Conta criada com sucesso!");
	}

	private static void accessAccountAdm() {
		System.out.print("Digite o seu CPF: ");
		String cpf = sc.next();

		System.out.print("Digite a sua senha: ");
		String password = sc.next();

		boolean found = false;
		for (Administrator administrador : Program.listAdm) {
			if (administrador.getCpf().equals(cpf) && administrador.getPassword().equals(password)) {
				System.out.println("Entrando na conta...");
				found = true;
				menuAdm2();
				break;
			}
		}

		if (!found) {
			System.out.println("CPF ou senha incorretos.");
		}
	}

	private static void menuAdm2() {
		boolean running = true;

		while (running) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa:");
			System.out.println("1 - Criar academia");
			System.out.println("2 - Cadastrar novo aluno");
			System.out.println("3 - Cadastrar novo funcionário");
			System.out.println("4 - Gerar relatórios");
			System.out.println("5 - Excluir conta");
			System.out.println("\n0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

			System.out.print("Escolha uma opção: ");
			int choice = ScanUtil.readOpt();

			sc.nextLine();

			switch (choice) {
			case 1:
				System.out.println("Criar academia");
				break;

			case 2:
				System.out.println("-=-=-=-=-=-=-=-=-=-=\\nCadastrar novo aluno");
				System.out.println("\nAcademia: ");
				String gymName = sc.nextLine();
				System.out.println("Informe o seu nome: ");
				String name = sc.nextLine();
				System.out.println("Informe o seu CPF: ");
				String cpf = ScanUtil.readCpf();
				System.out.println("Informe o plano do aluno: ");
				String plain = sc.nextLine();
				System.out.println("Digite uma senha para o aluno");
				String password = sc.nextLine();
				
				System.out.println("\nAluno cadastrado com sucesso! ");
				GymMember gm = new GymMember(name, cpf, gymName, plain, password);
				Program.gym.addMembers(gm);
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
				System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

}
