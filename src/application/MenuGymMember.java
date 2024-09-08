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
public class MenuGymMember {
	private String currentlyCpf;
	private static MenuGymMember instance;
	private static Scanner sc = new Scanner(System.in);
	private Gym gym;
	private List<GymMember> members = gym.getMembers();

	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

	private MenuGymMember() {

	}
	
	public static MenuGymMember getInstance() {
		if (instance == null) {
			instance = new MenuGymMember();
		}
		return instance;
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

				switch (opt) {
				case 1:
					accessValidateGymMemberAccount();
					break;
				case 2:
					showCurrentlyMembers();
					break;
				case 0:
					running = false;
					System.out.println("Going back to previous menu...");
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
	
	private void showCurrentlyMembers() {
		System.out.println(ANSI_BLUE_BACKGROUND);
		System.out.println("=============== CURRENTLY MEMBERS ===============");
		System.out.println(ANSI_RESET);
		int sum = 0;
		for (GymMember gm : members) {
			if (gm.getCheckIn() == true) {
				sum++;
			}
		}
		System.out.println("Total currently members on gym: " + sum);
	}

	private void accessValidateGymMemberAccount() {
		System.out.print("Type your CPF: ");
		String cpf = ValidDocumentsScan.readCpfVal();
		if (cpf == null) {
			System.out.println("Error: Member doesn't registered on system!");
			return;
		}
		System.out.print("Type your password: ");
		String password = sc.nextLine();
		try {
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
			System.out.println("|  0 - Back to previous menu              |");
			System.out.println("===========================================");
			System.out.println(ANSI_RESET);
			System.out.print("Type your option: ");
			try {
				int opt = sc.nextInt();
				sc.nextLine();
				switch (opt) {
				
				case 1:
					showTrain();
					break;
				case 2:
					showInstructorName();
					break;
				case 3:
					showCurrentlyMembershipPlan();
				case 0:
					running = false;
					currentlyCpf = null;
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
			}

		}
	}
	
	private void showTrain() {
		System.out.println(ANSI_BLUE_BACKGROUND);
		System.out.println("=============== YOUR TRAIN LIST ===============");
		System.out.println(ANSI_RESET);
		Train train = null;

		for (GymMember member : members) {
			if (member.getCpf().equals(this.currentlyCpf)) {
				train = member.getTrain();
			}
		}
		
		if(train.getInstructorName() == null) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("No one instructor made your train list yet");
			System.out.println(ANSI_RESET);
			return;
		}
		System.out.println(train);
	}
	
	private void showInstructorName() {
		System.out.println(ANSI_BLUE_BACKGROUND);
		System.out.println("=============== INSTRUCTOR NAME ===============");
		System.out.println(ANSI_RESET);
		String name = null;
		for (GymMember member : members) {
			if (member.getCpf().equals(this.currentlyCpf)) {
				name = member.getTrain().getInstructorName();
			}
		}
		
		if(name == null) {
			System.out.println(ANSI_RED_BACKGROUND);
			System.out.println("No one instructor made your train list yet");
			System.out.println(ANSI_RESET);
			return;
		}
		System.out.println(name);
	}
	
	private void showCurrentlyMembershipPlan() {
		System.out.println(ANSI_BLUE_BACKGROUND);
		System.out.println("=============== MEMBERSHIP PLAN ===============");
		System.out.println(ANSI_RESET);
		String membership = null;
		for (GymMember member : members) {
			if (member.getCpf().equals(this.currentlyCpf)) {
				membership = member.getMembershipPlan().toString();
			}
		}
		System.out.println(membership);
	}
}
