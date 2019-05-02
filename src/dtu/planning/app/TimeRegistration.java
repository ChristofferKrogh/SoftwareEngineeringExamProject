package dtu.planning.app;

import java.util.GregorianCalendar;

public class TimeRegistration {
	private GregorianCalendar date;
	private int amountOfTime;
	private Employee employee;
	
	// We have chosen to make the key the day for an employee for an activity 
	public TimeRegistration (Employee employee, GregorianCalendar date, int amountOfTime) {
		this.employee = employee;
		this.date = date;
		this.amountOfTime = amountOfTime;
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
	
	public void correctTime(int amountOfTime) {
		this.amountOfTime = amountOfTime;
	}
 	
}
