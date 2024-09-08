package application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
	private static Scanner sc = new Scanner(System.in);
	private static MenuAdm menuAdm;
	private static MenuGymMember menuGymMember;
	private static MenuInstructor menuInstructor;

	public static void main(String[] args) {
		menuAdm = MenuAdm.getInstance();  
		menuGymMember = MenuGymMember.getInstance();  
		menuInstructor = MenuInstructor.getInstance();  

		System.out.println("Welcome to GymManagement System!");
		System.out.println("Please, type what you are: ");
		boolean running = true;

		while (running) {
			System.out.println("\u001B[40m"); 
			System.out.println("=============== MAIN MENU ==============");
			System.out.println("|  1 - Administrator                   |");
			System.out.println("|  2 - Instructor                      |");
			System.out.println("|  3 - Gym Member                      |");
			System.out.println("|  0 - Exit                            |");
			System.out.println("========================================");
			System.out.println("\u001B[0m");  
			System.out.print("Type your option:");

			try {
				int opt = sc.nextInt();
				sc.nextLine();

				switch (opt) {
					case 1:
						menuAdm.displayMenu();
						break;
					case 2:
						menuInstructor.displayMenu();
						break;
					case 3:
						menuGymMember.displayMenu();
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
			}
		}
	}
}
