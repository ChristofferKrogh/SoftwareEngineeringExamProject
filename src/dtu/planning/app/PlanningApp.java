package dtu.planning.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlanningApp {
	private List<Project> projects = new ArrayList<>();
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
}
