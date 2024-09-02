package model.entities;

import java.util.Timer;
import java.util.TimerTask;

import model.services.MembershipPlan;

public class GymMember {

	private Timer timer;
	private String name;
	private String cpf;
	private String instructorName;
	private String[][] trainList = {
		    {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"},
		    null
		};
	private MembershipPlan membershipPlan;
	private String password;
	private boolean checkIn;


	public GymMember(String name, String cpf, MembershipPlan membershipPlan, String password) {
		this.name = name;
		this.cpf = cpf;
		this.membershipPlan = membershipPlan;
		this.password = password;
		this.checkIn = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String[][] getTrainList() {
		return trainList;
	}

	public void setTrainList(String[][] trainList) {
		this.trainList = trainList;
	}

	public MembershipPlan getPlan() {
		return membershipPlan;
	}


	public String getPassword() {
		return password;
	}
	
	public boolean getCheckin() {
		return checkIn;
	}
	
	public void setCheckIn() {
		this.checkIn = true;
	}
	

    public void train(int durationInSeconds) {
        timer = new Timer();
        long endTime = System.currentTimeMillis() + durationInSeconds * 1000;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                long remainingTime = (endTime - System.currentTimeMillis()) / 1000;
                if (remainingTime > 0) {
                    System.out.println("Tempo restante: " + remainingTime + " segundos");
                } else {
                    System.out.println("Treino concluído!");
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }
	
}
