package dtu.planning.app;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dtu.planning.app.NotProjectLeaderException;

public class Project {
	private String name;
	private boolean isProjectInternal;
	private Employee projectLeader;
	private List<Activity> activities = new ArrayList<>();
	private int number;
	private GregorianCalendar startDate = new GregorianCalendar(0000, 1, 1);
	private GregorianCalendar endDate = new GregorianCalendar(3000, 1, 1);

	public Project(String name, boolean isProjectInternal, int projectCount) {
		this.name = name;
		this.isProjectInternal = isProjectInternal;
		this.number = generateNumber(projectCount);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isProjectInternal() {
		return this.isProjectInternal;
	}
	
	public void setProjectLeader(Employee employee) {
		this.projectLeader = employee;
	}
	
	public Employee getProjectLeader() {
		return projectLeader;
	}
	
	public int getProjectNumber() {
		return number;
	}

	public void addActivity(String activityName, int expectedStart, int expectedEnd, int expectedAmountOfHours) {
		// Does not check that the projectID of the activity does match the project of which it is being assigned.
		activities.add(new Activity(activityName, expectedStart, expectedEnd, expectedAmountOfHours, number));
	}
	
	public void assignEmployee(String activityName, Employee projectLeader, Employee employee ) throws NotProjectLeaderException, ActivityNotFoundException {
		// Check that projectleader is projectleader for this project. If not stop!
		if (this.projectLeader != projectLeader) {
			throw new NotProjectLeaderException("You are not the project leader for this project");
		}
		
		// Find and get the activity by name.
		Activity activity = getActivityByName(activityName);
		
		// assign employee to that activity
		activity.assignEmployee(projectLeader,employee);
	}
	
	public List<Employee> getEmployeesAssignedToActivity(String activityName) throws ActivityNotFoundException {
		// Find and get the activity by name.
		Activity activity = getActivityByName(activityName);
		
		// Return list of assigned employees
		return activity.getAssignedEmployees();
	}
	
	public Activity getActivityByName(String activityName) throws ActivityNotFoundException {
		// Find activity by name
		Optional<Activity> r = activities
						      .stream()
						      .filter(b -> b.getName().equals(activityName))
						      .findFirst();
		if (!r.isPresent()) {
			throw new ActivityNotFoundException("The activity does not exist");
		}
	    return (Activity) r.get();
	}
	
//	public boolean hasProjectLeader() {
//		return false;
//	}
	
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
	
	private int generateNumber(int projectCount) {
		// TODO: Describe the assumption in the report
		// Assumption: There will never be created more than 10000 projects in the span
		// 			   of 1 year.
		int projectNumber = projectCount % 10000;
		
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		year %= 100;
		projectNumber = year * 10000 + projectNumber;
		return projectNumber;
	}

}
