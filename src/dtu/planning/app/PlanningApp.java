package dtu.planning.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
// import java.util.stream.Collectors;

public class PlanningApp {
	// Storage for the projects
	private List<Project> projects = new ArrayList<>();
	
	// Storage for the list of employees that work for the company
	private List<Employee> employees = new ArrayList<>();
	
	// Counter to ensure unique ID's for each project
	public int projectCount = 0;
	
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
	
	public List<Project> getProjects() {
		return projects;
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void assignEmployee(int projectNumber, String activityName, Employee projectLeader, Employee employee) throws OperationNotAllowedException, NotProjectLeaderException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		// Check that the employee given exists, if not throw exception
		Optional<Employee> r = employees
						      .stream()
						      .filter(b -> b.getInitials().equals(employee.getInitials()))
						      .findFirst();
		if (r.isEmpty()) {
			throw new OperationNotAllowedException("The employee does not exist");
		}
		
		// Assign employee to the activity
		project.assignEmployee(activityName, projectLeader, employee);		
	}
	
	public void addActivity(int projectNumber, String activityName, int expectedStart, int expectedEnd, int expectedAmountOfHours) throws OperationNotAllowedException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		// Add activity to that project
		project.addActivity(activityName, expectedStart, expectedEnd, expectedAmountOfHours, projectNumber);
		
	}
	
	public void setProjectLeader(int projectNumber, Employee employee) throws OperationNotAllowedException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		project.setProjectLeader(employee);
	}
}
