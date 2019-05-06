package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.Project;

public class SearchSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ActivityHolder activityHolder;
	private EmployeeHolder employeeHolder;

	public SearchSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, ActivityHolder activityHolder, EmployeeHolder employeeHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.activityHolder = activityHolder;
		this.employeeHolder = employeeHolder;
	}
	
	@When("I search for an activity with name {string}")
	public void iSearchForAnActivityWithName(String activityName) throws OperationNotAllowedException {
	    try {
			activityHolder.setActivity(planningAppHolder.getPlanningApp().searchForActivity(projectHolder.getProject().getProjectNumber(), activityName));
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("I search for a regular activity with name {string}")
	public void iSearchForARegularActivityWithName(String activityName) {
	    try {
			activityHolder.setActivity(planningAppHolder.getPlanningApp().searchForRegActivity(activityName));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("I search a project with id {int}")
	public void iSearchAProjectWithId(Integer searchTerm) {
	    try {
			projectHolder.setProject(planningAppHolder.getPlanningApp().searchForProject(searchTerm));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("I search a project with name {string}")
	public void iSearchAProjectWithName(String searchTerm) {
		try {
			List<Project> searchResults = planningAppHolder.getPlanningApp().searchForProjectsByName(searchTerm);
			projectHolder.setProject(searchResults.get(0));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("I search for an employee with initials {string}")
	public void iSearchForAnEmployeeWithInitials(String employeeInitials) {
	    try {
			employeeHolder.setEmployee(planningAppHolder.getPlanningApp().searchForEmployee(employeeInitials));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("I search for an employee with name {string}")
	public void iSearchForAnEmployeeWithName(String employeeName) {
		try {
			List<Employee> searchResults = planningAppHolder.getPlanningApp().searchForEmployeesByName(employeeName);
			employeeHolder.setEmployee(searchResults.get(0));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("I get an activity with name {string}")
	public void iGetAnActivityWithName(String activityName) {
		assertEquals(activityHolder.getActivity().getName(),activityName);
	}
	
	@Then("I get a project with id {int}")
	public void iGetAProjectWithId(int projectId) {
		assertEquals(projectHolder.getProject().getProjectNumber(),projectId);
	}
	
	@Then("I get a project with name {string}")
	public void iGetAProjectWithName(String projectName) {
	    assertEquals(projectHolder.getProject().getName(),projectName);
	}
	
	@Then("I get an employee with intitials {string}")
	public void iGetAnEmployeeWithIntitials(String employeeInitials) {
		assertEquals(employeeHolder.getEmployee().getInitials(),employeeInitials);
	}
	
	@Then("I get an employee with name {string}")
	public void iGetAnEmployeeWithName(String employeeName) {
	    assertEquals(employeeHolder.getEmployee().getName(),employeeName);
	}
}
