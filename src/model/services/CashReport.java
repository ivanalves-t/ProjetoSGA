package model.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.entities.Employee;
import model.entities.Gym;
import model.entities.GymMember;
import model.entities.Instructor;
import model.entities.MaintenanceMan;

public class CashReport implements Report {

    private String message;
    private String reportDate; // Armazena a data de geração do relatório
    private static Gym gym;

    public CashReport() {
        // Certifique-se de que a instância de Gym está inicializada
        gym = Gym.getInstance(); // Substitua isso se você tem um método diferente para obter a instância

        // Captura a data e hora atuais
        LocalDateTime now = LocalDateTime.now();
        // Formata a data e hora como uma string legível
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.reportDate = now.format(formatter);

        // Obtém a lista de membros da academia e gera a mensagem do relatório
        this.message = generateReport();
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
        report.append("Employee Payments:\n");
        report.append(String.format("%-20s %-15s %15s\n", "Employee", "Type", "Payment"));
        report.append("-".repeat(50)).append("\n");

        List<Employee> employees = gym.getEmployees();
        if (employees != null) {
            for (Employee employee : employees) {
                double payment = employee.payment();
                totalPayments += payment;
                
                String employeeType;
                if (employee instanceof Instructor) {
                    employeeType = "Instructor";
                } else if (employee instanceof MaintenanceMan) {
                    employeeType = "MaintenanceMan";
                } else {
                    employeeType = "General";
                }
                
                report.append(String.format("%-20s %-15s %15.2f\n", employee.getName(), employeeType, payment));
            }
        }
        report.append(String.format("\nTotal Payments: %15.2f\n\n", totalPayments));

        // Relatório das mensalidades dos membros
        report.append("Membership Monthly Fees:\n");
        report.append(String.format("%-20s %15s\n", "Member", "Monthly Fee"));
        report.append("-".repeat(35)).append("\n");
        List<GymMember> members = gym.getMembers();
        if (members != null) {
            for (GymMember member : members) {
                double payment = member.getMembershipPlan().getMonthly();
                totalMemberships += payment;
                report.append(String.format("%-20s %15.2f\n", member.getName(), payment));
            }
        }
        report.append(String.format("\nTotal Monthly Fees: %15.2f\n", totalMemberships));

        return report.toString();
    }

    @Override
    public String toString() {
        return message;
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
