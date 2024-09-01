package application;

import java.util.Scanner;

import util.ScanUtil;

public class MenuEmployee {

	private static Scanner sc = new Scanner (System.in);
	
	public static void displayMenu() {
		System.out.println("Digite o que você é: ");
		boolean running = true;

		while (running == true) {
			System.out.println("=-=-=-=-=-=-=-=-=-=-\n1- Administrador");
			System.out.println("2- Funcionário");
			System.out.println("3- Aluno");
			System.out.println("0- sair\n=-=-=-=-=-=-=-=-=-=-");

			System.out.println("Escolha uma opção: ");

			byte opt = (byte) ScanUtil.readOpt();

			switch (opt) {

			case 1:
				MenuAdm.displayMenu();
				break;
			case 2:
				MenuEmployee.displayMenu();
				break;
			case 3:
				MenuGymMember.displayMenu();
			case 0:
				running = false;
				sc.close();
				break;
			}

		}

	}
}
