package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.entities.Gym;
import model.entities.GymMember;
import model.exceptions.CpfDoesntMatchException;
import model.services.Train;
import util.ValidDocumentsScan;
//done
public class GymMemberMenu {
	private String currentlyCpf;
	private static Scanner sc = new Scanner(System.in);
	private Gym gym;

	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

	public GymMemberMenu() {
		gym = Gym.getInstance();
	}

	public void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println(ANSI_BLUE_BACKGROUND);
			System.out.println("=============== MEMBER MENU ===============");
			System.out.println("|  1 - Sign in member account             |");
			System.out.println("|  2 - Show current members frequency     |");
			System.out.println("|  0 - Back to previous menu              |");
			System.out.println("===========================================");
			System.out.println(ANSI_RESET);

			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();
				switch (opt) {
				case 1:
					running = false;
					accessValidateGymMemberAccount();
					break;
				case 2:
					running = false;
					showCurrentlyMembers();
					break;
				case 0:
					running = false;
					System.out.println("Going back to main menu...");
					currentlyCpf = null;
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
				sc.nextLine();
			}
		}
	}
	
	private void showCurrentlyMembers() {
		System.out.println(ANSI_BLUE_BACKGROUND);
		System.out.println("=============== CURRENTLY MEMBERS ===============");
		System.out.println(ANSI_RESET);
		int sum = 0;
		try {
			List<GymMember> members = gym.getMembers();
			for (GymMember gm : members) {
				if (gm.getCheckIn() == true) {
					sum++;
				}
			}
			System.out.println("Total currently members on gym: " + sum);
			displayMenu();
		}catch(Exception e ) {
			e.printStackTrace();
			displayMenu();
		}
	}

	private void accessValidateGymMemberAccount() {
		System.out.print("Type your CPF: ");
		String cpf = ValidDocumentsScan.readCpfVal();
		if (cpf == null) {
			System.out.println("Error: Ask to your admin to register you!");
			displayMenu();
			return;
		}
		System.out.print("Type your password: ");
		String password = ValidDocumentsScan.readPassword();
		try {
			List<GymMember> members = gym.getMembers();
			for (GymMember gymMember : members) {
				if (!gymMember.getCpf().equals(cpf)) {
					throw new CpfDoesntMatchException(" Wrong cpf!");
				}
				if (!gymMember.getPassword().equals(password)) {
					throw new IllegalArgumentException(" Wrong Password!");
				}
				currentlyCpf = gymMember.getCpf();
				accessAccountGymMember();
			}
			
		}catch(CpfDoesntMatchException | IllegalArgumentException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: " + e.getMessage());
			System.out.println(ANSI_RESET);
			System.out.println("Tip: first acces? you password could be your CPF");
			displayMenu();
		}
	}

	private void accessAccountGymMember() {

		boolean running = true;

		while (running == true) {
			System.out.println(ANSI_BLUE_BACKGROUND);
			System.out.println("=============== MENU MEMBER ===============");
			System.out.println("|  1 - Show train list                    |");
			System.out.println("|  2 - Show linked instructor's name      |");
			System.out.println("|  3 - Show currently membership plan     |");
			System.out.println("|  4 - Modify password                    |");
			System.out.println("|  0 - Back to previous menu              |");
			System.out.println("===========================================");
			System.out.println(ANSI_RESET);
			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();
				switch (opt) {
				
				case 1:
					running = false;
					showTrain();
					break;
				case 2:
					running = false;
					showInstructorName();
					break;
				case 3:
					running = false;
					showCurrentlyMembershipPlan();
				case 4:
					running = false;
					alterPassword();
				case 0:
					running = false;
					currentlyCpf = null;
					displayMenu();
					break;
				default:
					System.out.println(ANSI_RED_BACKGROUND);
					System.out.println("Error: Invalid value! Type a number between the valid options!");
					System.out.println(ANSI_RESET);
					break;
				}
				
			}catch(InputMismatchException e) {
				System.out.println(ANSI_RED_BACKGROUND);
				System.out.println("Error: Please, enter a number inside the range of options.");
				System.out.println(ANSI_RESET);
				sc.nextLine();
			}

		}
	}
	
	private void alterPassword() {
		System.out.println(ANSI_BLUE_BACKGROUND);
		System.out.println("=============== SET NEW PASSWORD ===============");
		System.out.println(ANSI_RESET);
		
		System.out.print("Please, enter your new password: ");
		String password = ValidDocumentsScan.readPassword();
		for (GymMember gm : gym.getMembers()) {
			if (gm.getCpf().equals(currentlyCpf)){
				gm.setPassword(password);
			}
		}
		System.out.println(ANSI_GREEN_BACKGROUND);
		System.out.println("Password modified successfully!");
		System.out.println(ANSI_RESET);
		System.out.println("Please sign in again.");
		displayMenu();
	}
	
	private void showTrain() {
		try {
			System.out.println(ANSI_BLUE_BACKGROUND);
			System.out.println("=============== YOUR TRAIN LIST ===============");
			System.out.println(ANSI_RESET);
			Train train = null;
			List<GymMember> members = gym.getMembers();
			for (GymMember member : members) {
				if (member.getCpf().equals(this.currentlyCpf)) {
					train = member.getTrain();
				}
			}
			if (train == null) {
				throw new NullPointerException("Empty. Ask a instructor to train you.");
			}
			System.out.println(train);
			accessAccountGymMember();
			
		}catch (NullPointerException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: " + e.getMessage());
			System.out.println(ANSI_RESET);
			accessAccountGymMember();
		}
	}
	
	private void showInstructorName() {
		System.out.println(ANSI_BLUE_BACKGROUND);
		System.out.println("=============== INSTRUCTOR NAME ===============");
		System.out.println(ANSI_RESET);
		try {
		String name = null;
		List<GymMember> members = gym.getMembers();
		for (GymMember member : members) {
			if (member.getCpf().equals(this.currentlyCpf)) {
				name = member.getTrain().getInstructorName();
			}
		}
			if(name == null) {
				throw new NullPointerException("No one instructor made your train list yet");
			}
			System.out.println(name);
			accessAccountGymMember();
			
		}catch(NullPointerException e) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("Error: " + e.getMessage());
			System.out.println(ANSI_RESET);
			accessAccountGymMember();
		}
	}
	
	private void showCurrentlyMembershipPlan() {
		System.out.println(ANSI_BLUE_BACKGROUND);
		System.out.println("=============== MEMBERSHIP PLAN ===============");
		System.out.println(ANSI_RESET);
		String membership = null;
		List<GymMember> members = gym.getMembers();
		for (GymMember member : members) {
			if (member.getCpf().equals(this.currentlyCpf)) {
				membership = member.getMembershipPlan().toString();
			}
		}
		System.out.println(membership);
		accessAccountGymMember();
	}
}
