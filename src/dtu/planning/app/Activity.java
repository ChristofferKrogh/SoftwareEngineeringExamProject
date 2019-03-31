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
	
	public Activity(String name, int expectedStart, int expectedEnd, int expectedAmountOfHours, int associatedProjectNumber) {
		this.name = name; 
		this.expectedStart = expectedStart; 
		this.expectedEnd = expectedEnd; 
		this.expectedAmountOfHours = expectedAmountOfHours; 
		this.associatedProjectNumber = associatedProjectNumber; 	
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
	
	public void assignEmployee(Employee e) {
		employees.add(e);
	}

}
