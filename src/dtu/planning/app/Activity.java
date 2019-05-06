package dtu.planning.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class Activity {

	private String name;
	private GregorianCalendar startWeek;
	private GregorianCalendar endWeek;
	private float expectedAmountOfHours;
	private List<Employee> employees = new ArrayList<>();
	private List<TimeRegistration> timeRegistrations = new ArrayList<>();

	public Activity(String name, GregorianCalendar startWeek, GregorianCalendar endWeek, float expectedAmountOfHours) {
		this.name = name;
		this.startWeek = startWeek;
		this.endWeek = endWeek;
		this.expectedAmountOfHours = expectedAmountOfHours;
	}

	public Activity(String name, GregorianCalendar startWeek, GregorianCalendar endWeek) {
		this.name = name;
		this.startWeek = startWeek;
		this.endWeek = endWeek;
		this.expectedAmountOfHours = 0;
	}

	public String getName() {
		return name;
	}

	public float getExpectedAmountOfHours() {
		return expectedAmountOfHours;
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
	
	// TODO: there are no tests for the method below
	public String getStartWeekString() {
		return "week " + startWeek.get(GregorianCalendar.WEEK_OF_YEAR)
				+ " of " + startWeek.get(GregorianCalendar.YEAR);
	}

	// TODO: there are no tests for the method below
	public String getEndWeekString() {
		return "week " + endWeek.get(GregorianCalendar.WEEK_OF_YEAR)
				+ " of " + endWeek.get(GregorianCalendar.YEAR);
	}

	public void setStartWeek(GregorianCalendar newStartWeek) throws OperationNotAllowedException {
		if (newStartWeek.after(endWeek)) {
			throw new OperationNotAllowedException("The start week must be before the end week");
		}
		this.startWeek = newStartWeek;
	}

	public void setEndWeek(GregorianCalendar newEndWeek) throws OperationNotAllowedException {
		if (newEndWeek.before(startWeek)) {
			throw new OperationNotAllowedException("The end week must be after the start week");
		}		
		this.endWeek = newEndWeek;
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

	public void setExpectedAmountOfHours(float hours) throws OperationNotAllowedException {
		if(hours < 0) {
			throw new OperationNotAllowedException("The expected amount of hours must be at least 0");
		}
		this.expectedAmountOfHours = hours; 	
	}

	public void removeEmployee(Employee employee) {
		int i = -1;
		for (Employee e : employees) {
			if (e.equals(employee)) {
				i = employees.indexOf(e);
			}
		}
		if (i != -1) 
		employees.remove(i);	
	}
	
}
