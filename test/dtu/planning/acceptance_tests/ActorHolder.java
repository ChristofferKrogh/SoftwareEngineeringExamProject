package dtu.planning.acceptance_tests;

import dtu.planning.app.Employee;

public class ActorHolder {
    private Employee actor;

    public Employee getActor() {
        return actor;
    }

    public void setActor(Employee actor) {
        this.actor = actor;
    }
}
