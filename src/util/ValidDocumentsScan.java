package util;

import java.util.Scanner;

import model.exceptions.CnpjAlreadyExistsException;
import model.exceptions.CnpjRangeException;
import model.exceptions.CpfAlreadyExistsException;
import model.exceptions.CpfDoesntMatchException;
import model.exceptions.CpfRangeException;
import model.exceptions.NameException;
import model.exceptions.RangeNameException;

public class ValidDocumentsScan implements DocumentsRepository {

	public static String readTrain() {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String train = null;

		while (!valid) {
			try {
				train = sc.nextLine();
				if (train == null || train.isEmpty() || train.trim().length() < 10) {
					throw new IllegalArgumentException(
							"The train of train cannot be empty. Train must be longer than 10 caracters.");
				}
				valid = true;
				return train;
			} catch (IllegalArgumentException e) {
				System.out.println("\u001B[41mError: " + e.getMessage() + "\u001B[0m");
			}
		}

		return null;
	}

	public static String readName() {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String name = null;

		while (!valid) {
			try {
				name = sc.nextLine();
				if (name == null || name.isEmpty() || name.trim().length() < 5 || !(name.matches("^[a-zA-Z\\s]+$"))) {
					throw new IllegalArgumentException(
							"The name must be longer than 5 caracters with no numbers with no count spacebars.");
				}
				if (name.length() > 14) {
					throw new RangeNameException("The name must be lower than 15 caracterswith no count spacebars.");
				}
				valid = true;
				return name;
			} catch (IllegalArgumentException | NameException e) {
				System.out.println("\u001B[41mError: " + e.getMessage() + "\u001B[0m");
			}
		}

		return null;
	}

	public static String readNameOpt() {
		Scanner sc = new Scanner(System.in);
		String name = null;
		byte tries = 4;
		while (tries > 0) {
			try {
				name = sc.nextLine();
				if (name == null || name.isEmpty() || name.trim().length() < 5 || !(name.matches("^[a-zA-Z\\s]+$"))) {
					throw new IllegalArgumentException(
							"The name must be longer than 5 caracters with no numbers with no count spacebars.");
				}
				if (name.length() > 14) {
					throw new RangeNameException("The name must be lower than 15 caracters with no count spacebars.");
				}
				return name;
			} catch (IllegalArgumentException| RangeNameException e) {
				if (tries > 1) {
					System.out.println("\u001B[41mError: " + e.getMessage() + "\nYou got " + (tries - 1)
							+ " tryies remaining\u001B[0m");
				}
				tries--;
			}
		}

		return null;
	}

	public static String readPassword() {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String password = null;

		while (!valid) {
			try {
				password = sc.nextLine();
				if (password == null || password.isEmpty() || password.trim().length() < 5) {
					throw new IllegalArgumentException(
							"The password must be longer than 5 caracters with no count spacebars.");
				}
				if (password.trim().length() > 11) {
					throw new IllegalArgumentException(
							"The password must be lower than 11 caracters with no count spacebars.");
				}
				valid = true;
				return password;
			} catch (IllegalArgumentException e) {
				System.out.println("\u001B[41mError: " + e.getMessage() + "\u001B[0m");
			}
		}
		return null;
	}

	public static String readNewCpfOpt() {
		Scanner sc = new Scanner(System.in);
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
					System.out.println("\u001B[41mError: " + e.getMessage() + "\nYou got " + (tries - 1)
							+ " tryies remaining\u001B[0m");
				}
				tries--;
			}
		}

		return null;
	}

	public static String readNewCpf() {
		Scanner sc = new Scanner(System.in);
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
		Scanner sc = new Scanner(System.in);
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
		Scanner sc = new Scanner(System.in);
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
					System.out.println("\u001B[41mError: " + e.getMessage() + "\nYou got " + (tries - 1)
							+ " tryies remaining\u001B[0m");
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
				System.out.print("Enter gym membership ('m' for monthly, 'q' for quarterly, 'a' for annual): ");
				String input = sc.nextLine().toLowerCase(); // Read the entire line and convert to lowercase

				if (input.length() != 1) {
					throw new IllegalArgumentException("Type only one character: 'm', 'q', or 'a'.");
				}

				opt = input.charAt(0);
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

	public static void deleteCpf(String cpf) {
		documents.remove(cpf);
	}

	public static void deleteCnpj(String cpf) {
		documents.remove(cpf);
	}

}
