package dtu.planning.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dtu.planning.app.NotProjectLeaderException;

public class Project {
	private String name;
	private boolean isProjectInternal;
	private Employee projectLeader;
	private List<Activity> activities = new ArrayList<>();
	private int number;
	// start and end dates are missing

	public Project(String name, boolean isProjectInternal, int projectCount) {
		this.name = name;
		this.isProjectInternal = isProjectInternal;
		this.number = projectCount;
//		this.number = generateNumber();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isProjectInternal() {
		return this.isProjectInternal;
	}
	
	public Employee getProjectLeader() {
		return projectLeader;
	}
	
	public void setProjectLeader(Employee employee) {
		this.projectLeader = employee;
	}
	
	public int getProjectNumber() {
		return number;
	}

	public void addActivity(String name, int expectedStart, int expectedEnd, int expectedAmountOfHours, int associatedProjectNumber) {
		// Does not check that the projectID of the activity does match the project of which it is being assigned.
		activities.add(new Activity(name, expectedStart, expectedEnd, expectedAmountOfHours, associatedProjectNumber));
	}
	
	public void assignEmployee(String activityName, Employee projectLeader, Employee employee ) throws NotProjectLeaderException {
		// Todo: Check that projectleader is projectleader for this project
		if (this.projectLeader != projectLeader) {
			throw new NotProjectLeaderException("You are not the project leader for this project");
		}
		
		Activity activity = getActivityByName(activityName);
		
		// assign employee to that activity
		activity.assignEmployee(projectLeader,employee);
	}
	
	public List<Employee> getEmployeesAssignedToActivity(String activityName) {
		Activity activity = getActivityByName(activityName);
		return activity.getAssignedEmployees();
	}
	
	private Activity getActivityByName(String activityName) {
		// Find activity by name
		Optional r = activities
			      .stream()
			      .filter(b -> b.getName().equals(activityName))
			      .findFirst();
	    return (Activity) r.get();
	}
	
	public boolean hasProjectLeader() {
		return false;
	}
	
//	private int generateNumber() {
//		// todo: include dato
//	}

}
