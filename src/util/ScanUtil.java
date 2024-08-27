package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.exceptions.CnpjDoesntMatchException;
import model.exceptions.CnpjRangeException;
import model.exceptions.CpfRangeException;
import model.exceptions.NameDoesntMatchException;

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

	public static String readCpf() {
		boolean valid = false;
		String cpf = null;
		while (!valid) {
			try {
				cpf = sc.next();
				if (cpf.length() != 11 || !cpf.matches("\\d+")) {
					throw new CpfRangeException("Cpf deve ter exatamente 11 dígitos numéricos!");
				}
				valid = true;
			} catch (CpfRangeException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		return cpf;
	}

	public static String readCnpj() {
		boolean valid = false;
		String cnpj = null;
		while (!valid) {
			try {
				cnpj = sc.next();
				if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
					throw new CnpjRangeException("Cnpj deve ter exatamente 14 dígitos numéricos.");
				}
				valid = true;
			} catch (CnpjRangeException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		return cnpj;
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

	public static String readCnpjVal(String comp) {
		boolean valid = false;
		String cnpj = null;
		while (!valid) {
			try {
				cnpj = sc.nextLine();
				if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
					throw new CnpjRangeException("Cnpj deve ter exatamente 14 dígitos numéricos.");
				}
				if (!cnpj.equals(comp)) {
					throw new CnpjDoesntMatchException("Cnpj incorreto, tente novamente.");
				}
				valid = true;
			} catch (CnpjRangeException e) {
				System.out.println("Erro: " + e.getMessage());
			} catch (CnpjDoesntMatchException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		return cnpj;
	}

	public static String readNameVal(String comp) {
		boolean valid = false;
		String name = null;
		while (!valid) {
			try {
				name = sc.nextLine();
				if (!name.equals(comp)) {
					throw new NameDoesntMatchException("Erro! Digite o mesmo nome que utilizou ao registrar sua conta de administrador");
				}
				valid = true;
			} catch (NameDoesntMatchException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		return name;
	}

}
