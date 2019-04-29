package dtu.planning.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.GregorianCalendar;

public class Activity {
	
	private GregorianCalendar startWeek;
	private GregorianCalendar endWeek;
	private String name;

	private GregorianCalendar expectedStart;
	private GregorianCalendar expectedEnd;
	private int expectedAmountOfHours;
	private int associatedProjectNumber; 
	private List<Employee> employees = new ArrayList<>();
	private List<TimeRegistration> timeRegistrations = new ArrayList<>();
	
	public Activity(String name, GregorianCalendar expectedStart, GregorianCalendar  expectedEnd, int expectedAmountOfHours, int associatedProjectNumber) {
		this.name = name; 
		this.expectedStart = expectedStart; 
		this.expectedEnd = expectedEnd; 
		this.expectedAmountOfHours = expectedAmountOfHours; 
		this.associatedProjectNumber = associatedProjectNumber; 	
	}
	
	public Activity(String name, GregorianCalendar startWeek, GregorianCalendar endWeek) {
		this.name = name;
		this.expectedStart = startWeek;
		this.expectedEnd = endWeek;
		this.expectedAmountOfHours = 0; 
		this.associatedProjectNumber = 0; 
	}

	public Activity(String name, int associatedProjectNumber) {
		this.name = name;
		this.associatedProjectNumber = associatedProjectNumber;
		this.expectedStart = null;
		this.expectedEnd = null;
		this.expectedAmountOfHours = 0;
	}
	
//	public Activity(String name, GregorianCalendar startWeek, GregorianCalendar endWeek) {
//		this.name = name;
//		this.startWeek = startWeek;
//		this.endWeek = endWeek;
//		this.expectedAmountOfHours = 0; 
//		this.associatedProjectNumber = 0; 
//	}
	
	public String getName() {
		return name; 
	}

	public void setExpectedStart(GregorianCalendar newStartDate) throws ActivityNotFoundException {
		if (newStartDate.after(expectedEnd)) {
			throw new ActivityNotFoundException("The start date must be before the end date");
		}
		expectedStart = newStartDate;
	}

	public void setExpectedEnd(GregorianCalendar newEndDate) throws ActivityNotFoundException{
		if (newEndDate.before(expectedStart)) {
			throw new ActivityNotFoundException("The end date must be after the start date");
		}
		expectedEnd = newEndDate;
	}

	public void setExpectedAmountOfHours(int newHours)throws ActivityNotFoundException{
		if (newHours <0) {
			throw new ActivityNotFoundException("The expected amount of hours can not be a negative number");
		}
		expectedAmountOfHours = newHours;
	}
	
	public GregorianCalendar getExpectedStart() {
		return expectedStart; 
	}
	
	public GregorianCalendar getExpectedEnd() {
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
	
	public TimeRegistration getTimeRegistrationForEmployeeOnDate (Employee employee, GregorianCalendar date) throws TimeRegistrationNotFoundException {
		for (TimeRegistration t : this.timeRegistrations) {
			if (t.getEmployee()==employee && t.getDate()==date) {
				return t;
			}
		}
		throw new TimeRegistrationNotFoundException("The employee has not registered time for this activity");
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
