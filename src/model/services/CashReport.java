package model.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.entities.Employee;
import model.entities.Gym;
import model.entities.GymMember;

public class CashReport implements Report {

	private String message;
	private String reportDate; // Armazena a data de geração do relatório
	private static Gym gym;

	
	
	public CashReport() {

		// Obtém a lista de membros da academia
		this.message = generateReport();

		// Captura a data e hora atuais
		LocalDateTime now = LocalDateTime.now();
		// Formata a data e hora como uma string legível
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.reportDate = now.format(formatter);

	}

	@Override
	public String generateReport() {
		double totalPayments = 0.0;
		double totalMemberships = 0.0;
		StringBuilder report = new StringBuilder();

		// Cabeçalho do relatório com a data de geração
		report.append(message).append("\n");
		report.append("Data de Geração: ").append(reportDate).append("\n\n");

		// Relatório de pagamentos dos funcionários
		report.append("Pagamentos dos Funcionários:\n");
		for (Employee employee : gym.getEmployees()) {
			double payment = employee.payment();
			totalPayments += payment;
			report.append("Funcionário: ").append(employee.getName()).append(", Pagamento: R$ ").append(payment)
					.append("\n");
		}
		report.append("Total de Pagamentos: R$ ").append(totalPayments).append("\n\n");

		// Relatório das mensalidades dos membros
		report.append("Mensalidades dos Membros:\n");
		for (GymMember member : gym.getMembers()) {
			double payment = member.getMembershipPlan().getMonthly();
			totalMemberships += payment;
			report.append("Membro: ").append(member.getName()).append(", Mensalidade: R$ ").append(payment)
					.append("\n");
		}
		report.append("Total de Mensalidades: R$ ").append(totalMemberships).append("\n");

		return report.toString();
	}

}
