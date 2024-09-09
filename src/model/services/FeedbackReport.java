package model.services;

public class FeedbackReport implements Report {
	
	private int grade;
	private String comment;
	private String memberName;
	
	public FeedbackReport(int grade, String comment, String memberName) {
		this.grade = grade;
		this.comment = comment;
		this.memberName = memberName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String generateReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
