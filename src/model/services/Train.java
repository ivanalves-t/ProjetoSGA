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
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if (trainList[1] == null) {
		    throw new IllegalArgumentException("The train has not been created yet!");
		}
		sb.append("\n");
        for (int i = 0; i < trainList.length; i++) {
            for (int j = 0; j < trainList[i].length; j++) {
                sb.append(trainList[i][j] + "\t");
            }
            sb.append("\n");
        }
        return sb.toString();
	}

	public String getInstructorName() {
		return instructorName;
	}

	public String getMemberName() {
		return memberName;
	}
	
	
	
}
