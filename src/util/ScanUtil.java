package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.exceptions.MinimumWageException;

public class ScanUtil {

	private static Scanner sc = new Scanner(System.in);

	public static byte readOpt() {
		boolean valid = false;
		byte opt = -1;
		while (!valid) {
			try {
				opt = sc.nextByte();
				sc.nextLine();
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, insira um número.");
				sc.nextLine(); // Limpa o buffer da linha para evitar leituras indesejadas
			}
		}
		return opt;
	}
	
	public static Double readDouble() {
		boolean valid = false;
		Double d = null;
		while (!valid) {
			try {
				d = sc.nextDouble();
				sc.nextLine();
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, insira um número decimal.");
				sc.nextLine(); // Limpa o buffer da linha
			}
		}
		return d;
	}

	public static Double readWage() {
		boolean valid = false;
		Double d = null;
		while (!valid) {
			try {
				d = sc.nextDouble();
				sc.nextLine();
				valid = true;
				if (d < 1412.00) {
					throw new MinimumWageException("The minimum wage is R$ 1421.00!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, insira um número decimal.");
				sc.nextLine(); // Limpa o buffer da linha
			}catch (MinimumWageException e) {
				System.out.println("Erroe: " + e.getMessage());
			}
		}
		return d;
	}

}
