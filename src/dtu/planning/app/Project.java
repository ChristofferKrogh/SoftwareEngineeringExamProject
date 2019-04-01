package dtu.planning.app;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

public class Project {
	private String name;
	private boolean isProjectInternal;
	private Employee projectLeader;
	private List<Activity> activities;
	private int number;
	private GregorianCalendar startDate = new GregorianCalendar(0000, 1, 1);
	private GregorianCalendar endDate = new GregorianCalendar(3000, 1, 1);

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
	
	public int getProjectNumber() {
		return number;
	}
	
	public void setStartDate(GregorianCalendar newStartDate) throws OperationNotAllowedException {
		if (newStartDate.after(endDate)) {
			throw new OperationNotAllowedException("The start date must be before the end date");
		}
		startDate = newStartDate;
	}
	
	public void setEndDate(GregorianCalendar newEndDate) throws OperationNotAllowedException {
		if (newEndDate.before(startDate)) {
			throw new OperationNotAllowedException("The end date must be after the start date");
		}
		endDate = newEndDate;
	}
	
	public GregorianCalendar getStartDate() {
		return startDate;
	}
	
	public GregorianCalendar getEndDate() {
		return endDate;
	}
	
//	private int generateNumber() {
//		// todo: include dato
//	}

}
