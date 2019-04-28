package dtu.planning.app;

import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;

public class Activity {
	
	private String name; 
	private int expectedStart; 
	private int expectedEnd; 
	private GregorianCalendar startWeek;
	private GregorianCalendar endWeek;
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
	
	public Activity(String name, GregorianCalendar startWeek, GregorianCalendar endWeek) {
		this.name = name;
		this.startWeek = startWeek;
		this.endWeek = endWeek;
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
	
	public GregorianCalendar getStartWeek() {
		return startWeek;
	}
	
	public GregorianCalendar getEndWeek() {
		return endWeek;
	}
	
	public String getStartWeekString() {
		return "week " + startWeek.get(GregorianCalendar.WEEK_OF_YEAR)
				+ " of " + startWeek.get(GregorianCalendar.YEAR);
	}
	
	public String getEndWeekString() {
		return "week " + endWeek.get(GregorianCalendar.WEEK_OF_YEAR)
				+ " of " + endWeek.get(GregorianCalendar.YEAR);
	}
	
	public void setStartWeek(GregorianCalendar startWeek) {
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(GregorianCalendar endWeek) {
		this.endWeek = endWeek;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void assignEmployee(Employee employee) {
		employees.add(employee);
	}

	public void registerTime(TimeRegistration timeRegistration) {
		timeRegistrations.add(timeRegistration);
	}

	public List<TimeRegistration> getTimeRegistrations() {
		return timeRegistrations;
	}

	
	// TODO: there are no tests for the method below
	public boolean match(String searchText) {
		for (Employee e : employees) {
			if (e.match(searchText)) {
				return true;
			}
		}
		return name.toLowerCase().contains(searchText.toLowerCase());
	}
	
	public String toString() {
		return name;
	}

}
