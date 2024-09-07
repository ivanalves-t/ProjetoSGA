package model.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.entities.Gym;
import model.entities.GymMember;

public class FrequencyReport implements Report{
	
	private String message;
	private String reportDate; // Armazena a data de geração do relatório
	private static Gym gym;
	

	public FrequencyReport() {

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

			int totalOnline = 0;
			StringBuilder report = new StringBuilder();
			
			// Cabeçalho do relatório com a data de geração
			report.append("======= FREQUENCY REPORT =======\n");
			report.append("Generation date: ").append(reportDate).append("\n\n");
			
			// Relatório de pagamentos dos funcionários
			report.append("Membership report:\n");
			List<GymMember> members = gym.getMembers();
			for (GymMember gymMember : members) {
				String freq;
				if (gymMember.getCheckIn() == true) {
					freq = "present";
					totalOnline ++;
				}else {
					freq = "ausent";
				}
				report.append("Member: ").append(gymMember.getName()).append(", Frequency: ").append(freq)
				.append("\n");
			}
			report.append("Total members present: ").append(totalOnline).append("\n\n");
			return report.toString();
			
	}

	public String getMessage() {
		return message;
	}

	public String getReportDate() {
		return reportDate;
	}
	
	
}
