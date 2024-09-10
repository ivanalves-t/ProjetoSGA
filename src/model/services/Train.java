package model.services;

public class Train {

	private String instructorName;
	private String memberName;
	private String[][] trainList = {
		    {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"},
		    null
		};
	
	public Train(String instructorName, String memberName, String[][] trainList) {
		this.memberName = memberName;
		this.instructorName = instructorName;
		this.trainList = trainList;
	}
	
	public String[][] getTrainList() {
		return trainList;
	}
	
	public void setTrainList(String[][] trainList) {
		this.trainList = trainList;
	}
	
	public String getInstructorName() {
		return instructorName;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	@Override
	public String toString() throws IllegalArgumentException {
	    StringBuilder sb = new StringBuilder();
	    
	    // Verifica se o treino foi criado
	    if (trainList[1] == null) {
	        throw new IllegalArgumentException("The train has not been created yet!");
	    }
	    
	    // Definindo largura de coluna para melhor alinhamento
	    int columnWidth = 20;
	    
	    // Cabeçalho
	    sb.append(String.format("%-" + columnWidth + "s", "Day"))
	      .append(String.format("%-" + columnWidth + "s", "Activity"))
	      .append("\n");
	    
	    // Linha de separação
	    for (int i = 0; i < 2; i++) {
	        sb.append(String.format("%-" + columnWidth + "s", "--------------------"));
	    }
	    sb.append("\n");

	    // Adicionando dados do treino
	    String[] days = trainList[0];
	    String[] activities = trainList[1];
	    
	    for (int i = 0; i < days.length; i++) {
	        sb.append(String.format("%-" + columnWidth + "s", days[i]))
	          .append(String.format("%-" + columnWidth + "s", activities[i] != null ? activities[i] : ""))
	          .append("\n");
	    }
	    
	    return sb.toString();
	}

	
	
	
}
