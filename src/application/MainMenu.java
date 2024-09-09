package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Gym;

public class MainMenu {
	private static Scanner sc = new Scanner(System.in);
	static {
		System.out.println("Welcome to GymManagement System!");
		System.out.println("Please, type what you are: ");
		
	}

	public static void main(String[] args) {

		boolean running = true;
		
		while (running) {
			System.out.println("\u001B[40m"); 
			System.out.println("=============== MAIN MENU ==============");
			System.out.println("|  1 - Administrator                   |");
			System.out.println("|  2 - Gym Member                      |");
			System.out.println("|  3 - Instructor                      |");
			System.out.println("|  4 - Maintenance employee            |");
			System.out.println("|  0 - Exit                            |");
			System.out.println("========================================");
			System.out.println("\u001B[0m");  
			System.out.print("Type your option: ");

			try {
				int opt = sc.nextInt();
				sc.nextLine();

				switch (opt) {
					case 1:
						running = false;
						AdmMenu admMenu = new AdmMenu();
						admMenu.displayMenu();  // Chama o método display do administrador
						break;
					case 2:
						running = false;
						GymMemberMenu gymMemberMenu = new GymMemberMenu();
						gymMemberMenu.displayMenu();  // Chama o método display do membro
						break;
					case 3:
						running = false;
						InstructorMenu instructorMenu = new InstructorMenu();
						instructorMenu.displayMenu();  // Chama o método display do instrutor
						break;
					case 4:
						running = false;
						MaintenanceEmployeeMenu maintenanceEmployeeMenu = new MaintenanceEmployeeMenu();
						maintenanceEmployeeMenu.displayMenu();  // Chama o método display do empregado de manutenção
						break;
					case 0:
						running = false;
						System.out.println("Exiting... Thank you for using the system!");
						break;
					default:
						System.out.println("\u001B[41m");
						System.out.println("Error: Invalid value! Type a number between the valid options!");
						System.out.println("\u001B[0m");
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("\u001B[41m");
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println("\u001B[0m");
				sc.nextLine();  // Limpa o scanner
			}
		}
	}
}
