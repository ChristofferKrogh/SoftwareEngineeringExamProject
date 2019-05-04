package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;

public class CreateActivitySteps {
		
	private PlanningApp planningApp = new PlanningApp();
	private ErrorMessageHolder errorMessageHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder; 
	private Project project;
	private Activity activity; 
	private int projectNumber;
	private GregorianCalendar date;
	private Employee employee; 
	private Employee projectLeader; 
		
	public CreateActivitySteps(ErrorMessageHolder errorMessageHolder, PlanningApp planningApp, ProjectHolder projectHolder, EmployeeHolder employeeHolder) {
		this.planningApp = planningApp; 
		this.errorMessageHolder = errorMessageHolder; 
		this.projectHolder = projectHolder; 
		this.employeeHolder = employeeHolder; 
	}
		
		
	@Given("project with id {int} exists")
	public void projectWithIdExists(int id) {
		project = new Project("Random name", false, id);
		projectHolder.setProject(project);
	}

	@Given("project leader has initials {string}")
	public void projectLeaderHasInitials(String initials){
		Employee employee = new Employee("Bob Small",initials);
		employeeHolder.setEmployee(employee);
		projectHolder.getProject().setProjectLeader(employee);	
	}

	@When("the project leader {string} creates an activity {string}")
	public void theProjectLeaderCreatesAnActivity(String initials, String name) {
		assertTrue(projectHolder.getProject().getProjectLeader().getInitials().equals(initials));
		Activity activity = new Activity(name, null, null, 0.0, projectHolder.getProject().getProjectNumber());
		
		try {
			projectHolder.getProject().addActivity(activity,initials,projectHolder.getProject().getProjectNumber());
		} catch (Throwable e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Then("the activity {string} is created for the project")
	public void theActivityIsCreatedForTheProject(String name) throws ActivityNotFoundException {
		assertThat(projectHolder.getProject().getActivityByName(name).getName(),is(equalTo(name)));    
	}

	@When("an employee {string} creates an activity {string}")
	public void anEmployeeCreatesAnActivity(String initials, String name) {
	
		Employee employee = new Employee(null,initials);
		try {
			Activity activity = new Activity(name, null, null, 0, projectHolder.getProject().getProjectNumber());
			projectHolder.getProject().addActivity(activity,employee.getInitials(),projectHolder.getProject().getProjectNumber());
			assertTrue(false);
		} catch (NotProjectLeaderException|OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	
	
		
}
