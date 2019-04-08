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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;
import dtu.planning.app.TimeRegistration;
import dtu.planning.app.OperationNotAllowedException;

public class CreateProjectSteps {
	
	private PlanningApp planningApp = new PlanningApp();
	private ErrorMessageHolder errorMessageHolder;
	private ProjectHolder projectHolder;
	private Project project;
	private GregorianCalendar date;
	
	public CreateProjectSteps(PlanningApp planningApp, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder) {
		this.planningApp = planningApp;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
	}
	
	@Given("there is an internal project with name {string}")
	public void thereIsAnInternalProjectWithName(String name) throws Exception {
		project = new Project(name, true, planningApp.projectCount);
		projectHolder.setProject(project);
	}

	@When("an employee creates the project")
	public void anEmployeeCreatesTheProject() throws Exception {
		project = projectHolder.getProject();
		planningApp.createProject(project);
	}

	@Then("the internal project with name {string} is created")
	public void theInternalProjectWithNameIsCreated(String name) throws Exception {
		project = projectHolder.getProject();
	    assertThat(project.getName(), is(equalTo(name)));
	    assertThat(project.isProjectInternal(), is(equalTo(true)));
	    assertThat(planningApp.getProjects(), hasItem(project));
	}
	
	@Then("the external project with name {string} is created")
	public void theExternalProjectWithNameIsCreated(String name) throws Exception {
		project = projectHolder.getProject();
		assertThat(project.getName(), is(equalTo(name)));
	    assertThat(project.isProjectInternal(), is(equalTo(false)));
	    assertThat(planningApp.getProjects(), hasItem(project));
	}

	@Then("the project is given a project number")
	public void theProjectIsGivenAProjectNumber() throws Exception {
		project = projectHolder.getProject();
		// TODO: change this when the project number is implemented
	    assertThat(project.getProjectNumber(), is(equalTo(planningApp.projectCount - 1)));
	}

	@Given("there is an external project with name {string}")
	public void thereIsAnExternalProjectWithName(String name) throws Exception {
		project = new Project(name, false, planningApp.projectCount);
		projectHolder.setProject(project);
	}
	
	@Given("there is a project with id {int}")
	public void thereIsAProjectWithId(Integer projectNumber) throws Exception {
		project = new Project("Test Project", true, projectNumber);
		projectHolder.setProject(project);
		planningApp.createProject(project);
	}

	@When("an employee edits the start date of the project to {int}\\/{int}\\/{int}")
	public void anEmployeeEditsTheStartDateOfTheProjectTo(Integer day, Integer month, Integer year) throws Exception {
	    date = new GregorianCalendar(year, month, day);
	    project = projectHolder.getProject();
	    try {
	    	planningApp.editStartDateOfProject(date, project.getProjectNumber());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the start date of the project with id {int} is {int}\\/{int}\\/{int}")
	public void theStartDateOfTheProjectWithIdIs(Integer projectNumber, Integer day, Integer month, Integer year) throws Exception {
	    project = planningApp.searchForProject(projectNumber);
	    projectHolder.setProject(project);
	    date = new GregorianCalendar(year, month, day);
	    assertTrue(project.getStartDate().equals(date));
	}
	
	@When("an employee edits the end date of the project to {int}\\/{int}\\/{int}")
	public void anEmployeeEditsTheEndDateOfTheProjectTo(Integer day, Integer month, Integer year) throws Exception {
		date = new GregorianCalendar(year, month, day);
		project = projectHolder.getProject();
		try {
			planningApp.editEndDateOfProject(date, project.getProjectNumber());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the end date of the project with id {int} is {int}\\/{int}\\/{int}")
	public void theEndDateOfTheProjectWithIdIs(Integer projectNumber, Integer day, Integer month, Integer year) throws Exception {
		project = planningApp.searchForProject(projectNumber);
		projectHolder.setProject(project);
	    date = new GregorianCalendar(year, month, day);
	    assertTrue(project.getEndDate().equals(date));
	}
	
//	@Given("there is not a project with id {int}")
//	public void thereIsNotAProjectWithId(Integer projectNumber) {
//	    // TODO: assertThat there is not a project with the projectNumber
//		assertThat(planningApp.getProjectNumbers(), hasItem(projectNumber));
//	}

//	@Then("I get the error message {string}")
//	public void iGetTheErrorMessage(String errorMessage) throws Exception {
//	    assertEquals(errorMessage, this.errorMessage.getErrorMessage());
//	}
	
	@When("an employee edits the start date of the project to a date after the end date")
	public void anEmployeeEditsTheStartDateOfTheProjectToADateAfterTheEndDate() throws Exception {
		GregorianCalendar endDate = new GregorianCalendar(2020, 1, 1);
		GregorianCalendar startDate = new GregorianCalendar(2020, 1, 2);
		project = projectHolder.getProject();
		try {
			planningApp.editEndDateOfProject(endDate, project.getProjectNumber());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		try {
			planningApp.editStartDateOfProject(startDate, project.getProjectNumber());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("an employee edits the end date of the project to a date before the start date")
	public void anEmployeeEditsTheEndDateOfTheProjectToADateBeforeTheStartDate() throws Exception {
		GregorianCalendar startDate = new GregorianCalendar(2020, 1, 2);
		GregorianCalendar endDate = new GregorianCalendar(2020, 1, 1);
		project = projectHolder.getProject();
		try {
			planningApp.editStartDateOfProject(startDate, project.getProjectNumber());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		try {
			planningApp.editEndDateOfProject(endDate, project.getProjectNumber());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
	}

}
