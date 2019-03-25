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

public class CreateProjectSteps {
	
	private PlanningApp planningApp = new PlanningApp();
	private Project project;
	
	@Given("there is an internal project with name {string}")
	public void thereIsAnInternalProjectWithName(String name) throws Exception {
		project = new Project(name, true, planningApp.projectCount);
	}

	@When("an employee creates the project")
	public void anEmployeeCreatesTheProject() throws Exception {
		planningApp.createProject(project);
	}

	@Then("the internal project with name {string} is created")
	public void theInternalProjectWithNameIsCreated(String name) throws Exception {
	    assertThat(project.getName(), is(equalTo(name)));
	    assertThat(project.isProjectInternal(), is(equalTo(true)));
	    assertThat(planningApp.getProjects(), hasItem(project));
	}
	
	@Then("the external project with name {string} is created")
	public void theExternalProjectWithNameIsCreated(String name) throws Exception {
		assertThat(project.getName(), is(equalTo(name)));
	    assertThat(project.isProjectInternal(), is(equalTo(false)));
	    assertThat(planningApp.getProjects(), hasItem(project));
	}

	@Then("the project is given a project number")
	public void theProjectIsGivenAProjectNumber() {
	    assertThat(project.getProjectNumber(), is(equalTo(planningApp.projectCount - 1)));
	}

	@Given("there is an external project with name {string}")
	public void thereIsAnExternalProjectWithName(String name) throws Exception {
		project = new Project(name, false, planningApp.projectCount);
	}
}
