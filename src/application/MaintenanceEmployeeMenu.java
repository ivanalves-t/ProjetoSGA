package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Gym;
import model.entities.MaintenanceEmployee;

public class MaintenanceEmployeeMenu {

	private Gym gym;
	private MaintenanceEmployee currentlyME;
	private static Scanner sc = new Scanner(System.in);
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

	public MaintenanceEmployeeMenu() {
		gym = Gym.getInstance();
	}

	public void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_WHITE_BACKGROUND);
			System.out.println("=============== INSTRUCTOR MENU ===============");
			System.out.println("|  1 - Sign in instructor account             |");
			System.out.println("|  2 - Show current members frequency         |");
			System.out.println("|  0 - Back to previous menu                  |");
			System.out.println("===============================================");
			System.out.println(ANSI_RESET);

			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();

				switch (opt) {
				case 1:
					running = false;
//					accessValidateInstructorAccount();
					break;
				case 2:
					running = false;
//					showCurrentlyMembers();
					break;
				case 0:
					running = false;
					System.out.println("Going back to main menu...\n\n");
					MainMenu.main(null);
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Invalid value! Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number inside the range of options!");
				System.out.println(ANSI_RESET);
			}
		}
	}
}
