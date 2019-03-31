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
	private Employee employee;

	@Given("the activity exists")
	public void theActivityExists() {
		activity = new Activity("Some Activity", 0, 1, 2 , 3); // Insert project counter instead of hardcoded number.
		/* Does it even make sense to make all of these checks?
		assertThat(activity.getName(),is(equalTo("Some Activity")));
		assertThat(activity.getExpectedStart(),is(equalTo(0)));
		assertThat(activity.getExpectedEnd(),is(equalTo(1)));
		assertThat(activity.getExpectedAmountOfHours(),is(equalTo(2)));
		assertThat(activity.getAssociatedProjectNumber(),is(equalTo(3))); */
		assertThat(activity,is(not(equalTo(null))));
	}

	@Given("the project leader is project leader for the overlying project") // This does not make any sense to check this. When would the project leader as per definition not be the project leader?
	public void theProjectLeaderIsProjectLeaderForTheOverlyingProject() {
		employee = new Employee("John Smith", "JS");
		project = new Project("Some project", false, 3); // Insert project counter instead of hardcoded number.
		assertThat(project.getName(),is(equalTo("Some project")));
		assertFalse(project.isProjectInternal());
		assertThat(project.getProjectNumber(),is(equalTo(3)));
		
		// NOT DONE!
		
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I assign an employee to the activity")
	public void iAssignAnEmployeeToTheActivity() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the employee is assigned to the activity")
	public void theEmployeeIsAssignedToTheActivity() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}


}
