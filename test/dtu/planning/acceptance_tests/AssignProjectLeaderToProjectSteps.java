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
	private ProjectHolder projectHolder;
	private Project project;
	private Employee projectLeader;
	private Employee employee;
	private ErrorMessageHolder errorMessage;
//	private String errorMessageHolder;
	
	public AssignProjectLeaderToProjectSteps(PlanningApp planningApp, ErrorMessageHolder errorMessage, ProjectHolder projectHolder) {
		this.planningApp = planningApp;
		this.errorMessage = errorMessage;
		this.projectHolder = projectHolder;
	}
	
//	@Given("employee with initials {string} exists")
//	public void employeeWithInitialsExists(String initials) throws Exception {
//		employee = new Employee(null,initials);
//	}
//
//	@Given("the project with id {int} exists")
//	public void projectWithProjectNumberExists(Integer pNumber) throws Exception {
//		project = new Project(null, false, pNumber);
//	}

//	@When("I assign employee with initials {string} as project leader")
//	public void iAssignEmployeeWithInitialsAsProjectLeader(String initials) throws Exception {
////		try {
//		// projektet her er en nullpointer, fordi det ikke er initialiseret... 
//			project = projectHolder.getProject();
//			project.setProjectLeader(employee);
//			projectHolder.setProject(project);
////		} catch (OperationNotAllowedException e) {
////			errorMessage.setErrorMessage(e.getMessage());
////		}
//	}
//
//	@Then("the employee with initials {string} is assigned as project leader for the project with id {int}")
//	public void theEmployeeWithInitialsIsAssignedAsProjectLeaderForTheProjectWithProjectNumber(String initials, Integer pNumber) throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//		project = projectHolder.getProject();
//		assertThat(employee.getInitials(),is(equalTo(initials)));
//		assertThat(project.getProjectLeader(),is(equalTo(employee)));
//	}
	
//	@Given("project with id {int} does not exist")
//	public void projectWithProjectNumberDoesNotExist(Integer pNumber) throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	assertFalse();
//	}
//
//	@Then("I get the error message {string}")
//	public void iGetTheErrorMessage(String errorMessage) throws Exception {
//		assertEquals(errorMessageHolder, this.errorMessageHolder.getErrorMessage());
//	}
//
//	@Given("project has a project leader")
//	public void projectHasAProjectLeader() throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	}
//
//	@Given("employee with initials {string} does not exist")
//	public void employeeWithInitialsDoesNotExist(String initials) throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	}
	
}
