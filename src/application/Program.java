package application;

import java.util.Scanner;

import util.ScanUtil;

public class Program {
	private static Scanner sc = new Scanner(System.in);
    private static MenuAdm menuAdm;
    private static MenuGymMember menuGymMember;
    private static MenuInstructor menuInstructor;
    
	public static void main(String[] args) {
        menuAdm = MenuAdm.getInstance();  // Inicializa a instância Singleton
        menuGymMember = MenuGymMember.getInstance();  // Inicializa a instância Singleton, se necessário
        menuInstructor = MenuInstructor.getInstance();  // Inicializa a instância Singleton, se necessário
        
        System.out.println("Welcome to GymManagement System!");
        System.out.println("Please, type what you are: ");
		boolean running = true;

		while (running == true) {
			System.out.println("=============== MAIN MENU ==============");
			System.out.println("|  1 - Admininstrator                  |");
			System.out.println("|  2 - Instructor                      |");
			System.out.println("|  3 - Gym Member                      |");
			System.out.println("|  0 - Back to previous menu           |");
			System.out.println("========================================");
			System.out.println("Type your option:");
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
				System.out.println("");
				break;
			}

		}

	}

}
