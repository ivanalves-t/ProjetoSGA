package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.exceptions.CpfAlreadyExcistsException;
import model.exceptions.CpfRangeException;

public class ScanUtil {
	
    public static int readOpt() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                return sc.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.next(); // Limpa o buffer
                sc.close();
            }
        }
    }
    
    public static String readCpf() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String cpf = sc.next();
                if (cpf.length() != 11 || !cpf.matches("\\d+")) {
                    throw new CpfRangeException("Cpf must be exactly 11 numerical digits.");
                }
                return cpf;
            } catch (CpfRangeException e) {
                System.out.println("Cpf must be exactly 11 numerical digits.");
                sc.next(); // Limpa o buffer
                sc.close();
            }
        }
    }
}

