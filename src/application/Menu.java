package application;

import java.util.ArrayList;
import java.util.Scanner;

import model.entities.Administrator;

public class Menu {
	
	static ArrayList<Administrator> listAdm = new ArrayList<Administrator>();
	
	private static int n;
	
	static Scanner sc = new Scanner(System.in);
	
	public static void menu(int opt) {
		switch (opt) {
		case 1:
			System.out.println("1- Criar conta");
			System.out.println("2- Acessar conta");
			System.out.println("Escolha uma opção: ");
			int opt1 = sc.nextInt();
			
			if(opt1 == 1) {
				System.out.println("Digite o seu CPF: ");
				int CPF = sc.nextInt(); 
				System.out.println("Escreva o seu nome: ");
				String nome = sc.nextLine();
				System.out.println("Crie uma senha: ");
				int password = sc.nextInt();
				
				listAdm.add(new Administrator(nome, CPF, password));
			
			}
			
			else if (opt1 == 2) {
				System.out.println("Faça o login");
				System.out.println("Digite o seu CPF: ");
				int CPF = sc.nextInt(); 
				System.out.println("Digite a sua senha: ");
				int password = sc.nextInt();
				
				for (Administrator administrator : listAdm) {
					if(administrator.getCPF() == CPF && administrator.getPassword() == password) {
						System.out.println("Entrando na conta...");
					}
				}
			}
		}
		
	}

	public static int getN() {
		return n;
	}
}

