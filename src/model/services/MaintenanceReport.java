package model.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.entities.Device;
import model.entities.Gym;

public class MaintenanceReport implements Report {

    private String maintenanceManName;
    private String reportDate;
    private Gym gym;
    private String message;

    public MaintenanceReport(String maintenanceManName) {
        this.maintenanceManName = maintenanceManName;
        this.message = generateReport(); // Gerar a mensagem após a inicialização dos dados
    }

    public String getMessage() {
        return message;
    }

    public String getMaintenanceManName() {
        return maintenanceManName;
    }

    public String getReportDate() {
        return reportDate;
    }

    @Override
    public String generateReport() {
        // Captura a data e hora atuais
        LocalDateTime now = LocalDateTime.now();
        // Formata a data e hora como uma string legível
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.reportDate = now.format(formatter);

        return toString();
    }

    @Override
    public String toString() {
        // Define o formato do relatório
        StringBuilder sb = new StringBuilder();

        // Adiciona o cabeçalho do relatório
        sb.append("============= Maintenance Report =============\n");
        sb.append("Date: ").append(reportDate).append("\n");
        sb.append("Maintenance Man: ").append(maintenanceManName).append("\n");

        // Adiciona o cabeçalho da tabela
        sb.append("\nDevices:\n");
        sb.append(String.format("%-20s %-30s\n", "Device status", "Device Name")); // Ajuste conforme os campos de Device
        sb.append(String.format("%s\n", "-".repeat(50))); // Linha separadora
        List<Device> devices = gym.getDevices();        // Adiciona detalhes dos dispositivos
        if (devices.isEmpty()) {
            sb.append("No devices registered.\n");
        } else {
            for (Device device : devices) {
                // Ajuste os métodos getId() e getName() conforme os métodos disponíveis em Device
                sb.append(String.format("%-20s %-30s\n", device.getStatus(), device.getDeviceName()));
            }
        }

        sb.append("===========================================\n\n");

        return sb.toString();
    }

}
