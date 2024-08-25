package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.entities.Administrator;
import model.entities.GymMembership;

public class Menu {

	static ArrayList<Administrator> listAdm = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	public static void menuAdm() {
		boolean running1 = true;

		while (running1) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa:");
			System.out.println("1 - Criar conta");
			System.out.println("2 - Acessar conta");
			System.out.println("0 - Sair\n=-=-=-=-=-=-=-=-=-=-");

			System.out.print("Escolha uma opção: ");
			int choice = readInt(); // Leitura direta com Scanner com exceção
			

			switch (choice) {
			case 1:
				createAccount();
				break;
			case 2:
				accessAccount();
				break;
			case 0:
				running1 = false;
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

	private static void createAccount() {
		System.out.print("Digite o seu CPF: ");
		int cpf = readInt(); // Leitura direta com Scanner
		sc.nextLine(); // Consumir o '\n' restante do buffer

		System.out.print("Escreva o seu nome: ");
		String name = sc.nextLine();

		System.out.print("Crie uma senha: ");
		int password = readInt(); // Leitura direta com Scanner
		
		System.out.print("Digite o nome da academia: ");
		String nameGym = sc.nextLine(); // Leitura direta com Scanner
		sc.nextLine();
		
		System.out.print("Digite o CNPJ: ");
		int CNPJ = readInt(); // Leitura direta com Scanner
		sc.nextLine();

		listAdm.add(new Administrator(name, cpf, password, nameGym, CNPJ));
		System.out.println("Conta criada com sucesso!");
	}

	private static void accessAccount() {
		System.out.print("Digite o seu CPF: ");
		int cpf = readInt(); // Leitura direta com Scanner

		System.out.print("Digite a sua senha: ");
		int password = readInt(); // Leitura direta com Scanner

		boolean found = false;
		for (Administrator administrador : listAdm) {
			if (administrador.getCpf() == cpf && administrador.getPassword() == password) {
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
		boolean running2 = true;

		while (running2) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa:");
			System.out.println("1 - Criar academia");
			System.out.println("2 - Cadastrar novo aluno");
			System.out.println("3 - Cadastrar novo funcionário");
			System.out.println("4 - Gerar relatórios");
			System.out.println("5 - Excluir conta");
			System.out.println("\n0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");
			
			System.out.print("Escolha uma opção: ");
			int choice = readInt(); // Leitura direta com Scanner
			
			sc.nextLine();
			
			switch (choice) {
			case 1:
				System.out.println("Criar academia");
				break;
				
			case 2:
				System.out.println("-=-=-=-=-=-=-=-=-=-=\\nCadastrar novo aluno");
				System.out.println("\nAcademia: ");
				String gymName = sc.nextLine();
				System.out.println("\nInforme o seu nome: ");
				String name = sc.nextLine();
				System.out.println("\nInforme o seu CPF: ");
				int CPF = readInt();
				sc.nextLine();
				System.out.println("\nInforme o plano do aluno: ");
				String plane = sc.nextLine();
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
				running2 = false;
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

	private static int readInt() {
		while (true) {
			try {
				return sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, insira um número.");
				sc.next(); // Limpa o buffer
			}
		}
	}
	
	static ArrayList<Administrator> listGymMember = new ArrayList<>();

	public static void menuGymMember() {
		boolean running3 = true;

		while (running3) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta do aluno:");
			System.out.println("1 - Entrar na conta.");
			System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

			System.out.print("Escolha uma opção: ");
			int choice = readInt(); // Leitura direta com Scanner

			switch (choice) {
			case 1:
				System.out.println("Entrando na conta...");
				accessAccountGymMember();
				break;
			case 0:
				running3 = false;
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

	private static void accessAccountGymMember() {
		System.out.print("Digite o seu CPF: ");
		int cpf = readInt(); // Leitura direta com Scanner

		System.out.print("Digite a sua senha: ");
		int password = readInt(); // Leitura direta com Scanner

		boolean found = false;
		for (Administrator administrador : listAdm) {

			if (administrador.getCpf() == cpf && administrador.getPassword() == password) {

				System.out.println("Entrando na conta...");
				found = true;
				menuGymMember();
				break;
			}
		}

		
		if (!found) {
			System.out.println("CPF ou senha incorretos.");
		}
	}	
}
			

		

