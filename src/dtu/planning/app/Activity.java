package dtu.planning.app;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	
	private String name; 
	private int expectedStart; 
	private int expectedEnd; 
	private int expectedAmountOfHours;
	private int associatedProjectNumber; 
	private List<Employee> employees = new ArrayList<>();
	private List<TimeRegistration> timeRegistrations = new ArrayList<>();
	
	public Activity(String name, int expectedStart, int expectedEnd, int expectedAmountOfHours, int associatedProjectNumber) {
		this.name = name; 
		this.expectedStart = expectedStart; 
		this.expectedEnd = expectedEnd; 
		this.expectedAmountOfHours = expectedAmountOfHours; 
		this.associatedProjectNumber = associatedProjectNumber; 	
	}
	
	public Activity(String name, int startWeek, int endWeek) {
		this.name = name;
		this.expectedStart = startWeek;
		this.expectedEnd = endWeek;
		this.expectedAmountOfHours = 0; 
		this.associatedProjectNumber = 0; 
	}
	
	public String getName() {
		return name; 
	}
	
	public int getExpectedStart() {
		return expectedStart; 
	}
	
	public int getExpectedEnd() {
		return expectedEnd; 
	}
	
	public int getExpectedAmountOfHours() {
		return expectedAmountOfHours; 
	}
	
	public int getAssociatedProjectNumber() {
		return associatedProjectNumber; 
	}
	
	public List<Employee> getAssignedEmployees() {
		return employees;
	}
	
	// First argument: The actor, must be the project leader
	// Second argument: The employee who's being added
	public void assignEmployee(Employee employee) {
		employees.add(employee);
	}

	public void registerTime(TimeRegistration timeRegistration) {
		timeRegistrations.add(timeRegistration);
	}

	public List<TimeRegistration> getTimeRegistrations() {
		return timeRegistrations;
	}

}
