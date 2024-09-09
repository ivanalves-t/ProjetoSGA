package model.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FeedbackReport implements Report {
	
	private int grade;
	private String comment;
	private String memberName;
	private String reportDate;
	
	public FeedbackReport(int grade, String comment, String memberName) {
		this.grade = grade;
		this.comment = comment;
		this.memberName = memberName;
		generateReport();
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
		// Captura a data e hora atuais
		LocalDateTime now = LocalDateTime.now();
		// Formata a data e hora como uma string legível
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.reportDate = now.format(formatter);
		return null;
	}
	@Override
	public String toString() {
	    // Define o formato do relatório
	    StringBuilder sb = new StringBuilder();

	    // Adiciona o cabeçalho do relatório
	    sb.append("============= Feedback Report ==============\n");
	    sb.append("Date: ").append(reportDate).append("\n");
	    sb.append("Member Name: ").append(memberName).append("\n");
	    sb.append("Grade: ").append(grade).append("\n");
	    sb.append("Comment: ").append(comment).append("\n");
	    sb.append("===========================================\n\n");

	    return sb.toString();
	}
}
