package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.entities.Administrator;

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

		listAdm.add(new Administrator(name, cpf, password));
		System.out.println("Conta criada com sucesso!");
	}

	private static void accessAccount() {
		System.out.print("Digite o seu CPF: ");
		int cpf = readInt(); // Leitura direta com Scanner

		System.out.print("Digite a sua senha: ");
		int password = readInt(); // Leitura direta com Scanner

		boolean found = false;
		for (Administrator administrator : listAdm) {
			if (administrator.getCpf() == cpf && administrator.getPassword() == password) {
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
			System.out.println("1 - Adicionar nova academia");
			System.out.println("2 - Gerenciar academia(s) existente(s)");
			System.out.println("3 - Excluir conta");
			System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

			System.out.print("Escolha uma opção: ");
			int choice = readInt(); // Leitura direta com Scanner

			switch (choice) {
			case 1:
				System.out.println("Funcionalidade de adicionar nova academia");
				break;
			case 2:
				System.out.println("Funcionalidade de gerenciar academias existentes");
				break;
			case 3:
				System.out.println("Funcionalidade de excluir conta");
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

}
