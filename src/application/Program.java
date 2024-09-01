package application;

import java.util.Scanner;

import util.ScanUtil;

public class Program {
	private static Scanner sc = new Scanner(System.in);
	private static MenuAdm menuAdm;
	private static MenuGymMember menuGymMember;
	private static MenuInstructor menuInstructor;
	
	public static void main(String[] args) {
		menuAdm = null;
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
				menuAdm.displayMenu();
				break;
			case 2:
				menuInstructor.displayMenu();
				break;
			case 3:
				menuGymMember.displayMenu();
			case 0:
				running = false;
				sc.close();
				break;
			default:
				System.out.println("Digite uma opção acima!");
				break;
			}

		}

	}

}
