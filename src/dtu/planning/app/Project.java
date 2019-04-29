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
	private GregorianCalendar startDate = new GregorianCalendar(1000, 1, 1);
	private GregorianCalendar endDate = new GregorianCalendar(3000, 1, 1);

	public Project(String name, boolean isProjectInternal, int projectCount) {
		this.name = name;
		this.isProjectInternal = isProjectInternal;
		this.number = generateNumber(projectCount);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isProjectInternal() {
		return this.isProjectInternal;
	}
	
	public void setInternal(boolean isProjectInternal) {
		this.isProjectInternal = isProjectInternal;
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

	public void addActivity(Activity activity) {
		// Does not check that the projectID of the activity does match the project of which it is being assigned.
		activities.add(activity);
	}
	
	// TODO: there are no tests for the method below
	public List<Activity> getAktivities() {
		return activities;
	}
	
	public void assignEmployee(String activityName, Employee projectLeader, Employee employee ) throws NotProjectLeaderException, ActivityNotFoundException {
		// Check that projectleader is projectleader for this project. If not stop!
		if (this.projectLeader != projectLeader) {
			throw new NotProjectLeaderException("You are not the project leader for this project");
		}
		
		// Find and get the activity by name.
		Activity activity = getActivityByName(activityName);
		
		// assign employee to that activity
		activity.assignEmployee(employee);
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
	
	// TODO: there are no tests for the method below
	public boolean hasProjectLeader() {
		if (projectLeader == null) {
			return false;
		}
		return true;
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
	
	// TODO: there are no tests for the method below
	public String getStartDateString() {
		int year = startDate.get(Calendar.YEAR);
		int month = startDate.get(Calendar.MONTH);
		int date = startDate.get(Calendar.DATE);
		return date + "/" + month + "/" + year;
	}
	
	// TODO: there are no tests for the method below
	public String getEndDateString() {
		int year = endDate.get(Calendar.YEAR);
		int month = endDate.get(Calendar.MONTH);
		int date = endDate.get(Calendar.DATE);
		return date + "/" + month + "/" + year;
	}
	
	private int generateNumber(int projectCount) {
		// Assumption: There will never be created more than 10000 
		// 			   projects in the span of 1 year.
		int projectNumber = projectCount % 10000;
		
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		year %= 100;
		projectNumber = year * 10000 + projectNumber;
		return projectNumber;
	}
	
	// TODO: there are no tests for the method below
	public boolean match(String searchText) {
		searchText = searchText.toLowerCase();
		String name = this.name.toLowerCase();
		if (searchText.contains("external")) {
			return !isProjectInternal;
		} else if (searchText.contains("internal")) {
			return isProjectInternal;
		} else if (projectLeader != null) {
			 return name.contains(searchText) ||
						Integer.toString(number).contains(searchText) ||
						projectLeader.match(searchText); // It might be overkill to include the project leader in the search
		} else {
			return name.contains(searchText) ||
					Integer.toString(number).contains(searchText);	
		}
	}
	
	// TODO: there are no tests for the method below
	public String toString() {
		return this.name + " - " + this.number;
	}

	public void generateReport(Employee projectLeader) throws NotProjectLeaderException {
		// TODO Auto-generated method stub
		// Check that projectleader is projectleader for this project. If not stop!
		if (this.projectLeader != projectLeader) {
			throw new NotProjectLeaderException("You are not the project leader for this project");
		}
	}

}
