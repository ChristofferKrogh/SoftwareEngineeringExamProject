package dtu.planning.app;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.GregorianCalendar;
//import java.util.Collections;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

public class PlanningApp {
	// Storage for the projects
	private List<Project> projects = new ArrayList<>();
	
	// Regular activities. These include all the regular activities
	// fx vacation and sickness
	private List<Activity> regularActivities = new ArrayList<>();
	
	// Storage for the list of employees that work for the company
	private List<Employee> employees = new ArrayList<>();
	
	// Counter to ensure unique ID's for each project
	public int projectCount = 0;
	
	// Counter to ensure unique ID's for each employee
	public int employeeCount = 0;
	
	public void createProject(Project project) {
		projects.add(project);
		projectCount++;
	}
	
	// TODO: der mangler test for nedenstående metode
	public Project createProject(String name, boolean isProjectInternal) {
		Project newProject = new Project(name, isProjectInternal, projectCount);
		projects.add(newProject);
		projectCount++;
		return newProject;
	}
	
	// TODO: there are no tests for the method below
	public void setNameOfProject(String name, int projectNumber) throws OperationNotAllowedException {
		searchForProject(projectNumber).setName(name);
	}
	
	// TODO: there are no tests for the method below
	public void setProjectInternal(boolean isProjectInternal, int projectNumber) throws OperationNotAllowedException {
		searchForProject(projectNumber).setInternal(isProjectInternal);
	}
	
	public void editStartDateOfProject(GregorianCalendar startDate, int projectNumber) throws OperationNotAllowedException {
		searchForProject(projectNumber).setStartDate(startDate);
	}
	
	public void editEndDateOfProject(GregorianCalendar endDate, int projectNumber) throws OperationNotAllowedException {
		searchForProject(projectNumber).setEndDate(endDate);
	}
	
	public Project searchForProject(int projectNumber) throws OperationNotAllowedException {
		for (Project p : projects) {
			if (p.getProjectNumber() == projectNumber) {
				return p;
			}
		}
		throw new OperationNotAllowedException("The project does not exist");
	}
	
	// TODO: der mangler test for nedenstående metode
	public List<Project> searchForProjectsByName(String name) {
		List<Project> searchResults = new ArrayList<>();
		for (Project p : projects) {
			if (p.match(name)) {
				searchResults.add(p);
			}
		}
		return searchResults;
	}
	
	public Employee searchForEmployee(String initials) throws OperationNotAllowedException {
		for (Employee e : employees) {
			if (e.getInitials() == initials) {
				return e;
			}
		}
		throw new OperationNotAllowedException("The employee does not exist");
	}
	
	// TODO: there are not tests for this method
	public Employee searchForEmployee(int employeeId) throws OperationNotAllowedException {
		for (Employee e : employees) {
			if (e.getEmployeeId() == employeeId) {
				return e;
			}
		}
		throw new OperationNotAllowedException("The employee does not exist");
	}
	
	// TODO: there are no tests for this method
		public List<Employee> searchForEmployeesByName(String name) {
			List<Employee> searchResults = new ArrayList<>();
			for (Employee e : employees) {
				if (e.match(name)) {
					searchResults.add(e);
				}
			}
			return searchResults;
		}
	
	public List<Integer> getProjectNumbers() {
		List<Integer> projectNumbers = new ArrayList<>();
		for (Project p : projects) {
			projectNumbers.add(p.getProjectNumber());
		}
		return projectNumbers;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void addRegularActivity(Activity activity) {
		regularActivities.add(activity);
	}
	
	public List<Activity> getRegularActivities() {
		return regularActivities;
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	//TODO: there are not tests for this method
	public Employee createEmployee(String name) {
		Employee newEmployee = new Employee(name, employeeCount);
		employees.add(newEmployee);
		employeeCount++;
		return newEmployee;
	}
	
	public List<String> getEmployeeInitials() {
		List<String> employeeInitials = new ArrayList<>();
		for (Employee e : employees) {
			employeeInitials.add(e.getInitials());
		}
		return employeeInitials;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void assignEmployee(int projectNumber, String activityName, Employee projectLeader, Employee employee) throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		// Check that the employee given exists, if not throw exception
		this.checkEmployeeExist(employee);
		
		// Assign employee to the activity
		project.assignEmployee(activityName, projectLeader, employee);		
	}
	
	private void checkEmployeeExist(Employee employee) throws OperationNotAllowedException {
		Optional<Employee> r = employees
			      .stream()
			      .filter(b -> b.getInitials().equals(employee.getInitials()))
			      .findFirst();
		if (!r.isPresent()) {
		throw new OperationNotAllowedException("The employee does not exist");
		}	
	}

	public Activity addActivity(int projectNumber, String activityName, GregorianCalendar  expectedStart, GregorianCalendar  expectedEnd, int expectedAmountOfHours) throws OperationNotAllowedException{
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		// Create new activity
		Activity newActivity = new Activity(activityName,projectNumber);
		/*
		if (expectedEnd == null && expectedStart == null){
			newActivity = new Activity(activityName,project.getProjectNumber());
		} else {
			newActivity = new Activity(activityName, expectedStart, expectedEnd, expectedAmountOfHours, project.getProjectNumber());
		}*/

		// Add activity to that project
		project.addActivity(newActivity);
		return newActivity;
	}

	// TODO no test for the method below
	public void editStartDateOfActivity(GregorianCalendar startDate, int projectNumber, String name) throws ActivityNotFoundException{
		searchForActivity(projectNumber, name).setExpectedStart(startDate);
	}

	// TODO no test for the method below
	public void editEndDateOfActivity(GregorianCalendar endDate, int projectNumber, String name) throws ActivityNotFoundException {
		searchForActivity(projectNumber, name).setExpectedEnd(endDate);
	}

	// TODO no test for the method below
	public void editExpectedAmountOfHoursForActivity(int hours, int projectNumber, String name)throws ActivityNotFoundException {
		searchForActivity(projectNumber, name).setExpectedAmountOfHours(hours);
	}

	// TODO no test for the method below
	public Activity searchForActivity(int projectNumber, String name) throws ActivityNotFoundException {
		for (Project p : projects) {
			if (p.getProjectNumber() == projectNumber) {
				Activity a = p.getActivityByName(name);
				return a;
			}
		}
		throw new  ActivityNotFoundException("The activity does not exist");
	}
	
	public void setProjectLeader(int projectNumber, String initials) throws OperationNotAllowedException {
		// Find employee from initials
		Employee employee = this.searchForEmployee(initials);
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		project.setProjectLeader(employee);
	}
	
	//TODO: there are no tests for this method
	public void setProjectLeader(int projectNumber, int employeeId) throws OperationNotAllowedException {
		// Find employee from initials
		Employee employee = this.searchForEmployee(employeeId);
		// Find project from id
		Project project = this.searchForProject(projectNumber);
			
		project.setProjectLeader(employee);
	}

	public void registerTime(int projectNumber, String activityName, TimeRegistration timeRegistration) throws OperationNotAllowedException, ActivityNotFoundException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		// Find activity in project
		Activity activity = project.getActivityByName(activityName);
		
		// Check that the employee given exists, if not throw exception
		this.checkEmployeeExist(timeRegistration.getEmployee());
		
		// Add time registration to that activity
		activity.registerTime(timeRegistration);
	}
}
