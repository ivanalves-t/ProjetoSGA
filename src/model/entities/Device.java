package model.entities;

public class Device {

    private String deviceName;
    private String status;
    
    // Construtor
    public Device(String deviceName, String status) {
        this.deviceName = deviceName;
        this.status = status;
    }
    
    // Métodos para manipular o status, se necessário
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    
    public String toString() {
    	return "Device: " + deviceName + "Status: " + status; 
    }
}
