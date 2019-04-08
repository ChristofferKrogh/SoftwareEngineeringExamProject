package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.Employee;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;
import dtu.planning.app.TimeRegistration;

public class AssignProjectLeaderToProjectSteps {
	
	private PlanningApp planningApp = new PlanningApp();
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private Project project;
	private Employee projectLeader;
	private Employee employee;
	private ErrorMessageHolder errorMessage;
//	private String errorMessageHolder;
	
	public AssignProjectLeaderToProjectSteps(PlanningAppHolder planningAppHolder, PlanningApp planningApp, ErrorMessageHolder errorMessage, ProjectHolder projectHolder, EmployeeHolder employeeHolder) {
		this.planningAppHolder = planningAppHolder;
		this.planningApp = planningApp;
		this.errorMessage = errorMessage;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
	}
	
	// implemented in other file
//	@Given("employee with initials {string} exists")
//	public void employeeWithInitialsExists(String initials) throws Exception {
//		employee = new Employee(null,initials);
//	}

	// implemented in other file
//	@Given("the project with id {int} exists")
//	public void projectWithProjectNumberExists(Integer pNumber) throws Exception {
//		project = new Project(null, false, pNumber);
//	}

	@When("I assign employee with initials {string} as project leader")
	public void iAssignEmployeeWithInitialsAsProjectLeader(String initials) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
		// projektet her er en nullpointer, fordi det ikke er initialiseret... 
			project = projectHolder.getProject();
			employee = employeeHolder.getEmployee();
			planningApp.setProjectLeader(project.getProjectNumber(), employee.getInitials()); //employee.getInitials()
			projectHolder.setProject(project);
			employeeHolder.setEmployee(employee);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
		planningAppHolder.setPlanningApp(planningApp);
	}

	@Then("the employee with initials {string} is assigned as project leader for the project with id {int}")
	public void theEmployeeWithInitialsIsAssignedAsProjectLeaderForTheProjectWithProjectNumber(String initials, Integer pNumber) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new cucumber.api.PendingException();
		project = projectHolder.getProject();
		employee = employeeHolder.getEmployee();
		assertThat(employee.getInitials(),is(equalTo(initials)));
		assertThat(project.getProjectLeader(),is(equalTo(employee)));
	}
	
	@Given("project with id {int} does not exist")
	public void projectWithProjectNumberDoesNotExist(Integer pNumber) throws Exception {
		assertThat(planningApp.getProjectNumbers(), not(hasItem(pNumber)));
		project = new Project("Test Project", true, pNumber);
		projectHolder.setProject(project);
	}
//
//	@Then("I get the error message {string}")
//	public void iGetTheErrorMessage(String errorMessage) throws Exception {
//		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
//	}

//	@Given("project has a project leader")
//	public void projectHasAProjectLeader() throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	}
//
	@Given("employee with initials {string} does not exist")
	public void employeeWithInitialsDoesNotExist(String initials) {
		assertThat(planningApp.getEmployeeInitials(), not(hasItem(initials)));
		employee = new Employee(null,initials);
		employeeHolder.setEmployee(employee);
	}
	
}
