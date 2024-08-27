package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.exceptions.CnpjDoesntMatchException;
import model.exceptions.CnpjRangeException;
import model.exceptions.CpfRangeException;
import model.exceptions.NameDoesntMatchException;

public class ScanUtil {

    private static Scanner sc = new Scanner(System.in);

    public static int readOpt() {
        while (true) {
            try {
                return sc.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.nextLine(); // Limpa o buffer da linha para evitar leituras indesejadas
            }
        }
    }

    public static String readCpf() {
        while (true) {
            try {
                String cpf = sc.next();
                if (cpf.length() != 11 || !cpf.matches("\\d+")) {
                    throw new CpfRangeException("Cpf deve ter exatamente 11 dígitos numéricos!");
                }
                return cpf;
            } catch (CpfRangeException e) {
                System.out.println("Erro: " + e.getMessage());
                sc.nextLine(); // Limpa o buffer da linha
            }
        }
    }

    public static String readCnpj() {
        while (true) {
            try {
                String cnpj = sc.next();
                if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
                    throw new CnpjRangeException("Cnpj deve ter exatamente 14 dígitos numéricos.");
                }
                return cnpj;
            } catch (CnpjRangeException e) {
                System.out.println("Erro: " + e.getMessage());
                sc.nextLine(); // Limpa o buffer da linha
            }
        }
    }

    public static Double readDouble() {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número decimal.");
                sc.nextLine(); // Limpa o buffer da linha
            }
        }
    }
    
    public static String readCnpjVal(String comp) {
        while (true) {
            try {
                String cnpj = sc.next();
                if (cnpj.length() != 14 || !cnpj.matches("\\d+")) {
                    throw new CpfRangeException("Cnpj deve ter exatamente 14 dígitos numéricos.");
                }if (!cnpj.equals(comp)) {
                	throw new CnpjDoesntMatchException("Cnpj incorreto, tente novamente.");
                }
                return cnpj;
            } catch (CnpjDoesntMatchException e) {
                System.out.println("Erro: " + e.getMessage());
                sc.nextLine(); // Limpa o buffer da linha
            }
        }
    }
    
    public static String readNameVal(String comp) {
    	boolean val = false;
    	String nome = "l";
        while (!val) {
            try {
                nome = sc.next();
                if (!nome.equals(comp)) {
                	throw new NameDoesntMatchException("Nome incorreto, tente novamente.");
                }
               val = true;
            } catch (NameDoesntMatchException e) {
                System.out.println("Erro: " + e.getMessage());
                sc.nextLine(); // Limpa o buffer da linha
            }
        }
        return nome;
    }
}
