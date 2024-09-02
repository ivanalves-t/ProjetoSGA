package model.services;

public class MembershipPlan {

	private String kind;
	private double value;
	
	public MembershipPlan(String kind, double value) {
		super();
		this.kind = kind;
		this.value = value;
	}

	public String getKind() {
		return kind;
	}

	public double getMonthly() {
		return value;
	}
	
	
}
