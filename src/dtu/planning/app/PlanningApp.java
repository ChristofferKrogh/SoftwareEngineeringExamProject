package dtu.planning.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

public class PlanningApp {
	// Storage for the projects
	private List<Project> projects = new ArrayList<>();
	
	// Regular project. This project stores all the regular activities
	// fx vacation and sickness
	private Project regularProject = new Project("Regular Activities", true, 0);
	
	// Storage for the list of employees that work for the company
	private List<Employee> employees = new ArrayList<>();
	
	// Counter to ensure unique ID's for each project
	public int projectCount = 1;
	
	public PlanningApp() {
		initializeRegularProject();
	}
	
	private void initializeRegularProject() {
		try {
			regularProject.setStartDate(new GregorianCalendar(2000, 1, 1));
			regularProject.setEndDate(new GregorianCalendar(3000, 1, 1));
		} catch (Exception e) {
			System.out.println("The initalization process failed");		
		}
//		regularProject.addActivity("Sickness", 1, 2, 99999);
//		regularProject.addActivity("Vacation", 1, 2, 99999);
//		regularProject.addActivity("Courses", 1, 2, 99999);
	}
	
	public void createProject(Project project) {
		projects.add(project);
		projectCount++;
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
	
	public Employee searchForEmployee(String initials) throws OperationNotAllowedException {
		for (Employee e : employees) {
			if (e.getInitials() == initials) {
				return e;
			}
		}
		throw new OperationNotAllowedException("The employee does not exist");
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

	public void addEmployee(Employee employee) {
		employees.add(employee);
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
		Optional<Employee> r = employees
						      .stream()
						      .filter(b -> b.getInitials().equals(employee.getInitials()))
						      .findFirst();
		if (!r.isPresent()) {
			throw new OperationNotAllowedException("The employee does not exist");
		}
		
		// Assign employee to the activity
		project.assignEmployee(activityName, projectLeader, employee);		
	}
	
	public void addActivity(int projectNumber, String activityName, int expectedStart, int expectedEnd, int expectedAmountOfHours) throws OperationNotAllowedException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		// Create new activity
		Activity activity = new Activity(activityName, expectedStart, expectedEnd, expectedAmountOfHours, project.getProjectNumber());
		
		// Add activity to that project
		project.addActivity(activity);
		
	}
	
	public void setProjectLeader(int projectNumber, String initials) throws OperationNotAllowedException { // String initials
		// Find employee from initials
		Employee employee = this.searchForEmployee(initials);
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		project.setProjectLeader(employee);
	}

	public void registerTime(int projectNumber, String activityName, TimeRegistration timeRegistration) throws OperationNotAllowedException, ActivityNotFoundException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		// Find activity in project
		Activity activity = project.getActivityByName(activityName);
		
		// Add time registration to that activity
		activity.registerTime(timeRegistration);
	}
}
