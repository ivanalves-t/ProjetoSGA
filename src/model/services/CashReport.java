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
			report.append("======= CASH REPORT =======\n");
			report.append("Generation date: ").append(reportDate).append("\n\n");
			
			// Relatório de pagamentos dos funcionários
			report.append("Employee payments:\n");
			List<Employee> employees = gym.getEmployees();
			for (Employee employee : employees) {
				double payment = employee.payment();
				totalPayments += payment;
				report.append("Employee: ").append(employee.getName()).append(", Payment: R$ ").append(payment)
				.append("\n");
			}
			report.append("Total payments: R$ ").append(totalPayments).append("\n\n");
			
			// Relatório das mensalidades dos membros
			report.append("Memberships monthlys:\n");
			List<GymMember> members = gym.getMembers();
			for (GymMember member : members) {
				double payment = member.getMembershipPlan().getMonthly();
				totalMemberships += payment;
				report.append("Member: ").append(member.getName()).append(", Monthly: R$ ").append(payment)
				.append("\n");
			}
			report.append("Total monthlys: R$ ").append(totalMemberships).append("\n");
			return report.toString();
			
			

	}

	public String getMessage() {
		return message;
	}

	public String getReportDate() {
		return reportDate;
	}

	public Gym getGym() {
		return gym;
	}
	
	

}
