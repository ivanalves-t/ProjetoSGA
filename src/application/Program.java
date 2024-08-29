package application;

import java.util.Scanner;

import model.entities.Administrator;
import model.entities.Gym;
import model.services.FrequencyReport;
import util.ScanUtil;

public class Program {
	static Gym gym = new Gym();
	static Administrator administrator = new Administrator();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

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
