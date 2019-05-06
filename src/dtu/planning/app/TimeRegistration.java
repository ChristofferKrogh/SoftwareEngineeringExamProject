package dtu.planning.app;

import java.util.GregorianCalendar;

public class TimeRegistration {
	private GregorianCalendar date;
	// TODO: change from int to float
	private float amountOfTime;
	private Employee employee;
	
	// We have chosen to make the key the day for an employee for an activity 
	public TimeRegistration (Employee employee, GregorianCalendar date, float amountOfTime) throws OperationNotAllowedException {
		if (amountOfTime < 0) {
			throw new OperationNotAllowedException("You cannot report negative hours");
		}
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
	
	public float getAmountOfTime() {
		return amountOfTime;
	}
	
	public void correctTime(int amountOfTime) {
		this.amountOfTime = amountOfTime;
	}
 	
	public String toString() {
		int year = date.get(GregorianCalendar.YEAR);
		int month = date.get(GregorianCalendar.MONTH) + 1;
		int day = date.get(GregorianCalendar.DATE);
		return amountOfTime + " hours on " + day + "/" + month + "/" + year + " for " + employee.getName();
	}
	
}
