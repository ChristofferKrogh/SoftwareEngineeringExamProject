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

	@Given("the activity exists")
	public void theActivityExists() {
		activity = new Activity("Some Activity", 0, 1, 2 , 3); // Insert project counter instead of hardcoded number?
	}

	@Given("the project leader is project leader for the overlying project") // This does not make any sense to check this. When would the project leader as per definition not be the project leader?
	public void theProjectLeaderIsProjectLeaderForTheOverlyingProject() {
		projectLeader = new Employee("John Smith", "JS");
		project = new Project("Some project", false, 3); // Insert project counter instead of hardcoded number?
		project.setProjectLeader(projectLeader);
	}

	@When("I assign an employee to the activity")
	public void iAssignAnEmployeeToTheActivity() {
		employee = new Employee("Jane Doe","JD");
		activity.assignEmployee(employee); // Should properly be expanded. Nothing here says who's assigning the employee.
	}

	@Then("the employee is assigned to the activity")
	public void theEmployeeIsAssignedToTheActivity() {
		assertTrue(activity.getAssignedEmployees().contains(employee));
	}


}
