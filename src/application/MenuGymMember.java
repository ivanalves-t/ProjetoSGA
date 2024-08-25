package application;

import java.util.Scanner;

import model.entities.Administrator;
import model.entities.GymMember;
import util.ScanUtil;

public class MenuGymMember{

	public static Scanner sc = new Scanner(System.in);
	
	public static void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta do aluno:");
			System.out.println("1 - Entrar na conta.");
			System.out.println("2 - Excluir conta.");
			System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

			System.out.print("Escolha uma opção: ");
			byte choice = (byte) ScanUtil.readOpt();

			switch (choice) {
			case 1:
				System.out.println("Entrando na conta...");
				accessValidateAccountGymMember();
				break;
			case 2:
				deleteAccountGymMember();
				System.out.println();
			case 0:
				running = false;
				System.out.println("Saindo...");
				sc.close();
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}
	


	private static void accessValidateAccountGymMember() {
		System.out.print("Digite o seu CPF: ");
		String cpf = sc.nextLine();

		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine();

		boolean found = false;
		for (Administrator administrador : Program.listAdm) {

			if (administrador.getCpf().equals(cpf) && administrador.getPassword().equals(password)) {

				System.out.println("Entrando na conta...");
				found = true;
				accessAccountGymMember();
				break;
			}
		}

		if (!found) {
			System.out.println("CPF ou senha incorretos.");
		}
	}
	
	private static void deleteAccountGymMember() {
		System.out.print("Digite o seu CPF: ");
		String cpf = sc.nextLine();

		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine();

		boolean found = false;
		for (GymMember gm : Program.gym.getMembers()) {

			if (gm.getCpf().equals(cpf) && gm.getPassword().equals(password)) {

				System.out.println("Deletando a conta...");
				found = true;
				Program.gym.deleteMembers(gm);
				displayMenu();
				break;
			}
		}

		if (!found) {
			System.out.println("CPF ou senha incorretos.");
		}
	}
	
	private static void accessAccountGymMember() {

		System.out.println("Digite o que você é: ");
		boolean running = true;

		while (running == true) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta do aluno:");
			System.out.println("1 - Exibir lista de treino.");
			System.out.println("2 - Exibir nome do instrutor vinculado.");
			System.out.println("3 - Exibir plano atual.");
			System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

			System.out.println("Escolha uma opção: ");

			byte opt = (byte) ScanUtil.readOpt();

			switch (opt) {

			case 1:
				MenuAdm.displayMenu();
				break;
			case 2:
				MenuGymMember.displayMenu();
				break;
			case 0:
				running = false;
				break;
			}

		}
	}
}
