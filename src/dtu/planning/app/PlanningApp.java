package dtu.planning.app;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public class PlanningApp {
	// Storage for the projects
	private List<Project> projects = new ArrayList<>();

	// Regular activities. These include all the regular activities
	// fx vacation and sickness
	private List<Activity> regularActivities = new ArrayList<>();

	// Storage for the list of employees that work for the company
	private List<Employee> employees = new ArrayList<>();

	private List<TimeRegistration> timeRegistration = new ArrayList<>();

	// Counter to ensure unique ID's for each project
	public int projectCount = 0;

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

	public void setNameOfProject(String name, int projectNumber) throws OperationNotAllowedException {
		searchForProject(projectNumber).setName(name);
	}

	public void setProjectInternal(boolean isProjectInternal, int projectNumber) throws OperationNotAllowedException {
		searchForProject(projectNumber).setInternal(isProjectInternal);
	}

	public void editStartDateOfProject(GregorianCalendar startDate, int projectNumber) throws OperationNotAllowedException {
		searchForProject(projectNumber).setStartDate(startDate);
	}

	public void editEndDateOfProject(GregorianCalendar endDate, int projectNumber) throws OperationNotAllowedException {
		searchForProject(projectNumber).setEndDate(endDate);
	}

	public void editStartWeekOfRegular(GregorianCalendar startWeek, String regularActivityName) throws OperationNotAllowedException {
		searchForRegActivity(regularActivityName).setStartWeek(startWeek);
	}

	public void editEndWeekOfRegular(GregorianCalendar endWeek, String regularActivityName) throws OperationNotAllowedException {
		searchForRegActivity(regularActivityName).setEndWeek(endWeek);
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

	public Activity searchForRegActivity(String name) throws OperationNotAllowedException {
		for (Activity a : regularActivities) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		throw new OperationNotAllowedException("The regular activity does not exist");
	}

	// TODO: there are no tests for the method below
	public List<Activity> searchForRegActivitiesByName(String searchText) {
		List<Activity> searchResults = new ArrayList<>();
		for (Activity a : regularActivities) {
			if (a.match(searchText)) {
				searchResults.add(a);
			}
		}
		return searchResults;
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
//			if (!p.isPresent()) {
//				throw new OperationNotAllowedException("The project does not exist");
//			}
		}
		return projectNumbers;
	}

	public List<Project> getProjects(){
		return projects;
	}
	
	public Activity addRegularActivity(Activity activity, String initials) throws OperationNotAllowedException {
		Employee employee = searchForEmployee(initials);
		activity.assignEmployee(employee);
		activity.setName(activity.getName() + " - " + employee.getName());
		regularActivities.add(activity);
		return activity;
	}

	public List<Activity> getRegularActivities() {
		return regularActivities;
	}

	public void addEmployee(Employee employee) throws OperationNotAllowedException {
		for (Employee e : employees) {
			if (e.getInitials().equals(employee.getInitials())) {
				throw new OperationNotAllowedException("An employee with the same initials is already in the system");
			}
		}
		employees.add(employee);
	}

	public List<String> getEmployeeInitials() {
		List<String> employeeInitials = new ArrayList<>();
		for (Employee e : employees) {
			employeeInitials.add(e.getInitials());
		}
		return employeeInitials;
	}

	// TODO: there are no tests for the method below
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void assignEmployee(int projectNumber, String activityName, String projectLeaderInitials, String employeeInitials) throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
		Employee employee = searchForEmployee(employeeInitials);
		Employee projectLeader = searchForEmployee(projectLeaderInitials);
		
		// Find project from id
		Project project = this.searchForProject(projectNumber);

		// Check that the employee given exists, if not throw exception
		this.checkEmployeeExist(employee);

		// Assign employee to the activity
		project.assignEmployee(activityName, projectLeader, employee);
		
	};

	private void checkEmployeeExist(Employee employee) throws OperationNotAllowedException {
		Optional<Employee> r = employees
			      .stream()
			      .filter(b -> b.getInitials().equals(employee.getInitials()))
			      .findFirst();
		if (!r.isPresent()) {
			throw new OperationNotAllowedException("The employee does not exist");
		}
	}

	public Activity addActivity(int projectNumber, String activityName, GregorianCalendar startWeek, GregorianCalendar endWeek, int expectedAmountOfHours, String actorInitials) throws OperationNotAllowedException, NotProjectLeaderException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		
		// Create new activity
		Activity activity = new Activity(activityName, startWeek, endWeek, expectedAmountOfHours);

		
		// Add activity to that project
		project.addActivity(activity, actorInitials);
		return activity;
	}
    
    
	// TODO no test for the method below
	public void editStartDateOfActivity(GregorianCalendar startDate, int projectNumber, String name) throws ActivityNotFoundException, OperationNotAllowedException{
		searchForActivity(projectNumber, name).setStartWeek(startDate);
	}

	public void editEndDateOfActivity(GregorianCalendar endDate, int projectNumber, String name) throws ActivityNotFoundException, OperationNotAllowedException {
		searchForActivity(projectNumber, name).setEndWeek(endDate);
	}

	// TODO no test for the method below
	public void editExpectedAmountOfHoursForActivity(int hours, int projectNumber, String name)throws ActivityNotFoundException, OperationNotAllowedException {
		searchForActivity(projectNumber, name).setExpectedAmountOfHours(hours);
	}

	public Activity searchForActivity(int projectNumber, String name) throws ActivityNotFoundException, OperationNotAllowedException {
		Project project = searchForProject(projectNumber);
		return project.getActivityByName(name);
	}
    

	public void setProjectLeader(int projectNumber, String initials) throws OperationNotAllowedException {
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

		// Check that the employee given exists, if not throw exception
		this.checkEmployeeExist(timeRegistration.getEmployee());

		// Add time registration to that activity
		activity.registerTime(timeRegistration);
	}

	public Report generateReport(int projectNumber, Employee projectLeader) throws NotProjectLeaderException, OperationNotAllowedException {
		// Find project from id
		Project project = this.searchForProject(projectNumber);
		return project.generateReport(projectLeader);
	}
	
	public List<TimeRegistration> getAllTimeRegistrationsForEmployeeOnDate(Employee employee, GregorianCalendar date) throws TimeRegistrationNotFoundException {
		for(Project p : projects) {
			List<Activity> activities = p.getActivities();
			for(Activity a : activities) {
					timeRegistration.add(a.getTimeRegistrationForEmployeeOnDate(employee, date));
			}
		}
		return timeRegistration;
	}
	
	public int getDailyUsedTime(String initials, GregorianCalendar date) throws TimeRegistrationNotFoundException, OperationNotAllowedException {
		int dailyUsedTime = 0;
		for(TimeRegistration t : getAllTimeRegistrationsForEmployeeOnDate(searchForEmployee(initials), date)) {
			dailyUsedTime += t.getAmountOfTime();
		}
		return dailyUsedTime;
	};
	
	public SimpleEntry<List<Activity>, List<Integer>> getAllRelevantActivitiesForEmployee(Employee employee) {
		List<Activity> relevantActivities = new ArrayList<>();
		List<Integer> projectNumbers = new ArrayList<>();
		for (Project p : projects) {
			for (Activity a : p.getActivities()) {
				if (a.getAssignedEmployees().contains(employee)) {
					relevantActivities.add(a);
					projectNumbers.add(p.getProjectNumber());
				}
			}
		}
		AbstractMap.SimpleEntry<List<Activity>, List<Integer>> activitiesWithProjectNumbers = new AbstractMap.SimpleEntry<>(relevantActivities, projectNumbers);
		return activitiesWithProjectNumbers;
	}
}
