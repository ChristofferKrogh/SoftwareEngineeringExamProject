package dtu.planning.app;

import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.Collections;
import java.util.List;
// import java.util.stream.Collectors;

public class PlanningApp {
	// Storage for the projects
	private List<Project> projects = new ArrayList<>();
	
	// Storage for the list of employees that work for the company
	private List<Employee> employees = new ArrayList<>();
	
	// Counter to ensure unique ID's for each project
	public int projectCount = 0;
	
	public void createProject(String name, boolean isProjectInternal) {
		Project newProject = new Project(name, isProjectInternal, projectCount);
		projects.add(newProject);
		projectCount++;
	}
	
	public void createProject(Project project) {
		projects.add(project);
		projectCount++;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	
}
