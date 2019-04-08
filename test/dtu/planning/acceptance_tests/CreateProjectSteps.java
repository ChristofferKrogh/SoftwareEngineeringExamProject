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
import java.util.Date;
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
	
	private PlanningAppHolder planningAppHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ProjectHolder projectHolder;
	
	public CreateProjectSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
	}
	
	@Given("there is an internal project with name {string}")
	public void thereIsAnInternalProjectWithName(String name) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = new Project(name, true, planningApp.projectCount);
		projectHolder.setProject(project);
	}

	@When("an employee creates the project")
	public void anEmployeeCreatesTheProject() throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = projectHolder.getProject();
		planningApp.createProject(project);
	}

	@Then("the internal project with name {string} is created")
	public void theInternalProjectWithNameIsCreated(String name) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = projectHolder.getProject();
	    assertThat(project.getName(), is(equalTo(name)));
	    assertThat(project.isProjectInternal(), is(equalTo(true)));
	    assertThat(planningApp.getProjects(), hasItem(project));
	}
	
	@Then("the external project with name {string} is created")
	public void theExternalProjectWithNameIsCreated(String name) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = projectHolder.getProject();
		assertThat(project.getName(), is(equalTo(name)));
	    assertThat(project.isProjectInternal(), is(equalTo(false)));
	    assertThat(planningApp.getProjects(), hasItem(project));
	}

	@Then("the project is given a project number")
	public void theProjectIsGivenAProjectNumber() throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = projectHolder.getProject();
		int projectNumber = (planningApp.projectCount - 1) % 10000;
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		year %= 100;
		projectNumber = year * 10000 + projectNumber;
	    assertThat(project.getProjectNumber(), is(equalTo(projectNumber)));
	    
	}

	@Given("there is an external project with name {string}")
	public void thereIsAnExternalProjectWithName(String name) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = new Project(name, false, planningApp.projectCount);
		projectHolder.setProject(project);
	}
	
	@Given("there is a project with id {int}")
	public void thereIsAProjectWithId(Integer projectNumber) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = new Project("Test Project", true, projectNumber);
		projectHolder.setProject(project);
		planningApp.createProject(project);
	}

	@When("an employee edits the start date of the project to {int}\\/{int}\\/{int}")
	public void anEmployeeEditsTheStartDateOfTheProjectTo(Integer day, Integer month, Integer year) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		GregorianCalendar date = new GregorianCalendar(year, month, day);
	    Project project = projectHolder.getProject();
	    try {
	    	planningApp.editStartDateOfProject(date, project.getProjectNumber());
		    projectHolder.setProject(planningApp.searchForProject(project.getProjectNumber()));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the start date of the project is {int}\\/{int}\\/{int}")
	public void theStartDateOfTheProjectWithIdIs(Integer day, Integer month, Integer year) throws Exception {
		Project project = projectHolder.getProject();
		GregorianCalendar date = new GregorianCalendar(year, month, day);
	    assertTrue(project.getStartDate().equals(date));
	}
	
	@When("an employee edits the end date of the project to {int}\\/{int}\\/{int}")
	public void anEmployeeEditsTheEndDateOfTheProjectTo(Integer day, Integer month, Integer year) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		Project project = projectHolder.getProject();
		try {
			planningApp.editEndDateOfProject(date, project.getProjectNumber());
		    projectHolder.setProject(planningApp.searchForProject(project.getProjectNumber()));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the end date of the project is {int}\\/{int}\\/{int}")
	public void theEndDateOfTheProjectWithIdIs(Integer day, Integer month, Integer year) throws Exception {
		Project project = projectHolder.getProject();
		GregorianCalendar date = new GregorianCalendar(year, month, day);
	    assertTrue(project.getEndDate().equals(date));
	}
	
	@When("an employee edits the start date of the project to a date after the end date")
	public void anEmployeeEditsTheStartDateOfTheProjectToADateAfterTheEndDate() throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		GregorianCalendar endDate = new GregorianCalendar(2020, 1, 1);
		GregorianCalendar startDate = new GregorianCalendar(2020, 1, 2);
		Project project = projectHolder.getProject();
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
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		GregorianCalendar startDate = new GregorianCalendar(2020, 1, 2);
		GregorianCalendar endDate = new GregorianCalendar(2020, 1, 1);
		Project project = projectHolder.getProject();
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
