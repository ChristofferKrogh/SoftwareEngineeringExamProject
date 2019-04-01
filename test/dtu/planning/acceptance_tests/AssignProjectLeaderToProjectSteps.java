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
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;
import dtu.planning.app.TimeRegistration;

public class AssignProjectLeaderToProjectSteps {
	
	private PlanningApp planningApp = new PlanningApp();
	private Project project;
	private Employee projectLeader;
	private Employee employee;
	private ErrorMessageHolder errorMessage;
	
//	@Given("employee with initials {string} exists")
//	public void employeeWithInitialsExists(String initials) throws Exception {
//		employee = new Employee(null,initials);
//	}
//
//	@Given("project with project number {int} exists")
//	public void projectWithProjectNumberExists(Integer pNumber) throws Exception {
//		project = new Project(null, false, pNumber);
//	}
//
//	@Given("no project leader is assigned to project with project number {int}")
//	public void noProjectLeaderIsAssignedToProjectWithProjectNumber(Integer pNumber) throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//		assertFalse();
//	}
//
//	@When("I assign employee with initials {string} as project leader")
//	public void iAssignEmployeeWithInitialsAsProjectLeader(String initials) throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	}
//
//	@Then("the employee with initials {string} is assigned as project leader for the project with project number {int}")
//	public void theEmployeeWithInitialsIsAssignedAsProjectLeaderForTheProjectWithProjectNumber(String initials, Integer pNumber) throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	}
	
//	@Given("project with project number {int} does not exist")
//	public void projectWithProjectNumberDoesNotExist(Integer pNumber) throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	assertFalse();
//	}

//	@Then("I get the error message {string}")
//	public void iGetTheErrorMessage(String errorMessage) throws Exception {
//		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
//	}

//	@Given("project has a project leader")
//	public void projectHasAProjectLeader() throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	}

//	@Given("employee with initials {string} does not exist")
//	public void employeeWithInitialsDoesNotExist(String initials) throws Exception {
//	    // Write code here that turns the phrase above into concrete actions
////	    throw new cucumber.api.PendingException();
//	}
	
}
