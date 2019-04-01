package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

public class AssignEmployeeToActivitySteps {
	
	private PlanningApp planningApp = new PlanningApp();
	private Activity activity;
	private Project project;
	private Employee projectLeader;
	private Employee employee;

	@Given("employee with initials {string} exists")
	public void employeeWithInitialsExists(String initials) {
		// Employee name doesn't matter, so it is set to null.
		employee = new Employee(null,initials);
	}

	@Given("the activity with name {string} exists")
	public void theActivityWithNameExists(String name) {
		// The values 0, 1, 2, 3 are chosen as an example.
		// Activity does not test that the assigned project id, actually exists or is the id that it is assigned to
		activity = new Activity(name, 0, 1, 2, 1);
	}

	@Given("the project with id {int} exists")
	public void theProjectWithIDExists(int projectid) {
		// Name does not matter here, so it is set to null. It does not matter if the project is internal or external so it is set to false
		project = new Project(null, false, projectid);
	}

	@Given("the project leader is project leader for the project")
	public void theProjectLeaderIsProjectLeaderForTheProject() {
		projectLeader = new Employee("John Smith", "JS");
		project.setProjectLeader(projectLeader);
	}

	@Given("the activity is assigned to the project")
	public void theActivityIsAssignedToTheProject() {
		project.assignActivity(activity);
	}

	@When("the project leader assign the employee to the activity")
	public void theProjectLeaderAssignTheEmployeeToTheActivity() {
		activity.assignEmployee(projectLeader,employee);
	}

	@Then("the employee {string} is assigned to the activity {string}")
	public void theEmployeeIsAssignedToTheActivity(String employeeInitials, String activityName) {
		assertThat(employee.getInitials(),is(equalTo(employeeInitials)));
		assertThat(activity.getName(),is(equalTo(activityName)));
		assertTrue(activity.getAssignedEmployees().contains(employee));
	}



}
