package dtu.planning.acceptance_tests;

import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;

public class ActorHolder {
	private Employee actor;
	
	public Employee getActor() {
		return actor;
	}

	public void setActor(Employee actor) {
		this.actor = actor;
	}
}
