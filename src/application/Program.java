package application;

import java.util.Scanner;

public class Program {
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o que você é: ");
		boolean running = true;
		
		while(running == true) {
			System.out.println("=-=-=-=-=-=-=-=-=-=-\n1- Administrador");
			System.out.println("2- Funcionário");
			System.out.println("3- Aluno");
			System.out.println("4- Gerar relatório de presença");
			System.out.println("0- sair\n=-=-=-=-=-=-=-=-=-=-");
		
			System.out.println("Escolha uma opção: ");
			
			int opt = sc.nextInt();
			
			switch (opt) {
			case 1: 
					Menu.menuAdm();
					break;
			case 3:
				Menu.menuGymMember();
				break;
			case 0:
				running = false;
				break;
			}
	
		}
	}
}
