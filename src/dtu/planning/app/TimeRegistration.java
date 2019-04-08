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
	
	public TimeRegistration (Employee employee, GregorianCalendar date, int amountOfTime, timeUnits timeUnit) {
		this.employee = employee;
		this.date = date;
		this.amountOfTime = amountOfTime;
		this.timeUnit = timeUnit;
	}
	
	
}
