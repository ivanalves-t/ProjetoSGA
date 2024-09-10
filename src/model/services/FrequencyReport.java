package model.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.entities.Gym;
import model.entities.GymMember;

public class FrequencyReport implements Report {
    
    private String message;
    private String reportDate; // Armazena a data de geração do relatório
    private Gym gym = Gym.getInstance(); // Inicializa a instância de Gym

    public FrequencyReport() {
        // Captura a data e hora atuais
        LocalDateTime now = LocalDateTime.now();
        // Formata a data e hora como uma string legível
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.reportDate = now.format(formatter);

        // Gera a mensagem do relatório
        this.message = generateReport();
    }

    @Override
    public String generateReport() {
        int totalOnline = 0;
        StringBuilder report = new StringBuilder();
        
        // Cabeçalho do relatório com a data de geração
        report.append("======= FREQUENCY REPORT =======\n");
        report.append("Generation date: ").append(reportDate).append("\n\n");
        
        // Relatório de frequência dos membros
        report.append("Membership report:\n");
        List<GymMember> members = gym.getMembers();
        for (GymMember gymMember : members) {
            String freq;
            if (gymMember.getCheckIn()) {
                freq = "present";
                totalOnline++;
            } else {
                freq = "absent";
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
    
    @Override
    public String toString() {
        // Retorna a mensagem formatada
        return message;
    }
}
