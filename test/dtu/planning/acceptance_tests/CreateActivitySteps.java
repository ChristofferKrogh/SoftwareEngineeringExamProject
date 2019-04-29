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
import java.lang.Integer;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.*;

public class CreateActivitySteps {

	private PlanningApp planningApp = new PlanningApp();
	private ErrorMessageHolder errorMessage;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private Activity activity;
	private int projectNumber;
	private GregorianCalendar date;
	private Employee projectLeader;

	public CreateActivitySteps(ErrorMessageHolder errorMessage, PlanningApp planningApp, ProjectHolder projectHolder, EmployeeHolder employeeHolder) {
		this.planningApp = planningApp;
		this.errorMessage = errorMessage;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
	}

	@Given("project with id {int} exists")
	public void projectWithIdExists(int id) {
		Project project = new dtu.planning.app.Project("hrj", false, id);
		projectHolder.setProject(project);
	}

	@Given("project leader has initials {string}")
	public void projectLeaderHasInitials(String init){
		Employee employee = new Employee(null,init);
		employeeHolder.setEmployee(employee);
		projectHolder.getProject().setProjectLeader(employee);
	}

	@When("the project leader creates an activity {string}")
	public void theProjectLeaderCreatesAnActivity(String name) {
		assertThat(projectHolder.getProject().getProjectLeader(),is(equalTo(employeeHolder.getEmployee())));
		Activity activity = new Activity(name, null, null, 2, 1);
		projectHolder.getProject().addActivity(activity);
	}

	@Then("the activity {string} is created for the project")
	public void theActivityIsCreatedForTheProject(String name) throws ActivityNotFoundException {
		assertThat(projectHolder.getProject().getActivityByName(name).getName(),is(equalTo(name)));
	}

	@When("an employee {string} creates an activity {string}")
	public void anEmployeeCreatesAnActivity(String init, String name) {
		Employee employee = new Employee(null,init);
		//assertThat(projectHolder.getProject().getProjectLeader(),is(equalTo(employee))); // There could be an error
		Activity activity = new Activity(name, null, null, 2, 1);
		projectHolder.getProject().addActivity(activity);
	}

	@Then("get the error message {string}")
	public void getTheErrorMessage(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}



}