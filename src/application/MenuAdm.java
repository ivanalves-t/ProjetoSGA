package application;

import java.util.Scanner;

import model.entities.Administrator;
import model.entities.Gym;
import model.entities.GymMember;
import util.ScanUtil;
import util.ValidDocumentsScan;

public class MenuAdm{

    private static Scanner sc = new Scanner(System.in);

    public static void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa:");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Acessar conta");
            System.out.println("0 - Sair\n=-=-=-=-=-=-=-=-=-=-");

            System.out.print("Escolha uma opção: ");
            byte choice = (byte) ScanUtil.readOpt();

            switch (choice) {
                case 1:
                    createAccountAdm();
                    break;
                case 2:
                	if (Program.administrator.getCpf() == null) {
                		System.out.println("Erro: crie uma conta antes de acessa-la!");
                		break;
                	}
                	accessAccountAdm();
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Erro: Digite um número dentre as opções listadas!");
            }
        }
    }

    private static void createAccountAdm() {
        System.out.print("Digite o seu CPF: ");
        String cpf = ValidDocumentsScan.readCpf();

        System.out.print("Escreva o seu nome: ");
        String name = sc.nextLine();

        System.out.print("Crie uma senha: ");
        String password = sc.nextLine();

        System.out.print("Digite o nome da academia: ");
        String nameGym = sc.nextLine();

        System.out.print("Digite o CNPJ: ");
        String CNPJ = ValidDocumentsScan.readCnpj();

        Program.administrator = new Administrator(name, cpf, password, nameGym, CNPJ);
        System.out.println("Conta criada com sucesso!");
    }

    private static void accessAccountAdm() {
        System.out.print("Digite o seu CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Digite a sua senha: ");
        String password = sc.nextLine();

        boolean found = false;

        if (Program.administrator.getCpf().equals(cpf) && Program.administrator.getPassword().equals(password)) {
            System.out.println("Entrando na conta...");
            found = true;
            menuAdm2();
        }

        if (!found) {
            System.out.println("CPF ou senha incorretos.");
        }
    }

    private static void menuAdm2() {
        boolean running = true;

        while (running) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa:");
            System.out.println("1 - Criar academia");
            System.out.println("2 - Cadastrar novo aluno");
            System.out.println("3 - Cadastrar novo funcionário");
            System.out.println("4 - Gerar relatórios");
            System.out.println("5 - Excluir conta");
            System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");

            System.out.print("Escolha uma opção: ");
            byte choice = (byte) ScanUtil.readOpt();

            switch (choice) {
                case 1:
                    System.out.println("-=-=-=-=-=-=-=-=-=-=\nCadastrar nova academia");
                    System.out.print("Nome da Academia: ");
                    String gymName = ValidDocumentsScan.readNameVal(Program.administrator.getNameGym()); // Usa o método com validação

                    System.out.print("Informe o CNPJ da academia: ");
                    String ownerCnpj = ValidDocumentsScan.readCnpjVal(Program.administrator.getCnpj());

                    System.out.print("Informe o valor mensal: ");
                    double[] planValue = Program.gym.generatePlan(ScanUtil.readDouble());

                    Program.gym = new Gym(gymName, ownerCnpj, planValue);
                    System.out.println("Academia cadastrada com sucesso!");
                    break;


                case 2:
                    System.out.println("-=-=-=-=-=-=-=-=-=-=\nCadastrar novo aluno");

                    System.out.print("Informe o nome do aluno: ");
                    String name = sc.nextLine();

                    System.out.print("Informe o CPF do aluno: ");
                    String cpf = ValidDocumentsScan.readCpf();

                    sc.nextLine();  // Limpa o buffer após a leitura do CPF

                    System.out.print("Informe o plano do aluno: ");
                    String plain = sc.nextLine();

                    System.out.print("Digite uma senha para o aluno:");
                    String password = sc.nextLine();

                    System.out.println("Aluno cadastrado com sucesso!");
                    for (GymMember g : Program.gym.getMembers()) {
						if (g.getCpf().equals(cpf)) {
							System.out.println("Cpf já registrado no sistema!");
							break;
						}
					}
                    Program.gym.addMembers(new GymMember(name, cpf, plain, password));
                    break;

                case 3:
                    
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    System.out.println("Funcionalidade de excluir conta");
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Erro: Digite um número dentre as opções listadas!");
            }
        }
    }

    private static void generateReports() {
    	boolean running = true;
    	
    	while (running) {
    		System.out.println("-=-=-=-=-=-=-=-=-=-=\nConta Administrativa/ Relatorios:");
    		System.out.println("1 - Mostrar dados do administrador");
    		System.out.println("2 - Relatórios de pagamentos");
    		System.out.println("3 - Relatório de frequência atual");
    		System.out.println("4 - Mostrar todos os alunos");
    		System.out.println("5 - Mostrar todos os funcionários");
    		System.out.println("0 - Menu Anterior\n-=-=-=-=-=-=-=-=-=-=\n");
    		
    		System.out.print("Escolha uma opção: ");
    		byte choice = (byte) ScanUtil.readOpt();
    		
    		switch (choice) {
    		case 1:
    			System.out.println(Program.administrator);
    			break;
    			
    		case 2:
    			System.out.println("-=-=-=-=-=-=-=-=-=-=\nCadastrar novo aluno");
    			
    			System.out.println("Informe o nome do aluno: ");
    			String name = sc.nextLine();
    			
    			System.out.println("Informe o seu CPF do aluno: ");
    			String cpf = ValidDocumentsScan.readCpf();
    			
    			sc.nextLine();  // Limpa o buffer após a leitura do CPF
    			
    			System.out.println("Informe o plano do aluno: ");
    			String plain = sc.nextLine();
    			
    			System.out.println("Digite uma senha para o aluno:");
    			String password = sc.nextLine();
    			
    			System.out.println("Aluno cadastrado com sucesso!");
    			Program.gym.addMembers(new GymMember(name, cpf, plain, password));
    			break;
    			
    		case 3:
    			System.out.println("Cadastrar novo funcionário");
    			break;
    		case 4:
    			System.out.println("Gerar relatórios");
    			break;
    		case 5:
    			System.out.println("Funcionalidade de excluir conta");
    			break;
    		case 0:
    			running = false;
    			System.out.println("Saindo...");
    			break;
    		default:
    			System.out.println("Erro: Digite um número dentre as opções listadas!");
    		}
    	}
    }
}
