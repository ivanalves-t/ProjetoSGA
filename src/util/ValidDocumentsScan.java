package util;

import java.util.Scanner;

import model.exceptions.CnpjAlreadyExistsException;
import model.exceptions.CnpjDoesntMatchException;
import model.exceptions.CnpjRangeException;
import model.exceptions.CpfAlreadyExistsException;
import model.exceptions.CpfDoesntMatchException;
import model.exceptions.CpfRangeException;

public class ValidDocumentsScan implements DocumentsRepository {

	private static Scanner sc = new Scanner(System.in);

	public static String readNewCpfOpt() {
		int tries = 4;
		String cpf;
		while (tries > 0) {
			try {
				cpf = sc.next();
				if (cpf.length() != 11 || !cpf.matches("\\d+")) {
					throw new CpfRangeException("CPF must be exatly 11 numerical digits.");
				}
				if (DocumentsRepository.documents.contains(cpf)) {
					throw new CpfAlreadyExistsException("CPF already registered on system!");
				}
				DocumentsRepository.documents.add(cpf);
				return cpf;
			} catch (CpfRangeException | CpfAlreadyExistsException e) {
				if (tries > 1) {
					System.out.println("\u001B[41mError: " + e.getMessage() + "\nYou got " + (tries - 1) + " tryies remaining\u001B[0m");
				}
				tries--;
			}
		}
		return null;
	}
	
	public static String readNewCpf() {
		boolean valid = false;
		String cpf;
		while (!valid) {
			try {
				cpf = sc.next();
				if (cpf.length() != 11 || !cpf.matches("\\d+")) {
					throw new CpfRangeException("CPF must be exatly 11 numerical digits.");
				}
				if (DocumentsRepository.documents.contains(cpf)) {
					throw new CpfAlreadyExistsException("CPF already registered on system!");
				}
				DocumentsRepository.documents.add(cpf);
				return cpf;
			} catch (CpfRangeException | CpfAlreadyExistsException e) {
				System.out.println("\u001B[41mError: " + e.getMessage() + "\u001B[0m");
			}
		}
		return null;
	}

	public static String readNewCnpj() {
		boolean valid = false;
		String cnpj;
		while (!valid) {
			try {
				cnpj = sc.next();
				if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
					throw new CnpjRangeException("Cnpj must be exactly 14 numerical digits.");
				}
				if (DocumentsRepository.documents.contains(cnpj)) {
					throw new CnpjAlreadyExistsException("Cnpj already registered on system!");
				}
				DocumentsRepository.documents.add(cnpj);

				return cnpj;
			} catch (CnpjRangeException | CnpjAlreadyExistsException e) {
				System.out.println("\u001B[41mError: " + e.getMessage() + "\u001B[0m");
			}
		}
		return null;
	}

	public static String readCpfVal() {
		int tries = 4;
		String cpf;
		while (tries > 0) {
			try {
				cpf = sc.next();
				if (cpf.length() != 11 || !cpf.matches("\\d+")) {
					throw new CpfRangeException("Cpf must be exactly 11 numerical digits!");
				}
				if (!DocumentsRepository.documents.contains(cpf)) {
					throw new CpfDoesntMatchException("Cpf doesn't registered on system yet!");
				}
				return cpf;
			} catch (CpfRangeException | CpfDoesntMatchException e) {
				if (tries > 1) {
					System.out.println(
							"\u001B[41mError: " + e.getMessage() + "\nYou got " + (tries - 1) + " tryies remaining\u001B[0m");
				}
				tries--;
			}
		}
		return null;
	}

	public static String readPlan() {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		char opt = ' ';
		char[] expectedChars = { 'm', 'q', 'a' };

		while (!valid) {
			try {
				opt = sc.next().toLowerCase().charAt(0);
				sc.nextLine();

				if (new String(expectedChars).indexOf(opt) == -1) {
					throw new IllegalArgumentException("Type only 'm', 'q' or 'a'.");
				}

				valid = true;
			} catch (IllegalArgumentException e) {
				System.out.println("\u001B[41mError: " + e.getMessage() + "\u001B[0m");
			}
		}

		return String.valueOf(opt);
	}

	public static String deleteCpf() {
		int tries = 4;
		String cpf;

		while (tries > 0) {
			try {
				cpf = sc.next();
				if (cpf.length() != 11 || !cpf.matches("\\d+")) {
					throw new CnpjRangeException("CPF must be exactly 11 numerical digits!");
				}
				if (!DocumentsRepository.documents.contains(cpf)) {
					throw new CnpjDoesntMatchException("CPF doesn't registered on system yet!");
				}
				DocumentsRepository.documents.remove(cpf);

				return cpf;
			} catch (CnpjRangeException | CnpjDoesntMatchException e) {
				if (tries > 1) {
					System.out.println(
							"\u001B[41mError: " + e.getMessage() + "\nYou got " + (tries - 1) + " tryies remaining\u001B[0m");
				}
				tries--;
			}
		}

		return null;
	}

	public static String deleteCnpj() {
		int tries = 4;
		String cnpj;

		while (tries > 0) {
			try {
				cnpj = sc.next();
				if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
					throw new CnpjRangeException("CNPJ must be exactly 14 numerical digits.");
				}
				if (!DocumentsRepository.documents.contains(cnpj)) {
					throw new CnpjDoesntMatchException("CNPJ doesn't registered on system yet!");
				}
				DocumentsRepository.documents.remove(cnpj);

				return cnpj;
			} catch (CnpjRangeException | CnpjDoesntMatchException e) {
				if (tries > 1) {
					System.out.println(
							"\u001B[41mError: " + e.getMessage() + "\nYou got " + (tries - 1) + " tryies remaining\u001B[0m");
				}
				tries--;
			}
		}
		return null;
	}

}
