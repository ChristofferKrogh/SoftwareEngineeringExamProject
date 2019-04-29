package dtu.planning.app;

import java.util.GregorianCalendar;

public class TimeRegistration {
	public enum timeUnits {
		HOURS,
		DAYS
	}
	
	private GregorianCalendar date;
	private int amountOfTime;
	private timeUnits timeUnit;
	private Employee employee;
	
	// We have chosen to make the key the day for an employee for an activity 
	public TimeRegistration (Employee employee, GregorianCalendar date, int amountOfTime, timeUnits timeUnit) {
		this.employee = employee;
		this.date = date;
		this.amountOfTime = amountOfTime;
		this.timeUnit = timeUnit;
	}
	
	public Employee getEmployee () {
		return employee;
	}
	
	public GregorianCalendar getDate() {
		return date;
	}
	
	public int getAmountOfTime() {
		return amountOfTime;
	}
	
	public timeUnits getTimeUnit() {
		return timeUnit;
	}
	
	public void correctTime(int amountOfTime) {
		this.amountOfTime = amountOfTime;
	}
 	
}
