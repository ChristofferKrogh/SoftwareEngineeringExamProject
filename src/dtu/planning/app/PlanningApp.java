package dtu.planning.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlanningApp {
	private List<Project> projects = new ArrayList<>();
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
}
