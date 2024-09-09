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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(kind + " ");
		sb.append("R$: ").append(String.format("%.2f", value));
		
		return sb.toString();
	}
}
