package application;

import java.util.Scanner;

import model.entities.Gym;
import model.entities.GymMember;
import util.ScanUtil;

public class MenuGymMember{
    private static MenuGymMember instance;
	private static Scanner sc = new Scanner(System.in);
	private Gym gym;
	
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
			System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta do aluno:");
			System.out.println("1 - Entrar na conta.");
			System.out.println("2 - Exibir quantidade de membros atuais");
			System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

			System.out.print("Escolha uma opção: ");
			byte choice = (byte) ScanUtil.readOpt();

			switch (choice) {
			case 1:
				System.out.println("Entrando na conta...");
				accessValidateAccountGymMember();
				break;
			case 2:
				int sum = 0;
				for (GymMember gm : gym.getMembers()) {
					if(gm.getCheckin() == true) {
						sum ++;
					}
				}
				System.out.println("total de membros atualmente na academia: " + sum);
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
	


	private void accessValidateAccountGymMember() {
		System.out.print("Digite o seu CPF: ");
		String cpf = sc.nextLine();

		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine();

		boolean found = false;
		for (GymMember g : gym.getMembers()) {

			if (g.getCpf().equals(cpf) && g.getPassword().equals(password)) {

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
	
	private void accessAccountGymMember() {

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
				
				break;
			case 2:

				break;
			case 0:
				running = false;
				break;
			}

		}
	}
	
}
