package util;

import java.util.Scanner;

import model.exceptions.CnpjAlreadyExistsException;
import model.exceptions.CnpjDoesntMatchException;
import model.exceptions.CnpjRangeException;
import model.exceptions.CpfAlreadyExistsException;
import model.exceptions.CpfRangeException;

public class ValidDocumentsScan implements DocumentsRepository {

	private static Scanner sc = new Scanner(System.in);

	public static String readNewCpf() {
		int tries = 4;
		String cpf;
		while (tries > 0) {
			try {
				cpf = sc.next();
				if (cpf.length() != 11 || !cpf.matches("\\d+")) {
					throw new CpfRangeException("CPF deve ter exatamente 11 dígitos numéricos.");
				}
				if (DocumentsRepository.documents.contains(cpf)) {
					throw new CpfAlreadyExistsException("CPF já registrado no sistema!");
				}
				DocumentsRepository.documents.add(cpf);
				System.out.println("Sucesso!");
				return cpf;
			} catch (CpfRangeException | CpfAlreadyExistsException e) {
	        	if(tries > 1) {
	        		System.out.println("Erro: " + e.getMessage() + "\nVocê tem " + (tries - 1) + " tentativa(s) restante(s)");	        		
	        	}
	            tries--;
			}
		}
		return null;
	}

	public static String readNewCnpj() {
		int tries = 4;
		String cnpj;
		while (tries > 0) {
			try {
				cnpj = sc.next();
				if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
					throw new CnpjRangeException("Cnpj deve ter exatamente 14 dígitos numéricos.");
				}
				if (DocumentsRepository.documents.contains(cnpj)) {
					throw new CnpjAlreadyExistsException("Cnpj já registrado no sistema!");
				}
				DocumentsRepository.documents.add(cnpj);
				System.out.println("Sucesso!");
				return cnpj;
			} catch (CnpjRangeException | CnpjAlreadyExistsException e) {
	        	if(tries > 1) {
	        		System.out.println("Erro: " + e.getMessage() + "\nVocê tem " + (tries - 1) + " tentativa(s) restante(s)");	        		
	        	}
	            tries--;
			}
		}
		return null;
	}

	public static String readCnpjVal() {
		int tries = 4;
		String cnpj;
		while (tries > 0) {
			try {
				cnpj = sc.nextLine();
				if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
					throw new CnpjRangeException("Cnpj deve ter exatamente 14 dígitos numéricos.");
				}
				if (!DocumentsRepository.documents.contains(cnpj)) {
					throw new CnpjDoesntMatchException("Cnpj não registrado no sistema");
				}
				return cnpj;
			} catch (CnpjRangeException | CnpjDoesntMatchException e) {
	        	if(tries > 1) {
	        		System.out.println("Erro: " + e.getMessage() + "\nVocê tem " + (tries - 1) + " tentativa(s) restante(s)");	        		
	        	}
	            tries--;
			}
		}
	    System.out.println("Número máximo de tentativas excedido.");
	    return null;
	}

	public static String readCpfVal() {
		int tries = 4;
		String cpf;
		while (tries > 0) {
			try {
				cpf = sc.nextLine();
				if (cpf.length() != 11 || !cpf.matches("\\d+")) {
					throw new CnpjRangeException("Cpf deve ter exatamente 11 dígitos numéricos.");
				}
				if (!DocumentsRepository.documents.contains(cpf)) {
					throw new CnpjDoesntMatchException("Cpf não registrado no sistema");
				}
				return cpf;
			} catch (CnpjRangeException | CnpjDoesntMatchException e) {
	        	if(tries > 1) {
	        		System.out.println("Erro: " + e.getMessage() + "\nVocê tem " + (tries - 1) + " tentativa(s) restante(s)");	        		
	        	}
	            tries--;
			}
		}
	    System.out.println("Número máximo de tentativas excedido.");
	    return null;
	}
	
	public static String readPlan() {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		char opt = ' ';
		char[] expectedChars = { 'm', 't', 'a' };

		while (!valid) {
			try {
				System.out.print("Digite uma opção: ");
				opt = sc.next().toLowerCase().charAt(0);
				sc.nextLine();

				if (new String(expectedChars).indexOf(opt) == -1) {
					throw new IllegalArgumentException("Digite somente 'm', 't' ou 'a'.");
				}

				valid = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		return String.valueOf(opt);
	}
	
	public static String deleteCpf() {
	    int tries = 4;
	    String cpf;

	    while (tries > 0) {
	        try {
	            cpf = sc.nextLine();
	            if (cpf.length() != 14 || !cpf.matches("\\d+")) {
	                throw new CnpjRangeException("CPF deve ter exatamente 11 dígitos numéricos.");
	            }
	            if (!DocumentsRepository.documents.contains(cpf)) {
	                throw new CnpjDoesntMatchException("CPF não registrado no sistema");
	            }
	            DocumentsRepository.documents.remove(cpf);
	            System.out.println("Sucesso!");
	            return cpf;
	        } catch (CnpjRangeException | CnpjDoesntMatchException e) {
	        	if(tries > 1) {
	        		System.out.println("Erro: " + e.getMessage() + "\nVocê tem " + (tries - 1) + " tentativa(s) restante(s)");	        		
	        	}
	            tries--;
	        }
	    }

	    System.out.println("Número máximo de tentativas excedido.");
	    return null;
	}
	
	public static String deleteCnpj() {
	    int tries = 4;
	    String cnpj;

	    while (tries > 0) {
	        try {
	            cnpj = sc.nextLine();
	            if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
	                throw new CnpjRangeException("CNPJ deve ter exatamente 14 dígitos numéricos.");
	            }
	            if (!DocumentsRepository.documents.contains(cnpj)) {
	                throw new CnpjDoesntMatchException("CNPJ não registrado no sistema");
	            }
	            DocumentsRepository.documents.remove(cnpj);
	            System.out.println("Sucesso!");
	            return cnpj;
	        } catch (CnpjRangeException | CnpjDoesntMatchException e) {
	        	if(tries > 1) {
	        		System.out.println("Erro: " + e.getMessage() + "\nVocê tem " + (tries - 1) + " tentativa(s) restante(s)");	        		
	        	}
	            tries--;
	        }
	    }

	    System.out.println("Número máximo de tentativas excedido.");
	    return null;
	}
	
}
